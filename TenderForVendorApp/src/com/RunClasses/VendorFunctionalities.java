package com.RunClasses;

import java.util.List;
import java.util.Scanner;

import com.BeanClasses.BidBean;
import com.BeanClasses.TenderBean;
import com.ColorAndModel.ConsoleColors;
import com.DaoImpl.DaoIntForVendor;
import com.DaoImpl.VendorInterfaceIMP;
import com.Exceptions.BidException;
import com.Exceptions.TenderException;

public class VendorFunctionalities {
       static int vdid = 0;
	  public void getfunctionalities(int vid) {
		   vdid = vid;
		  Scanner sc = new Scanner(System.in);
		  System.out.println();
		  System.out.println(ConsoleColors.GREEN+"Welcome again, Lot of functionalities are there for you.."+ConsoleColors.WHITE);
		  System.out.println(ConsoleColors.BANANA_YELLOW+"You can perform any operation by putting 1 to 5 at Bottom."+ConsoleColors.WHITE);
		  System.out.println("1. View all the Tenders.");
		  System.out.println("2. Place a Bid against a Tender.");
		  System.out.println("3. View status of a Bid(Whether Selected or Not).");
		  System.out.println("4. View his own Bid History.");
		  System.out.println("5. Exit.");
		  
		  System.out.println(ConsoleColors.BANANA_YELLOW+"Put your choise here..."+ConsoleColors.WHITE);
		  
		  int res = sc.nextInt();
		  DaoIntForVendor ad = new VendorInterfaceIMP();
		  switch(res) {
		  
		  case 1: 
			          System.out.println();
			  try {
					List<TenderBean> list = ad.getAllTenders();
					  System.out.println(ConsoleColors.BANANA_YELLOW+"List of All the Tenders..."+ConsoleColors.WHITE);
				  for(TenderBean v : list) {
					
					  System.out.println(ConsoleColors.ORANGE+"Tender Id -> "+ConsoleColors.WHITE+v.getTid());        
					  System.out.println(ConsoleColors.ORANGE+"Tender Name -> "+ConsoleColors.WHITE+v.getTname());
					  System.out.println(ConsoleColors.ORANGE+"Tender Amount -> "+ConsoleColors.WHITE+v.getTamount());
					  if(v.isStatus()) {
						  System.out.println(ConsoleColors.ORANGE+"Tender status ->"+ConsoleColors.WHITE+" Assigned");  
					  }else {
						  System.out.println(ConsoleColors.ORANGE+"Tender status ->"+ConsoleColors.WHITE+" Pending"); 
					  }
					 if(v.getBidid()==0) {
						 System.out.println(ConsoleColors.ORANGE+"Tender final bid -->"+ConsoleColors.WHITE+" NA");
						 System.out.println(ConsoleColors.ORANGE+"Vender Id who assigned for tender -->"+ConsoleColors.WHITE+" NA");
					 }else {
						 System.out.println(ConsoleColors.ORANGE+"Tender final bid Id --> "+ConsoleColors.WHITE+v.getBidid());
						 System.out.println(ConsoleColors.ORANGE+"Vender Id who assigned for tender --> "+ConsoleColors.WHITE+v.getVendorid());
					 }
					  System.out.println(ConsoleColors.CYAN+"---------------------------------"+ConsoleColors.WHITE);
				  }
				} catch (TenderException e) {
					// TODO Auto-generated catch block
				    System.out.println(ConsoleColors.RED+e.getMessage()+ConsoleColors.WHITE);
				}
				 
				 VendorFunctionalities.Choise();
			  
			  break;
			  
		  case 2: 
			  System.out.println();
			  System.out.println(ConsoleColors.BANANA_YELLOW+"Please fill All the Details of Your Bid.."+ConsoleColors.WHITE);
			  
				 System.out.println(ConsoleColors.CYAN+"Tender Id for which you want to fill bid..");
				 int tid = sc.nextInt();
				 System.out.println("Bid Amount..."+ConsoleColors.WHITE);
				 int amount = sc.nextInt();
				 sc.nextLine();
				
				 BidBean t1 = new BidBean(amount,tid,vid);
				  
				 try {
					String mass = ad.addNewBid(t1);
					if(mass!=null) {
						System.out.println(ConsoleColors.GREEN+mass+ConsoleColors.WHITE);
						VendorFunctionalities.Choise();
					}
				} catch (BidException e) {
					// TODO Auto-generated catch block
					System.out.println();
				   System.out.println(ConsoleColors.RED+e.getMessage()+ConsoleColors.WHITE);
				   VendorFunctionalities.Choise();
				   
				}
				 
				 break;
			  
			  
		  case 3:
			  System.out.println();
			  System.out.println(ConsoleColors.CYAN+"Select Bid Id which status you want to see...");
			  int bid = sc.nextInt();
			  
			  try {
				BidBean bd = ad.getStatusOfBid(bid);
				System.out.println(ConsoleColors.BANANA_YELLOW+"Here, All the Detail of bid.."+ConsoleColors.WHITE);
				System.out.println(ConsoleColors.ORANGE+"Bid Id --> "+ConsoleColors.WHITE+bd.getBid());
				System.out.println(ConsoleColors.ORANGE+"Bid Amount --> "+ConsoleColors.WHITE+bd.getBamount());
				if(bd.getBstatus()==null) {
					System.out.println(ConsoleColors.ORANGE+"Bid Status -->"+ConsoleColors.WHITE+" Pending");
				}else {
				System.out.println(ConsoleColors.ORANGE+"Bid Status -->"+ConsoleColors.WHITE+bd.getBstatus());
				}
				System.out.println(ConsoleColors.ORANGE+"Bid Tender Id --> "+ConsoleColors.WHITE+bd.getTid());
				System.out.println(ConsoleColors.ORANGE+"Bid Vendor Id --> "+ConsoleColors.WHITE+bd.getVid());
				 
				
			} catch (BidException e) {
				// TODO Auto-generated catch block
				System.out.println();
				System.out.println(ConsoleColors.RED+e.getMessage()+ConsoleColors.WHITE);
			}
			  
			  VendorFunctionalities.Choise();
			  
			  break;
			  
		  case 4: 
			    System.out.println();
			try {
				List<BidBean> bd = ad.getBidOFVendor(vid);
				for(BidBean bd1 : bd) {
				System.out.println(ConsoleColors.BANANA_YELLOW+"Here, All the Detail of Your bids.."+ConsoleColors.WHITE);
				System.out.println(ConsoleColors.ORANGE+"Bid Id --> "+ConsoleColors.WHITE+bd1.getBid());
				System.out.println(ConsoleColors.ORANGE+"Bid Amount --> "+ConsoleColors.WHITE+bd1.getBamount());
				if(bd1.getBstatus()==null) {
					System.out.println(ConsoleColors.ORANGE+"Bid Status -->"+ConsoleColors.WHITE+" Pending");
				}else {
				System.out.println(ConsoleColors.ORANGE+"Bid Status -->"+ConsoleColors.WHITE+bd1.getBstatus());
				}
				System.out.println(ConsoleColors.ORANGE+"Bid Tender Id --> "+ConsoleColors.WHITE+bd1.getTid());
				System.out.println(ConsoleColors.ORANGE+"Bid Vendor Id --> "+ConsoleColors.WHITE+bd1.getVid());
				System.out.println("----------------------------------------");
				}
			} catch (BidException e) {
				// TODO Auto-generated catch block
				System.out.println();
				System.out.println(ConsoleColors.RED+e.getMessage()+ConsoleColors.WHITE);
			}
			  
			 VendorFunctionalities.Choise();
			  
			  break;
			  
		  case 5: 
			  System.out.println();
			  System.out.println(ConsoleColors.ORANGE+"Thanks for visiting us, Have a nice day.."+ConsoleColors.WHITE);
		    	
			  break;
			  
		  default :
			  System.out.println(ConsoleColors.RED+"Worng Option, Please fill right Option."+ConsoleColors.WHITE);
			  VendorFunctionalities.Choise();
		  }
		  
		 sc.close();
		  
	  }
	  
	  static void Choise() {
			 Scanner sc = new Scanner(System.in);
			 System.out.println(ConsoleColors.BANANA_YELLOW+"Please Put YES if want to perform More operations OR No for exit..."+ConsoleColors.WHITE);
				String res = sc.nextLine();
				res = res.toUpperCase();
				if(res.equals("YES")) {
				 VendorFunctionalities ad = new VendorFunctionalities();
				 ad.getfunctionalities(vdid);;;
				}else {
					System.out.println();
					System.out.println(ConsoleColors.ORANGE+"Thanks for visiting us, Have a nice day.."+ConsoleColors.WHITE);
				}
				sc.close();
		 }
}
