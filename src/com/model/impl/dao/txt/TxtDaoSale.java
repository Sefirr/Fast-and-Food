package com.model.impl.dao.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.model.dao.DaoSale;
import com.model.dto.TransferProduct;
import com.model.dto.TransferSale;
import com.model.exceptions.FileErrorException;
import com.model.impl.dto.TransferProductImp;
import com.model.impl.dto.TransferSaleImp;
import com.util.Common;
import com.util.Tools;

public class TxtDaoSale implements DaoSale {

	private static TxtDaoSale factory;

	public static TxtDaoSale getInstance() {
		if (factory == null)
			factory = new TxtDaoSale();

		return factory;
	}

	private TxtDaoSale() {

	}

	@Override
	public ArrayList<TransferSale> readSales() {
		// TODO Auto-generated method stub
		BufferedReader bf = null;
		Path input = Paths.get(Common.SALES_FILE);

		ArrayList<TransferSale> saleList = new ArrayList<TransferSale>();

		int saleIdentifier;
		String customer;
		ArrayList<TransferProduct> saleProductList;
		double salePrice;
		String saleStateText;
		boolean saleState;
		String date;

		int productNumber = -1;
		int productID;
		String productName;
		String productTag;
		double productPrice;
		int productAmount = -1;

		try {
			bf = Files.newBufferedReader(input, Charset.defaultCharset());
			String line = bf.readLine();

			if (line != null && Tools.isInteger(line))
				Tools.NUMSALES = Integer.parseInt(line);

			bf.readLine(); // white line
			line = bf.readLine();

			while (line != null) {

				saleProductList = new ArrayList<TransferProduct>();

				saleIdentifier = (Tools.isInteger(line.trim())) ? Integer
						.parseInt(line) : -1;
				line = bf.readLine();
				customer = (!line.isEmpty()) ? line.trim() : null;
				bf.readLine(); // -- line
				line = bf.readLine(); // Numero de productos

				if (Tools.isInteger(line))
					productNumber = Integer.parseInt(line);

				bf.readLine(); // white line
				line = bf.readLine(); // Identificador de producto

				while (!line.equalsIgnoreCase("--")) {
					productID = (Tools.isInteger(line.trim())) ? Integer
							.parseInt(line) : -1;
					line = bf.readLine();

					productName = (!line.isEmpty()) ? line.trim() : null;
					line = bf.readLine();

					productTag = (!line.isEmpty()) ? line.trim() : null;
					line = bf.readLine();

					productPrice = (Tools.isDouble(line.trim())) ? Double
							.parseDouble(line) : -1.0;
					line = bf.readLine();

					productAmount = (Tools.isInteger(line)) ? Integer
							.parseInt(line) : -1;

					saleProductList.add(new TransferProductImp(productID,
							productName, productTag, productPrice,
							productAmount));

					bf.readLine(); // white line
					line = bf.readLine();

				}
				;

				line = bf.readLine();
				date = (!line.isEmpty()) ? line.trim() : null;

				line = bf.readLine();
				salePrice = (Tools.isDouble(line.trim())) ? Double
						.parseDouble(line.trim()) : -1.0;

				line = bf.readLine();
				saleStateText = (!line.isEmpty()) ? line.trim() : null;

				if (saleStateText.equalsIgnoreCase("true"))
					saleState = true;
				else
					saleState = false;

				saleList.add(new TransferSaleImp(saleIdentifier, customer,
						saleProductList, date, salePrice, saleState));

				if (saleProductList.size() != productNumber)
					throw new FileErrorException(
							"El número de productos indicado en las ventas no es correcto.");

				bf.readLine(); // white line
				line = bf.readLine();

			}

			if (saleList.size() != Tools.NUMSALES)
				throw new FileErrorException(
						"El n�mero de ventas indicado no es correcto.");
		} catch (IOException ioe) {
			System.exit(2);

		} catch (FileErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bf.close();
			} catch (IOException ioe) {
				System.exit(1);
			}
		}

		return saleList;
	}

	@Override
	public void writeSales(ArrayList<TransferSale> saleList) {
		Path writeFilePath = Paths.get(Common.SALES_FILE);
		FileWriter writer = null;
		String newLine = System.getProperty("line.separator");

		if (Files.exists(writeFilePath)) {
			try {

				writer = new FileWriter(writeFilePath.toString());

				if (saleList == null || saleList.size() == 0) {
					writer.write("0");
					return;
				}

				writer.write(saleList.size() + newLine + newLine);

				for (TransferSale s : saleList) {
					if (s.toFile() != null)
						writer.write(s.toFile() + newLine);
					else
						writer.write(newLine);
				}

			} catch (IOException e1) {
				System.exit(2);

			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					System.exit(2);
				}
			}
		}

		else {
			try {

				File file = new File(Common.SALES_FILE);
				writer = new FileWriter(file.toString());

				if (saleList == null || saleList.size() == 0) {
					writer.write("0");
					return;
				}

				writer.write(saleList.size() + newLine + newLine);

				for (TransferSale s : saleList) {
					if (s.toFile() != null)
						writer.write(s.toFile() + newLine);
					else
						writer.write(newLine);
				}

			} catch (IOException e) {
				System.exit(2);

			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					System.exit(2);
				}
			}
		}
	}

	@Override
	public int numSales() {
		ArrayList<TransferSale> saleList = readSales();
		return saleList.size();
	}

	@Override
	public void addProduct(TransferProduct p, TransferSale s) {
		// TODO Auto-generated method stub
		ArrayList<TransferSale> saleList = readSales();
		TransferSale currentSale = saleList.get(s.getSaleIdentifier());

		if (productFound(currentSale, p)) {
			TransferProduct product = searchProduct(currentSale, p);
			product.setAmount(product.getAmount() + p.getAmount());
		} else {
			ArrayList<TransferProduct> currentList = currentSale
					.getProductList();
			currentList.add(p);

		}

		currentSale.setPrice(currentSale.getPrice() + p.getAmount()
				* p.getPrice());
		writeSales(saleList);

	}

	@Override
	public void removeProduct(TransferProduct p, TransferSale s) {
		// TODO Auto-generated method stub
		ArrayList<TransferSale> saleList = readSales();
		TransferSale currentSale = saleList.get(s.getSaleIdentifier());

		if (productFound(currentSale, p)) {
			TransferProduct product = searchProduct(currentSale, p);
			if (p.getAmount() == product.getAmount()) {
				ArrayList<TransferProduct> currentList = currentSale
						.getProductList();
				int id = currentList.indexOf(p) + 1;
				currentList.remove(id);
			} else {
				product.setAmount(product.getAmount() - p.getAmount());
			}
		}

		currentSale.setPrice(currentSale.getPrice() - p.getAmount()
				* p.getPrice());

		writeSales(saleList);
	}

	@Override
	public void addSale(TransferSale s) {
		// TODO Auto-generated method stub
		ArrayList<TransferSale> saleList = readSales();
		saleList.add(s);

		Tools.NUMSALES++;
		writeSales(saleList);

	}

	@Override
	public void deleteSale(TransferSale s) {
		// TODO Auto-generated method stub
		ArrayList<TransferSale> saleList = readSales();
		for (int pos = 0; pos < saleList.size(); pos++) {
			if (saleList.get(pos).getSaleIdentifier() == s.getSaleIdentifier()) {
				saleList.remove(pos);
			}

		}

		Tools.NUMSALES--;
		writeSales(saleList);

	}

	@Override
	public void finishSale(TransferSale s) {
		// TODO Auto-generated method stub
		ArrayList<TransferSale> saleList = readSales();
		TransferSale currentSale = saleList.get(s.getSaleIdentifier());
		currentSale.setCustomer(s.getCustomer());
		currentSale.finished();
		currentSale.setDate(s.getDate());

		writeSales(saleList);

	}

	@Override
	public void cancelSale(TransferSale s) {
		// TODO Auto-generated method stub
		ArrayList<TransferSale> saleList = readSales();
		for (int pos = 0; pos < saleList.size(); pos++) {
			if (saleList.get(pos).getSaleIdentifier() == s.getSaleIdentifier()) {
				saleList.remove(pos);
			}

		}

		Tools.NUMSALES--;
		writeSales(saleList);
	}

	@Override
	public boolean productFound(TransferSale currentSale, TransferProduct p) {
		// TODO Auto-generated method stub
		ArrayList<TransferProduct> currentList = currentSale.getProductList();
		for (TransferProduct product : currentList) {
			if (product.getId() == p.getId()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public TransferProduct searchProduct(TransferSale currentSale,
			TransferProduct p) {
		// TODO Auto-generated method stub
		ArrayList<TransferProduct> currentList = currentSale.getProductList();
		for (TransferProduct product : currentList) {
			if (product.getId() == p.getId())
				return product;
			if (product.getName().equalsIgnoreCase(p.getName()))
				return product;
		}

		return null;
	}

	@Override
	public TransferSale searchSale(TransferSale s) {
		ArrayList<TransferSale> saleList = readSales();
		for (TransferSale sale : saleList) {
			if (sale.getSaleIdentifier() == s.getSaleIdentifier())
				return sale;
		}
		return null;
	}

	@Override
	public void generateInvoicing(TransferSale s) {
		// TODO Auto-generated method stub
		ArrayList<TransferSale> saleList = readSales();
		TransferSale currentSale = searchSale(s);

		Path writeFilePath = Paths.get(Common.RECEIPT_FILE);
		FileWriter writer = null;
		String newLine = System.getProperty("line.separator");

		if (Files.exists(writeFilePath)) {
			try {

				writer = new FileWriter(writeFilePath.toString());

				if (saleList == null || saleList.size() == 0) {
					writer.write("0");
					return;
				}

				Iterator<TransferProduct> it = currentSale.getProductList()
						.iterator();

				StringBuilder sb = new StringBuilder();

				while (it.hasNext()) {
					TransferProduct p = it.next();
					sb.append(p.getName()
							+ Tools.makeSymbol(p.getName(), 33, " ")
							+ p.getAmount()
							+ Tools.makeSymbol(String.valueOf(p.getAmount()),
									8, " ")
							+ Tools.makeSymbol(String.valueOf(p.getPrice()),
									12, " ") + p.getPrice());
					sb.append(newLine);
				}

				writer.write("ID de venta: "
						+ currentSale.getSaleIdentifier()
						+ newLine
						+ "Fecha: "
						+ currentSale.getDate()
						+ newLine
						+ "Cliente: "
						+ currentSale.getCustomer()
						+ newLine
						+ Tools.makeSymbol("", 55, "-")
						+ newLine
						+ "Nombre                        Cantidad          Precio"
						+ newLine
						+ sb.toString()
						+ Tools.makeSymbol("", 55, "-")
						+ newLine
						+ "Total"
						+ Tools.makeSymbol("Total" + currentSale.getPrice(),
								55, ".") + currentSale.getPrice() + newLine);

			} catch (IOException e1) {
				System.exit(2);

			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					System.exit(2);
				}
			}
		}

		else {
			try {

				File file = new File(Common.RECEIPT_FILE);
				writer = new FileWriter(file.toString());

				if (saleList == null || saleList.size() == 0) {
					writer.write("0");
					return;
				}

				writer.write("ID de venta:" + currentSale.getSaleIdentifier()
						+ newLine + "Fecha: " + currentSale.getDate() + newLine
						+ "Cliente: " + currentSale.getCustomer() + newLine
						+ currentSale.toFile() + newLine
						+ "-----------------------" + newLine
						+ "Total............." + currentSale.getPrice()
						+ newLine);

			} catch (IOException e) {
				System.exit(2);

			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					System.exit(2);
				}
			}
		}

	}

	@Override
	public double showExchange(double pagado, TransferSale s) {
		// TODO Auto-generated method stub

		return Math.rint((pagado - s.getPrice())*100) / 100;

	}

	@Override
	public double showProfits() {
		// TODO Auto-generated method stub
		Date fecha = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");

		double tmp = 0.0;
		ArrayList<TransferSale> saleList = readSales();
		for (TransferSale s : saleList)
			if (s.getDate().equalsIgnoreCase(formateador.format(fecha)))
				tmp += s.getPrice();

		return Math.rint(tmp*100) / 100;
	}

	@Override
	public double showBalance() {
		// TODO Auto-generated method stub
		double tmp = 0.0;
		ArrayList<TransferSale> saleList = readSales();
		for (TransferSale s : saleList)
			tmp += s.getPrice();

		return Math.rint(tmp*100) / 100;

	}

}