package com.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.BeanClasses.BidBean;
import com.BeanClasses.TenderBean;
import com.BeanClasses.VendorBean;
import com.ColorAndModel.ConsoleColors;
import com.Exceptions.BidException;
import com.Exceptions.TenderException;
import com.Exceptions.VendorAuthenticateException;
import com.Utility.DbUtil;

public class VendorInterfaceIMP implements DaoIntForVendor {

	@Override
	public int loginNewVendor(VendorBean vb) throws VendorAuthenticateException {
	    int vid =-1;
	   
	   try(Connection conn =DbUtil.provideConnection()) {
		   
		   PreparedStatement ps = conn.prepareStatement("select * from vendors where vendorname=? and vendorpassword=?");
		   
		   ps.setString(1,vb.getVname());
		   ps.setString(2, vb.getVpass());
		   ResultSet re = ps.executeQuery();
		   
		   if(re.next()) {
			   System.out.println(ConsoleColors.GREEN+"Hello Mr. "+re.getString("vendorname")+" You have logined successfully.."+ConsoleColors.WHITE);
			   vid = re.getInt("venderid");
			 
		   }else {
			   throw new VendorAuthenticateException("You Have filled wrong credentials, Please fill Right UserName and Password..");
		   }
		
	} catch (SQLException e) {
		// TODO: handle exception
		throw new VendorAuthenticateException(e.getMessage());
	}
	   
	   return vid;
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
						throw new TenderException("Any Record not found..");
					}
					
				}catch(SQLException e) {
//					throw new AdminSignInException(e.getMessage());
					throw new TenderException(e.getMessage());
				}
				
				return list;
	}

	@Override
	public String addNewBid(BidBean bd) throws BidException {
	String mass = null;
		
		try(Connection conn = DbUtil.provideConnection()){
					
					PreparedStatement ps = conn.prepareStatement("insert into Bids(bidamount,tenderid,vendorid) values(?,?,?)");
					ps.setInt(1, bd.getBamount());
					ps.setInt(2, bd.getTid());
					ps.setInt(3, bd.getVid());
					
				    int re = ps.executeUpdate();
				    if(re>0) {
				    	mass = "Bid Added Successfully..";
				    }else {
				    	throw new BidException("Unable to add due to worng credentials...");
				    }
					
				}catch(SQLException e) {
//					throw new AdminSignInException(e.getMessage());
					throw new BidException(e.getMessage());
				}
				
				return mass;
	}

	@Override
	public BidBean getStatusOfBid(int bid) throws BidException {
	    
		BidBean bd = null;
		
		try(Connection conn = DbUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from bids where bidid=?");
			ps.setInt(1, bid);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int bamount = rs.getInt("bidamount");
				int tid = rs.getInt("tenderid");
				int vid = rs.getInt("vendorid");
				String status = rs.getString("bidstatus");
				
				bd = new BidBean(bid,bamount,status,tid,vid);
			}else {
				throw new BidException("There is any bid with this Id...");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new BidException(e.getMessage());
		}
		return bd;
	}

	@Override
	public List<BidBean> getBidOFVendor(int vid) throws BidException {
		 List<BidBean> list = new ArrayList<>();
			
			try(Connection conn = DbUtil.provideConnection()){
						
						PreparedStatement ps = conn.prepareStatement("select * from bids where vendorid =?");
					     ps.setInt(1, vid);
						ResultSet rs = ps.executeQuery();
						
						while(rs.next()) {
						 int id = rs.getInt("bidid");
						 String status = rs.getString("bidstatus");
						 int amount = rs.getInt("bidamount");
						
						 int tid = rs.getInt("tenderid");
						 int venderid = rs.getInt("vendorid");
						
						 BidBean vb = new BidBean(id,amount,status,tid,venderid);
						
						 list.add(vb);
						 
						}
						if(list.size()==0) {
							throw new BidException("Any Record not found..");
						}
						
					}catch(SQLException e) {
//						throw new AdminSignInException(e.getMessage());
						throw new BidException(e.getMessage());
					}
					
					return list;
	}

	
}
