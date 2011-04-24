import org.swixml.SwingEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The Actions class shows how to use the
 * <code>Actions</code> and <code>ActionCommand</code> attributes
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * @since swixml #065
 */
public class Actions extends WindowAdapter implements ActionListener {
  private SwingEngine swix;

  public JMenuItem mi_exit, mi_save;
  public JPanel pnl_North;
  //
  // For every Actions, there needs to be a
  // public AbstractAction member variables with an anonymous inner class instantiation
  //

  /** <code>Action</code> newAction handles the <em>new</em> action attribute */
  public Action newAction = new AbstractAction() {

    public void actionPerformed(ActionEvent e) {
      System.out.println( "New" ); // show the access outer class member ..
      this.setEnabled( false ); // disables ALL buttons that are tied to this action
    }
  };

  /** <code>Action</code> modifyAction handles the <em>modify</em> action attribute */
  public Action openAction = new AbstractAction() {
    /** Invoked when an action occurs. */
    public void actionPerformed(ActionEvent e) {
      System.out.println( "Open" );
    }
  };

  /** <code>Action</code> petAction handles the <em>combobox</em> */
  public Action petAction = new AbstractAction() {
     public void actionPerformed(ActionEvent e) {
       System.out.println( ((JComboBox) e.getSource()).getSelectedItem().toString() );
     }
   };


  /**
   * Constructs a new Actions object, registering action handlers for center_panel components.
   */
  private Actions() {
    try {
      swix = new SwingEngine( this );
      swix.render( "xml/actions.xml" );

      // at this point all AbstractActions are linked with the button etc.
      // ActionCommands however need to be linked manually, see below ...

      // add this class as an action listener to all buttons inside the panel with the id = center_panel
      swix.setActionListener( pnl_North, this );
      // add this class as an action listener to MenuItem with the id = mi_exit.
      mi_exit.addActionListener( this );
      // add this class as an action listener to MenuItem with the id = mi_save
      mi_save.addActionListener( this );
      //
      // Note, the mi_about MenuItem was not linked at all so far. Therefore, no action is performed when this
      // menu item gets requested.
      // The Toolbar button with the Actions="newAction" attribute is covered twice,
      // during parsing the AbstactAction newAction is linked in and later, the setActionListener() adds
      // this object's actionPerformed(). Therefore, when clicked, both  actionPerformed() methods are getting called
      //
      swix.getRootComponent().setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //
  //  Implement ActionListener
  //

  /**
   * Invoked when an action occurs.
   */
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    if ("AC_EXIT".equals( command )) {
      this.windowClosing( null );
    } else if ("AC_SAVE".equals( command )) {
      System.out.println( "Save" );
    } else {
      System.out.println( "Click" );
    }
  }

  //
  //  Overwrite Superclass implementation
  //

  /**
   * Invoked when the user attempts to close the window
   * from the window's system menu.  If the program does not
   * explicitly hide or dispose the window while processing
   * this event, the window close operation will be cancelled.
   */
  public void windowClosing(WindowEvent e) {
    System.out.println( "Good Bye!" );
    super.windowClosing(e);
    System.exit( 0 );
  }

  //
  //  Make the class bootable
  //

  public static void main(String[] args) {
    SwingEngine.DEBUG_MODE=true;
    new Actions();
  }
}
