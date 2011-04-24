
import org.swixml.SwingEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The Accelerator shows in the usage of accelerators.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @since swixml (#101)
 */
public class Accelerator {
  private static final String DESCRIPTOR = "xml/accelerator.xml";
  SwingEngine swix = new SwingEngine( this );

  public Accelerator() throws Exception {
    swix.render( Accelerator.DESCRIPTOR ).setVisible( true );
  }

  public Action newAction = new AbstractAction() {
    public void actionPerformed( ActionEvent e ) {
      JOptionPane.showMessageDialog( swix.getRootComponent(), "Sorry, not implemented yet." );
    }
  };

  public Action aboutAction = new AbstractAction() {
    public void actionPerformed( ActionEvent e ) {
      JOptionPane.showMessageDialog( swix.getRootComponent(), "This is the Accelerator Example." );
    }
  };

  public static void main( String[] args ) {
    try {
      new Accelerator();
    } catch (Exception e) {
      System.err.println( e.getMessage() );
    }
  }

}
