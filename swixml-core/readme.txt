$Id: readme.txt,v 1.3 2011/04/24, 05:05:06 wolfpaulus Exp $

Introduction
============

See the web site http://www.swixml.org


Installing the build tools
==========================

The SWIXML build system is based on Ant, which is a Java building tool
originally developed for the Jakarta Tomcat project but now used in many other
Apache projects and extended by many developers.

Ant is a little but very handy tool that uses a build file written in XML
(build.xml) as building instructions. For more information refer to
"http://ant.apache.org".

The only thing that you have to make sure of is that the "JAVA_HOME"
environment property is set to match the top level directory containing the
JVM you want to use. 

Building instructions
=====================

Ok, let's build the code. First, make sure your current working directory is
where the build.xml file is located. Then type

  ant 
- or -
  ant distribution

if everything is right and all the required packages are visible, this action
will generate a file called "swixml.jar" in the "./build" directory. Note, that
if you do further development, compilation time is reduced since Ant is able
to detect which files have changed and recompile them as needed.

If something went wrong, go to the Swixml forum, available at the swixml web site.at

  http://www.swixml.org/forum/


Build targets
=============

The build system is not only responsible for compiling SWIXML into a jar file,
but is also responsible for creating the HTML documentation in the form of
javadocs.

These are the meaningful targets for this build file:

 - package [default] -> creates ./build/swixml.jar
 - compile -> compiles the source code
 - samples -> compiles example code
 - javadoc -> generates the API documentation in ./build/javadocs
 - distribution -> builds everything, incl. jar, javadoc, tagdoc, xsd, etc. etc.
 - clean -> restores the distribution to its original and clean state

For example, to build the samples, type

build samples

To learn the details of what each target does, read the build.xml file.  
It is quite understandable.

Best,
Wolf Paulus and the Swixml Dev. Team.

