package kr.co.koscom.marketdata.api;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.marketdata.util.HttpClientUtil;

@Component
public class LogpressoApiCaller {

	private static String URI_PREFIX = "http://localhost:8888/logpresso/httpexport/query.json";
	
	private static String APIKEY = "69248edd-8060-c211-dad5-e22ec9790903";
	
	@Autowired
	private HttpClientUtil httpClientUtil;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public JsonNode get10mLog(String issueCode) {
    	String query = "table duration=10m marketdata_trade | search 단축코드 == \"" + issueCode + "\"";
		return executeQuery(query);
	}
	
	private JsonNode executeQuery(String query) {
		
		try {
			String jsonStr = httpClientUtil.execute(URI_PREFIX + "?_apikey=" + URLEncoder.encode(APIKEY, "UTF-8") 
													+ "&_q=" + URLEncoder.encode(query, "UTF-8"));

			// 응답 받은 json이 배열형태가 아닌 line break로 구분되어 있어, 배열형태로 바꾸어준다.
			String[] lines = jsonStr.split("\n");
			String finalStr = Arrays.stream(lines).collect(Collectors.joining(", "));
			finalStr = "[" + finalStr + "]";

			JsonNode node = objectMapper.readTree(finalStr);
			
			return node;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
