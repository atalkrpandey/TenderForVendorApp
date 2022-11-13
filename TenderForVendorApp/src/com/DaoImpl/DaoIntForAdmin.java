package com.DaoImpl;

import java.util.List;

import com.BeanClasses.BidBean;
import com.BeanClasses.BidTenderDTO;
import com.BeanClasses.TenderBean;
import com.BeanClasses.VendorBean;
import com.Exceptions.AdminSignInException;
import com.Exceptions.BidException;
import com.Exceptions.TenderException;
import com.Exceptions.VendorException;

public interface DaoIntForAdmin {

	
	public String loginAdmin(String email, String password) throws AdminSignInException;
	
	public String addNewVendor(VendorBean v1) throws VendorException;
	
	public List<VendorBean> getAllVendors() throws VendorException;
	
	public String createNewTender(TenderBean tb) throws TenderException;
	
	public List<TenderBean> getAllTenders() throws TenderException;
	
	public List<BidBean> getBidsOfTender(int bid) throws BidException; 
	
	public BidTenderDTO assignVendor(int tenderid) throws TenderException;
	
}
