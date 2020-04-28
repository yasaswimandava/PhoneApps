package com.mindtree.phoneapps.enity;

import java.util.Set;

public class Phone {
	private int phoneid;
	private String phonename;
	private String brandname;
	private Set<App> apps;
	public Phone() {
	}
	public Phone(String phonename, String brandname) {
		this.phonename = phonename;
		this.brandname = brandname;
	}
	public Phone(int phoneid, String phonename, String brandname, Set<App> apps) {
		this.phoneid = phoneid;
		this.phonename = phonename;
		this.brandname = brandname;
		this.apps = apps;
	}
	public Phone(int phoneid, String phonename, String brandname) {
		this.phoneid = phoneid;
		this.phonename = phonename;
		this.brandname = brandname;
	}
	public int getPhoneid() {
		return phoneid;
	}
	public void setPhoneid(int phoneid) {
		this.phoneid = phoneid;
	}
	public String getPhonename() {
		return phonename;
	}
	public void setPhonename(String phonename) {
		this.phonename = phonename;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public Set<App> getApps() {
		return apps;
	}
	public void setApps(Set<App> apps) {
		this.apps = apps;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apps == null) ? 0 : apps.hashCode());
		result = prime * result + ((brandname == null) ? 0 : brandname.hashCode());
		result = prime * result + phoneid;
		result = prime * result + ((phonename == null) ? 0 : phonename.hashCode());
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
		Phone other = (Phone) obj;
		if (apps == null) {
			if (other.apps != null)
				return false;
		} else if (!apps.equals(other.apps))
			return false;
		if (brandname == null) {
			if (other.brandname != null)
				return false;
		} else if (!brandname.equals(other.brandname))
			return false;
		if (phoneid != other.phoneid)
			return false;
		if (phonename == null) {
			if (other.phonename != null)
				return false;
		} else if (!phonename.equals(other.phonename))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Phone [phoneid=" + phoneid + ", phonename=" + phonename + ", brandname=" + brandname + ", apps=" + apps
				+ "]";
	}
	
}
