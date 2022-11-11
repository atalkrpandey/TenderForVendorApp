package com.RunClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.BeanClasses.BidBean;
import com.BeanClasses.BidTenderDTO;
import com.BeanClasses.TenderBean;
import com.BeanClasses.VendorBean;
import com.ColorAndModel.TableModelForBidTender;
import com.DaoImpl.DaoIntForAdmin;
import com.DaoImpl.ImplOfAdminInf;
import com.Exceptions.BidException;
import com.Exceptions.TenderException;
import com.Exceptions.VendorException;

public class AdminFunctionalities {

	 public void getFunctions()  {
		 
		 Scanner sc = new Scanner(System.in);
		 
		 System.out.println("Select the Categories by serial No.(like 1,2,3..) Which kind of operation you want to perform...");
		 System.out.println("1. Register new Vendor.");
		 System.out.println("2. View all the vendors.");
		 System.out.println("3. Create new tenders.");
		 System.out.println("4. View All the Tenders.");
		 System.out.println("5. View All the Bids of a tender.");
		 System.out.println("6. Assign tender to a vendor.");
		 System.out.println("7. Exit.");
		 
		 int res = sc.nextInt();
		 sc.nextLine();
		  
		  DaoIntForAdmin ad = new ImplOfAdminInf();
		 switch(res) {
		 
		
		 case 1 : 
			 
			 
			 System.out.println("Please fill All the Details of Vendor..");
			 System.out.println("Vendor Name..");
			 String name = sc.nextLine();
			 System.out.println("Vendor email ID...");
			 String email = sc.nextLine();
			 System.out.println("Vendor Phone NO...");
			 String phone = sc.nextLine();
			 System.out.println("Vendor New Password...");
			 String pass = sc.nextLine();
			 VendorBean v1 = new VendorBean(0,name,email,phone,pass);
			  
			 try {
				String mass = ad.addNewVendor(v1);
				if(mass!=null) {
					System.out.println(mass);
					AdminFunctionalities.Choise();
				}
			} catch (VendorException e) {
				// TODO Auto-generated catch block
			   System.out.println(e.getMessage());
			   AdminFunctionalities.Choise();
			   
			}
			  break;
			  
			  
		 case 2 : 
			 
			 try {
				List<VendorBean> list = ad.getAllVendors();
				  System.out.println("Here, all the list of Vendors in system...");
			  for(VendorBean v : list) {
				
				  System.out.println("Vendor Id -> "+v.getVid());
				  System.out.println("Vendor Name -> "+v.getVname());
				  System.out.println("Vendor Email Id -> "+v.getVemail());
				  System.out.println("Vendor Phone -> "+v.getVphone());
				  System.out.println("Vendor Password -> "+v.getVpass());
				  System.out.println("---------------------------------");
			  }
			} catch (VendorException e) {
				// TODO Auto-generated catch block
			    System.out.println(e.getMessage());
			}
			 
			 AdminFunctionalities.Choise();
			 
			 break;
			 
			 
			 
		 case 3 : 
			 
			 System.out.println("Please fill All the Details of Tender..");
			 System.out.println("Tender Name..");
			 String tname = sc.nextLine();
			 System.out.println("Tender Amount...");
			 int amount = sc.nextInt();
			 sc.nextLine();
			
			 TenderBean t1 = new TenderBean(tname,amount);
			  
			 try {
				String mass = ad.createNewTender(t1);
				if(mass!=null) {
					System.out.println(mass);
					AdminFunctionalities.Choise();
				}
			} catch (TenderException e) {
				// TODO Auto-generated catch block
			   System.out.println(e.getMessage());
			   AdminFunctionalities.Choise();
			   
			}
			 
			 break;
			 
			 
		 case 4 : 
			 
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
				 
				 AdminFunctionalities.Choise();
			 
			 
			 break;
			 
			 
		 case 5 :  
			 
			  System.out.println("Plese put the TenderId for which you want to find the bids..");
			  int tid = sc.nextInt();
			 
			 try {
					List<BidBean> list = ad.getBidsOfTender(tid);
					System.out.println("Here, all the list of Bids which is bidded for Tender ->"+tid);
				  for(BidBean v : list) {
					
					  System.out.println("Bid Id -> "+v.getBid());        
					  
					  System.out.println("Bid Amount -> "+v.getBamount());
					  if(v.getBstatus()!=null) {
						  System.out.println("Bid status -> Selected");  
					  }else {
						  System.out.println("Bid status -> Pending"); 
					  }
					
						 System.out.println("Tender Id of Bid --> "+v.getTid());
						 System.out.println("Vender Id of Bid -->"+v.getVid());
					
					  System.out.println("---------------------------------");
				  }
				} catch (BidException e) {
					// TODO Auto-generated catch block
				    System.out.println(e.getMessage());
				}
				 
				 AdminFunctionalities.Choise();
			 
			 break;
			 
			 
	     case 6 : 
	    	 System.out.println();
	    	 System.out.println("Please put Vender Id for which you want to assign bid...");
	    	 int id = sc.nextInt();
			try {
				BidTenderDTO bt = ad.assignVendor(id);
			
				System.out.println();
				System.out.println( "Tender Assigned successfully. All details releted to that are here...");
				
				System.out.println("Tender Id --> "+bt.getTenderid());
				System.out.println("Tender Name --> "+bt.getTname());
				System.out.println("Tender Amount --> "+bt.getTamount());
				System.out.println("Allotted Bid Id --> "+bt.getBidid());
				System.out.println("Selected Bid Amount --> "+bt.getBidamount());
				System.out.println("Allotted Vendor Id --> "+bt.getVendorid());
			} catch (TenderException e) {
				// TODO Auto-generated catch block
				  System.out.println(e.getMessage());
			}
	    	 
			 AdminFunctionalities.Choise();
			 
			 break;
			 
			 
			 
	     case 7 :
	    	 
	    	 System.out.println("Thanks visiting us, Have a nice day..");
	    	 break;
	    default :
	    	
	    	System.out.println("Sorry, Option not found. Please put Right option..");
	    	 AdminFunctionalities.Choise();
		 }
		 
		 sc.close();
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
				System.out.println("Thanks for visiting us, Have a nice day..");
			}
			sc.close();
	 }
}
