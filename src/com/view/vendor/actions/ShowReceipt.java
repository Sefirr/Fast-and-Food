package com.view.vendor.actions;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Container;
import java.awt.FlowLayout;
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
import com.controller.saleController;
import com.controller.command.bussinessEvent;
import com.util.Common;
import com.util.Tools;
import com.util.Watchable;
import com.util.Watcher;



@SuppressWarnings("serial")
public class ShowReceipt extends JFrame implements Watcher {

    saleController controller;
    frameController framecontroller;

    private Container contentPane;
    private JPanel mainPanel;

    private double price;

    private JPanel contentPanel;

    private JPanel totalPricePanel;
    private JLabel totalPriceText;
    private JLabel totalPriceLabel;

    private JPanel formPanel;
    private JLabel payedAmountLabel;
    private JTextField payedAmountField;

    private JPanel differencePanel;
    private JLabel differenceTextLabel;
    private JLabel differenceLabel;

    private JPanel buttonPanel;
    private JLabel printReceiptLabel;
    private Choice receiptChoice;
    private JButton doneButton,
                    cancelButton;
    
    private String customer;
    
    /* Indicador de venta actual a añadir */
    private int currentSale;
    
    private String fecha;
    
	/* Total pagado por el cliente */
   	private Double pagado;
    

    public ShowReceipt(frameController framecontroler, saleController cont) {
        super("Factura");
        this.framecontroller = framecontroler;
        this.controller = cont;
        
        framecontroller.addWatcherToSale(this);

        initUI();
    }

    private void initUI() {
        contentPane = this.getContentPane();

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new TitledBorder("Generar Factura y Cambio"));

        contentPanel = new JPanel(new GridLayout(2,0));
        totalPricePanel = new JPanel();

        totalPriceText = new JLabel("Subtotal: ");
        totalPriceLabel = new JLabel();

        totalPricePanel.add(totalPriceText);
        totalPricePanel.add(totalPriceLabel);

        formPanel = new JPanel(new FlowLayout());

        payedAmountLabel = new JLabel("Cantidad Pagada: ");

        differenceLabel = new JLabel();
        payedAmountField = new JTextField();
        payedAmountField.setPreferredSize(Tools.TEXTFIELDSIZE);

        // TODO maybe remove - not needed
        payedAmountField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Connect to controller
            	try {
            		if(payedAmountField.getText().isEmpty())
            			throw new NumberFormatException();
            		
	            	pagado = Double.valueOf(payedAmountField.getText());
	        	
	            	controller.actionSale(bussinessEvent.MOSTRAR_CAMBIO, pagado + "_" + currentSale + "_");
            	} catch(NumberFormatException e1) {
            		JOptionPane.showMessageDialog(null, Common.SHOWEXCHANGE_ERRORMESSAGE + System.lineSeparator() + "La cantidad introducida está en un formato que no es correcto.");
            	}
               
            }
        });

        formPanel.add(payedAmountLabel);
        formPanel.add(payedAmountField);

        differencePanel = new JPanel();
        differenceTextLabel = new JLabel("Total a devolver: ");
        differencePanel.add(differenceTextLabel);
        differencePanel.add(differenceLabel);

        contentPanel.add(totalPricePanel);
        contentPanel.add(formPanel);
        contentPanel.add(differencePanel);

        buttonPanel = new JPanel(new FlowLayout());
        printReceiptLabel = new JLabel("Imprimir Factura:");
        receiptChoice = new Choice();
        doneButton = new JButton("Aceptar");
        cancelButton = new JButton("Cancelar");

        receiptChoice.add("Sin ticket");
        receiptChoice.add("Simplificado");
        receiptChoice.add("Completo");

        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // confirms the buffer sale and prints receipt if checked
                //controller.submitSaleEvent(receiptChoice.getSelectedIndex());
            	controller.actionSale(bussinessEvent.FINALIZAR_VENTA, currentSale + "_" + customer + "_" + fecha + "_");
                receiptChoice.select(0);
                ShowReceipt.this.setVisible(false);
                ShowReceipt.this.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // should drop the buffer - maybe not needed
                receiptChoice.select(0);
                ShowReceipt.this.setVisible(false);
                ShowReceipt.this.dispose();
            }
        });

        buttonPanel.add(printReceiptLabel);
        buttonPanel.add(receiptChoice);
        buttonPanel.add(doneButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(contentPanel, BorderLayout.PAGE_START);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(mainPanel);
    }

    public void display(Double _price) {
        this.price = _price;
        totalPriceLabel.setText(String.valueOf(price));
        differenceLabel.setText("0.0");

        this.setLocationRelativeTo(null);
        this.setSize(Tools.ACTION_DIMENSION);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }

	public void setSaleIdentifier(int currentSale) {
		// TODO Auto-generated method stub
		this.currentSale = currentSale;
	}

	public void setCustomer(String customer) {
		// TODO Auto-generated method stub
		this.customer = customer;
	}

	public void setDate(String format) {
		// TODO Auto-generated method stub
		this.fecha = format;
	}
	
	@Override
	public void update(Watchable o, Object arg) {
		// TODO Auto-generated method stub
		String cadena = String.valueOf(arg);
    	String[] contenido = cadena.split(" ");
    	int PETICION = Integer.parseInt(contenido[0]);
		cadena = cadena.substring(contenido[0].length()+1);
		
		switch(PETICION) {
		case bussinessEvent.MOSTRAR_CAMBIO:
			String[] contenido2 = cadena.split(",");
			if(currentSale == Integer.parseInt(contenido2[0])) {
				differenceLabel.setText(String.valueOf(contenido2[1]));
				payedAmountField.setText(null);
			}
		break;
		}
		
	}
}
