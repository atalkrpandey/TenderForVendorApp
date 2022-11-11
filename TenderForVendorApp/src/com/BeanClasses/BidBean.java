package com.BeanClasses;

public class BidBean {

	private int bid;
	private int bamount;
	private String bstatus;
	private int tid;
	private int vid;
	
	public BidBean() {
		// TODO Auto-generated constructor stub
	}

	public BidBean(int bid, int bamount, String bstatus, int tid, int vid) {
		super();
		this.bid = bid;
		this.bamount = bamount;
		this.bstatus = bstatus;
		this.tid = tid;
		this.vid = vid;
	}
	
	public BidBean(int bamount, int tid, int vid) {
		super();
		
		this.bamount = bamount;
	
		this.tid = tid;
		this.vid = vid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getBamount() {
		return bamount;
	}

	public void setBamount(int bamount) {
		this.bamount = bamount;
	}

	public String getBstatus() {
		return bstatus;
	}

	public void setBstatus(String bstatus) {
		this.bstatus = bstatus;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	@Override
	public String toString() {
		return "BidBean [bid=" + bid + ", bamount=" + bamount + ", bstatus=" + bstatus + ", tid=" + tid + ", vid=" + vid
				+ "]";
	}
	
	
	
	
}
