package com.DaoImpl;

import com.BeanClasses.VendorBean;
import com.BeanClasses.VendorExpection;
import com.Exceptions.AdminSignInException;

public interface DaoIntForAdmin {

	
	public String loginAdmin(String email, String password) throws AdminSignInException;
	
	public String addNewVendor(VendorBean v1) throws VendorExpection;
}
