
import org.swixml.SwingEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.IOException;

import com.apple.eio.FileManager;

/**
 * The HelloMac class shows a couple of the Mac specifics exposed
 * <code>HeeloMac</code> renders the GUI, which is described in hellomac.xml
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @since swixml 1.1
 */
public class HelloMac  extends WindowAdapter {
  private SwingEngine swix;

  private HelloMac() throws Exception {
    swix= new SwingEngine( this );
    swix.render( "xml/hellomac.xml" );    
    swix.getRootComponent().setVisible( true );
  }

  public Action actionAbout = new AbstractAction() {
    public void actionPerformed( ActionEvent e ) {
      JOptionPane.showMessageDialog( swix.getRootComponent(), "This is the Mac OS X Example." );
    }
  };

  public Action actionHelp = new AbstractAction() {
    public void actionPerformed( ActionEvent e ) {
      try {
        FileManager.openURL("http://www.swixml.org/apidocs/index.html");
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  };

  public Action actionExit = new AbstractAction() {
    public void actionPerformed( ActionEvent e ) {
      JOptionPane.showMessageDialog( swix.getRootComponent(), swix.getLocalizer().getString("mis_Exit"));
      HelloMac.this.windowClosing(null);
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

  //
  //  Make the class bootable
  //
  public static void main( String[] args ) throws Exception {
    new HelloMac();
  }
}