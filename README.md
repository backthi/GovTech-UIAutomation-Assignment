{\rtf1\ansi\ansicpg1252\cocoartf2580
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;\f1\fswiss\fcharset0 Helvetica-Bold;\f2\froman\fcharset0 Times-Bold;
\f3\froman\fcharset0 Times-Roman;}
{\colortbl;\red255\green255\blue255;\red0\green0\blue0;\red17\green109\blue18;}
{\*\expandedcolortbl;;\cssrgb\c0\c0\c0;\csgenericrgb\c6667\c42745\c7059;}
\paperw11900\paperh16840\margl1440\margr1440\vieww11520\viewh8400\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0

\f0\fs24 \cf0 1) Copy the Zip file\
2) UnZip it inside any Folder\
3) Open Command Prompt\
4) use the below command to go inside the Project folder\
       
\f1\b cd -Path till Extracted Project folder 
\f0\b0 \
(Ex: In case of MAC: 
\f1\b cd /Users/backthis/Documents/Selenium_MavericksProject
\f0\b0 \
In case of Windows: C:\\Users\\Project\\Selenium_MavericksProject)\
5) type the below command to execute the Project\
\pard\pardeftab720\sl340\partightenfactor0

\f2\b\fs26 \cf2 \expnd0\expndtw0\kerning0
\outl0\strokewidth0 \strokec2      mvn clean test -DsuiteXmlFile=testng.xml\
6) For Report \'97> Please refer the below path inside the Project Folder\
     ../
\f1\fs24 \cf0 \kerning1\expnd0\expndtw0 \outl0\strokewidth0 Selenium_MavericksProject/
\f0\b0\fs26 \cf3 /test-output/ExtentReport.html\

\f3 \cf2 \expnd0\expndtw0\kerning0
\outl0\strokewidth0 \strokec2 7) By Default, it runs on Chrome browser. If we need to run on FireFox, Update \'93Browser\'94 name as \'93
\f0 \cf3 \kerning1\expnd0\expndtw0 \outl0\strokewidth0 firefox
\f3 \cf2 \expnd0\expndtw0\kerning0
\outl0\strokewidth0 \strokec2 \'94 in Config.Properties file which is in 
\f0 \cf3 \kerning1\expnd0\expndtw0 \outl0\strokewidth0 /src/test/resources/Config.properties path\

\f3 \cf2 \expnd0\expndtw0\kerning0
\outl0\strokewidth0 \strokec2 \
\
Windows compatibility\
Tools Spec\
Video\
}