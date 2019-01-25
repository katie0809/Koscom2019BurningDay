package kr.co.koscom.marketdata.api;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.marketdata.model.Price;
import kr.co.koscom.marketdata.util.HttpClientUtil;

@Component
public class MarketDataApiCaller {

	private static String URI_PREFIX = "https://sandbox-apigw.koscom.co.kr/v2/market/stocks/kospi/";
	private static String URI_POSTFIX = "/price";
	
	private static String APIKEY = "l7xx230ef2235e34448c982eb192ac98e206";
	
	@Autowired
	private HttpClientUtil httpClientUtil;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public Price getPrice(String issueCode) {
		
		try {
			String jsonStr = httpClientUtil.execute(URI_PREFIX + URLEncoder.encode(issueCode, "UTF-8") + URI_POSTFIX + "?apikey=" + URLEncoder.encode(APIKEY, "UTF-8"));
			JsonNode node = objectMapper.readTree(jsonStr);
			System.out.println(node);
			Price price = objectMapper.readValue(node.findValue("result").toString(), Price.class);
			
			return price;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
