import java.awt.Container;

import org.swixml.SwingEngine;

// $Id: MenuBarWithConstraints.java,v 1.1 2005/08/22 21:20:01 tichy Exp $
/**
 * Sample program to show a menubar with constraints attribute in it.
 * 
 */
public class MenuBarWithConstraints {

	/**
	 * @param args
	 * @throws Exception if something goes wrong
	 */
	public static void main(String[] args) throws Exception {
		SwingEngine se = new SwingEngine();
		Container container = se.render("xml/menu-bar.xml");
		container.setVisible(true);
	}

}
