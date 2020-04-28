package com.mindtree.phoneapps.service;

import java.util.Map;
import java.util.Set;

import com.mindtree.phoneapps.enity.App;
import com.mindtree.phoneapps.enity.Phone;
import com.mindtree.phoneapps.exception.serviceexception.PhoneAppsApplicationServiceException;

public interface PhoneAppService {

	int addPhone(String phonename, String brandname) throws PhoneAppsApplicationServiceException;

	int addAppsToPhone(String phonename, String appname, String appsize) throws PhoneAppsApplicationServiceException;

	Set<App> getAllAppsSorted(String phonename) throws PhoneAppsApplicationServiceException;

	Map<Integer, Integer> getPhoneWithHighestApps() throws PhoneAppsApplicationServiceException;

	Map<Phone, App> getAllPhones() throws PhoneAppsApplicationServiceException;

	Map<Phone, App> deletePhone(String pname) throws PhoneAppsApplicationServiceException;

}
