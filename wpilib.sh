#!/bin/bash

BASE_URL="http://first.wpi.edu/FRC/roborio/release/eclipse/"
SITE_MAP=$BASE_URL"site.xml"

# user libraries available to be installed
NI_VISION="https://github.com/wpilibsuite/nivision/releases/download/v1.0.0/WPINIVisionWrapper_1_0_0.zip"
CTRE_CANTALON="http://ctr-electronics.com/downloads/lib/CTRE_FRCLibs_NON-WINDOWS_v4.4.1.9.zip"

# install user libraries
install_lib () {
    TMP="mktemp"
    wget -qnc $1 -O $TMP
    unzip -q $TMP java/lib/*.jar -d wpilib/user
    rm $TMP
}

# remove and create wpilib directory
echo "Creating wpilib directory"
rm -rf wpilib
mkdir -p wpilib/user/java/lib

if [ ${1-none} = "optional" ]; then
    echo "Downloading NI Vision and CTRE Cantalon jars"
    install_lib $NI_VISION
    install_lib $CTRE_CANTALON
fi

# download xml that contains the path to the jar on the website
echo "Downloading site.xml"
wget -qnc $SITE_MAP

# find and download the jar
FEATURE=$(awk '/.java/ {print substr($2, 6, length($2)-6)}' site.xml)
echo "Downloading $FEATURE from $BASE_URL"
wget -qnc $BASE_URL$FEATURE

# create temporary directory and unzip the jar
TMP_DIR=$(mktemp -d)
mv *.jar site.xml $TMP_DIR
echo "Unzipping feature.xml from $FEATURE"
unzip -q $TMP_DIR/*.jar feature.xml -d $TMP_DIR

# from the feature.xml unzipped, get the jar name and version to download
TMP_FEATURE=$TMP_DIR/feature.xml
PLUGIN=$(grep 'id="' $TMP_FEATURE | tail -n1 | cut -d \" -f 2)
VERSION=$(grep 'version="' $TMP_FEATURE | tail -n1 | cut -d \" -f 2)

# plugin jar name and the url on the website
PLUGIN_JAR=$PLUGIN"_"$VERSION".jar"
PLUGIN_URL=$BASE_URL"plugins/"$PLUGIN_JAR

# download the plugin jar and unzip
echo "Downloading $PLUGIN_JAR and unzipping resources/java.zip"
wget -qnc $PLUGIN_URL -P $TMP_DIR
unzip -q $TMP_DIR/$PLUGIN_JAR resources/java.zip -d $TMP_DIR

#unzip java.zip to wpilib in current directory
echo "Unzipping ant/ and lib/ from resources/java.zip to wpilib/" 
unzip -q $TMP_DIR/resources/java.zip ant/* lib/* -d wpilib

# delete temporary directory
rm -rf $TMP_DIR/*
rm -rf $TMP_DIR
