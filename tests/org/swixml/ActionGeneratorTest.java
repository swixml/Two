package org.swixml;

import junit.framework.TestCase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class ActionGeneratorTest extends TestCase {

  public static final String DESCRIPTOR = "xml/action.xml";
  private Container container;
  private JButton btn1, btn2;
  private int counter;

  public Action submitAction = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      ActionGeneratorTest.this.submit();
    }
  };

  public ActionGeneratorTest() {
    super("Test auto generation of Action wrappers");
  }

  public ActionGeneratorTest(String s) {
    super(s);
  }

  public void submit() {
    counter++;
  }

  /**
   * Renders the test GUI into the container field.<br>
   * Note: Like with every testcase, the setup method is going to be performed before
   * the execution of every test..() method.
   *
   * @throws Exception
   */
  public void setUp() throws Exception {
    SwingEngine se = new SwingEngine(this);
    container = se.render(DESCRIPTOR);
  }

  /**
   * Clears the container
   */
  public void teardown() {
    container.removeAll();
    container = null;
  }

  /**
   * Tests if the JButtons were correctly initialized/mapped by the SwingEngine.
   */
  public void testMapping() {
    TestCase.assertNotNull("JButton should have been mapped to private fields.", btn1);
    TestCase.assertNotNull("JButton should have been mapped to private fields.", btn2);
    TestCase.assertNotNull("Action should have been mapped to the JButton.", btn1.getAction());
    TestCase.assertNotNull("Action should have been generated and mapped to the JButton.", btn2.getAction());

    int i = counter;
    btn1.getAction().actionPerformed(null);
    TestCase.assertEquals("Action should have been called and exec. correnctly", ++i, counter);
    btn2.getAction().actionPerformed(null);
    TestCase.assertEquals("Action should have been generated and wrap a client method", ++i, counter);
  }
}
