package com.view.manager.actions;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.controller.frameController;
import com.controller.orderController;
import com.controller.command.bussinessEvent;
import com.util.Common;
import com.util.Tools;

/**
 * Interfaz gráfica para añadir productos al pedido.
 * @author Borja y Javier
 *
 */

@SuppressWarnings("serial")
public class addProductToOrderModify extends JFrame {
    
	/* Controladores */
    private orderController orderController;		// Controlador  del subsistema de pedidos
    
    /* Panel principal de la interfaz */
    private JPanel mainPanel;
    
    /* Panel de contenido */
    private JPanel contentPanel;
  
    private	JLabel productIdLabel;
    private JLabel productNameLabel;
    private JLabel productTagLabel;
    private JLabel productAmountLabel;
    private JLabel productPriceLabel;
    private JLabel whitespace;
    
    private JTextField productIdField;
    private JTextField  productNameField;
    private JTextField productTagField;
    private JTextField productAmountField;
    private JTextField productPriceField;
    
    /* Panel de acciones */
    private JPanel actionPanel;
    
    private JButton addProductButton;
    
    /* Indicador del pedido actual a añadir */
    private int currentOrder;

    /**
     * Constructor de la interfaz gráfica para añadir productos al pedido.
     * @param cont 	Controlador de la interfaz gráfica.
     */
    
    public addProductToOrderModify(frameController cont) {
        super("Añadir producto");
        this.orderController = (orderController) cont.getController(0);
        
        initUI();
        
    }
    
    /**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */

    private void initUI() {
    	/* Panel principal de la interfaz */
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new TitledBorder("Datos para añadir producto"));
        
        /* Panel de contenido */
        contentPanel = new JPanel(new GridLayout(10,0));
        
        productIdLabel = new JLabel("ID del producto");
        productNameLabel = new JLabel("Nombre del producto");
        productTagLabel = new JLabel("Etiqueta");
        productAmountLabel = new JLabel("Cantidad");
        productPriceLabel = new JLabel("Precio");
        whitespace = new JLabel("                                   ");
        
        productIdField = new JTextField();
        productIdField.setPreferredSize(Tools.TEXTFIELDSIZE);
        productNameField =  new JTextField();
        productNameField.setPreferredSize(Tools.TEXTFIELDSIZE);
        productTagField = new JTextField();
        productTagField.setPreferredSize(Tools.TEXTFIELDSIZE);
        productAmountField = new JTextField();
        productAmountField.setPreferredSize(Tools.TEXTFIELDSIZE);
        productPriceField = new JTextField();
        productPriceField.setPreferredSize(Tools.TEXTFIELDSIZE);

        contentPanel.add(productIdLabel);
        contentPanel.add(productIdField);
        contentPanel.add(productNameLabel);
        contentPanel.add(productNameField);
        contentPanel.add(productTagLabel);
        contentPanel.add(productTagField);
        contentPanel.add(productAmountLabel);
        contentPanel.add(productAmountField);
        contentPanel.add(productPriceLabel);
        contentPanel.add(productPriceField);
        contentPanel.add(whitespace);
        
        /* Panel de acciones */
        actionPanel = new JPanel();
        
        addProductButton = new JButton("Insertar producto");
        
        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // connect to controller
            	try {            		
	            	int productId =  Integer.parseInt(productIdField.getText());
	            	String productName = productNameField.getText();
	            	String productTag = productTagField.getText();
	            	int productAmount = Integer.parseInt(productAmountField.getText());
	            	Double productPrice = Double.valueOf(productPriceField.getText());
	            	
	            	if(productName.isEmpty() || productTag.isEmpty())
	            		throw new NumberFormatException();
	            	
	            	orderController.actionOrder(bussinessEvent.AÑADIR_PRODUCTO_PEDIDO_MODIFY, productId + "_" + productName + "_" + productTag + "_" + productAmount + "_" + productPrice + "_" + currentOrder + "_");
            	} catch(NumberFormatException e1) {
            		JOptionPane.showMessageDialog(null, Common.ADDPRODUCT_ERRORMESSAGE + System.lineSeparator() + "Los datos que has introducido no son correctos.");
            	}
            	
                setVisible(false);
                dispose();
            }
            
        });
        
        // Se añaden los botones al panel de acciones
        actionPanel.add(addProductButton);
        
        mainPanel.add(contentPanel, BorderLayout.PAGE_START);
        mainPanel.add(actionPanel, BorderLayout.CENTER);
        this.add(mainPanel);
        
        this.setLocation(150,150);
        this.setSize(Tools.ACTION_DIMENSION);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
    }
    
    /**
	 * Método que inicializa el atributo currentOrder, es decir, para que la aplicación sepa cual es el pedido
	 * que se esta realizando en este mismo instante.
	 * @param currentOrder Identificador del pedido.
	 */
    
    public void currentOrder(int currentOrder) {
    	this.currentOrder = currentOrder;
    }
    
}