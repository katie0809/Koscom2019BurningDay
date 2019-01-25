package kr.co.koscom.marketdata.model;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.marketdata.util.HttpClientUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.io.DataOutputStream;
import java.nio.charset.Charset;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.marketdata.util.HttpClientUtil;

public class Companies {
	 static int index=0;
	private String market="";
	private String name="";
	private String score="";
	
	private int emotionalScore;
	
	public int getEmotionalScore() {
		return emotionalScore;
	}
	public void setEmotionalScore(int emotionalScore) {
//		this.emotionalScore = emotionalScore;
		Random random = new Random();
		int randomNum = random.nextInt(10);
		this.emotionalScore = randomNum;
		System.out.println("★★★★★감정분석점수 : " + this.emotionalScore);
	}
	
	private List<Docs> docs = new ArrayList<Docs>();
	
	@Autowired
	private HttpClientUtil httpClientUtil;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
//		System.out.println("★★★키워드 : " + this.name
//		this.name = name.toString().substring(1, this.name.length()-1);
		setUrls(name);
	}
	
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	


   class Document {
       public String id, language, text;

       public Document(String id, String language, String text){
           this.id = id;
           this.language = language;
           this.text = text;
       }
   }

   class Documents {
       public List<Document> documents;

       public Documents() {
           this.documents = new ArrayList<Document>();
       }
       public void add(String id, String language, String text) {
           this.documents.add (new Document (id, language, text));
       }
   }

   
   
    public static String GetSentiment (Documents documents) throws Exception {
         //sentiment accesskey
       String accessKey = "39e6733415d24912a52275f6bc5e7f1d";
       String host = "https://westcentralus.api.cognitive.microsoft.com";
       String path = "/text/analytics/v2.0/sentiment";
       
         String text = new Gson().toJson(documents);
         byte[] encoded_text = text.getBytes("UTF-8");

         URL url = new URL(host+path);
         HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
         connection.setRequestMethod("POST");
         connection.setRequestProperty("Content-Type", "text/json");
         connection.setRequestProperty("Ocp-Apim-Subscription-Key", accessKey);
         connection.setDoOutput(true);

         DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
         wr.write(encoded_text, 0, encoded_text.length);
         wr.flush();
         wr.close();

         StringBuilder response = new StringBuilder ();
         BufferedReader in = new BufferedReader(
         new InputStreamReader(connection.getInputStream()));
         String line;
         while ((line = in.readLine()) != null) {
             response.append(line);
         }
         in.close();

         return response.toString();
     }
     

     public static String prettify(String json_text) {
         JsonParser parser = new JsonParser();
         JsonObject json = parser.parse(json_text).getAsJsonObject();
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         return gson.toJson(json);
     }

	public void setUrls(String query) {
		this.setEmotionalScore(10);
		
		if(name == null) return;
		
		try{
				List<Docs> ret = new ArrayList<Docs>();
			    StringBuilder urlBuilder = new StringBuilder("https://sandbox-apigw.koscom.co.kr/v1/haystack/{category}/{section}/_search".replace("{category}", URLEncoder.encode("news", "UTF-8")).replace("{section}", URLEncoder.encode("politics,economy,society,culture,world,tech,opinion", "UTF-8")));
		        urlBuilder.append("?");
		        urlBuilder.append(URLEncoder.encode("query","UTF-8") + "=" + URLEncoder.encode(query.substring(1,query.length()-1), "UTF-8") + "&");
		        urlBuilder.append(URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + "&");
		        urlBuilder.append(URLEncoder.encode("apikey","UTF-8") + "=" + URLEncoder.encode("l7xx30a19d389f204686a4b2a0e150ade045", "UTF-8"));
		        URL url = new URL(urlBuilder.toString());
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        
		        BufferedReader rd;
		        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		        }
		        StringBuilder sb = new StringBuilder();
		        String line;
		        while ((line = rd.readLine()) != null) {
		            sb.append(line);
		        }
		        rd.close();
		        conn.disconnect();
		        JsonNode json = objectMapper.readTree(sb.toString());
		        
		        //받아온 결과값에서 필요한 도큐먼트들을 뽑아서 리스트에 저장한다
		        JsonNode documents = json.findValue("docs");
		        List<JsonNode> titles = documents.findValues("title");
		        List<JsonNode> contents = documents.findValues("content");
		        List<JsonNode> content_urls = documents.findValues("content_url");
		        List<JsonNode> image_urls = documents.findValues("image_urls");
		        
		        for(JsonNode title : titles)
		        {
		        	Docs doc = new Docs();
		        	doc.setTitle(title.toString());
		        	ret.add(doc);
		        }
		        
		        int idx=0;
		        for(JsonNode content : contents)
		        {
		        	ret.get(idx).content = content.toString();
		        	idx++;
		        }
		        
		        idx=0;
		        for(JsonNode content_url : content_urls)
		        {
		        	ret.get(idx).setContentUrl(content_url.toString());
		        	idx++;
		        }
		        
		        idx=0;
		        for(JsonNode image_url : image_urls)
		        {
		        	if(image_url == null || image_url.toString() == "")
		        	{
		        		idx++;
		        		continue;
		        	}
		        	ret.get(idx).setImageUrl(image_url.toString());
		        	idx++;
		        }
		        
		        docs = ret;
		        for(Docs d : docs)
	              {
	                 index++;
	                 String clientId = "vgOEw__AkOgw7vGYkE0H";//애플리케이션 클라이언트 아이디값";
	                  String clientSecret = "GJAcUa6YVA";//애플리케이션 클라이언트 시크릿값";
	                  
	                 System.out.println("Title : " + d.getTitle() +"\n"+ "Content : " + d.getContent().toString() + "Url : " + d.getContentUrl() + "Img : " + d.getImageUrl());
	                 
	                 if(index<=5){
	                 String text = URLEncoder.encode(d.getContent().toString(), "UTF-8");
	                 //System.out.println(text);
	                     String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	                     URL url13 = new URL(apiURL);
	                     HttpURLConnection con = (HttpURLConnection)url13.openConnection();
	                     con.setRequestMethod("POST");
	                     con.setRequestProperty("X-Naver-Client-Id", clientId);
	                     con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	                     // post request
	                     String postParams = "source=ko&target=en&text=" + text;
	                     //System.out.println(postParams);
	                     
	                     con.setDoOutput(true);
	                     DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	                     wr.writeBytes(postParams);
	                     wr.flush();
	                     wr.close();
	                     int responseCode = con.getResponseCode();
	                     BufferedReader br;;
	                     if(responseCode==200) { // 정상 호출
	                         br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	                     } else {  // 에러 발생
	                         br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	                     }
	                     String inputLine;
	                     StringBuffer response = new StringBuffer();
	                     while ((inputLine = br.readLine()) != null) {
	                         response.append(inputLine);
	                     }            
	                     br.close();
	   
	                     //System.out.println(response.toString());
	                     ObjectMapper objectMapper = new ObjectMapper();
	                     JsonNode node = objectMapper.readTree(response.toString());
	                     //System.out.println(node.get("message"));
	                     System.out.println("번역 : " + node.get("message").get("result").get("translatedText"));
	                     // 여기까지가 입력한 문장을 번역하는 부분
	                 
	                     String temp = node.get("message").get("result").get("translatedText").asText();
	                      Documents documents12 = new Documents();
	                      documents12.add ("1", "en", temp);
	                      //Getsentiment 함수에 감성 분석하고자 하는 문장을 입력
	                      String result = prettify(GetSentiment (documents12));
	                      //System.out.println (result); // json 파일 출력하는 부분
	                      
	                      JsonParser Parser = new JsonParser();
	                      JsonObject jsonObj = (JsonObject) Parser.parse(result);
	                      JsonArray memberArray = (JsonArray) jsonObj.get("documents");
	                      
	                      for (int i = 0; i < memberArray.size(); i++) {          
	                          JsonObject object = (JsonObject) memberArray.get(i);
	                          //System.out.println("문서번호 : " + object.get("id"));
	                          
	                          if(object.get("score").getAsDouble()>=0.5){
	                            System.out.println("감성 분석 점수 측정 : " + object.get("score").getAsDouble());
	                             System.out.println("긍정");
	                          }
	                          else{
	                            System.out.println("감성 분석 점수 측정 : " + object.get("score").getAsDouble());
	                             System.out.println("부정");
	                          }
	                       }   
	
	
	                     
	                 }
	              }
			
		}
		catch(Exception e)
		{
		}
		
		return;
	}
	public List<Docs> getDocs() {
		return docs;
	}
	public void setDocs(List<Docs> docs) {
		this.docs = docs;
	}
	
}
