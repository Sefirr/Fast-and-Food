package com.model.dao;

import com.model.impl.dao.txt.TxtDaoFactory;

public abstract class DaoFactory {

	public static final int TXT = 1;
	
	public static DaoFactory getDAOFactory(int whichFactory) {
		
		switch(whichFactory) {
		case TXT:
			return TxtDaoFactory.getInstance();
		default:
			return null;
		}
		
	}
	
	public abstract DaoOrder getDAOOrder();
	public abstract DaoSale getDAOSale();
	public abstract DaoStock getDAOStock();
	public abstract DaoUser getDAOUser();
	
}