package com.funfunny.freeBoard.bean;

import java.time.LocalDateTime;

public class FreeBoardReply {
	
	private int no;
	private String name;
	private String ip;
	private LocalDateTime reg_date;
	private LocalDateTime mod_date;
	private int board_no;
	private String contents;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public LocalDateTime getReg_date() {
		return reg_date;
	}
	public void setReg_date(LocalDateTime reg_date) {
		this.reg_date = reg_date;
	}
	public LocalDateTime getMod_date() {
		return mod_date;
	}
	public void setMod_date(LocalDateTime mod_date) {
		this.mod_date = mod_date;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
	
}
