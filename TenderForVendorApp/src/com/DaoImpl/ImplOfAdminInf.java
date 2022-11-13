package com.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.BeanClasses.BidBean;
import com.BeanClasses.BidTenderDTO;
import com.BeanClasses.TenderBean;
import com.BeanClasses.VendorBean;
import com.ColorAndModel.ConsoleColors;
import com.Exceptions.AdminSignInException;
import com.Exceptions.BidException;
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
				System.out.println(ConsoleColors.GREEN+"Hello, Weicome Mr. "+name.toUpperCase()+ConsoleColors.WHITE);
				mass = "You have logined successfully..";
			}else {
				throw new AdminSignInException("Invalid email or password.");
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
				throw new VendorException("No record found..");
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
					ps.setInt(2, t1.getTamount());
					
				    int re = ps.executeUpdate();
				    if(re>0) {
				    	mass = "Tender Added Successfully..";
				    }else {
				    	throw new TenderException("Unable to add due to Invalid credentials...");
				    }
					
				}catch(SQLException e) {
//					throw new AdminSignInException(e.getMessage());
					throw new TenderException(e.getMessage());
				}
				
				return mass;
	}

	@Override
	public List<TenderBean> getAllTenders() throws TenderException {
		List<TenderBean> list = new ArrayList<>();
		
		try(Connection conn = DbUtil.provideConnection()){
					
					PreparedStatement ps = conn.prepareStatement("select * from tenders");
				     
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()) {
					 int id = rs.getInt("tenderid");
					 String name = rs.getString("tendername");
					 int amount = rs.getInt("tenderamount");
					 boolean status = rs.getBoolean("status");
					 int bidid = rs.getInt("bidid");
					 int venderid = rs.getInt("venderid");
					
					 TenderBean vb = new TenderBean(name,amount);
					 vb.setTid(id);
					 vb.setBidid(bidid);
					 vb.setStatus(status);
					 vb.setVendorid(venderid);
					 list.add(vb);
					 
					}
					if(list.size()==0) {
						throw new TenderException("No record found..");
					}
					
				}catch(SQLException e) {
//					throw new AdminSignInException(e.getMessage());
					throw new TenderException(e.getMessage());
				}
				
				return list;
	}

	@Override
	public List<BidBean> getBidsOfTender(int bid) throws BidException {
List<BidBean> list = new ArrayList<>();
		
		try(Connection conn = DbUtil.provideConnection()){
					
					PreparedStatement ps = conn.prepareStatement("select * from Bids where tenderID = ?");
					ps.setInt(1, bid);
				     
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()) {
					 int id = rs.getInt("bidid");
					
					 int amount = rs.getInt("bidamount");
					 String status = rs.getString("bidstatus");
					 int tdid = rs.getInt("tenderid");
					 int venderid = rs.getInt("vendorid");
					 
					 
					 BidBean vb = new BidBean(id,amount,status,tdid,venderid);
					
					 list.add(vb);
					 
					}
					if(list.size()==0) {
						throw new BidException("No record found..");
					}
					
				}catch(SQLException e) {
//					throw new AdminSignInException(e.getMessage());
					throw new BidException(e.getMessage());
				}
				
				return list;
	}

	@Override
	public BidTenderDTO assignVendor(int tenderid) throws TenderException {
		BidTenderDTO bt = null;
		int vendorid;
		int bidid;
		int bamount;
		int tamount;
		String tname;
		try(Connection conn = DbUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select vendorid, bidid,min(bidamount) from bids where tenderid=?");
			ps.setInt(1, tenderid);
			ResultSet rs =  ps.executeQuery();
			if(rs.next()) {
				vendorid = rs.getInt("vendorid");
				bidid = rs.getInt("bidid");
				bamount = rs.getInt("min(bidamount)");
				PreparedStatement ps11 = conn.prepareStatement("select tenderamount from tenders where tenderid=?");
				ps11.setInt(1, tenderid);
				ResultSet r11 =ps11.executeQuery();
				if(r11.next()) {
					int amount = r11.getInt("tenderamount");
					if(bamount>amount) {
						throw new TenderException("No Any Bid is there which Bid amount is less then tender amount..");
					}
				}
			
				try(Connection conn1 = DbUtil.provideConnection()) {
					
					PreparedStatement ps1 = conn.prepareStatement("update tenders set venderid=?,bidid=?,status=true where tenderid=?");
					ps1.setInt(1, vendorid);
					ps1.setInt(2, bidid);
					ps1.setInt(3, tenderid);
					
					int re = ps1.executeUpdate();
					
					if(re>0) {
						
						try(Connection conn2 = DbUtil.provideConnection()) {
							
							PreparedStatement ps3 = conn2.prepareStatement("select * from tenders where tenderid=?");
							ps3.setInt(1, tenderid);
							
						ResultSet rs3 =	ps3.executeQuery();
						if(rs3.next()) {
							
							PreparedStatement ps4 = conn2.prepareStatement("select * from vendors where venderid=?");
                            ps4.setInt(1, vendorid);
                            ResultSet r = ps4.executeQuery();
                            String name=null;
                            if(r.next()) {
                               name = r.getString("vendorname");
                            }
							tamount = rs3.getInt("tenderamount");
							tname = rs3.getString("tendername");
							bt = new BidTenderDTO(tenderid,tname,bidid,vendorid,bamount,tamount);
						    bt.setVname(name);
							try(Connection con = DbUtil.provideConnection()) {
								
								
								
								PreparedStatement pd = conn2.prepareStatement("update bids set bidstatus = 'selected' where bidid!=?");
								pd.setInt(1,bidid);
								pd.executeUpdate();
                                try(Connection conn4 = DbUtil.provideConnection()) {
                                	PreparedStatement pd1 = conn4.prepareStatement("update bids set bidstatus = 'Rejected' where bidid!=?");
    								pd1.setInt(1,bidid);
    								pd1.executeUpdate();	
								} catch (SQLException e) {
									// TODO: handle exception
									throw new TenderException(e.getMessage());
								}
								
							} catch (Exception e) {
								// TODO: handle exception
								throw new TenderException(e.getMessage());
							}
							
						}
							
						} catch (SQLException e) {
							// TODO: handle exception
							throw new TenderException(e.getMessage());
						}
					}else {
						throw new TenderException("This Tender Id did not found in the system...");
					}
					
				} catch (SQLException e) {
					// TODO: handle exception
					throw new TenderException(e.getMessage());
				}
				
			}else {
				throw new TenderException("This tender Id did not founnd in the system...");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new TenderException(e.getMessage());
		}
		
		
		
		return bt;
	}

}
