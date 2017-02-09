package com.view.manager.actions;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.controller.orderController;
import com.controller.command.bussinessEvent;
import com.util.Common;
import com.util.Tools;
import com.util.Watchable;
import com.util.Watcher;

/**
 * Interfaz gráfica para añadir pedidos.
 * @author Javier
 *
 */

@SuppressWarnings("serial")
public class addOrder extends JFrame implements Watcher {
    
	// Controladores
	private frameController frameController;		// Controlador de interfaz gráfica
	private orderController orderController;		// Controlador del subsistema de pedidos
	
	/* Panel principal de la interfaz */
	private JPanel mainPanel;
	
	/* Panel de acciones de la cabecera */
    private JPanel actionPanelHeader;
    
    private JButton addProductButton; 
    private JButton removeProductButton;
    private JButton cancelOrderButton;
    private JButton endOrderButton;
    
    /* Panel de lista de productos del pedido */
    private JPanel orderProductListPanel;
    private DefaultTableModel orderProductsTableModel;
    private JTable orderProductsTable;
    private JScrollPane orderProductListScrollable;
    
    /* Panel de acciones del pie*/
    private JPanel actionPanelFooter;
    
    private JPanel stateOrderPanel;
    private JLabel stateOrderLabel;
    private Choice stateOrderChoice;
    
    /* Panel del cliente */
    private JPanel supplierPanel;
    private JLabel supplierNameLabel;
    private JTextField supplierTextField;
    private String supplier;
    
    /* Indicador del pedido actual a añadir */
    private int currentOrder;
    
    /* Link a todas aquella ventanas que se abren individualmente por separado de la aplicación */
    private ArrayList<addProductToOrder> addProductLink;
    private ArrayList<removeProductToOrder> removeProductLink;
    
    /**
     * Constructor de la interfaz gráfica para añadir pedidos.
     * @param cont 	Controlador de la interfaz gráfica.
     */
    
    public addOrder(frameController cont) {
    	super("Añadir pedido");
    	this.frameController = cont;
    	this.orderController = (orderController) cont.getController(0);
    	
    	frameController.addWatcherToOrder(this);
    	
    	this.addProductLink = new ArrayList<addProductToOrder>();
    	this.removeProductLink = new ArrayList<removeProductToOrder>();
        
        initUI();
        
    }
    
    /**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */
    
    private void initUI() {
    	/* Panel principal de la interfaz */
    	mainPanel = new JPanel(new BorderLayout());        

        /* Panel de acciones de la cabecera */
        actionPanelHeader = new JPanel();
        actionPanelHeader.setBorder(new TitledBorder("Acciones de la venta"));

        addProductButton =      new JButton("Agregar producto");
        removeProductButton =   new JButton("Eliminar producto");
        cancelOrderButton =         new JButton("Cancelar pedido");
        endOrderButton =         new JButton("Finalizar pedido");

        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Connect to controller
            	addProductToOrder addProduct = new addProductToOrder(frameController);
            	addProductLink.add(addProduct);
            	addProduct.currentOrder(currentOrder);
            	addProduct.setVisible(true);
            }
        });
         
        removeProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Connect to controller
            	removeProductToOrder removeProduct = new removeProductToOrder(frameController);
            	removeProductLink.add(removeProduct);
		        removeProduct.currentOrder(currentOrder);
		        removeProduct.setVisible(true);
            }
        });
        
        cancelOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Connect to controller
            	 orderController.actionOrder(bussinessEvent.CANCELAR_PEDIDO, currentOrder + "_");
                 dispose();
            }
        });
            
         
        endOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Connect to controller
            	try {
	            	Date fecha = new Date();
	                SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
	            	supplier = supplierTextField.getText();
	            	
	            	if(supplier.isEmpty())
	            		throw new NumberFormatException();
	            	
	            	 if(orderProductsTableModel.getRowCount() == 0)
	                     JOptionPane.showMessageDialog(addOrder.this, "Debes haber añadido algún producto al pedido.");
	                 else {
	                	//stateOrderChoice.getSelectedIndex();
	                	orderController.actionOrder(bussinessEvent.FINALIZAR_PEDIDO, currentOrder + "_" + supplier + "_" + formateador.format(fecha) + "_" + stateOrderChoice.getSelectedIndex() + "_");
	                	stateOrderChoice.select(0);
	                	setVisible(false);
	 	            	dispose();
	 	            	
	 	            	for(addProductToOrder o: addProductLink)
	 	            		o.dispose();
	 	            	
	 	            	for(removeProductToOrder o: removeProductLink)
	 	            		o.dispose();
	                 }
            	} catch(NumberFormatException e1) {
            		JOptionPane.showMessageDialog(null, Common.FINISHSALE_ERRORMESSAGE + System.lineSeparator() + "El pedido debe estar registrado a un proveedor. Por favor, introduzca el nombre del proveedor.");
            	}
            }
        });

        // Se añaden los botones al panel de acciones
        actionPanelHeader.add(addProductButton);
        actionPanelHeader.add(removeProductButton);
        actionPanelHeader.add(cancelOrderButton);
        actionPanelHeader.add(endOrderButton);

        /* Tabla de productos de la venta */
        orderProductListPanel = new JPanel();
        orderProductListPanel.setBorder(new TitledBorder("Lista de productos del pedido"));

        orderProductsTableModel = new DefaultTableModel(new Object[]{"Producto", "Cantidad"}, 0);
        orderProductsTable = new JTable(orderProductsTableModel) {
        	public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            };
        };
        orderProductsTable.setEnabled(true);
        orderProductsTable.setRowSelectionAllowed(true);
        
        orderProductListPanel.add(orderProductsTable);
        
        orderProductListScrollable = new JScrollPane(orderProductsTable);
        orderProductListScrollable.setPreferredSize(new Dimension(750, 350));

        orderProductListPanel.add(orderProductListScrollable);
        
        /* Panel de acciones del pie */
        actionPanelFooter = new JPanel(new GridLayout(1,2));
        
        /* Panel del estado del pedido */
        stateOrderPanel = new JPanel();
        stateOrderLabel = new JLabel("Estado del pedido: ");
        stateOrderChoice = new Choice();
        
        stateOrderChoice.add("NO FINALIZADO");
        stateOrderChoice.add("FINALIZADO");
        
        stateOrderPanel.add(stateOrderLabel);
        stateOrderPanel.add(stateOrderChoice);
        
        /* Panel de clientes */
        supplierPanel = new JPanel();
        supplierNameLabel = new JLabel("Nombre del proveedor: ");
        supplierTextField = new JTextField();
        supplierTextField.setPreferredSize(Tools.TEXTFIELDSIZE);
        
        supplierPanel.add(supplierNameLabel);
        supplierPanel.add(supplierTextField);
        
        actionPanelFooter.add(stateOrderPanel);
        actionPanelFooter.add(supplierPanel);

        mainPanel.add(actionPanelHeader, BorderLayout.PAGE_START);
        mainPanel.add(orderProductListPanel, BorderLayout.CENTER);
        mainPanel.add(actionPanelFooter, BorderLayout.PAGE_END);
        
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
		
		switch(PETICION) {
		case bussinessEvent.PEDIDO_ACTUAL:
			String[] contenido2 = cadena.split(",");
			if(currentOrder == Integer.parseInt(contenido2[0])) { 
				// Si el pedido no está vacio
				if(contenido2.length > 1) {
					updateOrderTable(contenido2[1]);
				} else {
					updateOrderTable("");
				}
			}
			break;
		}
    
    }
    
    /**
	 * Método de actualización de la tabla de pedidos.
	 * @param orderContents Contenido de la tabla de pedidos.
	 */
    
    private void updateOrderTable(final String orderContents) {
        String[] contents = null;

        if(!orderContents.equals(""))
            contents = orderContents.split("_");
        
        orderProductsTableModel.setRowCount(0);
        
        if(contents != null) {
            for(int i = 0; i < contents.length - 1; i++)
                if((i%3) == 0) {
                	orderProductsTableModel.addRow(new Object[]{contents[i], contents[i+2]});
                }
        }
    }
    
    /**
	 * Método que inicializa el atributo currentOrder, es decir, para que la aplicación sepa cual es el pedido
	 * que se esta realizando en este mismo instante.
	 * @param currentOrder Identificador del pedido.
	 */
    
    public void currentOrder(int currentOrder) {
    	this.currentOrder = currentOrder;
    }
    
    /**
	 * Método que sirve para "limpiar" las ventanas de aplicación una vez han sido utilizadas.
	 */
    
    public void clear() {
    	orderProductsTableModel.setRowCount(0);
    	supplierTextField.setEditable(true);
    	supplierTextField.setText("");
    }

}