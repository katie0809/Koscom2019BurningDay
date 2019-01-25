package kr.co.koscom.marketdata.api;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.marketdata.model.Companies;
import kr.co.koscom.marketdata.model.Trends;
import kr.co.koscom.marketdata.util.HttpClientUtil;

@Component
public class HaystackApiCaller {

	private static String URI_PREFIX = "https://sandbox-apigw.koscom.co.kr/v1/haystack/topics/trending/news";
	private static String URI_POSTFIX = "/home";
	
	private static String APIKEY = "l7xx30a19d389f204686a4b2a0e150ade045";
	
	@Autowired
	private HttpClientUtil httpClientUtil;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public List<Trends> showTrends()
	{
		// 현재 시장에서 트렌드인 토픽과 키워드들을 보여준다.
		try {
			
			StringBuilder urlBuilder = new StringBuilder("https://sandbox-apigw.koscom.co.kr/v1/haystack/topics/trending/news");
	        urlBuilder.append("?");
	        urlBuilder.append(URLEncoder.encode("apikey","UTF-8") + "=" + URLEncoder.encode("l7xx30a19d389f204686a4b2a0e150ade045", "UTF-8"));
	    
	        //API를 통해 트랜드 결과값을 받아온다
			String jsonStr = httpClientUtil.execute(urlBuilder.toString());
			
			//읽어온 값을 Trends 구조체에 매핑
			JsonNode node = objectMapper.readTree(jsonStr);
			
			List<Trends> trends = new ArrayList<Trends>();
			List<JsonNode> topics = node.findValues("topic");
			List<JsonNode> keywords = node.findValues("keywords");
			List<JsonNode> scores = node.findValues("score");
			
			for(JsonNode topic : topics)
			{
				Trends trend = new Trends();
				trend.setTopic(topic.toString());
				trends.add(trend);
			}
			
			int idx = 0;
			for(JsonNode keyword : keywords)
			{	
				trends.get(idx).setKeywords(keyword.findValues("keyword").toString());
				idx++;
			}
			
			idx = 0;
			for(JsonNode score : scores)
			{
				trends.get(idx).setScore(score.toString());
				idx++;
			}
			//Trends trend = objectMapper.readValue(node.findValue("all").toString(), Trends.class);
			
			return trends;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//사용자 쿼리와 연관된 기업을 추출해온다
	public List<Companies> showCompanies(String query)
	{
		try{
			 StringBuilder urlBuilder = new StringBuilder("https://sandbox-apigw.koscom.co.kr/v1/haystack/entities/company/_search");
	        urlBuilder.append("?");
	        urlBuilder.append(URLEncoder.encode("query","UTF-8") + "=" + URLEncoder.encode(query, "UTF-8") + "&");
	        urlBuilder.append(URLEncoder.encode("apikey","UTF-8") + "=" + URLEncoder.encode("l7xx30a19d389f204686a4b2a0e150ade045", "UTF-8"));
	    
	        //API瑜� �넻�빐 �듃�옖�뱶 寃곌낵媛믪쓣 諛쏆븘�삩�떎
			String jsonStr = httpClientUtil.execute(urlBuilder.toString());
			
			//�씫�뼱�삩 媛믪쓣 Trends 援ъ“泥댁뿉 留ㅽ븨
			JsonNode node = objectMapper.readTree(jsonStr);
			System.out.println(urlBuilder.toString());
			List<Companies> companies = new ArrayList<Companies>();
			List<JsonNode> markets = node.findValues("market");
			List<JsonNode> names = node.findValues("name");
			List<JsonNode> scores = node.findValues("score");
			

			for(JsonNode name : names)
			{
				Companies company = new Companies();
				company.setName(name.toString());
				
				companies.add(company);
			}
			
			int idx = 0;
			for(JsonNode score : scores)
			{
				companies.get(idx).setScore(score.toString());
				idx++;
			}
			
			idx = 0;
			for(JsonNode market : markets)
			{
				companies.get(idx).setMarket(market.toString());
				idx++;
			}
			//Trends trend = objectMapper.readValue(node.findValue("all").toString(), Trends.class);
			
			return companies;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	
	}
}
