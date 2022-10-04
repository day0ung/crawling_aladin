package com.cra.aladin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.cra.aladin.dao.BookDao;
import com.cra.aladin.dto.BookDto;
import com.cra.aladin.utils.AladinPage;
import com.cra.aladin.utils.ParsingData;

@Component
public class AladinCrawlerApplication implements ApplicationRunner{
	

	@Autowired
	BookDao bookDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Map<String, Object> parsingPage = new HashMap<>();
		
		Map<String, String> data = ParsingData.getParsingData();
		for (String key : data.keySet()) {
			String url = data.get(key);
			Document doc = Jsoup.connect(url).get();
			int totalCnt = Integer.parseInt(doc.select("div.search_t_g").text().replaceAll("\\D+", ""));
			totalCnt = totalCnt > 500 ? 500: totalCnt;
			List<String> urlList = ParsingData.makeUrl(url, totalCnt);
			parsingPage.put(key, urlList);
		}
		
////		for (String gubun : parsingPage.keySet()) {
////			List<String> urlList = (List<String>)parsingPage.get(gubun);
////			AladinPage page = new AladinPage(gubun, urlList);
////			List<BookDto> bookList = page.parsingPage();
////			System.out.println(bookList.size());
////		}
		List<CompletableFuture<List<BookDto>>> futureList = new ArrayList<>();
		for ( String gubun : parsingPage.keySet()) {
			@SuppressWarnings("unchecked")
			List<String> urlList = (List<String>)parsingPage.get(gubun);
			futureList.add(CompletableFuture.supplyAsync(()-> {
				AladinPage page = new AladinPage(gubun, urlList);
				return page.parsingPage();
			})
			);
		}
		
		
		CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();
		List<List<BookDto>> bookList = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
		for (List<BookDto> list : bookList) {
			for (BookDto dto : list) {
				bookDao.insertBookData(dto);
			}
		}
		
		
		
	}
	

}
