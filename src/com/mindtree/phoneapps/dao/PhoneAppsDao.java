package com.mindtree.phoneapps.dao;

import java.util.Map;
import java.util.Set;

import com.mindtree.phoneapps.enity.App;
import com.mindtree.phoneapps.enity.Phone;
import com.mindtree.phoneapps.exception.daoexception.PhoneAppsApplicationDaoException;

public interface PhoneAppsDao {

	int insertPhone(Phone phone) throws PhoneAppsApplicationDaoException;

	boolean validatePhoneFromDataBase(String phonename) throws PhoneAppsApplicationDaoException;

	int getPhoneId(String phonename) throws PhoneAppsApplicationDaoException;

	int insertApp(App app,int getid) throws PhoneAppsApplicationDaoException;

	int getAppId(String appname) throws PhoneAppsApplicationDaoException;

	Set<App> getAllApps(int getid) throws PhoneAppsApplicationDaoException;

	Map<Integer, Integer> getHighestApps() throws PhoneAppsApplicationDaoException;

	Map<Phone, App> getPhones() throws PhoneAppsApplicationDaoException;

	Map<Phone, App> deletePhoneFromDataBase(int getid) throws PhoneAppsApplicationDaoException;

	

}
