package com.DaoImpl;

import java.util.List;

import com.BeanClasses.TenderBean;
import com.BeanClasses.VendorBean;
import com.Exceptions.AdminSignInException;
import com.Exceptions.TenderException;
import com.Exceptions.VendorException;

public interface DaoIntForAdmin {

	
	public String loginAdmin(String email, String password) throws AdminSignInException;
	
	public String addNewVendor(VendorBean v1) throws VendorException;
	
	public List<VendorBean> getAllVendors() throws VendorException;
	
	public String createNewTender(TenderBean tb) throws TenderException;
}
