/*--
 Copyright (C) 2003-2007 Wolf Paulus.
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:

 1. Redistributions of source code must retain the above copyright
 notice, this list of conditions, and the following disclaimer.

 2. Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions, and the disclaimer that follows
 these conditions in the documentation and/or other materials provided
 with the distribution.

 3. The end-user documentation included with the redistribution,
 if any, must include the following acknowledgment:
        "This product includes software developed by the
         SWIXML Project (http://www.swixml.org/)."
 Alternately, this acknowledgment may appear in the software itself,
 if and wherever such third-party acknowledgments normally appear.

 4. The name "Swixml" must not be used to endorse or promote products
 derived from this software without prior written permission. For
 written permission, please contact <info_AT_swixml_DOT_org>

 5. Products derived from this software may not be called "Swixml",
 nor may "Swixml" appear in their name, without prior written
 permission from the Swixml Project Management.

 THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED.  IN NO EVENT SHALL THE SWIXML PROJECT OR ITS
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 SUCH DAMAGE.
 ====================================================================

 This software consists of voluntary contributions made by many
 individuals on behalf of the Swixml Project and was originally
 created by Wolf Paulus <wolf_AT_swixml_DOT_org>. For more information
 on the Swixml Project, please see <http://www.swixml.org/>.
*/

package org.swixml;

import java.awt.*;
import java.lang.reflect.Field;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.*;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

/**
 * Test class running layout related tests.
 *
 * @author Karl Tauber
 */
public class LayoutTest extends TestCase {

  private static final String DESCRIPTOR = "xml/layout.xml";
  private Container container;

  // auto bound through Swixml
  private JPanel borderPanel1;
  private JPanel borderPanel2;
  private JPanel borderPanel3;
  private JPanel borderPanel10;
  private JPanel cardPanel1;
  private JPanel cardPanel2;
  private JPanel cardPanel3;
  private JPanel cardPanel10;
  private JPanel flowPanel1;
  private JPanel flowPanel2;
  private JPanel flowPanel3;
  private JPanel flowPanel4;
  private JPanel gridPanel1;
  private JPanel gridPanel2;
  private JPanel gridPanel3;
  private JPanel gridPanel4;
  private JPanel gridPanel5;
  private JPanel gridBagPanel1;
  private JPanel gridBagPanel2;
  private JPanel gridBagPanel3;
  private JPanel gridBagPanel4;
  private JPanel gridBagPanel5;
  private JPanel gridBagPanel6;
  private JPanel gridBagPanel10;
  private JPanel formPanel1;
  private JPanel formPanel2;


  public LayoutTest() {
    super("Test layout related things");
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

  /**
   * Tests the BorderLayoutConverter
   */
  public void testBorderLayout() {
    TestCase.assertNotNull("JPanel borderPanel1 auto bound through Swixml", borderPanel1);
    TestCase.assertNotNull("JPanel borderPanel2 auto bound through Swixml", borderPanel2);
    TestCase.assertNotNull("JPanel borderPanel3 auto bound through Swixml", borderPanel3);
    TestCase.assertNotNull("JPanel borderPanel10 auto bound through Swixml", borderPanel10);

    checkBorderLayout("borderPanel1", borderPanel1, 0, 0);
    checkBorderLayout("borderPanel2", borderPanel2, 1, 2);
    checkBorderLayout("borderPanel3", borderPanel3, 1, 2);

    // check constraints
    BorderLayout borderLayout10 = (BorderLayout) borderPanel10.getLayout();
    assertEquals(BorderLayout.NORTH,  borderLayout10.getConstraints(borderPanel10.getComponent(0)));
    assertEquals(BorderLayout.EAST,   borderLayout10.getConstraints(borderPanel10.getComponent(1)));
    assertEquals(BorderLayout.SOUTH,  borderLayout10.getConstraints(borderPanel10.getComponent(2)));
    assertEquals(BorderLayout.WEST,   borderLayout10.getConstraints(borderPanel10.getComponent(3)));
    assertEquals(BorderLayout.CENTER, borderLayout10.getConstraints(borderPanel10.getComponent(4)));
  }

  private static void checkBorderLayout(String message, JPanel borderPanel, int hgap, int vgap) {
    LayoutManager layout = borderPanel.getLayout();
    assertTrue(message, layout instanceof BorderLayout);

    BorderLayout borderLayout = (BorderLayout) layout;
    assertEquals(message + ": hgap ", hgap, borderLayout.getHgap());
    assertEquals(message + ": vgap ", vgap, borderLayout.getVgap());
  }

  /**
   * Tests the CardLayoutConverter
   */
  public void testCardLayout() {
    TestCase.assertNotNull("JPanel cardPanel1 auto bound through Swixml", cardPanel1);
    TestCase.assertNotNull("JPanel cardPanel2 auto bound through Swixml", cardPanel2);
    TestCase.assertNotNull("JPanel cardPanel3 auto bound through Swixml", cardPanel3);
    TestCase.assertNotNull("JPanel cardPanel10 auto bound through Swixml", cardPanel10);

    checkCardLayout("cardPanel1", cardPanel1, 0, 0);
    checkCardLayout("cardPanel2", cardPanel2, 1, 2);
    checkCardLayout("cardPanel3", cardPanel3, 1, 2);

    // check card names
    CardLayout cardLayout10 = (CardLayout) cardPanel10.getLayout();
    try {
      // Because CardLayout has no API to access card names,
      // use reflection to access private field.
      Field currentCardField = cardLayout10.getClass().getDeclaredField("currentCard");
      currentCardField.setAccessible(true);

      cardLayout10.show(cardPanel10, "card2");
      assertEquals(1, currentCardField.getInt(cardLayout10));

      cardLayout10.show(cardPanel10, "card1");
      assertEquals(0, currentCardField.getInt(cardLayout10));
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new AssertionFailedError(ex.getMessage());
    }
  }

  private static void checkCardLayout(String message, JPanel cardPanel, int hgap, int vgap) {
    LayoutManager layout = cardPanel.getLayout();
    assertTrue(message, layout instanceof CardLayout);

    CardLayout cardLayout = (CardLayout) layout;
    assertEquals(message + ": hgap ", hgap, cardLayout.getHgap());
    assertEquals(message + ": vgap ", vgap, cardLayout.getVgap());
  }

  /**
   * Tests the FlowLayoutConverter
   */
  public void testFlowLayout() {
    TestCase.assertNotNull("JPanel flowPanel1 auto bound through Swixml", flowPanel1);
    TestCase.assertNotNull("JPanel flowPanel2 auto bound through Swixml", flowPanel2);
    TestCase.assertNotNull("JPanel flowPanel3 auto bound through Swixml", flowPanel3);
    TestCase.assertNotNull("JPanel flowPanel4 auto bound through Swixml", flowPanel4);

    checkFlowLayout("flowPanel1", flowPanel1, FlowLayout.CENTER, 5, 5);
    checkFlowLayout("flowPanel2", flowPanel2, FlowLayout.RIGHT, 5, 5);
    checkFlowLayout("flowPanel3", flowPanel3, FlowLayout.LEFT, 1, 2);
    checkFlowLayout("flowPanel4", flowPanel4, FlowLayout.LEFT, 1, 2);
  }

  private static void checkFlowLayout(String message, JPanel flowPanel, int align, int hgap, int vgap) {
    LayoutManager layout = flowPanel.getLayout();
    assertTrue(message, layout instanceof FlowLayout);

    FlowLayout flowLayout = (FlowLayout) layout;
    assertEquals(message + ": alignment ", align, flowLayout.getAlignment());
    assertEquals(message + ": hgap ", hgap, flowLayout.getHgap());
    assertEquals(message + ": vgap ", vgap, flowLayout.getVgap());
  }

  /**
   * Tests the GridLayoutConverter
   */
  public void testGridLayout() {
    TestCase.assertNotNull("JPanel gridPanel1 auto bound through Swixml", gridPanel1);
    TestCase.assertNotNull("JPanel gridPanel2 auto bound through Swixml", gridPanel2);
    TestCase.assertNotNull("JPanel gridPanel3 auto bound through Swixml", gridPanel3);
    TestCase.assertNotNull("JPanel gridPanel4 auto bound through Swixml", gridPanel4);
    TestCase.assertNotNull("JPanel gridPanel5 auto bound through Swixml", gridPanel5);

    checkGridLayout("gridPanel1", gridPanel1, 1, 0, 0, 0);
    checkGridLayout("gridPanel2", gridPanel2, 2, 3, 0, 0);
    checkGridLayout("gridPanel3", gridPanel3, 4, 5, 6, 7);
    checkGridLayout("gridPanel4", gridPanel4, 2, 3, 0, 0);
    checkGridLayout("gridPanel5", gridPanel5, 4, 5, 6, 7);
  }

  private static void checkGridLayout(String message, JPanel gridPanel, int rows, int cols, int hgap, int vgap) {
    LayoutManager layout = gridPanel.getLayout();
    assertTrue(message, layout instanceof GridLayout);

    GridLayout gridLayout = (GridLayout) layout;
    assertEquals(message + ": rows ", rows, gridLayout.getRows());
    assertEquals(message + ": cols ", cols, gridLayout.getColumns());
    assertEquals(message + ": hgap ", hgap, gridLayout.getHgap());
    assertEquals(message + ": vgap ", vgap, gridLayout.getVgap());
  }

  /**
   * Tests the GridBagLayoutConverter
   */
  public void testGridBagLayout() {
    TestCase.assertNotNull("JPanel gridBagPanel1 auto bound through Swixml", gridBagPanel1);
    TestCase.assertNotNull("JPanel gridBagPanel2 auto bound through Swixml", gridBagPanel2);
    TestCase.assertNotNull("JPanel gridBagPanel3 auto bound through Swixml", gridBagPanel3);
    TestCase.assertNotNull("JPanel gridBagPanel4 auto bound through Swixml", gridBagPanel4);
    TestCase.assertNotNull("JPanel gridBagPanel5 auto bound through Swixml", gridBagPanel5);
    TestCase.assertNotNull("JPanel gridBagPanel6 auto bound through Swixml", gridBagPanel6);
    TestCase.assertNotNull("JPanel gridBagPanel10 auto bound through Swixml", gridBagPanel10);

    checkGridBagLayout("gridBagPanel1", gridBagPanel1, null, null, null, null);
    checkGridBagLayout("gridBagPanel2", gridBagPanel2, new int[] {1, 2, 3}, null, null, null);
    checkGridBagLayout("gridBagPanel3", gridBagPanel3, null, new int[] {4, 5, 6}, null, null);
    checkGridBagLayout("gridBagPanel4", gridBagPanel4, null, null, new double[] {0.1, 0.2, 1.0}, null);
    checkGridBagLayout("gridBagPanel5", gridBagPanel5, null, null, null, new double[] {0.3, 0.4});
    checkGridBagLayout("gridBagPanel6", gridBagPanel6, new int[] {1, 2, 3},
        new int[] {4, 5, 6}, new double[] {0.1, 0.2, 1.0}, new double[] {0.3, 0.4});

    GridBagLayout gridBagLayout10 = (GridBagLayout) gridBagPanel10.getLayout();
    checkGridBagConstraints("gridBagPanel10 [0]",
        new GridBagConstraints(),
        gridBagLayout10.getConstraints(gridBagPanel10.getComponent(0)));
    checkGridBagConstraints("gridBagPanel10 [1]",
        new GridBagConstraints(1, 2, 3, 4, 0.1, 1.0,
            GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
            new Insets(1,2,3,4), 5, 6),
        gridBagLayout10.getConstraints(gridBagPanel10.getComponent(1)));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 4;
    checkGridBagConstraints("gridBagPanel10 [2]",
        gbc,
        gridBagLayout10.getConstraints(gridBagPanel10.getComponent(2)));
  }

  private static void checkGridBagLayout(String message, JPanel gridBagPanel,
      int columnWidths[], int rowHeights[], double columnWeights[], double rowWeights[])
  {
    LayoutManager layout = gridBagPanel.getLayout();
    assertTrue(message, layout instanceof GridBagLayout);

    GridBagLayout gridBagLayout = (GridBagLayout) layout;
    assertEquals(message + ": columnWidths ", columnWidths, gridBagLayout.columnWidths);
    assertEquals(message + ": rowHeights ", rowHeights, gridBagLayout.rowHeights);
    assertEquals(message + ": columnWeights ", columnWeights, gridBagLayout.columnWeights);
    assertEquals(message + ": rowWeights ", rowWeights, gridBagLayout.rowWeights);
  }

  private static void checkGridBagConstraints(String message,
      GridBagConstraints expected, GridBagConstraints actual)
  {
    assertEquals(message + ": gridx ", expected.gridx, actual.gridx);
    assertEquals(message + ": gridy ", expected.gridy, actual.gridy);
    assertEquals(message + ": gridwidth ", expected.gridwidth, actual.gridwidth);
    assertEquals(message + ": gridheight ", expected.gridheight, actual.gridheight);
    assertEquals(message + ": weightx ", expected.weightx, actual.weightx);
    assertEquals(message + ": weighty ", expected.weighty, actual.weighty);
    assertEquals(message + ": anchor ", expected.anchor, actual.anchor);
    assertEquals(message + ": fill ", expected.fill, actual.fill);
    assertEquals(message + ": insets.top ", expected.insets.top, actual.insets.top);
    assertEquals(message + ": insets.left ", expected.insets.left, actual.insets.left);
    assertEquals(message + ": insets.bottom ", expected.insets.bottom, actual.insets.bottom);
    assertEquals(message + ": insets.right ", expected.insets.right, actual.insets.right);
    assertEquals(message + ": ipadx ", expected.ipadx, actual.ipadx);
    assertEquals(message + ": ipady ", expected.ipady, actual.ipady);
  }

  public void testFormLayout() {
    TestCase.assertNotNull("JPanel formPanel1 auto bound through Swixml", formPanel1);
    TestCase.assertNotNull("JPanel formPanel2 auto bound through Swixml", formPanel2);

    checkFormLayout("formPanel1", formPanel1,
        "p, 3dlu, p, 3dlu, p",
        "p, 3dlu, p, 3dlu, p",
        new int[0][],
        new int[0][],
        "1,3",
        "3,3,3,1");

    checkFormLayout("formPanel2", formPanel2,
        "p, p, p, p, p, p, p, p, p, p",
        "p, p, p, p, p, p, p, p, p, p",
        new int[][] {{ 1,3,5,7 }, { 9,2,4 }, { 6,8,10 }},
        new int[][] {{ 6,8,10 }, { 2,4,9 }, { 1,3,5,7 }});
  }

  private static void checkFormLayout(String message, JPanel formPanel,
      String encodedColumnSpecs, String encodedRowSpecs,
      int[][] columnGroupIndices, int[][] rowGroupIndices,
      String... expectedConstraints)
  {
    LayoutManager layout = formPanel.getLayout();
    assertTrue(message, layout instanceof FormLayout);

    ColumnSpec[] expectedColumnSpecs = ColumnSpec.decodeSpecs(encodedColumnSpecs);
    RowSpec[] expectedRowSpecs = RowSpec.decodeSpecs(encodedRowSpecs);

    FormLayout formLayout = (FormLayout) layout;
    int columnCount = formLayout.getColumnCount();
    int rowCount = formLayout.getRowCount();
    int componentCount = formPanel.getComponentCount();

    assertEquals(message + ": columnCount ", expectedColumnSpecs.length, columnCount);
    assertEquals(message + ": rowCount ", expectedRowSpecs.length, rowCount);
    assertEquals(message + ": componentCount ", expectedConstraints.length, componentCount);

    // check column specs
    for (int i = 0; i < columnCount; i++) {
      ColumnSpec spec = formLayout.getColumnSpec(i + 1);
      assertEquals(message + ": column[" + i + "] ",
          expectedColumnSpecs[i].toString(), spec.toString() );  
    }

    // check row specs
    for (int i = 0; i < rowCount; i++) {
      RowSpec spec = formLayout.getRowSpec(i + 1);
      assertEquals(message + ": row[" + i + "] ",
          expectedRowSpecs[i].toString(), spec.toString() );  
    }

    // check column and row groups
    assertEquals(message + ": columnGroups ", columnGroupIndices, formLayout.getColumnGroups());
    assertEquals(message + ": rowGroups ", rowGroupIndices, formLayout.getRowGroups());

    // check component constraints
    for (int i = 0; i < componentCount; i++) {
      CellConstraints cc = formLayout.getConstraints(formPanel.getComponent(i));
      assertEquals(message + ": cc[" + i + "] ",
          new CellConstraints(expectedConstraints[i]).toString(), cc.toString() );  
    }
  }

  /**
   * Asserts that two int[] are equal.
   */
  private static void assertEquals(String message, int[] expected, int[] actual) {
    if (expected == null)
      assertNull(message, actual);
    else
      assertNotNull(message, actual);

    if (expected != null) {
      assertEquals(message + ": length ", expected.length, actual.length);
      for (int i = 0; i < expected.length; i++) {
        assertEquals(message + "[" + i + "] ", expected[i], actual[i]);
      }
    }
  }

  /**
   * Asserts that two double[] are equal.
   */
  private static void assertEquals(String message, double[] expected, double[] actual) {
    if (expected == null)
      assertNull(message, actual);
    else
      assertNotNull(message, actual);

    if (expected != null) {
      assertEquals(message + ": length ", expected.length, actual.length);
      for (int i = 0; i < expected.length; i++) {
        assertEquals(message + "[" + i + "] ", expected[i], actual[i]);
      }
    }
  }

  /**
   * Asserts that two int[][] are equal.
   */
  private static void assertEquals(String message, int[][] expected, int[][] actual) {
    if (expected == null)
      assertNull(message, actual);
    else
      assertNotNull(message, actual);

    if (expected != null) {
      assertEquals(message + ": length ", expected.length, actual.length);
      for (int i = 0; i < expected.length; i++) {
        assertEquals(message + "[" + i + "] ", expected[i], actual[i]);
      }
    }
  }
}
