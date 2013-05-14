import com.toedter.calendar.*;
import org.swixml.SwingEngine;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomTags extends WindowAdapter {


  public CustomTags() throws Exception {
    SwingEngine swix = new SwingEngine(this);
    swix.getTaglib().registerTag("Calendar", JCalendar.class);
    swix.render("xml/customtags.xml").setVisible(true);
  }

  /**
   * Invoked when a window is in the process of being closed.
   * The close operation can be overridden at this point.
   */
  public void windowClosing(WindowEvent e) {
    super.windowClosing(e);
    System.exit(0);
  }

  //
  //  Make the class bootable
  //
  public static void main(String[] args) throws Exception {
    new CustomTags();
  }
}
