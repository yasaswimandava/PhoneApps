package com.mindtree.phoneapps.dao.daoimplementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.mindtree.phoneapps.dao.PhoneAppsDao;
import com.mindtree.phoneapps.enity.App;
import com.mindtree.phoneapps.enity.Phone;
import com.mindtree.phoneapps.exception.daoexception.PhoneAppsApplicationDaoException;
import com.mindtree.phoneapps.util.PhoneAppsJdbcConnectionInputUtility;

public class PhoneAppsDaoImplementation implements PhoneAppsDao {
	PhoneAppsJdbcConnectionInputUtility connect = new PhoneAppsJdbcConnectionInputUtility();

	@Override
	public int insertPhone(Phone phone) throws PhoneAppsApplicationDaoException {
		Connection con = PhoneAppsJdbcConnectionInputUtility.getConnection();
		CallableStatement call = null;
		try {
			call = con.prepareCall("{call insert_phone(?,?)}");
			call.setString(1, phone.getPhonename());
			call.setString(2, phone.getBrandname());
			call.executeUpdate();
		} catch (SQLException e) {
			throw new PhoneAppsApplicationDaoException(e);
		} finally {
			connect.closeResource(con);
			connect.closeResource(call);
		}
		return 1;
	}

	@Override
	public boolean validatePhoneFromDataBase(String phonename) throws PhoneAppsApplicationDaoException {
		Connection con = PhoneAppsJdbcConnectionInputUtility.getConnection();
		CallableStatement call = null;
		ResultSet rst = null;
		try {
			call = con.prepareCall("{call check_phone(?)}");
			call.setString(1, phonename);
			rst = call.executeQuery();
			while (rst.next()) {
				if (phonename.equalsIgnoreCase(rst.getString(1)))
					;
				return true;
			}
		} catch (SQLException e) {
			throw new PhoneAppsApplicationDaoException(e);
		} finally {
			connect.closeResource(con);
			connect.closeResource(rst);
			connect.closeResource(call);
		}
		return false;
	}

	@Override
	public int getPhoneId(String phonename) throws PhoneAppsApplicationDaoException {
		Connection con = PhoneAppsJdbcConnectionInputUtility.getConnection();
		CallableStatement call = null;
		ResultSet rst = null;
		int temp = 0;
		try {
			call = con.prepareCall("{call get_phoneid(?)}");
			call.setString(1, phonename);
			rst = call.executeQuery();
			while (rst.next()) {
				temp = rst.getInt(1);
			}

		} catch (SQLException e) {
			throw new PhoneAppsApplicationDaoException(e);
		} finally {
			connect.closeResource(con);
			connect.closeResource(rst);
			connect.closeResource(call);
		}
		return temp;
	}

	@Override
	public int insertApp(App app, int getid) throws PhoneAppsApplicationDaoException {
		Connection con = PhoneAppsJdbcConnectionInputUtility.getConnection();
		CallableStatement call = null;
		try {
			call = con.prepareCall("{call insert_app(?,?,?)}");
			call.setString(1, app.getAppname());
			call.setString(2, app.getAppsize());
			call.setInt(3, getid);
			call.executeUpdate();
		} catch (SQLException e) {
			throw new PhoneAppsApplicationDaoException(e);
		} finally {
			connect.closeResource(con);
			connect.closeResource(call);
		}

		return 1;
	}

	@Override
	public int getAppId(String appname) throws PhoneAppsApplicationDaoException {
		Connection con = PhoneAppsJdbcConnectionInputUtility.getConnection();
		CallableStatement call = null;
		ResultSet rst = null;
		int tmp = 0;
		try {
			call = con.prepareCall("{call get_appid(?)}");
			call.setString(1, appname);
			rst = call.executeQuery();
			while (rst.next()) {
				tmp = rst.getInt(1);
			}
		} catch (SQLException e) {
			throw new PhoneAppsApplicationDaoException(e);
		}
		return tmp;
	}

	@Override
	public Set<App> getAllApps(int getid) throws PhoneAppsApplicationDaoException {
		Connection con = PhoneAppsJdbcConnectionInputUtility.getConnection();
		CallableStatement call = null;
		ResultSet rst = null;
		Set<App> tem = new TreeSet<App>();
		try {
			call = con.prepareCall("{call get_apps(?)}");
			call.setInt(1, getid);
			rst = call.executeQuery();
			while (rst.next()) {
				App ap = new App();
				ap.setAppid(rst.getInt(1));
				ap.setAppname(rst.getString(2));
				ap.setAppsize(rst.getString(3));
				tem.add(ap);

			}
		} catch (SQLException e) {
			throw new PhoneAppsApplicationDaoException(e);
		}

		return tem;
	}

	@Override
	public Map<Integer, Integer> getHighestApps() throws PhoneAppsApplicationDaoException {
		Connection con = PhoneAppsJdbcConnectionInputUtility.getConnection();
		CallableStatement call = null;
		ResultSet rst = null;
		Map<Integer,Integer> temresult=new HashMap<Integer, Integer>();
		try {
			call=con.prepareCall("{call getphone_highestapps()}");
			rst=call.executeQuery();
			while(rst.next())
			{
				temresult.put(rst.getInt(1), rst.getInt(2));
			}
		} catch (SQLException e) {
			throw new PhoneAppsApplicationDaoException(e);
		}
		
		return temresult;
	}

	@Override
	public Map<Phone, App> getPhones() throws PhoneAppsApplicationDaoException {
		Connection con = PhoneAppsJdbcConnectionInputUtility.getConnection();
		CallableStatement call = null;
		ResultSet rst = null;
		Map<Phone,App> result=new HashMap<Phone,App>();
		try {
			call=con.prepareCall("{call getAll_Phones()}");
			rst=call.executeQuery();
			while(rst.next())
			{
				Phone ph=new Phone();
				ph.setPhoneid(rst.getInt(1));
				ph.setPhonename(rst.getString(2));
				ph.setBrandname(rst.getString(3));
				App app=new App();
				app.setAppid(rst.getInt(4));
				app.setAppname(rst.getString(5));
				app.setAppsize(rst.getString(6));
				result.put(ph, app);
		} }catch (SQLException e) {
			throw new PhoneAppsApplicationDaoException(e);
		}
		
		return result;
	}

	@Override
	public Map<Phone, App> deletePhoneFromDataBase(int getid) throws PhoneAppsApplicationDaoException {
		Connection con = PhoneAppsJdbcConnectionInputUtility.getConnection();
		CallableStatement call = null;
		ResultSet rst = null;
		Map<Phone,App> result=new HashMap<Phone,App>();
		try {
			call=con.prepareCall("{call delete_phone(?)}");
			call.setInt(1, getid);
			rst=call.executeQuery();
			while(rst.next())
			{
				Phone ph=new Phone();
				ph.setPhoneid(rst.getInt(1));
				ph.setPhonename(rst.getString(2));
				ph.setBrandname(rst.getString(3));
				App app=new App();
				app.setAppid(rst.getInt(4));
				app.setAppname(rst.getString(5));
				app.setAppsize(rst.getString(6));
				result.put(ph, app);
			}
		} catch (SQLException e) {
			throw new PhoneAppsApplicationDaoException(e);
		}
		
		return result;
	}

}
