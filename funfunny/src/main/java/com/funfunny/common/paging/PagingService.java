package com.funfunny.common.paging;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Service
public class PagingService {

	@SuppressWarnings("unchecked")
	public Object findPaginated(Pageable pageable, int totalSize, Object list) {
		Object bookPage	= new PageImpl<Object>((List<Object>) list, pageable, totalSize);
      return bookPage;
	}
	
	public List<Integer> pageNumers(int totalPages,int currentPage, int limit){

		List<Integer> pageNumbers = 
			IntStream
			.rangeClosed(currentPage-2 < 1 ? 1 : currentPage-2 , totalPages)
            .boxed().limit(limit)
            .collect(Collectors.toList());
	    return pageNumbers;
	}
	
}
