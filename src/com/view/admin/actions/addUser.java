package com.view.admin.actions;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.controller.frameController;
import com.controller.userController;
import com.controller.command.bussinessEvent;
import com.util.Common;
import com.util.Tools;
import com.util.Tools.UserPermission;

/**
 * Interfaz gráfica para añadir usuario.
 * @author Borja y Javier
 *
 */

@SuppressWarnings("serial")
public class addUser extends JFrame {
	
	/* Controladores */
    private userController userController;		// Controlador del subsistema de usuarios
    
    /* Panel principal de la interfaz */
	private JPanel mainPanel;
    
	/* Panel de contenido */
	private JPanel contentPanel;  
	
    private	JLabel employeeNameLabel;
    private JLabel userNameLabel;
    private JLabel passLabel;
    private JLabel permissionLabel;
    private JLabel whitespace;

    private JTextField  employeeNameField;
    private JTextField userNameField;
    private JPasswordField passwordField;

    private	Choice permissionChoice;
    
    /* Panel de acciones */
    private JPanel actionPanel;
    
    private JButton addUserButton;

    /**
     * Constructor de la interfaz gráfica de añadir usuario.
     * @param cont 	Controlador de la interfaz gráfica.
     */

    public addUser(frameController cont) {
        super("Añadir usuario");
        this.userController = (userController) cont.getController(3);
        
        initUI();
        
    }
    
    /**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */

    private void initUI() {
    	/* Panel principal de la interfaz */
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new TitledBorder("Datos para dar de alta a usuario"));

        /* Aqui se inicializan todos aquellos atributos que necesitan ser 
		 * inicializados y que no son controladores. 
		 * */
        
        
        /* Panel de contenido */
        contentPanel = new JPanel(new GridLayout(8,0));
        employeeNameLabel = new JLabel("Nombre de usuario");
        userNameLabel = new JLabel("Nick de usuario");
        passLabel = new JLabel("Contraseña");
        permissionLabel = new JLabel("Puesto de Trabajo");
        
        employeeNameField =  new JTextField();
        userNameField = new JTextField();
        userNameField.setPreferredSize(Tools.TEXTFIELDSIZE);
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(Tools.TEXTFIELDSIZE);
        
        permissionChoice = new Choice();
        permissionChoice.add("Administrador");
        permissionChoice.add("Encargado");
        permissionChoice.add("Vendedor");
        whitespace = new JLabel("                                   ");

        contentPanel.add(employeeNameLabel);
        contentPanel.add(employeeNameField);
        contentPanel.add(userNameLabel);
        contentPanel.add(userNameField);
        contentPanel.add(passLabel);
        contentPanel.add(passwordField);
        contentPanel.add(permissionLabel);
        contentPanel.add(permissionChoice);
        contentPanel.add(whitespace);
        
        /* Panel de acciones */
        actionPanel = new JPanel();
        
        addUserButton = new JButton("Insertar usuario");
        
        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // connect to controller
            	try {
            		String permissionString = permissionChoice.getSelectedItem();
                	UserPermission permission;
                	if(permissionString.equalsIgnoreCase("Vendedor"))
                		permission = UserPermission.VENDOR;
                	else 
                		permission = (permissionString.equalsIgnoreCase("Encargado")) ? UserPermission.MANAGER : UserPermission.ADMIN;
                	
                	String employeeName = employeeNameField.getText();
                	String userName = userNameField.getText();
                	char[] passChar = passwordField.getPassword();
                	String password = new String(passChar);
                	
                	if(employeeName.isEmpty() || userName.isEmpty() || password.isEmpty())
                		throw new NumberFormatException();
                	
                	userController.actionUser(bussinessEvent.INSERTAR_USUARIO, permission.name() + "_" + employeeName + "_" + userName + "_"+ password + "_");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, Common.ADDUSER_ERRORMESSAGE + System.lineSeparator() + "Los datos que has introducido no son correctos.");
				}
            	
                setVisible(false);
                dispose();
            }
            
        });
        
        // Se añaden los botones al panel de acciones
        actionPanel.add(addUserButton);
        
        mainPanel.add(contentPanel, BorderLayout.PAGE_START);
        mainPanel.add(actionPanel, BorderLayout.CENTER);
        this.add(mainPanel);
        
        this.setLocation(50,100);
        this.setSize(Tools.ACTION_DIMENSION);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
        this.setVisible(true);
    }
    
}