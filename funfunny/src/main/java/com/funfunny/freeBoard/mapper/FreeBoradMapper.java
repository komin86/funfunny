package com.funfunny.freeBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.funfunny.freeBoard.bean.FreeBoard;

@Mapper
public interface FreeBoradMapper {

	@Select("SELECT "
			+ "NO, ID, REG_DATE, GOOD_CNT, BAD_CNT, TITLE, CLICK_CNT "
			+ "FROM "
			+ "funfunny.FREE_BOARD "
			+ "ORDER BY NO DESC "
			+ "LIMIT #{pageSize} OFFSET #{currentPage}")
	List<FreeBoard> getList(@Param("pageSize") int pageSize, @Param("currentPage") int currentPage );

	@Select("SELECT COUNT(*) FROM funfunny.FREE_BOARD")
	int totalCnt();
	
	@Select("SELECT * FROM funfunny.FREE_BOARD WHERE NO= #{no}")
	FreeBoard getdata(@Param("no")int no);
	
	@Update("UPDATE funfunny.FREE_BOARD SET TITLE=#{title}, CONTENTS=#{contents}"
			+ "WHERE NO=#{no}")
	int update(FreeBoard freeBoard);
	
	
	
	//@SelectKey(before = false, keyProperty = "no", resultType = int.class, statement = { "SELECT LAST_INSERT_ID()" } )
	@Insert("INSERT INTO "
			+ "funfunny.FREE_BOARD(ID, IP, REG_DATE, TITLE, CONTENTS) "
			+ "VALUES(#{id}, #{ip}, now(), #{title}, #{contents}) ")
	int insert(FreeBoard freeBoard);
	
}
