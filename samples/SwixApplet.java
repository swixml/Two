
import org.swixml.SwingEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;


/**
 * The SwixmlApplet class shows how to use the SwixmlmlEngine
 * to create JApplets.
 * The XML descriptor needs to be referenced with an parameter like this:
 * xml=xml/SwinxmlApplet.xml
 * When ran locally using SUN's AppletViewer, the file needs to be made available here:
 * C:\Temlp\xml\...
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 */

public class SwixApplet extends JApplet {

  /** JTextField member gets instantiated through Swixml (look for id="tf" in the xml descriptor) */
  public JTextField tf;
  /**
   * Action appends a '#' to the textfields content.
   */
  public AbstractAction submit = new AbstractAction() {
    public void actionPerformed( ActionEvent e ) {
      tf.setText( tf.getText() + '#' );
    }
  };

  public void init() {

    super.init();
    try {
      String descriptorfile = this.getParameter( "xml" );
      if (descriptorfile == null) {
        descriptorfile = "xml/applet.xml";
      }
      new SwingEngine( this ).insert( new URL( getCodeBase(), descriptorfile ), this );
      this.setVisible( true );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
