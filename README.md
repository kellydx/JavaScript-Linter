# Programming Challenge - JavaScript Linter

This is a command line program using that accepts a command line argument the name of a JavaScript file. 
The program has the following features:

	1. Find declared variables that are not used in the code and report the name of 
	the variable and the line number the variable is declared on.
	
	2. Find single line if/else statements that don’t have curly brackets and report 
	if it is an ‘if’ or an ‘else’ block and the line number the block is found.
	
	3. Locate function calls that have not been declared and report the name of the 
	function and the line number that the function call happens on.
	
	4. Check if the text file has missing/ extra bracket.
	If it is a missing bracket:
       - Report the line number and the statement of the openning bracket
	   - If ot is an extra bracket
       - Report the line number the extra bracket is on

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for testing purposes. 

### Prerequisites
To compile java programs you must have JDK installed on your system. 
If you don’t have already installed, visit this links to install and configure Java on your system.

(http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html)

## Running the tests

A step by step series of examples that tell you to run the program using command line
This instruction applies for both Windows and MAC OS 

1.	First, navigate to the directory contains the java files

```
C:\Programming-Challenge>cd JSLinter
```
2. To run a Java program, you first need to compile it. After compiling the java program a .class 
file created with the class name. 

```
C:\Programming-Challenge\JSLinter> javac Driver.java
```
Note: If you get the error "'javac' is not recognized as an internal or external command,
operable program or batch file.", refers to this link to set up the environment
(https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/)

3. Finally, run your java program with ‘java’ command followed by your class name
and an argument (the name of the js file in double quotation marks)

```
C:\Programming-Challenge\JSLinter> java Driver "simpleSample.js"
```

## Built With

* [Java Pattern class](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html) 
* [Java Matcher class](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Matcher.html) 

## Authors

* **Kelly Duong** 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
