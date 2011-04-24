
import org.swixml.SwingEngine;

import java.awt.event.WindowAdapter;

/**
 * The XInclude class shows in simple way how to use xml includes.
 * <code>XInclude</code> extends the WindowAdapter and uses a SwingEngine to renders the GUI.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @since swixml 0.95
 */
public class XInclude extends WindowAdapter {
  private SwingEngine swix = new SwingEngine( this );


  private XInclude() {
    try {
      swix.render( "xml/xinclude.xml" ).setVisible( true );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main( String[] args ) {
    new XInclude();
  }
}

