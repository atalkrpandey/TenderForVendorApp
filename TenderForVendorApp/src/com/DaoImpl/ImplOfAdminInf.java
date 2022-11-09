package com.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.BeanClasses.TenderBean;
import com.BeanClasses.VendorBean;
import com.Exceptions.AdminSignInException;
import com.Exceptions.TenderException;
import com.Exceptions.VendorException;
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
	public String addNewVendor(VendorBean v1) throws VendorException {
		// TODO Auto-generated method stub
		String mass = null;
		
try(Connection conn = DbUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into vendors(vendorname,vendoremail,vendorphone,vendorpassword) values(?,?,?,?)");
			ps.setString(1, v1.getVname());
			ps.setString(2, v1.getVemail());
			ps.setString(3, v1.getVphone());
			ps.setString(4,v1.getVpass());
		    int re = ps.executeUpdate();
		    if(re>0) {
		    	mass = "Vendor Added Successfully..";
		    }else {
		    	throw new VendorException("Vendor Allready exist...");
		    }
			
		}catch(SQLException e) {
//			throw new AdminSignInException(e.getMessage());
			throw new VendorException(e.getMessage());
		}
		
		return mass;
	}

	@Override
	public List<VendorBean> getAllVendors() throws VendorException {
		List<VendorBean> list = new ArrayList<>();
		
try(Connection conn = DbUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from vendors");
		     
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
			 int id = rs.getInt("venderid");
			 String name = rs.getString("vendorname");
			 String email = rs.getString("vendoremail");
			 String phone = rs.getString("vendorphone");
			 String pass = rs.getString("vendorpassword");
			 VendorBean vb = new VendorBean(id,name,email,phone,pass);
			 list.add(vb);
			 
			}
			if(list.size()==0) {
				throw new VendorException("Any Record not found..");
			}
			
		}catch(SQLException e) {
//			throw new AdminSignInException(e.getMessage());
			throw new VendorException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public String createNewTender(TenderBean t1) throws TenderException {
		String mass = null;
		
		try(Connection conn = DbUtil.provideConnection()){
					
					PreparedStatement ps = conn.prepareStatement("insert into tenders(tendername,tenderamount) values(?,?)");
					ps.setString(1, t1.getTname());
					ps.setInt(2, t1.getTid());
					
				    int re = ps.executeUpdate();
				    if(re>0) {
				    	mass = "Tender Added Successfully..";
				    }else {
				    	throw new TenderException("Unable to add due to worng credentials...");
				    }
					
				}catch(SQLException e) {
//					throw new AdminSignInException(e.getMessage());
					throw new TenderException(e.getMessage());
				}
				
				return mass;
	}

}
