package com.view.admin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.controller.frameController;
import com.controller.userController;
import com.controller.command.bussinessEvent;
import com.model.dto.TransferUser;
import com.util.Tools;
import com.view.admin.actions.addUser;
import com.view.admin.actions.approveOrder;
import com.view.admin.actions.deleteUser;
import com.view.admin.actions.editAndSearchUser;

/**
 * Interfaz gráfica del menú de administración
 * @author Javier
 *
 */

@SuppressWarnings("serial")
public class adminMenu extends JPanel {
	
	/* Controladores */
	private frameController frameController; 	// Controlador de la interfaz gráfica
    private userController userController;		// Controlador del subsistema de usuarios
    
    /* Panel principal de la interfaz */
	private JPanel mainPanel;  
        
    /* Panel de acciones de un administrador */
    private JPanel adminActions;
    
    private JButton addUserButton; 
    private JButton removeUserButton;
    private JButton editAndSearchUserButton;
    private JButton validateOrderButton;
    private JButton logoutButton;
    
    /* Usuario logueado en la aplicación */
    private String _loggedUser;
    
    /* Link a todas aquella ventanas que se abren individualmente por separado de la aplicación */
    private ArrayList<addUser> addUserLink;
    private ArrayList<deleteUser> deleteUserLink;
    private ArrayList<editAndSearchUser> editAndSearchUserLink; 
    private ArrayList<approveOrder> approveOrderLink;

    /**
     * Constructor de la interfaz gráfica del menú de administración.
     * @param cont 	Controlador de la interfaz gráfica.
     */
    
    public adminMenu(frameController cont) {
        this.frameController = cont;
        this.userController = (userController) cont.getController(3);
   
        initUI();
        
    }

    /**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */
    
	private void initUI() {	
		/* Panel principal de la interfaz */
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(Tools.MENU_DIMENSION);
		
		/* Aqui se inicializan todos aquellos atributos que necesitan ser 
		 * inicializados y que no son controladores. 
		 * */        
        this.addUserLink = new ArrayList<addUser>();
        this.deleteUserLink = new ArrayList<deleteUser>();
        this.editAndSearchUserLink = new ArrayList<editAndSearchUser>();
        this.approveOrderLink = new ArrayList<approveOrder>();
		
		/* Panel de Acciones */
		adminActions = new JPanel();
        adminActions.setBorder(new TitledBorder("Opciones de administración"));
        
        addUserButton =         new JButton("Insertar usuario");
        removeUserButton =      new JButton("Eliminar usuario");
        editAndSearchUserButton =        new JButton("Modificar y/o buscar usuario");
        validateOrderButton =   new JButton("Confirmar pedido");
        logoutButton = new JButton("Logout");
        
        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 addUser aw = new addUser(frameController); 
                 addUserLink.add(aw);
                 
                 aw.setVisible(true);
            }
        });
        
        removeUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 deleteUser dw = new deleteUser(frameController); 
                 deleteUserLink.add(dw);
                 
                 dw.setVisible(true);
            }
        });
   
        editAndSearchUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	editAndSearchUser editAndSearchUser = new editAndSearchUser(frameController);
            	editAndSearchUserLink.add(editAndSearchUser);
            	
            	editAndSearchUser.clear();
            	editAndSearchUser.setVisible(true);
            }
        });
        
        validateOrderButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				approveOrder approveOrder = new approveOrder(frameController);
				approveOrderLink.add(approveOrder);
				
				approveOrder.clear();
				approveOrder.setVisible(true);
			}
        	
        });
        
        logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TransferUser buscado;
				buscado = (TransferUser) userController.actionUser(bussinessEvent.MOSTRAR_USUARIO, _loggedUser + "_");
				frameController.logoutEvent(buscado.getUserName(), buscado.getPassword());		
			}
        	
        });

        /* Se añaden los botones al panel de acciones de adminstración */
        adminActions.add(addUserButton);
        adminActions.add(removeUserButton);
        adminActions.add(editAndSearchUserButton);
        adminActions.add(validateOrderButton);
        adminActions.add(logoutButton);
        
        mainPanel.add(adminActions, BorderLayout.PAGE_START);
        this.add(mainPanel);
		
	}
	
	/**
	 * Método que inicializa el atributo _loggedUser, es decir, para que la aplicación sepa cual es el usuario
	 * que está logueado.
	 * @param username Usuario logueado en la aplcación.
	 */
	
	public void loggedUser(String username) {
    	this._loggedUser = username;
	}

	/**
	 * Método que cierra todas las ventanas cuando un usuario cierra la aplicación. Se llama desde el adminPanel. 
	 */
	
	public void dispose() {
		for(addUser o: addUserLink) {
			o.dispose();
		}
		
		for(deleteUser o: deleteUserLink) {
			o.dispose();
		}
		
		for(editAndSearchUser e: editAndSearchUserLink) {
			e.dispose();
		}
		
		for(approveOrder e: approveOrderLink) {
			e.dispose();
		}
	}

}