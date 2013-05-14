#!/bin/bash

echo
echo "SWIXML Two Build System"
echo "-------------------"
echo

if [ -z "$JAVA_HOME" ] ; then
  echo 'ERROR: JAVA_HOME not found in your environment.'
  echo
  echo 'Please, set the JAVA_HOME variable in your environment to match the'
  echo 'location of the Java Virtual Machine you want to use.'
  exit 1
fi

if [ `uname | grep -n CYGWIN` ] ; then
  PS=";"
elif [ `uname | grep -n Windows` ] ; then
  PS=";"
else
  PS=":"
fi

LOCALCLASSPATH=${JAVA_HOME}/lib/tools.jar${PS}${JAVA_HOME}/lib/dev.jar${PS}./lib/ant.jar${PS}./lib/ant-launcher.jar${PS}./lib/j2h.jar${PS}./lib/ui.jar
ANT_HOME=./lib

echo Starting Ant...
echo
echo $LOCALCLASSPATH


$JAVA_HOME/bin/java -Dant.home=$ANT_HOME -classpath $LOCALCLASSPATH org.apache.tools.ant.Main $*
