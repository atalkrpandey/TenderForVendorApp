package com.BeanClasses;

public class TenderBean {

	private int tid;
	private String tname;
	private int tamount;
	private boolean status;
	private int bidid;
	private int vendorid;
	
	public TenderBean() {
		// TODO Auto-generated constructor stub
	}

	public TenderBean(String tname, int tamount) {
		super();
		
		this.tname = tname;
		this.tamount = tamount;
		this.status = false;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getTamount() {
		return tamount;
	}

	public void setTamount(int tamount) {
		this.tamount = tamount;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getBidid() {
		return bidid;
	}

	public void setBidid(int bidid) {
		this.bidid = bidid;
	}

	public int getVendorid() {
		return vendorid;
	}

	public void setVendorid(int vendorid) {
		this.vendorid = vendorid;
	}

	@Override
	public String toString() {
		return "TenderBean [tid=" + tid + ", tname=" + tname + ", tamount=" + tamount + ", status=" + status
				+ ", bidid=" + bidid + ", vendorid=" + vendorid + "]";
	}
	
	
	
}
