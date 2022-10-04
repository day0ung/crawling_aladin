package com.cra.aladin.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cra.aladin.dto.BookDto;

public class AladinPage {
	String gubun = "";
	List<String> urlList = null;
	public AladinPage() {
		
	}
	public AladinPage(String gubun, List<String> urlList) {
		this.gubun = gubun;
		this.urlList = urlList;
	}
	
	public List<BookDto> parsingPage() {
		List<BookDto>  bookList = new ArrayList<>();
		for (String url : urlList) {
			//System.out.println(String.format("Request Aladin page, cateogry: %s, url: %s", gubun, url));
			try {
				Document doc = Jsoup.connect(url).get();
				Elements bookDiv = doc.select("div.ss_book_box");
				for (Element el : bookDiv) {
					String title = el.select("div.ss_book_list >ul > li > a.bo3").text(); 
					String href = el.select("div.ss_book_list >ul > li > a.bo3").attr("href");
					String itemId = getItemId(href);
					String img = el.select("img.front_cover").attr("src");
					String imgSrc = title+".img";
					String info = el.select("div.ss_book_list >ul > li:nth-child(2)").text().replaceAll("\\s+", "");
					if(!info.contains("|")) {
						info = el.select("div.ss_book_list >ul > li:nth-child(3)").text().replaceAll("\\s+", "");
					}
					String writer = "";
					String publisher = "";
					String pubYear = "";
					try {
						writer = info.split("\\|")[0];
						publisher = info.split("\\|")[1];
						pubYear = info.split("\\|")[2];
					} catch (Exception e) {
						System.out.println(e.getMessage() +" url: "+url);
					}
					int pageCnt = getPageCnt(href);
					System.out.println(pageCnt);
					String bookInfo = el.select("p.ss_promotion").text();
					
					String giho = makeGiho(gubun, writer);
					BookDto bookData = new BookDto(gubun, title, writer, publisher, img, imgSrc, bookInfo, giho, pubYear, itemId, pageCnt);
					bookList.add(bookData);
					
				}
			} catch (IOException e) {
				System.out.println(e.getMessage()+ ", url :"+url);
			}
		}
		return bookList;
	}
	
	private int getPageCnt(String href) {
		int pageCnt = 0;
		String pageCntStr = "";
		Document doc;
		try {
			doc = Jsoup.connect(href).get();
			String pageInfo = doc.select("div.conts_info_list1").text();
			
			String key = "";
			String regex = "(\\d+)쪽";
			
			Pattern p = Pattern.compile(regex);
			Matcher m =  p.matcher(pageInfo);
		
			while(m.find())
			{
				key = m.group(1);
			}
			
			pageCntStr =key;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		if(!pageCntStr.isEmpty() && pageCntStr != null) {
			pageCnt = Integer.parseInt(pageCntStr);
		}
		
		return pageCnt;
	}
	private String getItemId(String href) {
		String key = "";
		String regex = "ItemId=(\\w+)";
		
		Pattern p = Pattern.compile(regex);
		Matcher m =  p.matcher(href);
	
		while(m.find())
		{
			key = m.group(1);
		}
		return key;
	}
	
	private String makeGiho(String gubun, String writer) {
		int number = Integer.parseInt( gubun.replaceAll("\\D+", ""));
		int rndNum = number + ((int) (Math.random() * 99) + 1);
		String writerFirst = Character.toString(writer.charAt(0));
		String rnd =String.valueOf((int) (Math.random() * 99) + 1);
		String giho = rndNum+ "-"+writerFirst+rnd;
		return giho;
	}
	
}
