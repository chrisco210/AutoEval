# AutoEval
AutoEval is a program for automatically parsing surveys and data that may not have been designed to be automatically graded.  

# Installation
## Dependencies
* JRE 8 or later
* RachlinskiNET get (Coming soon)
* JDK if you are manually building 
## Automatic Installation (Windows only currently)
For the latest version, simply run
```
get AutoEval -v latest
```
This will install AutoEval in the default program files directory.
PLEASE NOTE: get for RachlinskiNET is currently in development, and will be available soon.
## Manual Installation 
Clone the repository, and compile the class files using javac.
Create MANIFEST.MF, it should look something like this
```
Main-Class: EntryPoint
```
Use jar.exe, specifying the manifest file you created, to create an executable jar file.
## Compiled jar files
Compiled jar files can be found at dl.rachlinski.cf/AutoEval
# Usage
## GUI
### Basic
To begin, open an image containing the survey you want to parse, or open a folder containing all the surveys you want to parse.
Then, under the image menu, select all areas containing questions you want to parse.  You can then select the number of options on the questions.
Under the actions menu, click Parse Responses (Pixel Count) and wait until the status bar says Done.  For text responses, under the actions menu, click Identify Text
You can view the responses in the console by clicking show responses under the actions meun. 
To export your responses, click export under the file menu, and choose from the given options.  Note: the file extension must match the requested export format for it to export properly.
## Scripting
Coming soon...

"You know what this project needs?"
"To be finished?"
"No! A scripting language!"

AutoEval contains its own custom scripting language.
All commands must be on their own lines.  Some commands can have arguments, which are processed as string arrays in the scripting engine.
Comments can be made using the Rem command
### Commands
Comment
```
REM This line will not be read by the script engine
```
Print to console
```
REM the OUT command will print whatever is to the right of it on a line to the console.
OUT test 123
```
Stop the program
```
REM The END command will end the program
END
```
Load files
```
REM use the FEED command to load files.  -F flag can be used to specify a single file, or -D to specify a directory
FEED -F C:\survey.jpg
FEED -D C:\surveys\
```
Set the question area
```
REM AQA can be used to set the question area using the format AQA -QuestionType Point1x,point1y point2x,point2y.  Question types include MC for multiple choice, TXT for text
AQA -MC 1,1 100,400
```
Set the question count
```
REM use the SQC command to set the question count.  Must be integer.
SQC 4
```
Parse the questions
```
REM use this command to start parsing the question
ParseQuestion
```
Labels
```
REM you can create labels using the LBL command
LBL foo
```
Jump to a label
```
REM use the JMP command to jump to labels
JMP bar
OUT this will not be displayed
LBL bar
OUT This will be displayed
```
The output of this program will be
```
This will be displayed
```
Define and assign a value to a variable
```
REM use the DEF command to set a variable. Format is DEF -DataType VariableName. Data types include int for integers, str for strings, dec for decimals.
DEF -int foo
REM use the SET command to assign a value to a variable.  Format is SET VariableName value. Variables can only include letters
SET foo 4
REM Arithmetic can also be used. Note: no spaces can be placed between operations. Operators include +, -, *, /, 
SET -AR foo foo+1
```
Create executable regions
```
REM There are no functions in AutoEval script, However you can create regions using the REG and the ENDREG commands. Code in regions will be ignored until regions are called

REG regionName
OUT Region being executed
ENDREG 
```
Call executable regions
```
REM use the USE command to call a region
REG foo
OUT bar
ENDREG

OUT This will be displayed first
USE foo
```
The output of this program will be
```
This will be displayed first
bar
```
If statements are also supported
```
REM Example of an if statement
REG false
OUT this is false
END
ENDREG

DEF -int foo
DEF -int bar
SET foo 4
SET bar 5 

IF foo > bar DO USE false
OUT this will not be displayed
```
The output of this program will be
```
this is false
```
## Command Line
The command line can use scripting commands.  See above section
