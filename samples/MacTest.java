
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
public class MacTest  extends WindowAdapter {
  private static SwingEngine swix;

  private MacTest() throws Exception {
    swix= new SwingEngine( this );
    swix.render( "xml/mactester.xml" );    
    swix.getRootComponent().setVisible( true );
  }

  
  //
  //  Make the class bootable
  //
  public static void main( String[] args ) throws Exception {
    new MacTest();
  }

/**
 * @return
 */
public static SwingEngine getSwix() {
    // TODO Auto-generated method stub
    return swix;
}


}