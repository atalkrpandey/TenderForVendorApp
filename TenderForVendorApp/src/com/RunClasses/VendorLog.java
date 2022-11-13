package com.RunClasses;

import java.util.Scanner;

import com.BeanClasses.VendorBean;
import com.ColorAndModel.ConsoleColors;
import com.DaoImpl.DaoIntForVendor;
import com.DaoImpl.VendorInterfaceIMP;
import com.Exceptions.VendorAuthenticateException;

public class VendorLog {

	public void login() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println(ConsoleColors.BANANA_YELLOW+"Please Login your Account first.. "+ConsoleColors.WHITE);
		System.out.println();
		System.out.println("Enter your UserName here...");
		String name = sc.nextLine();
		System.out.println("Enter your password here...");
		String password = sc.nextLine();
		
		DaoIntForVendor da = new VendorInterfaceIMP();
		try {
			int vid = da.loginNewVendor(new VendorBean(name, password));
			if(vid != -1) {
				;
				VendorFunctionalities a1 = new VendorFunctionalities();
				a1.getfunctionalities(vid);;
				
			}
		} catch (VendorAuthenticateException e) {
			// TODO Auto-generated catch block
			System.out.println(ConsoleColors.RED+e.getMessage()+ConsoleColors.WHITE);
			System.out.println(ConsoleColors.BANANA_YELLOW+"Please Enter YES if want to try again OR No for exit..."+ConsoleColors.WHITE);
			String res = sc.nextLine();
			res = res.toUpperCase();
			if(res.equals("YES")) {
			 VendorLog ad = new VendorLog();
			 ad.login();
			}else {
				System.out.println(ConsoleColors.ORANGE+"Thanks for Visiting us. We look forward to visiting you again. Have a nice day..."+ConsoleColors.WHITE);
			}
		}
		sc.close();
	}
	
}
