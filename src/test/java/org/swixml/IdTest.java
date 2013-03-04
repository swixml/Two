/*--
 $Id: ExtMappingTest.java,v 1.1 2005/06/01 00:02:43 wolfpaulus Exp $

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
import java.awt.event.ActionEvent;

/**
 * Test class running id and refid related tests.
 * @author Wolf Paulus
 */
public class IdTest extends TestCase {
  public static final String DESCRIPTOR = "xml/id.xml";
  private Container container;
  private JPanel pnl1, pnl11, pnl2;
  private JButton btn1, btn2,btn3;
  private boolean b1, b2;
  public Action a1 = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      b1 = true;
    }
  };

  public void m1() {
    b2 = true;
  }


  public IdTest() throws Exception {
    super("ID Tests");
    SwingEngine.DEBUG_MODE = true;
    container = new SwingEngine(this).render(DESCRIPTOR);
  }

  public void testRefId() {
    TestCase.assertNotNull(pnl1);
    TestCase.assertNotNull(pnl11);
    TestCase.assertNotNull(pnl2);

    TestCase.assertEquals(Color.red, pnl1.getBackground());
    TestCase.assertEquals(pnl1.getBackground(), pnl2.getBackground());
    TestCase.assertNotSame(pnl1.getBackground(), pnl11.getBackground());
    TestCase.assertEquals(pnl1.getFont(), pnl11.getFont());
    TestCase.assertNotSame(pnl1.getFont(), pnl2.getFont());

    TestCase.assertNotNull(btn1.getAction());
    TestCase.assertNotNull(btn2.getAction());
    TestCase.assertNotNull(btn3.getAction());

    TestCase.assertEquals(btn1.getAction(), btn3.getAction());
    TestCase.assertNotSame(btn1.getAction(), btn2.getAction());
    TestCase.assertFalse(b2);
    btn2.doClick();
    TestCase.assertTrue(b2);
    TestCase.assertFalse(b1);
    btn3.doClick();
    TestCase.assertTrue(b1);
  }
}

