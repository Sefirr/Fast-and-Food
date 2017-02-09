package com.model.dao;

import java.util.ArrayList;

import com.model.dto.TransferUser;
import com.model.exceptions.InvalidLoginException;
import com.model.exceptions.UserNotFoundException;

public interface DaoUser {
	public ArrayList<String> readLoggedUser();
	public void writeLoggedUser(ArrayList<String> userList);
	public ArrayList<TransferUser> readUsers();
	public void writeUsers(ArrayList<TransferUser> userList);
	public void login(TransferUser u) throws InvalidLoginException;
	public void logout(TransferUser u);
	public void addUser(TransferUser u);
	public void removeUser(TransferUser u);
	public void modifyUser(TransferUser u) throws UserNotFoundException;
	public boolean validateUser(String username, String password);
	public TransferUser searchUser(ArrayList<TransferUser> userList, TransferUser u);
	public boolean searchUser(ArrayList<String> userList, String u);

}