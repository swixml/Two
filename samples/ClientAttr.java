
import org.swixml.SwingEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;

/**
 * The ClientAttr shows in the usage of client attributes in swixml tags.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @since swixml 0.98
 */
public class ClientAttr extends WindowAdapter {
  private SwingEngine swix = new SwingEngine( this );

  public JButton btn;
  public JTextArea ta;
  public Action show = new AbstractAction() {
    public void actionPerformed( ActionEvent e ) {
      ta.setText( "X:" + btn.getClientProperty( "X" ) + "\n" + "Y:" + btn.getClientProperty( "Y" ) );
    }
  };

  private ClientAttr() {
    try {
      swix.render( "xml/clientattr.xml" ).setVisible( true );
      swix.forget( "x" );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main( String[] args ) {
    new ClientAttr();
  }
}