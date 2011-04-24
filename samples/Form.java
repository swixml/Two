
import org.swixml.SwingEngine;


/**
 * The Form class shows how to do a simple JGoodies FormLayout
 */
public class Form extends SwingEngine {
  /** Default ctor for a SwingEngine. */

  private Form() {
    try {
      render( "xml/form.xml" ).setVisible( true );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new Form();
  }
}