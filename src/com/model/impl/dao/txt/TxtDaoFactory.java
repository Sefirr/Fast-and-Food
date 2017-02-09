package com.model.impl.dao.txt;

import com.model.dao.DaoFactory;
import com.model.dao.DaoOrder;
import com.model.dao.DaoSale;
import com.model.dao.DaoUser;
import com.model.dao.DaoStock;

public class TxtDaoFactory extends DaoFactory {

	private static TxtDaoFactory factory;

	public static TxtDaoFactory getInstance() {
		if (factory == null)
			factory = new TxtDaoFactory();

		return factory;

	}

	private TxtDaoFactory() {

	}

	@Override
	public DaoOrder getDAOOrder() {
		// TODO Auto-generated method stub
		return TxtDaoOrder.getInstance();
	}

	@Override
	public DaoSale getDAOSale() {
		// TODO Auto-generated method stub
		return TxtDaoSale.getInstance();
	}

	@Override
	public DaoStock getDAOStock() {
		// TODO Auto-generated method stub
		return TxtDaoStock.getInstance();
	}

	@Override
	public DaoUser getDAOUser() {
		// TODO Auto-generated method stub
		return TxtDaoUser.getInstance();
	}

}
