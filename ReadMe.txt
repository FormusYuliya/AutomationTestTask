For running all autotests by running the "Regression.xml" file

Be sure that VM option "-ea -Dtestng.dtd.http=true" is applied for the project

In case if you will have some problems with Maven visibility, please do next:

1. Import Project from git
2. Proceed to the pom.xml file
3. Make mouse right click over the file
4. Click the "Add as maven file"


-----------------------Running on different OS ----------------------------

In case if you are going to run tests in MacOS or Linux system - Don`t forget to make drivers executable before ruuning tests


---------------------------Allure reporting--------------------------------

For creating an allure report run next command in terminal:

allure serve Full path to the "allure-results"  --port 9000