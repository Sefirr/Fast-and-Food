/**
 * 
 */
package com;

import com.controller.frameController;
import com.model.Shop;
import com.model.impl.shopImp;
import com.view.mainFrame;

/**
 * Punto de entrada a la aplicación, en el que se inician los principales componentes de 
 * la aplicación.
 * 
 * @author Borja y Javier
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shop core = new shopImp();
        frameController controller = new frameController(core);
        mainFrame view = new mainFrame(controller);
        controller.addView(view);

        view.enable();
       
	}

}