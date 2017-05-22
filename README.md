# Task20
The test task on TestNG test framework

Project to this task is correct, do not change Mathematics class!
Complete tasks, which are related to Training 20. 
1.       Use the project from Training 2 for this task. Create two classes with tests (see details below).
•	Class 1 
Should contain 4 unit tests (for each method in mathematics class)
•	Class 2 
Create one test with empty body
2.       Ignore test from Class 2 in test run
3.       Change the order of test execution
4.       Add before/after test/class/method annotations to Class 1. Methods marked with these annotations should be really helpful for test execution.
5.       Add timeout to some test, update test to make it failing.
6.       Create exception test for one of methods in mathematics class (not AssertionError).
7.       Create a couple of testng.xml files: 
•	File testng1.xml to run one test. 
•	File testng2.xml to run all tests. 
•	Add the first test from Class 1 to groups “smoke” and “fast”. Remaining tests add to group “fast”. Create file testng3.xml to run tests from group “smoke” and “fast”.
•	Exclude group “smoke” from test run from testng3.xml.
8.       Create DDT test in Class 1, you can use pairs of ‘X’ and ‘Y’ as datasets. 
•	Make all possible operations of mathematics class with each datasets. 
•	Make DDT test dependent from existent test/tests (explain your solution). 
•	Create DDT test with the help of Testng.xml file. 
•	Paste datasets in text file, update your DataProvider method, so you can get data from file.
