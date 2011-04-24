
import org.swixml.SwingEngine;

import javax.swing.*;

/**
 * This file contains proprietary information of CarlsbadCubes
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2002-2003
 *
 *
 *
 * Date: Feb 28, 2003
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * @since
 */

public class XPanel extends JPanel {

  private SwingEngine swix = new SwingEngine( this );


  public void setXml(String resource) {
    try {
      swix.insert( "xml/" + resource, this );
    } catch (Exception e) {
      System.err.println( e.getMessage() );
    }
  }
}
