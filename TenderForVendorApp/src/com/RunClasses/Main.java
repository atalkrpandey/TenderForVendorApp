package com.RunClasses;

import java.util.Scanner;

import com.ColorAndModel.ConsoleColors;

public class Main {

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(ConsoleColors.ORANGE+"Welcome to you @ India's one of the best Tender Management App TenderForVendor.."+ConsoleColors.WHITE);
		System.out.println();
		System.out.println(ConsoleColors.BANANA_YELLOW+"Please select one of the option like for Admin login --> 1.."+ConsoleColors.WHITE);
		System.out.println("1. Admin login");
		System.out.println("2. Vender login");
		System.out.println("3. Exit");
		int res = sc.nextInt();
		if(res==3) {
			System.out.println();
			System.out.println(ConsoleColors.ORANGE+"Thanks for Visiting us. We look forward to visiting you again. Have a nice day..."+ConsoleColors.WHITE);
		}else if(res ==2) {
			
			VendorLog v = new VendorLog();
			v.login();
			
			
		}else {
			AdminLog ad = new AdminLog();
			ad.AdminLogin();
		}
		
		sc.close();
		
		
	}
}
