package com.view.login;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.controller.frameController;
import com.util.Tools;

/**
 * Interfaz gráfica de login de usuarios.
 * @author Borja y Javier
 *
 */

@SuppressWarnings("serial")
public class loginPanel extends JPanel {

	/*
	 * Para que eclipse encuentre res:
	 * 
	 * Run > Run Configurations > Classpath > Select User Entries > Advanced >
	 * Add Folders > Ok > seleccionar res
	 */	

	/* Controladores */
	private frameController controller;		// Controlador de interfaz gráfica

	/* Panel principal de la interfaz */
	private JPanel mainPanel;
	
	/* Icono de la aplicación */
	private String ICONURL = "res/icons/fflogo.png";

	/* Panel de Logo */
	private JPanel iconPanel;
	private JLabel iconLabel;

	/* Panel de Contenido */
	private JPanel contentPanel;
	private JPanel subContentPanel;
	private JLabel usernameLabel;
	private JTextField usernameField;
	private JLabel passLabel;
	private JPasswordField passField;

	/* Panel de acciones */
	private JPanel actionPanel;
	
	private JButton loginButton;
	private JButton quitButton;

	/**
     * Constructor de la interfaz gráfica del panel de login de usuarios.
     * @param cont 	Controlador de la interfaz gráfica.
     */
	
	public loginPanel(frameController cont) {
		this.controller = cont;

		initUI();
		
	}

	/**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */
	
	private void initUI() {
		/* Panel principal de la interfaz */
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(Tools.SCREEN_DIMENSION);

		/* Panel de Logo */
		iconPanel = new JPanel();
		iconLabel = new JLabel(new ImageIcon(ICONURL));

		iconPanel.add(iconLabel);

		/* Panel de Contenido */
		contentPanel = new JPanel(new GridLayout(13, 0));

		subContentPanel = new JPanel(new GridLayout(2, 2));
		usernameLabel = new JLabel("Nombre de usuario: ");
		usernameField = new JTextField();
		passLabel = new JLabel("Contraseña");
		passField = new JPasswordField();
		passField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loginEvent(usernameField.getText(),
						String.valueOf(passField.getPassword()));
				usernameField.setText(null);
				passField.setText(null);
			}
		});

		usernameField.setPreferredSize(Tools.TEXTFIELDSIZE);
		passField.setPreferredSize(Tools.TEXTFIELDSIZE);

		subContentPanel.add(usernameLabel);
		subContentPanel.add(usernameField);
		subContentPanel.add(passLabel);
		subContentPanel.add(passField);

		for (int i = 0; i < 13; i++) {
			if (i == 5)
				contentPanel.add(subContentPanel);
			else
				contentPanel.add(new JPanel());
		}

		/* Panel de acciones */
		actionPanel = new JPanel();
		
		loginButton = new JButton("Login");
		quitButton = new JButton("Salir");

		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// connect to controller
				controller.loginEvent(usernameField.getText(),
						String.valueOf(passField.getPassword()));
				usernameField.setText(null);
				passField.setText(null);
			}
		});

		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// connect to controller
				controller.quitEvent();
			}
		});

		// Se añaden los botones al panel de acciones
		actionPanel.add(loginButton);
		actionPanel.add(quitButton);

		mainPanel.add(iconPanel, BorderLayout.PAGE_START);
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(actionPanel, BorderLayout.PAGE_END);
		this.add(mainPanel);
		
	}

}