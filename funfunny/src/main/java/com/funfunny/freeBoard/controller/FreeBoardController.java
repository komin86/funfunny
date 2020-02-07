package com.funfunny.freeBoard.controller;

import java.util.Optional;

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

import com.funfunny.common.paging.PagingService;
import com.funfunny.freeBoard.bean.FreeBoard;
import com.funfunny.freeBoard.mapper.FreeBoradMapper;

@Controller
public class FreeBoardController {


	
	@Autowired
	private FreeBoradMapper boradMapper;
	
	@Autowired
	private PagingService pagingService;
	
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
	public String freeBoardUpdate(FreeBoard board) {
		
		int no = board.getNo();
		int result = boradMapper.update(board);

		return result == 1 ? "redirect:/freeBoardDetail?no="+no : "redirect:/freeBoardDetail?no="+no+"&msg=0";
	}
}
