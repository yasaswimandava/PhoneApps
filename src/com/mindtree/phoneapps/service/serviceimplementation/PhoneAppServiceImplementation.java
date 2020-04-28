package com.mindtree.phoneapps.service.serviceimplementation;

import java.util.Map;
import java.util.Set;

import com.mindtree.phoneapps.dao.PhoneAppsDao;
import com.mindtree.phoneapps.dao.daoimplementation.PhoneAppsDaoImplementation;
import com.mindtree.phoneapps.enity.App;
import com.mindtree.phoneapps.enity.Phone;
import com.mindtree.phoneapps.exception.daoexception.PhoneAppsApplicationDaoException;
import com.mindtree.phoneapps.exception.serviceexception.PhoneAppsApplicationServiceException;
import com.mindtree.phoneapps.exception.serviceexception.customexception.AppsDoesnotFOundException;
import com.mindtree.phoneapps.exception.serviceexception.customexception.PhoneDoesnotFoundException;
import com.mindtree.phoneapps.exception.serviceexception.customexception.PhonesDoesntExistInDataBase;
import com.mindtree.phoneapps.service.PhoneAppService;

public class PhoneAppServiceImplementation implements PhoneAppService {
	PhoneAppsDao database = new PhoneAppsDaoImplementation();

	@Override
	public int addPhone(String phonename, String brandname) throws PhoneAppsApplicationServiceException {
		int result;
		Phone phone = new Phone(phonename, brandname);
		try {
			result = database.insertPhone(phone);
		} catch (PhoneAppsApplicationDaoException e) {
			throw new PhoneAppsApplicationServiceException(e);
		}

		return result;
	}

	@Override
	public int addAppsToPhone(String phonename, String appname, String appsize)
			throws PhoneAppsApplicationServiceException {
		boolean flag = false;
		int getid;
		int result = 0;
		try {
			flag = database.validatePhoneFromDataBase(phonename);
			if (flag == true) {
				getid = database.getPhoneId(phonename);
				if (getid == 0) {
					throw new PhoneDoesnotFoundException("phone not found");
				}
				App app = new App(appname, appsize);
				result = database.insertApp(app, getid);

			}
		} catch (PhoneAppsApplicationDaoException e) {
			throw new PhoneAppsApplicationServiceException(e);

		}
		return result;
	}

	@Override
	public Set<App> getAllAppsSorted(String phonename) throws PhoneAppsApplicationServiceException {
		int getid;
		Set<App> temp;
		try {
			boolean flag = database.validatePhoneFromDataBase(phonename);
			if (flag = true) {
				getid = database.getPhoneId(phonename);
				temp = database.getAllApps(getid);
				if (temp.isEmpty()) {
					throw new AppsDoesnotFOundException("phone doesnt have any apps");
				}
			} else {
				throw new PhoneDoesnotFoundException("phone doesnot exists");
			}
		} catch (PhoneAppsApplicationDaoException | PhoneDoesnotFoundException | AppsDoesnotFOundException e) {
			throw new PhoneAppsApplicationServiceException(e);
		}

		return temp;
	}

	@Override
	public Map<Integer, Integer> getPhoneWithHighestApps() throws PhoneAppsApplicationServiceException {
		Map<Integer, Integer> tempresult;
		try {
			tempresult = database.getHighestApps();
		} catch (PhoneAppsApplicationDaoException e) {
			throw new PhoneAppsApplicationServiceException(e);
		}
		return tempresult;
	}

	@Override
	public Map<Phone, App> getAllPhones() throws PhoneAppsApplicationServiceException {
		Map<Phone,App> resulttemp;
		try {
			resulttemp=database.getPhones();
			if(resulttemp.isEmpty())
			{
				throw new PhonesDoesntExistInDataBase("phones doesnt exist");
			}
		} catch (PhoneAppsApplicationDaoException e) {
			throw new PhoneAppsApplicationServiceException(e);
		}
		
		return resulttemp;
	}

	@Override
	public Map<Phone, App> deletePhone(String pname) throws PhoneAppsApplicationServiceException {
		Map<Phone,App> resulttemp;
		int getid;
		try {
			getid = database.getPhoneId(pname);
			if(getid==0)
			{
				throw new PhoneDoesnotFoundException();
			}
			resulttemp=database.deletePhoneFromDataBase(getid);
		} catch (PhoneAppsApplicationDaoException |PhoneDoesnotFoundException e) {
			throw new PhoneAppsApplicationServiceException(e);
		}
		
		return resulttemp;
	}
}
