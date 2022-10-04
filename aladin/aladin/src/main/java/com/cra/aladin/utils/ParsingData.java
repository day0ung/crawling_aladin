package com.cra.aladin.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParsingData {
	
	public static Map<String, String> getParsingData() {
		Map<String, String> data = new HashMap<>();
		data.put("100(건강/취미/레저)", "https://www.aladin.co.kr/shop/common/wnew.aspx?NewType=New&BranchType=1&CID=55890&ViewRowsCount=50");
		data.put("200(경제경영)", "https://www.aladin.co.kr/shop/common/wnew.aspx?NewType=New&BranchType=1&CID=170&ViewRowsCount=50");
		data.put("300(고전)", "https://www.aladin.co.kr/shop/common/wnew.aspx?NewType=New&BranchType=1&CID=2105&ViewRowsCount=50");
		data.put("400(과학)", "https://www.aladin.co.kr/shop/common/wnew.aspx?NewType=New&BranchType=1&CID=987&ViewRowsCount=50");
		data.put("500(만화)", "https://www.aladin.co.kr/shop/common/wnew.aspx?NewType=New&BranchType=1&CID=2551&ViewRowsCount=50");
		data.put("600(사회과학)", "https://www.aladin.co.kr/shop/common/wnew.aspx?NewType=New&BranchType=1&CID=798&ViewRowsCount=50");
		data.put("700(소설/시/희곡)", "https://www.aladin.co.kr/shop/common/wnew.aspx?NewType=New&BranchType=1&CID=1&ViewRowsCount=50");
		
		return data;
	}

	public static List<String> makeUrl(String url, int totalCnt) {
		List<String> urlList = new ArrayList<>();
		for (int i = 1; i <= 2; i++) {
			String pagenationUrl = url+"&page="+i;
			urlList.add(pagenationUrl);
		}

		return urlList;
	}

}
