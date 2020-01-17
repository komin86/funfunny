package com.funfunny.freeBoard.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.funfunny.common.paging.PagingService;
import com.funfunny.freeBoard.bean.FreeBoard;
import com.funfunny.freeBoard.mapper.FreeBoradMapper;
import com.funfunny.freeBoard.service.FreeBoradService;

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
        int pageSize = size.orElse(1);
      
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
}
