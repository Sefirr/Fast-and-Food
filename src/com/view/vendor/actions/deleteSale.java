package com.view.vendor.actions;

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
import com.controller.saleController;
import com.controller.command.bussinessEvent;
import com.util.Common;
import com.util.Tools;
/**
 * Interfaz gráfica para eliminar ventas.
 * @author Borja y Javier
 *
 */
@SuppressWarnings("serial")
public class deleteSale extends JFrame {
    
	/* Controladores */
    private saleController saleController;		// Controlador del subsistema de ventas
    
    /* Panel principal de la interfaz */
    private JPanel mainPanel;
    
    /* Panel de contenido */
    private JPanel contentPanel;
    
    private JLabel deleteSaleLabel;
    
    private JTextField deleteSaleField;

    /* Panel de acciones */
    private JPanel actionPanel;
    
    private JButton deleteSaleButton;

    /**
     * Constructor de la interfaz gráfica para eliminar ventas.
     * @param cont 	Controlador de la interfaz gráfica.
     */
    
    public deleteSale(frameController cont) { 
    	super("Eliminar una venta");
        saleController = (saleController) cont.getController(1);
        
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
     
        deleteSaleLabel = new JLabel("Introduzca id de venta ");
        
        deleteSaleField = new JTextField();
        deleteSaleField.setPreferredSize(Tools.TEXTFIELDSIZE);

        /* Panel de acciones */
        actionPanel = new JPanel();
        
        deleteSaleButton = new JButton("Eliminar venta");
        
        deleteSaleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // connect to controller
            	try {
            		saleController.actionSale(bussinessEvent.ELIMINAR_VENTA, Integer.parseInt(deleteSaleField.getText()) + "_");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, Common.DELETESALE_ERRORMESSAGE + System.lineSeparator() + "Los datos que has introducido no son correctos.");
				} 
            	
            	setVisible(false);
                dispose();
            }
        });
        
        // Se añaden los botones al panel de acciones
        actionPanel.add(deleteSaleButton);
        
        contentPanel.add(deleteSaleLabel);
        contentPanel.add(deleteSaleField);
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
    	deleteSaleField.setText("");
	}
    
}