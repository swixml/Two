import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
// $Id: MacAboutAction.java,v 1.1 2004/10/05 21:32:34 tichy Exp $

/**
 * Externalized AboutAction taken from {@link HelloMac}.
 * 
 * @author $Author: tichy $
 */
public class MacAboutAction extends AbstractAction {
    public void actionPerformed( ActionEvent e ) {
        JOptionPane.showMessageDialog( MacTest.getSwix().getRootComponent(), "This is the Mac OS X Example." );
      }
   
}
