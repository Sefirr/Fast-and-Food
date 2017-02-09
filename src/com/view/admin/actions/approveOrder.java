package com.view.admin.actions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.controller.frameController;
import com.controller.orderController;
import com.controller.command.bussinessEvent;
import com.model.dto.TransferOrder;
import com.model.dto.TransferProduct;
import com.util.Common;
import com.util.Tools;

@SuppressWarnings("serial")
public class approveOrder extends JFrame {
	
	/* Controladores */
	private frameController frameController;		// Controlador de la interfaz gráfica
    private orderController orderController;		// Controlador del subsistema de pedidos

    /* Panel principal de la interfaz */
	private JPanel mainPanel;
    
	/* Panel de contenido */
	private JPanel contentPanel;   
    private JLabel approveOrderLabel;
    private JTextField approveOrderField;

    /* Panel de acciones */
    private JPanel actionPanel;
    
    private JButton approveOrderButton;

    /**
     * Constructor de la interfaz gráfica de confirmación de pedidos.
     * @param cont 	Controlador de la interfaz gráfica.
     */
    
	public approveOrder(frameController cont) {
		// TODO Auto-generated constructor stub
		super("Confirmar pedido");
		this.frameController = cont;
		this.orderController = (orderController) cont.getController(0);
		
		initUI();
		
	}
	
	/**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */

	private void initUI() {
		/* Panel principal de la interfaz */  	
    	mainPanel = new JPanel(new BorderLayout());

    	/* Aqui se inicializan todos aquellos atributos que necesitan ser 
		 * inicializados y que no son controladores. 
		 * */
        
        
        /* Panel de contenido */
        contentPanel = new JPanel();
        approveOrderButton = new JButton("Confirmar pedido");
        approveOrderLabel = new JLabel("Introduzca id de pedido ");
        approveOrderField = new JTextField();
        approveOrderField.setPreferredSize(Tools.TEXTFIELDSIZE);

        /* Panel de acciones */
        actionPanel = new JPanel();

        approveOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // connect to controller
            	TransferOrder currentOrder;
            	try {
  					if((orderController.actionOrder(bussinessEvent.MOSTRAR_PEDIDO, Integer.parseInt(approveOrderField.getText()) + "_")!= null)  && !(((TransferOrder)orderController.actionOrder(bussinessEvent.MOSTRAR_PEDIDO, Integer.parseInt(approveOrderField.getText()) + "_")).getApproved())) {
  						orderController.actionOrder(bussinessEvent.CONFIRMAR_PEDIDO, Integer.parseInt(approveOrderField.getText()) + "_");
  						currentOrder = (TransferOrder) orderController.actionOrder(bussinessEvent.MOSTRAR_PEDIDO, Integer.parseInt(approveOrderField.getText()) + "_");
						ArrayList<TransferProduct> productList = currentOrder.getProductList();
						frameController.orderEvent(productList);
					} else {
						orderController.actionOrder(bussinessEvent.CONFIRMAR_PEDIDO, Integer.parseInt(approveOrderField.getText()) + "_");
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, Common.APPROVEORDER_ERRORMESSAGE + System.lineSeparator() + "Los datos que has introducido no son correctos.");
				}
            	
                setVisible(false);
                dispose();
            }
        });
        
        // Se añaden los botones al panel de acciones
        actionPanel.add(approveOrderButton);
        
        contentPanel.add(approveOrderLabel);
        contentPanel.add(approveOrderField);
        contentPanel.add(actionPanel);

        mainPanel.add(contentPanel, BorderLayout.PAGE_START);
        this.add(mainPanel); 
        
        this.setLocation(50, 100);
        this.setSize(Tools.ACTION_DIMENSION_MIN);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * Método que sirve para "limpiar" las ventanas de aplicación una vez han sido utilizadas.
	 */
	
	public void clear() {
		approveOrderField.setText("");
	}

}