package com.view.admin.actions;

import java.awt.BorderLayout;
import java.awt.Choice;
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
import com.controller.userController;
import com.controller.command.bussinessEvent;
import com.model.dto.TransferUser;
import com.util.Common;
import com.util.Tools;
import com.util.Watchable;
import com.util.Watcher;
import com.util.Tools.UserPermission;

/**
 *  Interfaz gráfica de búsqueda y modificación de usuarios.
 *  @author Borja y Javier
 *  
 */

@SuppressWarnings("serial")
public class editAndSearchUser extends JFrame implements Watcher {

	// Controladores */
	private userController userController;		// Controlador del subsistema de usuarios
	
	/* Panel principal de la interfaz */
	private JPanel mainPanel;
	
	/* Panel de contenido */
	private JPanel contentPanel;	
	
	private JLabel nombre;
	private JLabel employeeName;
	private JLabel password;
	private JLabel permission;
	
	private JTextField _nombre;
	private JTextField _employeeName;
	private JTextField _password;
	
	private Choice _permission;
	
	/* Panel de acciones */
	private JPanel actionPanel;
	
	private JButton btnModificarUsuario;
	private JButton btnBuscarUsuario;
	
	/**
     * Constructor de la interfaz gráfica de añadir usuario.
     * @param cont 	Controlador de la interfaz gráfica.
     */

	public editAndSearchUser(frameController cont) {
		super("Buscar y modificar usuario");
		this.userController = (userController) cont.getController(3);
		
		cont.addWatcherToUser(this);
		
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
		contentPanel.setBorder(new TitledBorder("Datos para modificar un usuario"));
		contentPanel.setLayout(new GridLayout(15,1));
	
		nombre = new JLabel("Nombre de usuario");
		employeeName = new JLabel("Nombre del trabajador");
		password = new JLabel("Contraseña");
		permission = new JLabel("Nivel de permisos");
		
		_nombre = new JTextField();
		_employeeName = new JTextField();
		_password = new JTextField();
		
		_permission = new Choice();
		_permission.add("Administrador");
		_permission.add("Encargado");
		_permission.add("Vendedor");
		_permission.add("Ninguno");
		
		/* Panel de acciones */
		actionPanel = new JPanel(new FlowLayout());
		
		btnBuscarUsuario = new JButton("Buscar usuario");
		btnModificarUsuario = new JButton("Modificar usuario");
		
		btnBuscarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// connect to controller
            	try {
            		
            		if(_nombre.getText().isEmpty())
            			throw new NumberFormatException();
            		
					@SuppressWarnings("unused")
					TransferUser currentUser;
					if(userController.actionUser(bussinessEvent.MOSTRAR_USUARIO, _nombre.getText() + "_") != null) {
						userController.actionUser(bussinessEvent.MOSTRAR_USUARIO, _nombre.getText() + "_");
						_employeeName.setEditable(false);
						_password.setEditable(true);
						_permission.setEnabled(true);
						btnModificarUsuario.setEnabled(true);
					}
            	} catch(NumberFormatException e1) {
            		JOptionPane.showMessageDialog(null, Common.SEARCHUSER_ERRORMESSAGE + System.lineSeparator() + "Los datos que has introducido no son correctos.");
            	}
            }
		});
		
		btnModificarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// connect to controller
            	UserPermission permission;
            	if(_permission.getSelectedItem().equalsIgnoreCase("Administrador"))
            		permission = UserPermission.ADMIN;
            	else if(_permission.getSelectedItem().equalsIgnoreCase("Encargado"))
            		permission = UserPermission.MANAGER;
            	else if(_permission.getSelectedItem().equalsIgnoreCase("Vendedor"))
            		permission = UserPermission.VENDOR;
            	else
            		permission = UserPermission.NONE;
            	
            	userController.actionUser(bussinessEvent.MODIFICAR_USUARIO, _nombre.getText() + "_" + _password.getText() + "_" + permission.name() + "_");
            }
		});
		
		// Se añaden los botones al panel de acciones		
		actionPanel.add(btnBuscarUsuario);
		actionPanel.add(btnModificarUsuario);
	
		contentPanel.add(employeeName);
		contentPanel.add(_employeeName);
		contentPanel.add(nombre);
		contentPanel.add(_nombre);
		contentPanel.add(password);
		contentPanel.add(_password);
		contentPanel.add(permission);
		contentPanel.add(_permission);
		contentPanel.add(new JLabel("                                   "));
		contentPanel.add(actionPanel);
		
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		this.add(mainPanel);
		
		this.setLocation(50, 100);
		this.setSize(Tools.WINDOW_DIMENSION);
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
    	case bussinessEvent.MOSTRAR_USUARIO:
    		updateUserInfo(cadena);
    		break;
    	}
    	
	}

	/**
	 * Método de actualización de la información del usuario a buscar
	 * @param userContents	Contenido de la información de usuarios.
	 */
	
	private void updateUserInfo(String userContents) {
        String[] contents = null;

        if(!userContents.equals(""))
            contents = userContents.split("_");

        if(contents != null) {
        	_nombre.setEditable(false);
	    	_employeeName.setText(contents[0]);
	    	_employeeName.setEditable(false);
	    	_password.setText(contents[3]);
	    	if(contents[2].equalsIgnoreCase("1"))
	    		_permission.select(0);
	    	else if(contents[2].equalsIgnoreCase("2"))
	    		_permission.select(1);
	    	else if(contents[2].equalsIgnoreCase("3"))
	    		_permission.select(2);
	    	else 
	    		_permission.select(3);
            	
        }
        
    }

	/**
	 * Método que sirve para "limpiar" las ventanas de aplicación una vez han sido utilizadas.
	 */
	
	public void clear() {
		_nombre.setText("");
		_employeeName.setText("");
		_nombre.setEditable(true);
		_employeeName.setEditable(false);
		_password.setEditable(false);
		_permission.setEnabled(false);
		btnModificarUsuario.setEnabled(false);
		_password.setText("");	
		_permission.select(0);
	}
	
}