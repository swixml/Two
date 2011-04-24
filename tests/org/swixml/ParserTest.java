package org.swixml;

import junit.framework.TestCase;

import java.awt.*;

/**
 * Misc. Parser Tests
 */
public class ParserTest extends TestCase {
  public static final String DESCRIPTOR = "xml/parsethis.xml";
  private Container container;

  public ParserTest() {
   super("Misc. Parser Tests ");
  }

  /**
   * Renders the test GUI into the container field.<br>
   * Note: Like with every testcase, the setup method is going to be performed before
   * the execution of every test..() method.
   *
   * @throws Exception
   */
  public void setUp() throws Exception {
    SwingEngine.DEBUG_MODE = true;
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

  public void testParseWithoutException() {
    TestCase.assertNotNull(container);
  }
}
