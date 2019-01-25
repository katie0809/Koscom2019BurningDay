package kr.co.koscom.marketdata.controller;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;

import kr.co.koscom.marketdata.api.HaystackApiCaller;
import kr.co.koscom.marketdata.api.LogpressoApiCaller;
import kr.co.koscom.marketdata.api.MarketDataApiCaller;
import kr.co.koscom.marketdata.model.Companies;
import kr.co.koscom.marketdata.model.Price;
import kr.co.koscom.marketdata.model.Trends;

@Controller
public class MarketDataController {
	
	@Autowired
	private MarketDataApiCaller marketDataApiCaller;

	@Autowired
	private LogpressoApiCaller logpressoApiCaller;
	
	@Autowired
	private HaystackApiCaller haystackApiCaller;

    @RequestMapping(path = "/marketdata/price/{issueCode}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public @ResponseBody Price priceJson(@PathVariable String issueCode) {
        return marketDataApiCaller.getPrice(issueCode);
    }
    
    
    @RequestMapping(path = "/marketdata/graph/price/{issueCode}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public String priceJson(@PathVariable String issueCode, Model model) {
        
    	model.addAttribute("issueCode", issueCode);
    	
    	// return view
    	return "graph";
    }

    @RequestMapping(path = "/marketdata/log/10m/{issueCode}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public @ResponseBody JsonNode log10mJson(@PathVariable String issueCode) {
        return logpressoApiCaller.get10mLog(issueCode);
    }
    
    /*
    @RequestMapping(path = "/home",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public @ResponseBody JsonNode trendsJson() {
        return haystackApiCaller.showTrends();
    }
    */
//    @RequestMapping(path = "/home",
//    		method = { RequestMethod.GET, RequestMethod.POST } )
//    public @ResponseBody List<Trends> trendsJson() {
//        return haystackApiCaller.showTrends();
//    }

    /* 정훈 수정 */
    @RequestMapping(path = "/home",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public String Home(Model model) {
    	
    	List<Trends> trends = new ArrayList<Trends>();
    	
    	trends = haystackApiCaller.showTrends();
    	
    	model.addAttribute("trends", trends); 
    	
    	// return view
    	return "home";
    }
    
    /* 정훈 수정 */
    @RequestMapping(path = "/homebackup",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public String Home_back(Model model) {
    	
    	List<Trends> trends = new ArrayList<Trends>();
    	
    	trends = haystackApiCaller.showTrends();
    	
    	model.addAttribute("trends", trends); 
    	
    	// return view
    	return "homebackup";
    }
    
    /* 정훈 수정 */
    @RequestMapping(path = "/companies",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public String Companys(HttpServletRequest httpServletequest, Model model) {
    	
    	List<Companies> companies = new ArrayList<Companies>();
    	System.out.println("★★★" + httpServletequest.getParameter("keyword"));
    	String keyword = httpServletequest.getParameter("keyword");
    	
    	companies = haystackApiCaller.showCompanies(keyword);
    	
    	model.addAttribute("companies", companies);
    	model.addAttribute("keyword", keyword);
    	
    	// return view
    	return "companies";
    }
    
    @RequestMapping(path = "/news",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public String News(HttpServletRequest httpServletequest, Model model) {
    	
    	List<Companies> companies = new ArrayList<Companies>();
    	
    	String keyword = httpServletequest.getParameter("keyword");
    	String companyName = httpServletequest.getParameter("companyName");
    	
    	System.out.println("★★★키워드 : " + httpServletequest.getParameter("keyword"));
    	System.out.println("★★★회사이름 : " + httpServletequest.getParameter("companyName"));
    	
    	companies = haystackApiCaller.showCompanies(keyword);
    	
    	
    	model.addAttribute("companies", companies);
    	model.addAttribute("keyword", keyword);
    	model.addAttribute("companyName", companyName);
    	
    	// return view
    	return "news";
    }
}
