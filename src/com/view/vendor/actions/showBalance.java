package com.view.vendor.actions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.controller.frameController;
import com.controller.command.bussinessEvent;
import com.util.Tools;
import com.util.Watchable;
import com.util.Watcher;

@SuppressWarnings("serial")
public class showBalance extends JFrame implements Watcher {

	/* Controladores */
	private frameController frameController;
	
	/* Panel principal de la interfaz */
    private JPanel mainPanel;
    
    /* Panel de contenido */
    private JPanel contentPanel;
    
    private JLabel textLabel;
    
    /* Panel de acciones */
    private JPanel actionPanel;
    
    private JButton doneButton;
    
    /**
     * Constructor de la interfaz gráfica para mostrar balance.
     * @param cont 	Controlador de la interfaz gráfica.
     */
    
    public showBalance(frameController cont) {
        super("Ver balance");
        this.frameController = cont;
        
        frameController.addWatcherToSale(this);
        
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
        
        textLabel = new JLabel("El balance total es: ");
        contentPanel.add(textLabel);
        
        /* Panel de acciones */        
        actionPanel = new JPanel();
        
        doneButton = new JButton("Aceptar");
        
        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showBalance.this.setVisible(false);
                showBalance.this.dispose();
            }
        });
        
        // Se añaden los botones al panel de acciones
        actionPanel.add(doneButton);
        
        mainPanel.add(contentPanel, BorderLayout.PAGE_START);
        mainPanel.add(actionPanel, BorderLayout.CENTER);
        this.add(mainPanel);
        
        this.setLocation(50,100);
        this.setSize(Tools.ACTION_DIMENSION_MIN);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    /**
	 * Método de notificación de los observadores, que son notificados por el modelo para refrescar todos
	 * aquellos componentes que necesitan ser actualizados, así como lanzar notifcaciones de éxito o error.
	 */

	@Override
	public void update(Watchable o, Object arg) {
		// TODO Auto-generated method stub
		String cadena = String.valueOf(arg);
    	String[] contenido = cadena.split(" ");
    	int PETICION = Integer.parseInt(contenido[0]);
		cadena = cadena.substring(contenido[0].length()+1);
		
		switch(PETICION) {
		case bussinessEvent.MOSTRAR_BALANCE:
			textLabel.setText("El balance total es: " + cadena);
			break;
		}
		
	}
	
}