package com.model.dto;

import java.io.Serializable;

import com.util.Tools.UserPermission;

public interface TransferUser extends Serializable {
	public UserPermission getPermission();
	public void setPermission(UserPermission _permission);
	public String getEmployeeName();
	public void setEmployeeName(String _employeeName);
	public String getUserName();
	public void setUserName(String _userName);
	public String getPassword();
	public void setPassword(String _password);
	public boolean checkPass(String password);
	public String displayContents();
	public String toFile();
	public String toString();

}