package com.view.vendor;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.controller.frameController;
import com.controller.command.bussinessEvent;
import com.util.Common;
import com.util.Notification;
import com.util.Tools;
import com.util.Watchable;
import com.util.Watcher;

/**
 * Interfaz gráfica del panel de ventas.
 * @author Borja y Javier
 *
 */

@SuppressWarnings("serial")
public class vendorPanel extends JPanel  implements Watcher {

	/* Controladores */
	private frameController controller;		// Controlador de la interfaz gráfica
	
	/* Panel principal de la interfaz */
	private JPanel mainPanel;
	
	/* Menú de administración de vendedor */
	vendorMenu menu;
	
	/* Thread que permite la concurrencia en todas las ventanas que se abran de la aplicación para
	 * mantener bien informados a todos los usuarios que utilicen la aplicación */
	private Thread concurrentThread = new Thread(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				try {
						Thread.sleep(2000);
						controller.reloadSales();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}});
	
	private JPanel saleTableHolder;
    private DefaultTableModel saleTableModel;
    private JTable saleTable;
    private JScrollPane saleScrollable;

    /**
     * Constructor de la interfaz gráfica del panel de ventas.
     * @param cont 	Controlador de la interfaz gráfica.
     */
    
	public vendorPanel(frameController cont) {		
		this.controller = cont;
		
	    this.controller.addWatcherToSale(this);
	    this.controller.addWatcherToUser(this);
	    
	    initUI();
	   
	}
	
	/**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */
	
	private void initUI() {
		/* Comienza la ejecución del hilo de concurrrencia */
		concurrentThread.start();
		
		/*Panel principal de la interfaz */
		mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(Tools.SCREEN_DIMENSION);
        
        /* Aqui se inicializan todos aquellos atributos que necesitan ser 
		 * inicializados y que no son controladores. 
		 * */
        menu = new vendorMenu(controller);
		
		saleTableHolder = new JPanel(new BorderLayout());
        saleTableHolder.setBorder(new TitledBorder("Ventas"));
        
        saleTableModel = new DefaultTableModel(new Object[]{"ID" , "Cliente", "Precio", "Estado", "Fecha"}, 0);
        saleTable = new JTable(saleTableModel) {
        	public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            };
        };
        
        saleScrollable = new JScrollPane(saleTable);
        saleTableHolder.add(saleScrollable);
		
	    mainPanel.add(menu, BorderLayout.NORTH);
	    mainPanel.add(saleTableHolder, BorderLayout.CENTER);    
	    this.add(mainPanel);
		
	}
	
	/**
	 * Método que cierra todas las ventanas cuando un usuario cierra la aplicación. Se llama desde el vendorPanel. 
	 */
	
	public void loggedUser(String username) {
    	menu.loggedUser(username);
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
    	
    	Notification nt;
    	
    	switch(PETICION) {
    	case bussinessEvent.ACTUALIZAR_VENTAS:
    		updateSaleTable(cadena);
    		break;
    	case bussinessEvent.ELIMINAR_VENTA:
    		nt = new Notification(Common.SUCCESFULL_DELETESALE_MESSAGE);
    		nt.start();
    		break;
    	case bussinessEvent.ERROR_DELETESALE:
    		JOptionPane.showMessageDialog(null, Common.DELETESALE_ERRORMESSAGE + System.lineSeparator() + cadena);
    		break;
    	case bussinessEvent.ERROR_SEARCHSALE:
    		JOptionPane.showMessageDialog(null, Common.SEARCHSALE_ERRORMESSAGE + System.lineSeparator() + cadena);
    		break;
    	case bussinessEvent.ERROR_MOSTRARCAMBIO:
    		JOptionPane.showMessageDialog(null, Common.SHOWEXCHANGE_ERRORMESSAGE + System.lineSeparator() + cadena);
    		break;
    	case bussinessEvent.AÑADIR_PRODUCTO_VENTA:
    		nt = new Notification(Common.SUCCESFULL_ADDPRODUCT_MESSAGE);
    		nt.start();
    		break;
    	case bussinessEvent.ERROR_ADDPRODUCT:
    		JOptionPane.showMessageDialog(null, Common.ADDPRODUCT_ERRORMESSAGE + System.lineSeparator() + cadena);
    		break;
    	case bussinessEvent.ELIMINAR_PRODUCTO_VENTA:
    		nt = new Notification(Common.SUCCESFULL_REMOVEPRODUCT_MESSAGE);
    		nt.start();
    		break;
    	case bussinessEvent.ERROR_REMOVEPRODUCT:
    		JOptionPane.showMessageDialog(null, Common.REMOVEPRODUCT_ERRORMESSAGE + System.lineSeparator() + cadena);
    		break;
    	case bussinessEvent.LOGOUTEVENT:
    		menu.dispose();
    		break;
    	}
    	
	}

	/**
	 * Método de actualización de la tabla de ventas.
	 * @param staffContents	Contenido de la tabla de ventas.
	 */
	
	private void updateSaleTable(final String saleContents) {
        String[] contents = null;

        if(!saleContents.equals(""))
            contents = saleContents.split("_");
        
        saleTableModel.setRowCount(0);
        
        if(contents != null) {
            for(int i = 0; i < contents.length - 1; i++)
                if((i%5) == 0) {
                	String finalizado = (contents[i+3].equalsIgnoreCase("false")) ? "No finalizado"  : "Finalizado";
                	if(finalizado.equalsIgnoreCase("Finalizado")) {
                		saleTableModel.addRow(new Object[]{contents[i], (contents[i+1].equalsIgnoreCase("null")) ? "Sin determinar" : contents[i+1], contents[i+2], (contents[i+3].equalsIgnoreCase("false")) ? "No finalizado"  : "Finalizado", (contents[i+4].equalsIgnoreCase("null")) ? "Sin determinar" : contents[i+4] });
                	}
                }
        }
        
    }

}