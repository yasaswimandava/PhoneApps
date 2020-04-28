package com.mindtree.phoneapps.enity;

public class App implements Comparable<App>{
	private int appid;
	private String appname;
	private String appsize;
	public App() {
	}
	public App(int appid, String appname, String appsize) {
		this.appid = appid;
		this.appname = appname;
		this.appsize = appsize;
	}
	public App(String appname, String appsize) {
		this.appname = appname;
		this.appsize = appsize;
	}
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getAppsize() {
		return appsize;
	}
	public void setAppsize(String appsize) {
		this.appsize = appsize;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + appid;
		result = prime * result + ((appname == null) ? 0 : appname.hashCode());
		result = prime * result + ((appsize == null) ? 0 : appsize.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		App other = (App) obj;
		if (appid != other.appid)
			return false;
		if (appname == null) {
			if (other.appname != null)
				return false;
		} else if (!appname.equals(other.appname))
			return false;
		if (appsize == null) {
			if (other.appsize != null)
				return false;
		} else if (!appsize.equals(other.appsize))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "App [appid=" + appid + ", appname=" + appname + ", appsize=" + appsize + "]";
	}
	@Override
	public int compareTo(App ap) {
	 int x=appsize.compareToIgnoreCase(ap.appsize);
	 if(x<0)
	  return 1;
	 else if(x>0)
		 return -1;
	 
	 return 0;
	}
	
	
}
