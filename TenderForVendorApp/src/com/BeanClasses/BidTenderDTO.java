package com.BeanClasses;

public class BidTenderDTO {
	
	private int tenderid;
	private String tname;
	private int bidid;
	private int vendorid;
	private int bidamount;
	private int tamount;
	
	public BidTenderDTO() {
		// TODO Auto-generated constructor stub
	}

	public BidTenderDTO(int tenderid, String tname, int bidid, int vendorid, int bidamount, int tamount) {
		super();
		this.tenderid = tenderid;
		this.tname = tname;
		this.bidid = bidid;
		this.vendorid = vendorid;
		this.bidamount = bidamount;
		this.tamount = tamount;
	}

	public int getTenderid() {
		return tenderid;
	}

	public void setTenderid(int tenderid) {
		this.tenderid = tenderid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
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

	public int getBidamount() {
		return bidamount;
	}

	public void setBidamount(int bidamount) {
		this.bidamount = bidamount;
	}

	public int getTamount() {
		return tamount;
	}

	public void setTamount(int tamount) {
		this.tamount = tamount;
	}

	@Override
	public String toString() {
		return "BidTenderDTO [tenderid=" + tenderid + ", tname=" + tname + ", bidid=" + bidid + ", vendorid=" + vendorid
				+ ", bidamount=" + bidamount + ", tamount=" + tamount + "]";
	}
	
	

}
