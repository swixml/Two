/*--
 $Id: LocalizationTest.java,v 1.2 2007/07/16 00:04:15 wolfpaulus Exp $

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

import junit.framework.TestCase;

import javax.swing.*;
import java.awt.*;

/**
 * Testcase for localization of Strings and comma separated lists of strings.
 */
public class LocalizationTest extends TestCase {
  public static final String DESCRIPTOR = "xml/local.xml";

  private JButton btn1, btn2, btn3, btn4, btn5; // set through Swixml
  private JTabbedPane tpane;   // set Through Swixml
  private Container container;

  public LocalizationTest() throws Exception {
    this("Test Localization of for key strings and comma separated keys");
  }

  public LocalizationTest(String s) throws Exception {
    super(s);
    container = new SwingEngine(this).render(DESCRIPTOR);
  }

  public void testLocalization() {
    TestCase.assertNotNull("UI needs to be instantiated", container);
    TestCase.assertEquals("Simple String Lookup should match", "Hello World", btn1.getText());
    TestCase.assertEquals("Simple String Lookup should match", "Hello, World", btn2.getText());
    TestCase.assertEquals("Simple String Lookup should match", "Alpha", btn3.getText());
    TestCase.assertEquals("Key without matching Value should return key", "abcdef0123456", btn4.getText());
    TestCase.assertEquals("Key without matching Value should return key", "abcdef,0123456", btn5.getText());
  }

  public void testStringListLocalization() {
    TestCase.assertEquals("TabbedPane with 3 tabs expected", 3, tpane.getTabCount());
    TestCase.assertEquals("Tab Title was set through Swixml", "Alpha", tpane.getTitleAt(0));
    TestCase.assertEquals("Tab Title was set through Swixml", "Bravo", tpane.getTitleAt(1));
    TestCase.assertEquals("Tab Title was set through Swixml", "Charlie", tpane.getTitleAt(2));
  }

}
