import org.swixml.SwingEngine;

import javax.swing.*;

public class HelloWorldnoAction {
  /**
   * submit counter
   */
  private int clicks;

  /**
   * JTextField member gets instantiated through Swixml (look for id="tf" in xml descriptor)
   */
  public JTextField tf;

  /**
   * Jlabel to display number of button clicks
   */
  public JLabel cnt;

  /**
   * bound, using an element's action attribute, which was set to submit.
   */
  public void submit() {

    tf.setText(tf.getText() + '#');
    cnt.setText(String.valueOf(++clicks));
  }


  /**
   * Renders UI at construction
   */
  private HelloWorldnoAction() throws Exception {
    new SwingEngine(this).render("xml/helloworld.xml").setVisible(true);
  }

  /**
   * Makes the class bootable
   */
  public static void main(String[] args) throws Exception {
    new HelloWorldnoAction();
  }
}

