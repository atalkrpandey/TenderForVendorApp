package com.RunClasses;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to you @ India's one of the best tendering app TenderForVendor..");
		System.out.println("Please select one of the option like for Admin login --> 1..");
		System.out.println("1. Admin login");
		System.out.println("2. Vender login");
		System.out.println("3. Exit");
		int res = sc.nextInt();
		if(res==3) {
			System.out.println("Thanks for Visiting us. We look forward to visiting you again. Have a nice day...");
		}else if(res ==2) {
			
		}else {
			AdminLog ad = new AdminLog();
			ad.AdminLogin();
		}
		
		sc.close();
		
		
	}
}
