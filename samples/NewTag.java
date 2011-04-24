
import org.swixml.ConverterLibrary;
import org.swixml.SwingEngine;

import java.util.TimeZone;

/**
 * Extend the TagLib with a new Class and a new Converter
 */
public class NewTag extends SwingEngine {

  private NewTag() {
    //
    // Register a new new Converter,
    // Generally, Converters should be regsitered before Tags
    //
    ConverterLibrary.getInstance().register( TimeZone.class, new TimeZoneConverter() );
    //
    //  Register a Tag that uses a SwingEngine itself ...
    //
    this.getTaglib().registerTag( "xpanel", XPanel.class );
    try {
      this.getTaglib().registerTag( "redlabel", RedLabel.class );
    } catch (Exception e) {
      System.err.println( e.getMessage() );
    }

    try {
      render( "xml/newtag.xml" ).setVisible( true );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new NewTag();
  }
}
