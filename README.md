
    *) Covered Both Happy Path and Negative Path E2E Flow
    *) Cross-Browser Compatibility - Works on Chrome Browser as well as FireFox Browser
    *) OS Compatibility - Works on both MAC and Windows
    *) CI/CD - CD Pipeline with Jenkins
    *) Parallel Execution - Increase the Execution Efficiency (Reduces Overall Execution time)
    *) Organised Framework Folder Structure (Used Page Object Model)
    *) Used Selenium ExplicitWait to reduce time-taken to locate the element
    *) Extent Report/ TestNG Reports - For Test Result analysis
    *) All Tests are Independent except the Login
    *) Used All Open-Source Tools:
           1) IDE: Intelij Idea
           2) Automation Tool: Selenium
           3) Execution Tool: TestNG
           4) Reporting: ExtentReport
           5) Project Management Tool: Maven
           6) CI/CD Pipeline: Jenkins
           7) Language Plugin: JDK (Java)
    *) **Enhancements**:
           1) Dockerize the project
           2) Report Portal - For TestResults DashBoard
    *) Note: This project has been implemented on MAC M1 Laptop
    
    GitHub URL:
        - https://github.com/backthi/GowTech-UIAutomation-Assignment
    Google Drive Links:
        1) Framework Link: https://drive.google.com/file/d/1TLBrikVh5ThF6Xt-Ir74cuPkm41GtGWX/view?usp=sharing
        2) Execution Videos Links: https://drive.google.com/file/d/1pawYf-iGetu6Vhyk6wbY1RIEVB2I5upp/view?usp=sharing 

# Initial Pre-requisites:
    1) Ensure JDK 1.8 is installed on your system and Setup the Environment Variable
        - Follow this link to install it on MAC: https://www.youtube.com/watch?v=wHY82KBigQU 
        - For Windows: https://www.youtube.com/watch?v=ClcHrcNXP9g
    2) Ensure Maven is installed and Setup the Environment Variable
       - Document Link: https://maven.apache.org/install.html
       - For MAC Video Link : https://www.youtube.com/watch?v=EoXImdzlAls
       - For Windows Video Link: https://www.youtube.com/watch?v=K9U-5aa8VwE
    3) Sample Example on Environment Variable setup in MAC m1 - .zshrc profile
      .zshrc file:
       export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home"
       export M2_HOME="/Users/backthidevis/Documents/mvn/apache-maven-3.8.6"
       export PATH="${PATH}:$JAVA_HOME:$M2_HOME/bin"
    4) Ensure Chrome and Firefox browsers are installed
    5) Install Jenkins: 
        Refer Docs Link: https://www.blazemeter.com/blog/how-to-integrate-your-github-repository-to-your-jenkins-project
        Refer Video link https://www.youtube.com/watch?v=lPxrPqSyCo8&t=284s

# How To Run the Test:
    Note: Do not disturb While running
### For Google Drive:
          - Download and Unzip the file
          - Open the Command Prompt, and type "cd \Path till Extracted Project folder"
           Ex: In case of MAC: cd /Users/backthidevis/Documents/WGP_UI_Automation/GowTech-UIAutomation-Assignment
               In case of Windows: cd C:\Users\backthidevis\Documents\WGP_UI_Automation\GowTech-UIAutomation-Assignment
          - Once you are inside “GowTech-UIAutomation-Assignment”, then type the below command to execute the Project
            * Type this command:  "mvn clean install" OR "mvn clean test -DsuiteXmlFile=testng.xml"
           Ex: backthidevis@BackthiDeviSs-MacBook-Air GowTech-UIAutomation-Assignment % mvn clean test -DsuiteXmlFile=testng.xml

###   From GitHub:

     - Ensure Git is installed
     - Create new folder in any Path, Ex: In Mac, under Documents
     - Go to the command prompt/Terminal of the New Folder
     Clone the Repository:
     - Using the command "git clone https://github.com/backthi/GowTech-UIAutomation-Assignment.git" to clone or Download the Zip file and Unzip it

     - Once you are inside “GowTech-UIAutomation-Assignment”, 
            * Run/Type this command:  "mvn clean install" OR "mvn clean test -DsuiteXmlFile=testng.xml"

###   Running on different Browser:
        - By Default, it runs on firefox browser. If we need to run on chrome, Update “Browser” name as “chrome” in Config.Properties file 
          which is in ../GowTech-UIAutomation-Assignment/src/test/resources/Config.properties path 
        - And in "WGPTest-E2E-NegativeScenarios.xml" update
          "value" as "chrome"

##  Test Scripts Details:
    There are 2 XML files(has TestScripts) inside "testng.xml" file which is available direct under the cloned folder
       
        1) XMLs/WGPTest-E2E-Parallel.xml - This is the Script for Parallel Execution. This has Happy E2E flow which can run on
          - both Chrome and Firefox browser simultaneously       
        2) XMLs/WGPTest-E2E-NegativeScenarios.xml - Which has the TestScript which Covers E2E Testing of User Story 3 AC2(AC 2: If there are any mandatory inputs missing, a validation error should trigger and the form
          should redirect to the section with the missing details. An error number should also be shown in the sidebar next to the offending section)
          - including Negative Scenarios UserStory 1, User Story 2 and field Validation

      3) By Default, When tests are started running, these 2 files(Already WGPTest-E2E-Parallel is parallel) will be running simultaneously
            - This will run 3 scripts in parallel (1 Nagative Test, 2 Happy Flow tests(one for Chrome, one for firefox))
            - This reduces the 3/4th of the execution time
            - Please refer the video "GowTechUIAutoAssignment_3TCsAtATime_ParallelPoolSize_Video.mp4" for more details

      4) If you want to run one after the other, open "pom.xml" under your framework folder,
            - Go to the bottom of pom.xml file, under "Property", update "value" as "1"


# TestResults Reports(Once Test is executed):

          We can get the following Reports from respective Path
          1) Extent Report Path: ..Framework Folder/ExtentReports/extent-report.html (You can refer Extent Reports(4th) Video)
              Ex: /Users/backthidevis/Documents/WGP_UI_Automation/GowTech-UIAutomation-Assignment/ExtentReports/extent-report.html
          2) TestNG Report Path:  ..Framework Folder/target/surefire-reports/index.html
              Ex: /Users/backthidevis/Documents/WGP_UI_Automation/GowTech-UIAutomation-Assignment/target/surefire-reports/index.html
          3) TestNG Suite Level Report Path:  ..Framework Folder/target/surefire-reports/index.html
              Ex: /Users/backthidevis/Documents/WGP_UI_Automation/GowTech-UIAutomation-Assignment/target/surefire-reports/gowTechWGPTestSuiteNegativeTestE2E/gowTechWGPNegativeTests.html

# Videos Details:

             1) GowTechUIAutoAssignment_UIAutomation_Jenkins_ParallelExecution.mp4 
             2) GowTechUIAutoAssignment_3TCsAtATime_ParallelPoolSize_Video.mp4 
             3) GowTechUIAutoAssignment_FailureTCWithScreenshot_Video.mp4 
             4) GowTechUIAutoAssignment_NegativeTest_ExtentReports.mp4
                    