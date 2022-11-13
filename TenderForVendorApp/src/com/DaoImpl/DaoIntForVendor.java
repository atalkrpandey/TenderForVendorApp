package com.DaoImpl;

import java.util.List;

import com.BeanClasses.BidBean;
import com.BeanClasses.TenderBean;
import com.BeanClasses.VendorBean;
import com.Exceptions.BidException;
import com.Exceptions.TenderException;
import com.Exceptions.VendorAuthenticateException;

public interface DaoIntForVendor {
	
	public int loginNewVendor(VendorBean vb) throws VendorAuthenticateException;
	
	public List<TenderBean> getAllTenders() throws TenderException;
	
	public String addNewBid(BidBean bd) throws BidException;
	
	public BidBean getStatusOfBid(int bid) throws BidException;
	
	public List<BidBean> getBidOFVendor(int vid) throws BidException;

}
