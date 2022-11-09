package com.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.BeanClasses.VendorBean;
import com.BeanClasses.VendorExpection;
import com.Exceptions.AdminSignInException;
import com.Utility.DbUtil;

public class ImplOfAdminInf implements DaoIntForAdmin {

	@Override
	public String loginAdmin(String email, String password) throws AdminSignInException {
		String mass = null;
		
		try(Connection conn = DbUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from administrator where adminemail=? and adminpassword=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String name = rs.getString("adminname");
				System.out.println("Hello, Wellcome to you MR. "+name);
				mass = "You have logined successfully..";
			}else {
				throw new AdminSignInException("Worng email or password.");
			}
			
			
		}catch(SQLException e) {
//			throw new AdminSignInException(e.getMessage());
			e.printStackTrace();
		}
		
		return mass;
	}

	@Override
	public String addNewVendor(VendorBean v1) throws VendorExpection {
		// TODO Auto-generated method stub
		String mass = null;
		
try(Connection conn = DbUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into vendors(vendorname,vendoremail,vendorphone,"
					+ "vendorpassword) values(?,?,?,?");
			ps.setString(1, v1.getVname());
			ps.setString(2, v1.getVemail());
			ps.setString(3, v1.getVphone());
			ps.setString(4,v1.getVpass());
		    int re = ps.executeUpdate();
		    if(re>0) {
		    	mass = "Vendor Added Successfully..";
		    }else {
		    	throw new VendorExpection("Vendor Allready exist...");
		    }
			
		}catch(SQLException e) {
//			throw new AdminSignInException(e.getMessage());
			throw new VendorExpection(e.getMessage());
		}
		
		return mass;
	}

}
