package com.RunClasses;

import java.util.Scanner;

import com.DaoImpl.DaoIntForAdmin;
import com.DaoImpl.ImplOfAdminInf;
import com.Exceptions.AdminSignInException;

public class AdminLog {

	
	public void AdminLogin() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Login your Account first.. ");
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
			System.out.println(e.getMessage());
			System.out.println("Please Put YES if want to try again OR No for exit...");
			String res = sc.nextLine();
			res = res.toUpperCase();
			if(res.equals("YES")) {
			 AdminLog ad = new AdminLog();
			 ad.AdminLogin();
			}else {
				System.out.println("Thanks visiting, Have a nice day..");
			}
		}
		sc.close();
	}
}
