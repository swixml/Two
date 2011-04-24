
/**
 * Localization, also shows localization for the MAC OS
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @since swixml (#129)
 */
import org.swixml.SwingEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Localization extends WindowAdapter {

  private static final String DESCRIPTOR = "xml/localization.xml";
  SwingEngine swix = new SwingEngine( this );



  public Localization() throws Exception {
    swix.render( Localization.DESCRIPTOR ).setVisible( true );
  }


  public Action actionOptions = new AbstractAction() {
    public void actionPerformed( ActionEvent e ) {
      JOptionPane.showMessageDialog( swix.getRootComponent(), "Sorry, " +swix.getLocalizer().getString("mis_Options") + " not implemented yet.");
    }
  };

  public Action actionAbout = new AbstractAction() {
    public void actionPerformed( ActionEvent e ) {
      JOptionPane.showMessageDialog( swix.getRootComponent(), "This is the Mac OS X Example." );
    }
  };

 public Action actionHelp = new AbstractAction() {
    public void actionPerformed( ActionEvent e ) {
      JOptionPane.showMessageDialog( swix.getRootComponent(), "Help ...." );
    }
  };

  public Action actionExit = new AbstractAction() {
    public void actionPerformed( ActionEvent e ) {
      JOptionPane.showMessageDialog( swix.getRootComponent(), swix.getLocalizer().getString("mis_Exit"));
      Localization.this.windowClosing(null);
    }
  };

  /**
   * Invoked when a window is in the process of being closed.
   * The close operation can be overridden at this point.
   */
  public void windowClosing( WindowEvent e ) {
    super.windowClosing( e );
    System.exit(0);
  }

  public static void main( String[] args ) {
    try {
      new Localization();
    } catch (Exception e) {
      System.err.println( e.getMessage() );
    }
  }

}
