package com.view.admin.actions;

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
import com.controller.userController;
import com.controller.command.bussinessEvent;
import com.util.Common;
import com.util.Tools;

@SuppressWarnings("serial")
public class deleteUser extends JFrame {

	/* Controladores */
	private userController userController;		// Controlador de la interfaz gráfica

	/* Panel principal de la interfaz */
	private JPanel mainPanel;

	/* Panel de Contenido */
	private JPanel contentPanel;
	private JLabel removeUserLabel;
	private JTextField removeUserField;

	/* Panel de Acciones */
	private JPanel actionPanel;
	private JButton removeUserButton;

	/**
     * Constructor de la interfaz gráfica de eliminar usuario.
     * @param cont 	Controlador de la interfaz gráfica.
     */
	
	public deleteUser(frameController cont) {
		super("Dar de baja a un usuario");
		this.userController = (userController) cont.getController(3);

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
		removeUserButton = new JButton("Eliminar usuario");
		removeUserLabel = new JLabel("Introduzca el nombre de usuario");
		removeUserField = new JTextField();
		removeUserField.setPreferredSize(Tools.TEXTFIELDSIZE);

		/* Panel de acciones */
		actionPanel = new JPanel();
		
		removeUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// connect to controller
				try {
					if (removeUserField.getText().isEmpty())
						throw new NumberFormatException();

					userController.actionUser(bussinessEvent.ELIMINAR_USUARIO,
							removeUserField.getText() + "_");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane
							.showMessageDialog(
									null,
									Common.DELETEUSER_ERRORMESSAGE
											+ System.lineSeparator()
											+ "Los datos que has introducido no son correctos.");
				}
				
				setVisible(false);
				dispose();
			}
		});
		
		// Se añaden los botones al panel de acciones
		actionPanel.add(removeUserButton);

		contentPanel.add(removeUserLabel);
		contentPanel.add(removeUserField);
		contentPanel.add(actionPanel);

		mainPanel.add(contentPanel, BorderLayout.PAGE_START);
		this.add(mainPanel);

		this.setLocation(50, 100);
		this.setSize(Tools.ACTION_DIMENSION_MIN);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}