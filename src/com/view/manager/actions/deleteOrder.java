package com.view.manager.actions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.controller.frameController;
import com.controller.orderController;
import com.controller.command.bussinessEvent;
import com.util.Common;
import com.util.Tools;

/**
 * Intefaz gráfica para eliminar pedidos.
 * @author Borja y Javier
 *
 */

@SuppressWarnings("serial")
public class deleteOrder extends JFrame {
	
	/* Controladores */
    private orderController orderController;		// Controlador del subsistema de pedidos
    
    /* Panel principal de la intefaz */
    private JPanel mainPanel;
    
    /* Panel de contenido */
    private JPanel contentPanel;
    
    private JLabel deleteOrderLabel;
    
    private JTextField deleteOrderField;

    /* Panel de acciones */
    private JPanel actionPanel;
    
    private JButton deleteOrderButton;

    /**
     * Constructor de la interfaz gráfica para eliminar pedidos.
     * @param cont 	Controlador de la interfaz gráfica.
     */
    
	public deleteOrder(frameController cont) {
		// TODO Auto-generated constructor stub
		super("Eliminar un pedido");
		this.orderController = (orderController) cont.getController(0);
		
		initUI();
		
	}
	
	/**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */

	private void initUI() {
		/* Panel principal de la interfaz */
    	mainPanel = new JPanel(new BorderLayout());

    	/* Panel de contenido */
        contentPanel = new JPanel();
        
        deleteOrderLabel = new JLabel("Introduzca id de pedido ");
        
        deleteOrderField = new JTextField();
        deleteOrderField.setPreferredSize(Tools.TEXTFIELDSIZE);
        
        /* Panel de acciones */
        actionPanel = new JPanel();
        
        deleteOrderButton = new JButton("Eliminar pedido");

        deleteOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // connect to controller
            	try {
            		orderController.actionOrder(bussinessEvent.ELIMINAR_PEDIDO, Integer.parseInt(deleteOrderField.getText()) + "_" );
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, Common.DELETEORDER_ERRORMESSAGE + System.lineSeparator() + "Los datos que has introducido no son correctos.");
				}
            	
                setVisible(false);
                dispose();
                
            }
        });
        
        // Se añaden los botones al panel de acciones
        actionPanel.add(deleteOrderButton);
        
        contentPanel.add(deleteOrderLabel);
        contentPanel.add(deleteOrderField);
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
		deleteOrderField.setText("");
	}

}