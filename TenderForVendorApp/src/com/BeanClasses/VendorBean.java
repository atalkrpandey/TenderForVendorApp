package com.BeanClasses;

public class VendorBean {
  
	private int vid;
	private String vname;
	private String vemail;
	private String vphone;
	private String vpass;
	
	public VendorBean() {
		// TODO Auto-generated constructor stub
	}

	public VendorBean(int vid, String vname, String vemail, String vphone, String vpass) {
		super();
		this.vid = vid;
		this.vname = vname;
		this.vemail = vemail;
		this.vphone = vphone;
		this.vpass = vpass;
	}
  
	public VendorBean( String vname, String vpass) {
		super();
		
		this.vname = vname;
		
		this.vpass = vpass;
	}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVemail() {
		return vemail;
	}

	public void setVemail(String vemail) {
		this.vemail = vemail;
	}

	public String getVphone() {
		return vphone;
	}

	public void setVphone(String vphone) {
		this.vphone = vphone;
	}

	public String getVpass() {
		return vpass;
	}

	public void setVpass(String vpass) {
		this.vpass = vpass;
	}

	@Override
	public String toString() {
		return "VendorBean [vid=" + vid + ", vname=" + vname + ", vemail=" + vemail + ", vphone=" + vphone + ", vpass="
				+ vpass + "]";
	}
	
	
}
