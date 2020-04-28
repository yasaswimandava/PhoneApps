package com.mindtree.phoneapps.client;

import com.mindtree.phoneapps.enity.App;
import com.mindtree.phoneapps.enity.Phone;
import com.mindtree.phoneapps.exception.serviceexception.PhoneAppsApplicationServiceException;
import com.mindtree.phoneapps.service.PhoneAppService;
import com.mindtree.phoneapps.service.serviceimplementation.PhoneAppServiceImplementation;
import com.mindtree.phoneapps.util.PhoneAppsInputUtility;
import java.util.*;
import java.util.stream.Collectors;

public class PhoneAppsApplication {
	public static void main(String[] args) {
		PhoneAppService service = new PhoneAppServiceImplementation();
		boolean flag = true;
		do {
			System.out.println("***WELCOME TO PHONEAPP APPLICATION");
			System.out.println("ENTER OPTION TO PERFORM");
			System.out.println("************************");
			System.out.println("PRESS 1: TO ADD PHONE");
			System.out.println("PRESS 2: ADD APP TO PHONE");
			System.out.println("PRESS 3: SORT ALL APS IN DECENDING ORDER OF SIZE OF A PARTICULAR phone");
			System.out.println("PRESS 4: GET ALL THE PHONE ID WITH their APPS count");
			System.out.println("PRESS 5: GET ALLTHE PHONES");
			System.out.println("PRESS 6: DELETE A PHONE");
			System.out.println("PRESS 7: EXIT");
			String option = PhoneAppsInputUtility.acceptString();
			switch (option) {
			case "1": {
				System.out.println("enter phone name");
				String phonename = PhoneAppsInputUtility.acceptString();
				System.out.println("enter brand name");
				String brandname = PhoneAppsInputUtility.acceptString();
				try {
					int result = service.addPhone(phonename, brandname);
					if (result == 1) {
						System.out.println("phone added sucessfully");
					}
				} catch (PhoneAppsApplicationServiceException e) {
					System.out.println(e.getMessage());
				}
			}
				break;
			case "2": {
				System.out.println("enter phone name to add app details");
				String phonename = PhoneAppsInputUtility.acceptString();
				System.out.println("enter app name");
				String appname = PhoneAppsInputUtility.acceptString();
				System.out.println("enter app size");
				String appsize = PhoneAppsInputUtility.acceptString();
				try {
					int result = service.addAppsToPhone(phonename, appname, appsize);
					if (result == 1) {
						System.out.println("added sucessfully");
					}
				} catch (PhoneAppsApplicationServiceException e) {
					System.out.println(e.getMessage());
				}
			}
				break;
			case "3": {
				System.out.println("enter phone to get all apps sorted  according to appsize (decending order)");
				String phonename = PhoneAppsInputUtility.acceptString();
				try {
					Set<App> result = service.getAllAppsSorted(phonename);
					for (App app : result) {
						System.out.println(app);
					}
					System.out.println("*****flitering app size which is equal to 80mb******");
					Set<App> app = result.stream().filter(i -> i.getAppsize().equalsIgnoreCase("80mb"))
							.collect(Collectors.toSet());
					System.out.println(app);
				} catch (PhoneAppsApplicationServiceException e) {
					System.out.println(e.getMessage());
				}
			}
				break;
			case "4": {
				try {
					Map<Integer, Integer> temp = service.getPhoneWithHighestApps();
					temp.entrySet().forEach(map -> System.out.println(map.getKey() + "\t" + map.getValue()));

				} catch (PhoneAppsApplicationServiceException e) {
					System.out.println(e.getMessage());
				}
			}
				break;
			case "5": {
				try {
					Map<Phone, App> allphones = service.getAllPhones();
					allphones.entrySet().forEach(e -> System.out.println(e.getKey() + "\t" + e.getValue()));
				} catch (PhoneAppsApplicationServiceException e) {
					System.out.println(e.getMessage());
				}
			}
				break;
			case "6":
				System.out.println("enter phone name that you want to delete");
				String pname = PhoneAppsInputUtility.acceptString();
				try {
					Map<Phone, App> result = service.deletePhone(pname);
					System.out.println("phone is deleted and updated list is");
					result.entrySet().forEach(map -> System.out.println(map.getKey() + "\t" + map.getValue()));
				} catch (PhoneAppsApplicationServiceException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "7":
				flag = false;
				System.out.println("THANK YOU VISIT AGAIN");
			default:
				System.out.println("ENTER VALID OPTION");

			}
		} while (flag);

	}
}
