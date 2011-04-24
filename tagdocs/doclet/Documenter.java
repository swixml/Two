/*--
 $Id: Documenter.java,v 1.2 2005/05/31 05:07:55 wolfpaulus Exp $

 Copyright (C) 2003-2004 Wolf Paulus.
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
package doclet;


import org.swixml.*;


import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Comparator;
import java.lang.reflect.Method;

/**
 * The Documenter writers SwiXml TAG and ATTRIBUTE documention HTML-formatted.
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.2 $
 */

public class Documenter {
  private static final String DIR = "./build/tagdocs/";
  private static final String API = "http://java.sun.com/javase/6/docs/api/";
  private Map tags = SwingTagLibrary.getInstance().getTagClasses();
  private Map converters = ConverterLibrary.getInstance().getConverters();

  public static void main( String[] args ) {
    Documenter doccer = new Documenter();
    try {
      doccer.taginfo();
      doccer.converterinfo();
    } catch (IOException e) {
      System.err.println( e.getMessage() );
    }
  }


  /**
   * Writes HTML-formatted inforamtion about the registered factories
   */
  public void taginfo() throws IOException {
    Writer w = new BufferedWriter( new FileWriter( new File( DIR + "swixmenu.html" ) ) );
    Object[] keys = tags.keySet().toArray();
    Arrays.sort( keys );

    w.write( "<html><head><LINK REL ='stylesheet' TYPE='text/css' HREF='swixml.css' TITLE='Style'></head>" );
    w.write( "<body leftmargin='2'><ul>" );
    w.write( "<li><a href='../2007/swixml.xsd' target='_blank'>Swixml XML-Schema</a></li>" );
    w.write( "<li><a href='customattr.html' target='content'>Custom Swixml Attributes</a></li>" );
    w.write( "<li><a href='converters.html' target='content'>Custom Attr. Converters</a></li><hr>" );

    for (int i = 0; i < keys.length; i++) {
      w.write( "<li><a href='" + keys[i] + ".html' target='content'>" + keys[i] + "</a></li>" );
    }
    w.write( "</ul></body></html>" );
    w.close();

    for (int i = 0; i < keys.length; i++) {
      Factory factory = (Factory) tags.get( keys[i] );
      String link = null;
      if (0>factory.getTemplate().getName().indexOf("javax")) {
        link = factory.getTemplate().getSuperclass().getName().replace( '.', '/' ) + ".html";
      }
      if (link==null) {
        link = factory.getTemplate().getName().replace( '.', '/' ) + ".html";
      }

      w = new BufferedWriter( new FileWriter( new File( DIR + keys[i] + ".html" ) ) );
      w.write( "<html><head><LINK REL ='stylesheet' TYPE='text/css' HREF='swixml.css' TITLE='Style'></head>" );
      w.write( "<body><h2>xml tag name: " + keys[i] + "</h2><h3>Swing obj.: <a href='" + API + link + "'>" + factory.getTemplate().getName() + "</a></h3><hr>" );
      w.write( "<h4>valid xml attributes:</h4>" );
      w.write( "<table cellspacing='2' cellpadding='4'  border='1'><thead><tr><th>Attribute Name</th><th>Attribute Type<br>(after conversion)</th><th>Method to be invoked</th><th>Method declaring Class</th></tr></thead><tbody>" );


      Object[] ms = factory.getSetters().toArray();
      Arrays.sort( ms,new Comparator() {
        public int compare( Object o1, Object o2 ) {
          Method m1= (Method) o1;
          Method m2= (Method) o2;
          return m1.getName().compareTo(m2.getName());
        }
      });

      for (int j = 0; j< ms.length; j++) {
        Method m = (Method) ms[j];
        String declaringClass = m.getDeclaringClass().getName().replace( '.', '/' ) + ".html";
        Class type = m.getParameterTypes()[0];
        String name = m.getName().substring( 3 );

        String para = type.getName();
        para = para.substring( para.lastIndexOf( '.' ) + 1 );

        w.write( "<tr><td><b>" + name + "</b></td>" );
        w.write( "<td>" + para + "</td>" );
        w.write( "<td><a href='" + API + declaringClass + "#set" + name + "(" + type.getName() + ")'>" + m.getName() + "</a></td>" );
        w.write( "<td>" + m.getDeclaringClass().getName() + "</td></tr>" );

      }

      w.write( "</tbody></table><hr>Copyright &copy; 2002 - 2007 - Wolf Paulus <a href='http://www.swixml.org' target='_top'>swixml.org</a>. All Rights Reserved.</body></html>" );
      w.close();
    }
  }


  /**
   * Writes HTML-formatted information about the registered factories
   */
  public void converterinfo() throws IOException {
    Writer w = new BufferedWriter( new FileWriter( new File( DIR + "converters.html" ) ) );

    w.write( "<html><head><title>Converter Library</title><LINK REL ='stylesheet' TYPE='text/css' HREF='swixml.css' TITLE='Style'></head><body>" );
    w.write( "<h3>These Object convert Strings into objects used as parameters to Swing setters.</h3>" );
    w.write( "<hr><table cellspacing='2' cellpadding='4' border='1'><thead><th>Target</th><th>Factory</th></thead><tbody>" );

    Object[] keys = converters.keySet().toArray();

    for (int i = 0; i < keys.length; i++) {

      Converter converter = (Converter) converters.get( keys[i] );
      w.write( "<tr><td>" + ((Class) keys[i]).getName() + "</td>" );
      w.write( "<td>" + converter.getClass().getName() + "</td></tr>" );
    }
    w.write( "</tbody></table><hr>Copyright &copy; 2002 - 2007 - Wolf Paulus <a href='http://www.swixml.org' target='_top'>swixml.org</a>. All Rights Reserved.</body></html>" );
    w.close();
  }
}
