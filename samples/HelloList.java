import org.swixml.SwingEngine;

import javax.swing.JList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class HelloList extends WindowAdapter {
	
	private JList mList; /*instantiated by swixml when rendering the UI */

	private HelloList() throws Exception {
		new SwingEngine( this ).render( "./xml/hellolist.xml" ).setVisible( true );
		System.out.println( mList.size() );	
		mList.addListSelectionListener( new ListSelectionListener() {
			public void valueChanged( final ListSelectionEvent e) {
			  	System.out.println( mList.getSelectedValue() );	
			}
		}); 
	}

	/**
	   * Invoked when a window is in the process of being closed.
	   * The close operation can be overridden at this point.
	   */
	  public void windowClosing(final WindowEvent e) {
	    super.windowClosing(e);
	    System.exit(0);
	  } 
	
	/** Makes the class bootable */
	public static void main( final String[] args ) throws Exception {
		new HelloList();
	}
	
}