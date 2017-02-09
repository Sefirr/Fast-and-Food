package com.model;

import com.model.dto.TransferUser;
import com.model.exceptions.DuplicateUserException;
import com.model.exceptions.InvalidLoginException;
import com.model.exceptions.UserNotFoundException;
import com.util.Watcher;
import com.util.Tools.UserPermission;

public interface userService {
	public void login(String username, String password) throws InvalidLoginException;
	public void logout(String username, String password);
	public void addUser(UserPermission permission, String employeeName, String userName, String password) throws DuplicateUserException;
	public void removeUser(String userName) throws UserNotFoundException;
	public void modifyUser(String userName, String password, UserPermission permisssion) throws UserNotFoundException;
	public TransferUser searchUser(String employeeName) throws UserNotFoundException;
	public void reloadUsers();
	
	public void dispatchWatcher(Watcher watcher);
	public void removeWatchers();
	
}
