package com.funfunny.freeBoard.bean;

import java.time.LocalDateTime;

public class FreeBoard {
	private int no; 
	private String name;
	private String IP;
	private LocalDateTime reg_date;
	private LocalDateTime mod_date;
	private int good_cnt;
	private int bad_cnt;
	private String title;
	private String contents;
	private int click_cnt;
	
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
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
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
	public int getGood_cnt() {
		return good_cnt;
	}
	public void setGood_cnt(int good_cnt) {
		this.good_cnt = good_cnt;
	}
	public int getBad_cnt() {
		return bad_cnt;
	}
	public void setBad_cnt(int bad_cnt) {
		this.bad_cnt = bad_cnt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getClick_cnt() {
		return click_cnt;
	}
	public void setClick_cnt(int click_cnt) {
		this.click_cnt = click_cnt;
	}
	
	
}
