package com.model.impl;

import java.util.ArrayList;

import com.controller.command.bussinessEvent;
import com.model.userService;
import com.model.dao.DaoFactory;
import com.model.dao.DaoUser;
import com.model.dto.TransferUser;
import com.model.exceptions.DuplicateUserException;
import com.model.exceptions.InvalidLoginException;
import com.model.exceptions.UserNotFoundException;
import com.model.impl.dto.TransferUserImp;
import com.util.Watchable;
import com.util.Watcher;
import com.util.Tools.UserPermission;

public class userServiceImp extends Watchable implements userService {

	@Override
	public void login(String username, String password)
			throws InvalidLoginException {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoUser staffDAO = factory.getDAOUser();

		try {
			ArrayList<TransferUser> userList = staffDAO.readUsers();
			ArrayList<String> loggedUserList = staffDAO.readLoggedUser();
			TransferUser u = new TransferUserImp();
			u.setUserName(username);
			u.setPassword(password);

			if (!staffDAO.validateUser(username, password))
				throw new InvalidLoginException(
						"El nombre de usuario o la contraseña introducidos son erroneos. ");
			if (staffDAO.searchUser(loggedUserList, username))
				throw new InvalidLoginException(username + " ya está logueado.");

			u.setPermission(staffDAO.searchUser(userList, u).getPermission());
			staffDAO.login(u);

			this.setChanged();
			this.notifyObservers(bussinessEvent.LOGINEVENT + " "
					+ u.getPermission().name());
		} catch (InvalidLoginException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_LOGIN + " "
					+ e.getMessage());

			throw e;
		}

	}

	@Override
	public void logout(String username, String password) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoUser staffDAO = factory.getDAOUser();

		TransferUser u = new TransferUserImp();
		u.setUserName(username);
		u.setPassword(password);

		staffDAO.logout(u);

		this.setChanged();
		this.notifyObservers(String.valueOf(bussinessEvent.LOGOUTEVENT) + " "
				+ UserPermission.NONE);

	}

	@Override
	public void addUser(UserPermission permission, String employeeName,
			String userName, String password) throws DuplicateUserException {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoUser staffDAO = factory.getDAOUser();

		try {
			ArrayList<TransferUser> userList = staffDAO.readUsers();
			TransferUser u = new TransferUserImp();
			u.setPermission(permission);
			u.setEmployeeName(employeeName);
			u.setUserName(userName);
			u.setPassword(password);

			if (staffDAO.searchUser(userList, u) != null)
				throw new DuplicateUserException(u.getUserName()
						+ " ya existe.");

			staffDAO.addUser(u);

			this.setChanged();
			this.notifyObservers(bussinessEvent.INSERTAR_USUARIO + " ");
		} catch (DuplicateUserException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_ADDUSER + " "
					+ e.getMessage());

			throw e;
		}

	}

	@Override
	public void removeUser(String userName) throws UserNotFoundException {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoUser staffDAO = factory.getDAOUser();

		try {
			ArrayList<TransferUser> userList = staffDAO.readUsers();
			TransferUser u = new TransferUserImp();
			u.setUserName(userName);

			if (staffDAO.searchUser(userList, u) == null)
				throw new UserNotFoundException(u.getUserName() + " no existe.");

			staffDAO.removeUser(u);

			this.setChanged();
			this.notifyObservers(bussinessEvent.ELIMINAR_USUARIO + " ");
		} catch (UserNotFoundException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_DELETEUSER + " "
					+ e.getMessage());

			throw e;
		}

	}

	@Override
	public void modifyUser(String userName, String password,
			UserPermission permisssion) throws UserNotFoundException {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoUser staffDAO = factory.getDAOUser();

		try {
			ArrayList<TransferUser> userList = staffDAO.readUsers();
			TransferUser u = new TransferUserImp();
			u.setUserName(userName);
			u.setPassword(password);
			u.setPermission(permisssion);

			if (staffDAO.searchUser(userList, u) == null)
				throw new UserNotFoundException(u.getUserName() + " no existe.");

			staffDAO.modifyUser(u);

			this.setChanged();
			this.notifyObservers(bussinessEvent.MODIFICAR_USUARIO + " ");
		} catch (UserNotFoundException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_MODIFYUSER + " "
					+ e.getMessage());
		}

	}

	@Override
	public TransferUser searchUser(String userName)
			throws UserNotFoundException {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoUser staffDAO = factory.getDAOUser();

		try {
			ArrayList<TransferUser> userList = staffDAO.readUsers();
			TransferUser u = new TransferUserImp();
			u.setUserName(userName);

			TransferUser currentUser = staffDAO.searchUser(userList, u);

			if (staffDAO.searchUser(userList, u) == null)
				throw new UserNotFoundException(u.getUserName() + " no existe.");

			this.setChanged();
			this.notifyObservers(bussinessEvent.MOSTRAR_USUARIO + " "
					+ currentUser.displayContents());

			return currentUser;
		} catch (UserNotFoundException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_SEARCHUSER + " "
					+ e.getMessage());

			throw e;
		}
	}

	@Override
	public void dispatchWatcher(Watcher watcher) {
		this.addWatcher(watcher);
	}

	@Override
	public void removeWatchers() {
		this.deleteWatchers();
	}

	@Override
	public void reloadUsers() {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoUser staffDAO = factory.getDAOUser();

		ArrayList<TransferUser> userList = staffDAO.readUsers();

		String contents = String.valueOf(bussinessEvent.ACTUALIZAR_USUARIOS)
				+ " ";
		for (TransferUser us : userList) {
			contents += us.displayContents();
		}

		this.setChanged();
		this.notifyObservers(contents);
	}

}
