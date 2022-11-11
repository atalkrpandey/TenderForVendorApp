package com.RunClasses;

import java.util.List;
import java.util.Scanner;

import com.BeanClasses.BidBean;
import com.BeanClasses.TenderBean;
import com.DaoImpl.DaoIntForVendor;
import com.DaoImpl.VendorInterfaceIMP;
import com.Exceptions.BidException;
import com.Exceptions.TenderException;

public class VendorFunctionalities {

	  public void getfunctionalities(int vid) {
		  Scanner sc = new Scanner(System.in);
		  System.out.println("Welcome again, Lot of functionalities are there for you..");
		  System.out.println("You can perform any operation by putting there serial No. at bottom (like 1,2,3..)");
		  System.out.println("1. View all the Tenders.");
		  System.out.println("2. Place a Bid against a Tender.");
		  System.out.println("3. View status of a Bid(Whether Selected or Not).");
		  System.out.println("4. View his own Bid History.");
		  System.out.println("5. Exit.");
		  
		  System.out.println("Put your choise here...");
		  
		  int res = sc.nextInt();
		  DaoIntForVendor ad = new VendorInterfaceIMP();
		  switch(res) {
		  
		  case 1: 
			  
			  try {
					List<TenderBean> list = ad.getAllTenders();
					  System.out.println("Here, all the list of Tenders in system...");
				  for(TenderBean v : list) {
					
					  System.out.println("Tender Id -> "+v.getTid());        
					  System.out.println("Tender Name -> "+v.getTname());
					  System.out.println("Tender Amount -> "+v.getTamount());
					  if(v.isStatus()) {
						  System.out.println("Tender status -> Assigned");  
					  }else {
						  System.out.println("Tender status -> Pending"); 
					  }
					 if(v.getBidid()==0) {
						 System.out.println("Tender final bid --> NA");
						 System.out.println("Vender Id who assigned for tender --> NA");
					 }else {
						 System.out.println("Tender final bid Id --> "+v.getBidid());
						 System.out.println("Vender Id who assigned for tender --> "+v.getVendorid());
					 }
					  System.out.println("---------------------------------");
				  }
				} catch (TenderException e) {
					// TODO Auto-generated catch block
				    System.out.println(e.getMessage());
				}
				 
				 VendorFunctionalities.Choise();
			  
			  break;
			  
		  case 2: 
			  
			  System.out.println("Please fill All the Details of Your Bid..");
			  
				 System.out.println("Tender Id for which you want to fill bid..");
				 int tid = sc.nextInt();
				 System.out.println("Bid Amount...");
				 int amount = sc.nextInt();
				 sc.nextLine();
				
				 BidBean t1 = new BidBean(amount,tid,vid);
				  
				 try {
					String mass = ad.addNewBid(t1);
					if(mass!=null) {
						System.out.println(mass);
						VendorFunctionalities.Choise();
					}
				} catch (BidException e) {
					// TODO Auto-generated catch block
				   System.out.println(e.getMessage());
				   VendorFunctionalities.Choise();
				   
				}
				 
				 break;
			  
			  
		  case 3:
			  System.out.println("Select Bid Id which status you want to see...");
			  int bid = sc.nextInt();
			  
			  try {
				BidBean bd = ad.getStatusOfBid(bid);
				System.out.println("Here, All the Detail of bid..");
				System.out.println("Bid Id --> "+bd.getBid());
				System.out.println("Bid Amount --> "+bd.getBamount());
				if(bd.getBstatus()==null) {
					System.out.println("Bid Status --> Pending");
				}else {
				System.out.println("Bid Status -->"+bd.getBstatus());
				}
				System.out.println("Bid Tender Id --> "+bd.getTid());
				System.out.println("Bid Vendor Id --> "+bd.getVid());
				 
				
			} catch (BidException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			  
			  VendorFunctionalities.Choise();
			  
			  break;
			  
		  case 4: 
			  
			try {
				List<BidBean> bd = ad.getBidOFVendor(vid);
				for(BidBean bd1 : bd) {
				System.out.println("Here, All the Detail of Your bids..");
				System.out.println("Bid Id --> "+bd1.getBid());
				System.out.println("Bid Amount --> "+bd1.getBamount());
				if(bd1.getBstatus()==null) {
					System.out.println("Bid Status --> Pending");
				}else {
				System.out.println("Bid Status -->"+bd1.getBstatus());
				}
				System.out.println("Bid Tender Id --> "+bd1.getTid());
				System.out.println("Bid Vendor Id --> "+bd1.getVid());
				System.out.println("----------------------------------------");
				}
			} catch (BidException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			  
			 VendorFunctionalities.Choise();
			  
			  break;
			  
		  case 5: 
			  
			  System.out.println("Thanks for visiting us, Have a nice day..");
		    	
			  break;
		  }
		  
	  }
	  
	  static void Choise() {
			 Scanner sc = new Scanner(System.in);
			 System.out.println("Please Put YES if want to More operations OR No for exit...");
				String res = sc.nextLine();
				res = res.toUpperCase();
				if(res.equals("YES")) {
				 AdminFunctionalities ad = new AdminFunctionalities();
				 ad.getFunctions();;
				}else {
					System.out.println("Thanks visiting us, Have a nice day..");
				}
				sc.close();
		 }
}
