package com.cra.aladin.dto;

import java.util.Date;

public class BookDto {
	String gubun;
	String title;
	String writer;
	String publisher;
	String image;
	String image_src;
	String info;
	String giho;
	String pubYear;
	String itemId;
	int pageCnt;
	Date regDt;
	
	public BookDto(){
		
	}
	

	public BookDto(String gubun, String title, String writer, String publisher, String image, String image_src,
			String info, String giho, String pubYear, String itemId, int pageCnt) {
		super();
		this.gubun = gubun;
		this.title = title;
		this.writer = writer;
		this.publisher = publisher;
		this.image = image;
		this.image_src = image_src;
		this.info = info;
		this.giho = giho;
		this.pubYear = pubYear;
		this.itemId= itemId;
		this.pageCnt = pageCnt;
	}
	
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImage_src() {
		return image_src;
	}
	public void setImage_src(String image_src) {
		this.image_src = image_src;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getGiho() {
		return giho;
	}
	public void setGiho(String giho) {
		this.giho = giho;
	}
	public String getPubYear() {
		return pubYear;
	}
	public void setPubYear(String pubYear) {
		this.pubYear = pubYear;
	}
	public String getItemId() {
		return itemId;
	}
	
	
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	




	@Override
	public String toString() {
		return "BookDto [gubun=" + gubun + ", title=" + title + ", writer=" + writer + ", publisher=" + publisher
				+ ", image=" + image + ", image_src=" + image_src + ", info=" + info + ", giho=" + giho + ", pubYear="
				+ pubYear + ", regDt=" + regDt + "]";
	}
	
	
	
	
}
