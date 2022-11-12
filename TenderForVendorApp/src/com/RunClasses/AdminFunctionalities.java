package com.RunClasses;


import java.util.List;
import java.util.Scanner;

import com.BeanClasses.BidBean;
import com.BeanClasses.BidTenderDTO;
import com.BeanClasses.TenderBean;
import com.BeanClasses.VendorBean;
import com.ColorAndModel.ConsoleColors;
import com.DaoImpl.DaoIntForAdmin;
import com.DaoImpl.ImplOfAdminInf;
import com.Exceptions.BidException;
import com.Exceptions.TenderException;
import com.Exceptions.VendorException;

public class AdminFunctionalities {

	 public void getFunctions()  {
		 
		 Scanner sc = new Scanner(System.in);
		 
		 System.out.println(ConsoleColors.BANANA_YELLOW+"Select the Categories by serial No.(like 1,2,3..) Which kind of operation you want to perform..."+ConsoleColors.WHITE);
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
			 
			 System.out.println();
			 System.out.println(ConsoleColors.BANANA_YELLOW+"Please fill All the Details of Vendor.."+ConsoleColors.WHITE);
			 System.out.println(ConsoleColors.CYAN+"Vendor Name..");
			 String name = sc.nextLine();
			 System.out.println("Vendor email ID...");
			 String email = sc.nextLine();
			 System.out.println("Vendor Phone NO...");
			 String phone = Long.toString(sc.nextLong());
			 sc.nextLine();
			 System.out.println(ConsoleColors.RED_ITALIC+"Note: Password should have atleast one character, one number, one special character and minimum password length 8."+ConsoleColors.CYAN);
			 System.out.println("Vendor New Password..."+ConsoleColors.WHITE);
			 boolean flag = false;
			 String pass = null;
			 while(flag!=true) {
			 pass = sc.nextLine();
			
			  flag = pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
			 if(flag==false) {
				 System.out.println(ConsoleColors.RED_BRIGHT+"Invalid Password, Please follow the instructions and try again..."+ConsoleColors.CYAN);
			 }
			 }
			 VendorBean v1 = new VendorBean(0,name,email,phone,pass);
			  
			 try {
				String mass = ad.addNewVendor(v1);
				if(mass!=null) {
					System.out.println(ConsoleColors.GREEN+mass+ConsoleColors.WHITE);
					AdminFunctionalities.choice();
				}
			} catch (VendorException e) {
				// TODO Auto-generated catch block
			   System.out.println(ConsoleColors.RED+e.getMessage()+ConsoleColors.WHITE);
			   AdminFunctionalities.choice();
			   
			}
			  break;
			  
			  
		 case 2 : 
			      System.out.println();
			 try {
				List<VendorBean> list = ad.getAllVendors();
				
				  System.out.println(ConsoleColors.BANANA_YELLOW+"List of all the Vendors..."+ConsoleColors.WHITE);
			  for(VendorBean v : list) {
				
				  System.out.println(ConsoleColors.ORANGE+"Vendor Id -> "+ConsoleColors.WHITE+v.getVid());
				  System.out.println(ConsoleColors.ORANGE+"Vendor Name -> "+ConsoleColors.WHITE+v.getVname());
				  System.out.println(ConsoleColors.ORANGE+"Vendor Email Id -> "+ConsoleColors.WHITE+v.getVemail());
				  System.out.println(ConsoleColors.ORANGE+"Vendor Phone -> "+ConsoleColors.WHITE+v.getVphone());
				  System.out.println(ConsoleColors.ORANGE+"Vendor Password -> "+ConsoleColors.WHITE+v.getVpass());
				  System.out.println(ConsoleColors.CYAN+"---------------------------------"+ConsoleColors.WHITE);
			  }
			} catch (VendorException e) {
				// TODO Auto-generated catch block
			    System.out.println(ConsoleColors.RED+e.getMessage()+ConsoleColors.WHITE);
			}
			 
			 AdminFunctionalities.choice();
			 
			 break;
			 
			 
			 
		 case 3 : 
			 System.out.println();
			 System.out.println(ConsoleColors.BANANA_YELLOW+"Please, Fill All the Details of Tender.."+ConsoleColors.WHITE);
			 System.out.println(ConsoleColors.CYAN+"Tender Name..");
			 String tname = sc.nextLine();
			 System.out.println("Tender Amount..."+ConsoleColors.WHITE);
			 int amount = sc.nextInt();
			 sc.nextLine();
			
			 TenderBean t1 = new TenderBean(tname,amount);
			  
			 try {
				String mass = ad.createNewTender(t1);
				if(mass!=null) {
					System.out.println(ConsoleColors.GREEN+mass+ConsoleColors.WHITE);
					AdminFunctionalities.choice();
				}
			} catch (TenderException e) {
				// TODO Auto-generated catch block
			   System.out.println(ConsoleColors.RED+e.getMessage()+ConsoleColors.WHITE);
			   AdminFunctionalities.choice();
			   
			}
			 
			 break;
			 
			 
		 case 4 : 
			          System.out.println();
			 try {
					List<TenderBean> list = ad.getAllTenders();
					  System.out.println(ConsoleColors.BANANA_YELLOW+"List of Tenders..."+ConsoleColors.WHITE);
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
				 
				 AdminFunctionalities.choice();
			 
			 
			 break;
			 
			 
		 case 5 :  
			  System.out.println();
			  System.out.println(ConsoleColors.CYAN+"Plese, Enter the TenderId for which you want to find the bids.."+ConsoleColors.WHITE);
			  int tid = sc.nextInt();
			 
			 try {
					List<BidBean> list = ad.getBidsOfTender(tid);
					System.out.println();
					System.out.println(ConsoleColors.BANANA_YELLOW+"List of Bids for Tender Id ->"+tid+ConsoleColors.WHITE);
				  for(BidBean v : list) {
					
					  System.out.println(ConsoleColors.ORANGE+"Bid Id -> "+ConsoleColors.WHITE+v.getBid());        
					  
					  System.out.println(ConsoleColors.ORANGE+"Bid Amount -> "+ConsoleColors.WHITE+v.getBamount());
					  if(v.getBstatus()!=null) {
						  System.out.println(ConsoleColors.ORANGE+"Bid status -> "+ConsoleColors.WHITE+" Selected");  
					  }else {
						  System.out.println(ConsoleColors.ORANGE+"Bid status -> "+ConsoleColors.WHITE+" Pending"); 
					  }
					
						 System.out.println(ConsoleColors.ORANGE+"Tender Id of Bid --> "+ConsoleColors.WHITE+v.getTid());
						 System.out.println(ConsoleColors.ORANGE+"Vender Id of Bid --> "+ConsoleColors.WHITE+v.getVid());
					
					  System.out.println(ConsoleColors.CYAN+"---------------------------------"+ConsoleColors.WHITE);
				  }
				} catch (BidException e) {
					// TODO Auto-generated catch block
				    System.out.println(ConsoleColors.RED+e.getMessage()+ConsoleColors.WHITE);
				}
				 
				 AdminFunctionalities.choice();
			 
			 break;
			 
			 
	     case 6 : 
	    	 System.out.println();
	    	 System.out.println(ConsoleColors.CYAN+"Please, Enter the Tender Id for which you want to assign bid..."+ConsoleColors.WHITE);
	    	 int id = sc.nextInt();
			try {
				BidTenderDTO bt = ad.assignVendor(id);
			
				System.out.println();
				System.out.println( ConsoleColors.BANANA_YELLOW+"Tender Assigned successfully. All details releted to that are here..."+ConsoleColors.WHITE);
				
				System.out.println(ConsoleColors.ORANGE+"Tender Id --> "+ConsoleColors.WHITE+bt.getTenderid());
				System.out.println(ConsoleColors.ORANGE+"Tender Name --> "+ConsoleColors.WHITE+bt.getTname());
				System.out.println(ConsoleColors.ORANGE+"Tender Amount --> "+ConsoleColors.WHITE+bt.getTamount());
				System.out.println(ConsoleColors.ORANGE+"Allotted Bid Id --> "+ConsoleColors.WHITE+bt.getBidid());
				System.out.println(ConsoleColors.ORANGE+"Selected Bid Amount --> "+ConsoleColors.WHITE+bt.getBidamount());
				System.out.println(ConsoleColors.ORANGE+"Allotted Vendor Id --> "+ConsoleColors.WHITE+bt.getVendorid());
				System.out.println(ConsoleColors.ORANGE+"Allotted Vendor Name --> "+ConsoleColors.WHITE+bt.getVname());
			} catch (TenderException e) {
				// TODO Auto-generated catch block
				System.out.println();
				  System.out.println(ConsoleColors.RED+e.getMessage()+ConsoleColors.RED);
			}
	    	 
			 AdminFunctionalities.choice();
			 
			 break;
			 
			 
			 
	     case 7 :
	    	 
	    	 System.out.println(ConsoleColors.ORANGE+"Thanks for visiting us, Have a nice day.."+ConsoleColors.WHITE);
	    	 break;
	     default :
		    	
		    	System.out.println(ConsoleColors.RED+"Sorry, Option did not found. Please Enter Right option.."+ConsoleColors.WHITE);
		    	 AdminFunctionalities.choice();
		    	 break;
		 }
		 
		 sc.close();
	 }
	 
	 static void choice() {
		 Scanner sc = new Scanner(System.in);
		 System.out.println(ConsoleColors.BANANA_YELLOW+"Please, Enter YES if want to More operations OR No for exit..."+ConsoleColors.WHITE);
			String res = sc.nextLine();
			res = res.toUpperCase();
			if(res.equals("YES")) {
			 AdminFunctionalities ad = new AdminFunctionalities();
			 ad.getFunctions();;
			}else {
				System.out.println(ConsoleColors.ORANGE+"Thanks for visiting us, Have a nice day.."+ConsoleColors.WHITE);
			}
			sc.close();
	 }
}
