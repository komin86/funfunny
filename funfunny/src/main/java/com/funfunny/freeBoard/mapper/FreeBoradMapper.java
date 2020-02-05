package com.funfunny.freeBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.funfunny.freeBoard.bean.FreeBoard;

@Mapper
public interface FreeBoradMapper {

	@Select("SELECT "
			+ "NO, NAME, REG_DATE, GOOD_CNT, BAD_CNT, TITLE, CLICK_CNT, USER_NO "
			+ "FROM "
			+ "funfunny.TB_FREE_BOARD "
			+ "LIMIT #{pageSize} OFFSET #{currentPage}")
	List<FreeBoard> getList(@Param("pageSize") int pageSize, @Param("currentPage") int currentPage );

	@Select("SELECT COUNT(*) FROM funfunny.TB_FREE_BOARD")
	int totalCnt();
	
	@Select("SELECT * FROM funfunny.TB_FREE_BOARD WHERE NO= #{no}")
	FreeBoard getdata(@Param("no")int no);
	
	@Update("UPDATE funfunny.TB_FREE_BOARD SET TITLE=#{title}, NAME=#{name}, CONTENTS=#{contentes}"
			+ "WHERE NO=#{no}")
	void update(FreeBoard board);
}
