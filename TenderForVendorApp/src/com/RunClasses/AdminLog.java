package com.RunClasses;

import java.util.Scanner;

import com.ColorAndModel.ConsoleColors;
import com.DaoImpl.DaoIntForAdmin;
import com.DaoImpl.ImplOfAdminInf;
import com.Exceptions.AdminSignInException;

public class AdminLog {

	
	public void AdminLogin() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println(ConsoleColors.BANANA_YELLOW+"Please Login your Account first.. "+ConsoleColors.WHITE);
		System.out.println();
		System.out.println("Put your email Id here...");
		String email = sc.nextLine();
		System.out.println("Put your password here...");
		String password = sc.nextLine();
		
		DaoIntForAdmin da = new ImplOfAdminInf();
		try {
			String massage = da.loginAdmin(email, password);
			if(massage!=null) {
				System.out.println(massage);
				AdminFunctionalities a1 = new AdminFunctionalities();
				a1.getFunctions();
				
			}
		} catch (AdminSignInException e) {
			// TODO Auto-generated catch block
			System.out.println(ConsoleColors.RED+e.getMessage()+ConsoleColors.WHITE);
			System.out.println(ConsoleColors.BANANA_YELLOW+"Please Put YES if want to try again OR No for exit..."+ConsoleColors.WHITE);
			String res = sc.nextLine();
			res = res.toUpperCase();
			if(res.equals("YES")) {
			 AdminLog ad = new AdminLog();
			 ad.AdminLogin();
			}else {
				System.out.println();
				System.out.println(ConsoleColors.ORANGE+"Thanks visiting, Have a nice day.."+ConsoleColors.WHITE);
			}
		}
		sc.close();
	}
}
