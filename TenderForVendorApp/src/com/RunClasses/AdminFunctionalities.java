package com.RunClasses;

import java.util.Scanner;

import com.BeanClasses.VendorBean;
import com.BeanClasses.VendorExpection;
import com.DaoImpl.DaoIntForAdmin;
import com.DaoImpl.ImplOfAdminInf;

public class AdminFunctionalities {

	 public void getFunctions() {
		 
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
				}
			} catch (VendorExpection e) {
				// TODO Auto-generated catch block
			   System.out.println(e.getMessage());
			   AdminFunctionalities.Choise();
			   
			}
			 
			 break;
		 case 2 :  
			 break;
		 case 3 :  
			 break;
		 case 4 :  
			 break;
		 case 5 :  
			 break;
	     case 6 :  
			 break;
	     case 7 :
	    	 System.out.println("Thanks visiting, Have a nice day..");
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
				System.out.println("Thanks visiting, Have a nice day..");
			}
			sc.close();
	 }
}
