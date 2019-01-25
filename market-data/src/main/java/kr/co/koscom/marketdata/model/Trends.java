package kr.co.koscom.marketdata.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Trends implements Serializable{

private static final long serialVersionUID = 5328249083677015804L;
	/*
	private long uid=0;
	private String uid_str="";
	private String category="";
	private String section="";
	private long rank=0;
	*/
	private String topic="";
	private List<String> keywords;
	private String score="";

	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}

	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(String keyword) {
		
		keyword = keyword.substring(2, keyword.length()-2);
		String[] st = keyword.split("\", \"");
		List<String> list = new ArrayList<String>();
		
		for(String word : st)
		{
			list.add(word);
		}
		this.keywords = list;
	}
	
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	/*
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getUidStr() {
		return uid_str;
	}
	public void SetUidStr(String uid_str) {
		this.uid_str = uid_str;
	}
	
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	
	public long getRank() {
		return rank;
	}
	public void setRank(long rank) {
		this.rank = rank;
	}
	
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	*/
	
}
