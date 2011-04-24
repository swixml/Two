package org.swixml;

import junit.framework.TestCase;

/**
 * Test class running include related tests.
 * @author <a href="mailto:alex73mail@gmail.com">Alex Buloichik</a>
 */
public class IncludeTest extends TestCase {
    public static final String DESCRIPTOR = "xml/include.xml";
    
    public IncludeTest() {
        SwingEngine.DEBUG_MODE = true;
    }
    
    public void testCreate() throws Exception {
        new SwingEngine(this).render(DESCRIPTOR);
    }
}
