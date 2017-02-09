package com.model.impl.dto;

import com.model.dto.TransferUser;
import com.util.Tools.UserPermission;

@SuppressWarnings("serial")
public class TransferUserImp implements TransferUser {

	private UserPermission _permission;
	private String _employeeName;
	private String _userName;
	private String _password;
	
	public TransferUserImp() {
		this._permission = UserPermission.NONE;
		this._employeeName = new String();
		this._userName = new String();
		this._password = new String();
	}
	
	public TransferUserImp(String userName, String password) {
        this._userName = userName;
        this._password = password;
    }
	
	public TransferUserImp(UserPermission permission, String employeeName, String userName, String password) {
        this._permission = permission;
        this._employeeName = employeeName;
        this._userName = userName;
        this._password = password;
    }
	
	
    public UserPermission getPermission() {
		return _permission;
	}

	public void setPermission(UserPermission _permission) {
		this._permission = _permission;
	}

	public String getEmployeeName() {
		return _employeeName;
	}

	public void setEmployeeName(String _employeeName) {
		this._employeeName = _employeeName;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String _userName) {
		this._userName = _userName;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String _password) {
		this._password = _password;
	}
	
	public boolean checkPass(String password) {
        return this._password.equals(password);
    }

	public String displayContents() {

        return this.getEmployeeName() + "_" + this.getUserName() + "_" + this.getPermission().ordinal() + "_" + this.getPassword() + "_"; 
        
    }
	
    public String toFile() {
        return  this._permission.ordinal() + "\n" + 
                this._employeeName + "\n" +
                this._userName + "\n" +
                this._password + "\n\n";
    }

    @Override
    public String toString() {
        return  this._permission.ordinal() + "\n" + 
                this._employeeName + "\n" +
                this._userName + "\n" +
                this._password + "\n";
    }
    	
}