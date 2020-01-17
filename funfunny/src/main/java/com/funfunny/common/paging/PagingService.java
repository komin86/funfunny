package com.funfunny.common.paging;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class PagingService {

	@SuppressWarnings("unchecked")
	public Object findPaginated(Pageable pageable, int totalSize, Object list) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		
		Object bookPage	= new PageImpl<Object>((List<Object>) list
				, PageRequest.of(currentPage, pageSize), totalSize);
        
      return bookPage;
	}
	
	public List<Integer> pageNumers(int totalPages,int currentPage, int limit){
		List<Integer> pageNumbers = 
			IntStream
			.rangeClosed(currentPage-1 == 0 ? 1 : currentPage-1, totalPages)
            .boxed().limit(limit)
            .collect(Collectors.toList());
	    return pageNumbers;
	}
	
	
}
