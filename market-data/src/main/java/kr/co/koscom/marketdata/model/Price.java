package kr.co.koscom.marketdata.model;

import java.io.Serializable;

public class Price implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5328249083677015804L;
	
	private String isuSrtCd;
	private long trdPrc;
	private String cmpprevddTpCd;
	private long opnprc;
	private long hgprc;
	private long lwprc;
	private long accTrdvol;
	private long trdTm;
	private long trdvol;
	private long accTrdval;
	private long cmpprevddPrc;
	private long bidordPrc_1;
	private long askordPrc_1;
	private String lstAskbidTpCd;
	
	public String getIsuSrtCd() {
		return isuSrtCd;
	}
	public void setIsuSrtCd(String isuSrtCd) {
		this.isuSrtCd = isuSrtCd;
	}
	public long getTrdPrc() {
		return trdPrc;
	}
	public void setTrdPrc(long trdPrc) {
		this.trdPrc = trdPrc;
	}
	public String getCmpprevddTpCd() {
		return cmpprevddTpCd;
	}
	public void setCmpprevddTpCd(String cmpprevddTpCd) {
		this.cmpprevddTpCd = cmpprevddTpCd;
	}
	public long getOpnprc() {
		return opnprc;
	}
	public void setOpnprc(long opnprc) {
		this.opnprc = opnprc;
	}
	public long getHgprc() {
		return hgprc;
	}
	public void setHgprc(long hgprc) {
		this.hgprc = hgprc;
	}
	public long getLwprc() {
		return lwprc;
	}
	public void setLwprc(long lwprc) {
		this.lwprc = lwprc;
	}
	public long getAccTrdvol() {
		return accTrdvol;
	}
	public void setAccTrdvol(long accTrdvol) {
		this.accTrdvol = accTrdvol;
	}
	public long getTrdTm() {
		return trdTm;
	}
	public void setTrdTm(long trdTm) {
		this.trdTm = trdTm;
	}
	public long getTrdvol() {
		return trdvol;
	}
	public void setTrdvol(long trdvol) {
		this.trdvol = trdvol;
	}
	public long getAccTrdval() {
		return accTrdval;
	}
	public void setAccTrdval(long accTrdval) {
		this.accTrdval = accTrdval;
	}
	public long getCmpprevddPrc() {
		return cmpprevddPrc;
	}
	public void setCmpprevddPrc(long cmpprevddPrc) {
		this.cmpprevddPrc = cmpprevddPrc;
	}
	public long getBidordPrc_1() {
		return bidordPrc_1;
	}
	public void setBidordPrc_1(long bidordPrc_1) {
		this.bidordPrc_1 = bidordPrc_1;
	}
	public long getAskordPrc_1() {
		return askordPrc_1;
	}
	public void setAskordPrc_1(long askordPrc_1) {
		this.askordPrc_1 = askordPrc_1;
	}
	public String getLstAskbidTpCd() {
		return lstAskbidTpCd;
	}
	public void setLstAskbidTpCd(String lstAskbidTpCd) {
		this.lstAskbidTpCd = lstAskbidTpCd;
	}
	
	
	
}
