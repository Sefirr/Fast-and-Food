package com.view.vendor.actions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.controller.frameController;
import com.controller.saleController;
import com.controller.stockController;
import com.controller.command.bussinessEvent;
import com.model.dto.TransferProduct;
import com.util.Common;
import com.util.Notification;
import com.util.Tools;
import com.util.Watchable;
import com.util.Watcher;

/**
 * Interfaz gráfica para añadir ventas.
 * @author Borja y Javier
 *
 */

@SuppressWarnings("serial")
public class addSale extends JFrame implements Watcher {
	
	/* Controladores */
	private frameController frameController;		// Controlador de la interfaz gráfica
	private saleController saleController;			// Controlador del subsistema de ventas
	private stockController stockController;		// Controlador del subsistema de almacén
	
	/* Panel principal de la interfaz */
	private JPanel mainPanel;
	
	/* Panel de acciones */
    private JPanel actionPanel;
    
    private JButton addProductButton;
	private JButton removeProductButton;
	private JButton cancelSaleButton;
	private JButton endSaleButton;
	
	private String selectedProductName = null;
    private double selectedProductPrice = -1.0;
    private int selectedProductAmount = 0;
    
    /* Panel de lista de productos de la venta */
    private JPanel saleProductListPanel;
    private DefaultTableModel saleProductsTableModel;
    private JTable saleProductsTable;
    private JScrollPane productListTableScrollable;

    /* Panel de contenido */
    private JPanel contentPanel;

    /* Tabla de productos */
    private JPanel productTablePanel;
    private DefaultTableModel productTableModel;
    private JTable productTable;
    private JScrollPane saleProductListScrollable;

    /* Panel de información de producto */
    private JPanel productInfoPanel;

    private JLabel  productNameLabel, 
    productPriceLabel, 
    productAmountLabel;

    private JTextField  productNameTextField, 
    productPriceTextField, 
    productAmountTextField;
    
    /* Panel del cliente */
    private JPanel customerPanel;
    private JLabel customerNameLabel;
    private JTextField customerTextField;
    private String customer;
    
    /* Indicador de venta actual a añadir */
    private int currentSale;
    
    /* Indicador para actualizar el almacén o no */
    private boolean updateStock;
    
    /* Link a todas aquella ventanas que se abren individualmente por separado de la aplicación */
    private ShowReceipt showReceipt;
    
    /**
     * Constructor de la interfaz gráfica para añadir ventas.
     * @param cont 	Controlador de la interfaz gráfica.
     */
    
    public addSale(frameController cont) {
    	super("Añadir venta");
    	this.frameController = cont;
        this.saleController = (saleController) cont.getController(1);
        this.stockController = (stockController) cont.getController(2); 
        
        this.frameController.addWatcherToSale(this);
        this.frameController.addWatcherToStock(this);
        
        this.showReceipt = new ShowReceipt(frameController, saleController);
        
        initUI();
        
    }
    
    /**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */
    
    private void initUI() {
    	/* Panel principal de la interfaz */
    	mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(Tools.SCREEN_DIMENSION);
        
        /* Aqui se inicializan todos aquellos atributos que necesitan ser 
		 * inicializados y que no son controladores. 
		 * */
        
        this.updateStock = true;

        /* Panel de acciones */
        actionPanel = new JPanel();
        actionPanel.setBorder(new TitledBorder("Acciones de la venta"));

        addProductButton =      new JButton("Agregar producto");
        removeProductButton =   new JButton("Eliminar producto");
        cancelSaleButton =      new JButton("Cancelar venta");
        endSaleButton =         new JButton("Finalizar venta");

        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Connect to controller
            	try {
            		selectedProductAmount = Integer.parseInt(productAmountTextField.getText());
	                TransferProduct p = (TransferProduct) stockController.actionStock(bussinessEvent.MOSTRAR_PRODUCTO, -1 + "_" + selectedProductName + "_");	                
	                	
					saleController.actionSale(bussinessEvent.AÑADIR_PRODUCTO_VENTA, p.getId() + "_" + selectedProductAmount + "_" + currentSale + "_");
					productNameTextField.setText("");
			    	productPriceTextField.setText("");
			    	productAmountTextField.setText("");
            	} catch(NumberFormatException e1) {
            		JOptionPane.showMessageDialog(null, Common.ADDPRODUCT_ERRORMESSAGE + System.lineSeparator() + "La cantidad que has introducido no es correcta.");
            	}
            }
        });
         
        removeProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Connect to controller
            	try {
		        	selectedProductAmount = Integer.parseInt(productAmountTextField.getText());
		        	
		        	TransferProduct p = (TransferProduct) stockController.actionStock(bussinessEvent.MOSTRAR_PRODUCTO, -1 + "_" + selectedProductName + "_");
		            saleController.actionSale(bussinessEvent.ELIMINAR_PRODUCTO_VENTA, p.getId() + "_" + selectedProductAmount + "_" + currentSale + "_");
		            
		            productNameTextField.setText("");
		        	productPriceTextField.setText("");
		        	productAmountTextField.setText("");
            	} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
            		JOptionPane.showMessageDialog(null, Common.REMOVEPRODUCT_ERRORMESSAGE + System.lineSeparator() + "La cantidad que has introducido no es correcta.");
				}
            }
        });
         
        cancelSaleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Connect to controller
                saleController.actionSale(bussinessEvent.CANCELAR_VENTA, currentSale + "_");
                dispose();
            }
        });
         
        endSaleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Connect to controller
            	try {
	            	
	            	customer = customerTextField.getText();
	            	
	            	if(customer.isEmpty())
	            		throw new NumberFormatException();
	            	
	            	 if(saleProductsTableModel.getRowCount() == 0)
	                     JOptionPane.showMessageDialog(addSale.this, "Debes haber añadido algún producto al carrito");
	                 else {
	                     frameController.saleEvent(currentSale);
	                     saleProductsTableModel.setRowCount(0);
	                 }
	            	
	             	 updateStock = true;
	               
	                
            	} catch(NumberFormatException e1) {
            		JOptionPane.showMessageDialog(null, Common.FINISHSALE_ERRORMESSAGE + System.lineSeparator() + "La venta debe estar destinada a un cliente. Por favor, introduzca el nombre del cliente.");
            	}
            }
        });

        actionPanel.add(addProductButton);
        actionPanel.add(removeProductButton);
        actionPanel.add(cancelSaleButton);
        actionPanel.add(endSaleButton);

        /* Tabla de productos de la venta */
        saleProductListPanel = new JPanel();
        saleProductListPanel.setBorder(new TitledBorder("Carrito de la compra"));

        saleProductsTableModel = new DefaultTableModel(new Object[]{"Producto", "Cantidad"}, 0);
        saleProductsTable = new JTable(saleProductsTableModel) {
        	public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            };
        };
        saleProductsTable.setEnabled(true);
        saleProductsTable.setRowSelectionAllowed(true);
         
        saleProductsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedProductName = (String) saleProductsTable.getValueAt(saleProductsTable.getSelectedRow(), 0);
            }
        });

        saleProductListScrollable = new JScrollPane(saleProductsTable);
        saleProductListScrollable.setPreferredSize(new Dimension(200, 625));

        saleProductListPanel.add(saleProductListScrollable);

        /* Panel de contenido principal */
        contentPanel = new JPanel(new BorderLayout());

        /* Tabla de Productos */
        productTablePanel = new JPanel(new GridLayout(1,2));
        productTablePanel.setBorder(new TitledBorder("Lista de Productos"));

        productTableModel = new DefaultTableModel(new Object[]{"Producto", "Precio", "Disponibilidad"}, 0);
        productTable = new JTable(productTableModel) {
        	public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            };
        };
        productTable.setRowSelectionAllowed(true);
        productTable.setEnabled(true);        
         
        productTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    selectedProductName = (String) productTable.getValueAt(productTable.getSelectedRow(), 0);
                    selectedProductPrice = Double.valueOf((String) productTable.getValueAt(productTable.getSelectedRow(), 1));
                    
                    productNameTextField.setText(selectedProductName);
                    productPriceTextField.setText(String.valueOf(selectedProductPrice));
                } catch (ClassCastException cce) {cce.printStackTrace();}
            }
        });

        productListTableScrollable = new JScrollPane(productTable);

        productTablePanel.add(productListTableScrollable);

        /* Panel de información de producto */
        productInfoPanel = new JPanel();
        productInfoPanel.setBorder(new TitledBorder("Información del producto"));

        productNameLabel =      new JLabel("Producto: ");
        productPriceLabel =     new JLabel("Precio: ");
        productAmountLabel =    new JLabel("Cantidad: ");

        productNameTextField =      new JTextField();
        productPriceTextField =     new JTextField();
        productAmountTextField =    new JTextField();

        productNameTextField.setEditable(false);
        productPriceTextField.setEditable(false);
        productAmountTextField.setEditable(true);

        productNameTextField.setColumns(10);
        productPriceTextField.setColumns(6);
        productAmountTextField.setColumns(4);


        productInfoPanel.add(productNameLabel);
        productInfoPanel.add(productNameTextField);
        productInfoPanel.add(new JLabel("                   "));
        productInfoPanel.add(productPriceLabel);
        productInfoPanel.add(productPriceTextField);
        productInfoPanel.add(new JLabel("                   "));
        productInfoPanel.add(productAmountLabel);
        productInfoPanel.add(productAmountTextField);

        contentPanel.add(productTablePanel, BorderLayout.CENTER);
        contentPanel.add(productInfoPanel, BorderLayout.PAGE_END);
        
        customerPanel = new JPanel();
        customerNameLabel = new JLabel("Nombre del cliente: ");
        customerTextField = new JTextField();
        customerTextField.setEditable(true);
        customerTextField.setPreferredSize(Tools.TEXTFIELDSIZE);
        
        customerPanel.add(customerNameLabel);
        customerPanel.add(customerTextField);

        /* Panel principal */
        mainPanel.add(actionPanel, BorderLayout.PAGE_START);
        mainPanel.add(saleProductListPanel, BorderLayout.LINE_START);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(customerPanel, BorderLayout.PAGE_END);   
        this.add(mainPanel, BorderLayout.CENTER);
        
        this.setLocation(50,100);
        this.setSize(Tools.WINDOW_DIMENSION);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);  
    }
    
    /**
	 * Método de notificación de los observadores, que son notificados por el modelo para refrescar todos
	 * aquellos componentes que necesitan ser actualizados, así como lanzar notifcaciones de éxito o error.
	 */
    
    @Override
    public void update(Watchable o, Object arg) {
    	
    	String cadena = String.valueOf(arg);
    	String[] contenido = cadena.split(" ");
    	int PETICION = Integer.parseInt(contenido[0]);
		cadena = cadena.substring(contenido[0].length()+1);
		
		Notification nt;
		
		switch(PETICION) {
		case bussinessEvent.ACTUALIZAR_STOCK:
			if(updateStock) {
				updateStockTable(cadena); 
				updateStock = false;
			}
			break;
		case bussinessEvent.VENTA_ACTUAL:
			String[] contenido2 = cadena.split(",");
			if(currentSale == Integer.parseInt(contenido2[0]))  {
				// Si la vent no está vacia
				if(contenido2.length > 1)
					updateSaleTable(contenido2[1]);
				else {
					updateSaleTable("");
				}
			}
			break;
		case bussinessEvent.FINALIZAR_VENTA_PROCESS:
			String[] contenido3 = cadena.split(",");
			showReceipt.setCustomer(customer);
			showReceipt.setSaleIdentifier(currentSale);
			Date fecha = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
            showReceipt.setDate(formateador.format(fecha));
            if(currentSale == Integer.parseInt(contenido3[0]))
            	showReceipt.display(Double.valueOf(contenido3[1]));
			break;
		case bussinessEvent.CANCELAR_VENTA:
    		nt = new Notification(Common.SUCCESFULL_CANCELSALE_MESSAGE);
    		nt.start();
    		break;
    	case bussinessEvent.FINALIZAR_VENTA_NOTIFY:
    		nt = new Notification(Common.SUCCESFULL_FINISHSALE_MESSAGE);
    		nt.start();
    		if(currentSale == Integer.parseInt(cadena))
    			dispose(); 
    		break;
		}
    }
    
    /**
	 * Método de actualización de la tabla de almacén.
	 * @param stockContents Contenido de la tabla del almacén.
	 */

    private void updateStockTable(final String stockContents) {
        String[] contents = null;

        if(!stockContents.equals(""))
            contents = stockContents.split("_");

        if(contents != null) {
            productTableModel.setRowCount(0);
            for(int i = 0; i < contents.length - 1; i++)
                if((i%5) == 0)
                    productTableModel.addRow(new Object[]{contents[i+1], contents[i+4], (Integer.parseInt(contents[i+3]) == 0) ? "No disponible"  : "Disponible" });
        }
    }
    
    /**
	 * Método de actualización de la tabla de ventas.
	 * @param saleContents Contenido de la tabla de ventas.
	 */
    
    public void updateSaleTable(final String saleContents) {
        String[] contents = null;
        if(!saleContents.equals("")) {
            contents = saleContents.split("_");
        }
        
        saleProductsTableModel.setRowCount(0);
        
        if(contents != null) {
            for(int i = 0; i < contents.length - 1; i++)
                if((i%3) == 0) {
                	saleProductsTableModel.addRow(new Object[]{ contents[i], contents[i+2] });
                }
        }
    }
    
    /**
	 * Método que inicializa el atributo currentSale, es decir, para que la aplicación sepa cual es la venta
	 * que se esta realizando en este mismo instante.
	 * @param currentSale Identificador de la venta.
	 */
    
    public void currentSale(int currentSale) {
    	this.currentSale = currentSale;
    }
    
    /**
	 * Método que sirve para "limpiar" las ventanas de aplicación una vez han sido utilizadas.
	 */
    
    public void clear() {
    	saleProductsTableModel.setRowCount(0);
    	customerTextField.setText("");
    	productNameTextField.setText("");
    	productPriceTextField.setText("");
    	productAmountTextField.setText("");
    }
    
    @Override
    public void dispose() {
    	super.dispose();
    }
    
}