package com.funfunny.freeBoard.bean;

import java.util.Date;

public class FreeBoard {
	private int no; 
	private String id;
	private String ip;
	private Date reg_date;
	private Date mod_date;
	private int good_cnt;
	private int bad_cnt;
	private String title;
	private String contents;
	private int click_cnt;
	private String good_cnt_id;
	private String bad_cnt_id;
	private String click_cnt_id;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public void setMod_date(Date mod_date) {
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
	public String getGood_cnt_id() {
		return good_cnt_id;
	}
	public void setGood_cnt_id(String good_cnt_id) {
		this.good_cnt_id = good_cnt_id;
	}
	public String getBad_cnt_id() {
		return bad_cnt_id;
	}
	public void setBad_cnt_id(String bad_cnt_id) {
		this.bad_cnt_id = bad_cnt_id;
	}
	public String getClick_cnt_id() {
		return click_cnt_id;
	}
	public void setClick_cnt_id(String click_cnt_id) {
		this.click_cnt_id = click_cnt_id;
	}
	
}
