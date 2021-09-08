package com.funfunny.freeBoard.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.funfunny.common.fileUpload.FileUploadService;
import com.funfunny.common.paging.PagingService;
import com.funfunny.freeBoard.bean.FreeBoard;
import com.funfunny.freeBoard.mapper.FreeBoradMapper;

import net.sf.json.JSONObject;


@Controller
public class FreeBoardController {


	
	public static final String freeBoradUrlPath = "image/freeBoard/";
	public static final String freeBoradStoragePath = "D:/image/freeBoard/";
	public static final String tempFreeBoradStoragePath = "D:/image/tempFreeBoard/";
	
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
        Pageable pageable = PageRequest.of(currentPage -1, size.orElse(5));
        
        int totalCnt = boradMapper.totalCnt();
        Object list = boradMapper.getList(pageable.getPageSize(), pageable.getPageNumber()*5);
        
        Page<FreeBoard> FreeBoardPage = (Page<FreeBoard>) pagingService.findPaginated(pageable, totalCnt, list);
        System.out.println(FreeBoardPage.getContent().get(0).getReg_date());
		model.addAttribute("FreeBoardPage", FreeBoardPage);
        int totalPages = FreeBoardPage.getTotalPages();
        if (totalPages > 0) {
            model.addAttribute("pageNumbers", pagingService.pageNumers(totalPages, currentPage,5));
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
	public String freeBoardUpdate(FreeBoard freeBoard) {

		System.out.println(freeBoard.getContents());
		
		if(boradMapper.update(freeBoard) > 0) {
			
			//파일 복사  ( 임시 > 사용 ) 
			Document document = Jsoup.parseBodyFragment(freeBoard.getContents());
			Elements element = document.getElementsByTag("img");
		
			for (Element element2 : element) {
				fileUploadService.fileCopy(tempFreeBoradStoragePath, freeBoradStoragePath, element2.attr("data-filename"));
				//파일 복사에 실패할 경우 로그 기록 사후처리
			}
	
		} 

		return "redirect:/freeBoardDetail?no="+freeBoard.getNo();
	}
	
	
	@PostMapping("/freeBoardImage")
	public @ResponseBody JSONObject freeBoardImage(@RequestParam("image") MultipartFile file) {
		
		JSONObject result = new JSONObject(); 
		
		if(file.isEmpty()) {
			result.put("status", "FNF");
			return result;
		}

		result = fileUploadService.fileUp(file, tempFreeBoradStoragePath, freeBoradUrlPath);

		return result;
	}
	
	@GetMapping("/freeBoardNew")
	public String freeBoardNew(Model model) {
		return "freeBoard/freeBoardNew";
	}
	
	@PostMapping("/freeBoardInsert")
	public String freeBoardInsert(@ModelAttribute FreeBoard freeBoard, Model model) {
		
		System.out.println(freeBoard.getTitle());
		System.out.println(freeBoard.getContents());
		
		//추후 벨리데이션 
		freeBoard.setId("테스터");
		freeBoard.setIp("192.168.10.111");
		
		//model.addAttribute("data", freeBoard);
		//return "freeBoard/freeBoardNew";
		
	
		if(boradMapper.insert(freeBoard) > 0) {
			
			//파일 복사  ( 임시 > 사용 ) 
			Document document = Jsoup.parseBodyFragment(freeBoard.getContents());
			Elements element = document.getElementsByTag("img");
		
			for (Element element2 : element) {
				fileUploadService.fileCopy(tempFreeBoradStoragePath, freeBoradStoragePath, element2.attr("data-filename"));
				//파일 복사에 실패할 경우 로그 기록 사후처리
			}
	
		} 
		
		return "redirect:/freeBoard";
	}
	
	public static void main(String[] args) {
		
		int[][] temp = {{1,6,8,1},{5,0,0,1},{4,2,1,1},{1,6,1,1}};
		
		Arrays.sort(temp, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				System.out.println("ok");
				System.out.println(o1[0] + " | " +o2[0]);
				if(o1[0] == o2[0]) {
					//if(o1[1] == o2[1]) {
					//	return o1[2] - o2[2];
					//} else {
						return o1[1] - o2[1];
					//}
				} else {
					return o1[0] - o2[0]; 

				}
			}
		});
		
		for(int i = 0 ; i < temp.length ; i++) {
			
			for(int numb : temp[i]) {
				System.out.print(numb);
			}
			System.out.println(" ");
		}
		
	}
}
