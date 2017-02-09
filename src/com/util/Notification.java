package com.util;

import com.sun.awt.AWTUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Una clase notification para representar las notificaciones de la aplicación.
 * Cada notificación tiene como atributos una ventana correspondiente a la que muestra
 * la notificación, la opacidad inicial que tendrá la ventana y el tiempo (en milisegundos
 * que permanecerá activa).
 * @author JAVIER
 *
 */
public class Notification extends Thread {

	private Ventana ventana;		// Ventana de la notificación
	private float opacidad = 0.3f; 	// Opacidad inicial
	private final int TIEMPO = 200; // Tiempo (en milisegundos) que estará activa la ventana

	/**
	 * Constructor de la notificación de un parámetro.
	 * @param message El mensaje que se mostrará en la notificación.
	 */
	
	public Notification(String message) {
		ventana = new Ventana();
		AWTUtilities.setWindowOpacity(ventana, 0.0f);
		ventana.agregarTexto(message, 2);
		ventana.setVisible(true);
	}

	/**
	 * Función que sirve para agregar texto a la notificación de un color determinado.
	 * @param msj Mensaje que se mostrará en la notificación.
	 * @param tipoColor Color en el que se mostrará el mensaje de la notificación.
	 */
	public void agregarTexto(String msj, int tipoColor) {
		ventana.agregarTexto(msj, tipoColor);
	}

	/** 
	 * Método run de la noticación, que al extender de un Thread, es necesario implementarlo.
	 */
	
	@Override
	public void run() {
		try {
			hacerVisible();
			Thread.sleep(TIEMPO);
			desvanecer();
			if (opacidad == 0.0) {
				ventana.dispose();
				ventana.setVisible(false);
			}
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}

	/**
	 * Función que sirve para hacer visible la notificación.
	 * @throws InterruptedException
	 */
	
	private void hacerVisible() throws InterruptedException {
		opacidad = 0.3f;
		while (opacidad < 1) {
			AWTUtilities.setWindowOpacity(ventana, opacidad);
			opacidad += 0.03f;
			Thread.sleep(20);
		}
	}

	/**
	 * Función que sirve para hacerse desvanecer la notificación.
	 * @throws InterruptedException
	 */
	
	private void desvanecer() throws InterruptedException {
		opacidad = 1.0f;
		while (opacidad > 0) {
			AWTUtilities.setWindowOpacity(ventana, opacidad);
			opacidad -= 0.03f;
			Thread.sleep(20);
		}

	}

	@SuppressWarnings("serial")
	private class Ventana extends JDialog {

		private final int BARRA_DE_ESTADO = 30; // Tamaño de la barra de estado
												// en windows
		private SimpleAttributeSet attrib;
		private JScrollPane scrollPane;
		private JTextPane textoPane;

		public Ventana() {
			iniciarComponentes();
			ubicacionVentana();
			attrib = new SimpleAttributeSet();
		}

		private void ubicacionVentana() {
			int tamanioX = getWidth();
			int tamanioY = getHeight();
			int maxX = (int) Toolkit.getDefaultToolkit().getScreenSize()
					.getWidth();
			int maxY = (int) Toolkit.getDefaultToolkit().getScreenSize()
					.getHeight();

			// ubicacion de la ventana
			setLocation(maxX - tamanioX, maxY - tamanioY - BARRA_DE_ESTADO);
		}

		private void iniciarComponentes() {
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			scrollPane = new JScrollPane();
			textoPane = new JTextPane();

			setAlwaysOnTop(true); // siempre arriba
			setPreferredSize(new java.awt.Dimension(280, 120)); // tamaño de la
																// ventana
			setResizable(false); // no se puede modificar el tamaño
			setUndecorated(true); // no tiene los controles de estado

			scrollPane.setAutoscrolls(true);
			textoPane.setEditable(false);
			scrollPane.setViewportView(textoPane);

			getContentPane().add(scrollPane, BorderLayout.CENTER);

			pack();
		}

		public void agregarTexto(String msj, int tipoColor) {
			try {
				StyleConstants.setForeground(attrib, getColorTexto(tipoColor));
				StyledDocument sd = textoPane.getStyledDocument();
				if (!textoPane.getText().isEmpty()) {
					sd.insertString(sd.getLength(), "n", attrib);
				}
				sd.insertString(sd.getLength(), msj, attrib);
			} catch (BadLocationException e) {
				System.err.println(e);
			}
		}

		private Color getColorTexto(int tipo) {
			switch (tipo) {
			case 0: // Verde
				return new Color(0, 255, 0);
			case 1: // Rojo
				return new Color(255, 0, 0);
			case 2: // Azul
				return new Color(0, 0, 255);
			default: // Negro
				return new Color(0, 0, 0);
			}
		}

	}

}