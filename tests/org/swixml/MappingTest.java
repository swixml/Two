/*--
 $Id: MappingTest.java,v 1.2 2005/06/01 00:04:15 wolfpaulus Exp $

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
 * Test class running auto-mapping related tests.
 * @author Wolf Paulus
 */
public class MappingTest extends TestCase implements GlobalActions {
    public static final String DESCRIPTOR = "xml/mappings.xml";
    private Container container;
    private JMenuItem miAbout;

    public JButton b1;
    public transient JButton b2;
    private JButton b3;
    private transient JButton b4;

    public MappingTest() {
        super("Test Mapping og XML Tags to JComponent Objects in the SwingEngine's Client" );
    }
    public MappingTest(String s) {
        super(s);
    }

    /**
     * Renders the test GUI into the container field.<br>
     * Note: Like with every testcase, the setup method is going to be performed before
     * the execution of every test..() method.
     * @throws Exception
     */
    public void setUp() throws Exception {
        SwingEngine se = new SwingEngine(this);
        container = se.render(MappingTest.DESCRIPTOR);
    }

    /**
     * Clears the container
     */
    public void teardown() {
        container.removeAll();
        container=null;
    }

    /**
     * Tests if the JButtons were correctly initialized/mapped by the SwingEngine.
     */
    public void testMappingPublicFields() {
        TestCase.assertNotNull("Public Fields, whose names have matching ids the the XML descriptor should be initializd by the SwingEngine.", b1);
        TestCase.assertNull("Transient Fields must not be initializd by the SwingEngine.", b2);
        TestCase.assertNotNull("Private Field, whose names have a matching ids the the XML descriptor should be initializd by the SwingEngine.", b3);
        TestCase.assertNull("Transient Fields must not be initializd by the SwingEngine.", b4);
    }

   public void testStaticInterfaceMappings() {
     TestCase.assertNotNull("aboutAction is statically defined in an Interface and should have been mapped into the private JMenuItem's action", miAbout.getAction());     
   }

}
