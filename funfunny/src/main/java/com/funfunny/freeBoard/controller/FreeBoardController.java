package com.funfunny.freeBoard.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.funfunny.common.fileUpload.FileUploadService;
import com.funfunny.common.paging.PagingService;
import com.funfunny.freeBoard.bean.FreeBoard;
import com.funfunny.freeBoard.mapper.FreeBoradMapper;


@Controller
public class FreeBoardController {


	
	@Autowired
	private FreeBoradMapper boradMapper;
	
	@Autowired
	private PagingService pagingService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@GetMapping("/freeBoard")
	@SuppressWarnings("unchecked")
	public String freeBoard(@RequestParam("page") Optional<Integer> page, 
		      @RequestParam("size") Optional<Integer> size, Model model) {
		int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
      
        Pageable pageable = PageRequest.of(currentPage -1, pageSize);
        
        int totalCnt = boradMapper.totalCnt();
        Object list = boradMapper.getList(pageable.getPageSize(), pageable.getPageNumber());
        
        Page<FreeBoard> FreeBoardPage = (Page<FreeBoard>) pagingService.findPaginated(pageable, totalCnt, list);
 
		model.addAttribute("FreeBoardPage", FreeBoardPage);
		
        int totalPages = FreeBoardPage.getTotalPages();
        if (totalPages > 0) {
            model.addAttribute("pageNumbers", pagingService.pageNumers(totalPages, currentPage, 7));
        }

		return "freeBoard/freeBoard";
	}
	
	
	@GetMapping("/freeBoardDetail" )
	public String freeBoardDetail(@RequestParam int no, Model model) {
		
		FreeBoard board = boradMapper.getdata(no);
		model.addAttribute("data", board);
		
		return "freeBoard/freeBoardDetail";
	}
	
	@PostMapping( "/freeBoardUpdate")
	public String freeBoardUpdate(@RequestParam("image") MultipartFile file, FreeBoard board) {
		
		
		System.out.println(file.isEmpty());
		System.out.println(file);
		System.out.println(board.getContents());
		int no = board.getNo();
		int result = boradMapper.update(board);

		return result == 1 ? "redirect:/freeBoardDetail?no="+no : "redirect:/freeBoardDetail?no="+no+"&msg=0";
	}
	
	
	@PostMapping("/freeBoardImage")
	public @ResponseBody String freeBoardImage(@RequestParam("image") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		
		
		if(file.isEmpty()) {
			System.out.println("fileName errr " );
			redirectAttributes.addFlashAttribute("err", "FNF");
			return "redirect:/freeBoardImage";
		}

		String fileName = fileUploadService.fileUp(file, FileUploadService.freeBoradTempStoragePath);

		return fileName;
	}
}
