[![Build Status](https://travis-ci.org/chrisco210/AutoEval.svg?branch=master)](https://travis-ci.org/chrisco210/AutoEval)
# AutoEval
AutoEval is a program for automatically parsing surveys and data that may not have been designed to be automatically graded.  

# Installation
## Dependencies
* JRE 8 or later
* Apache Maven if you want to build from source
## Downloads
Compiled jar files can be found at dl.rachlinski.cf/AutoEval
## Build from source
You can build easily with apache maven.

Using the included pom.xml, specify the "package" goal, and build using jdk 8 or higher.


# Usage
## GUI
### Basic
To begin, open an image containing the survey you want to parse, or open a folder containing all the surveys you want to parse.
Then, under the image menu, select all areas containing questions you want to parse.  You can then select the number of options on the questions.
Under the actions menu, click Parse Responses (Pixel Count) and wait until the status bar says Done.  For text responses, under the actions menu, click Identify Text
You can view the responses in the console by clicking show responses under the actions meun. 
To storage.export your responses, click storage.export under the file menu, and choose from the given options.  Note: the file extension must match the requested storage.export format for it to storage.export properly.
SQL Integration is coming soon.
### GUI Console
The command line can use scripting commands.  See the "scripting" section.

## Scripting

AutoEval contains its own custom scripting language.

All commands must be on their own lines.  Some commands can have arguments, which are processed as string arrays in the scripting engine.

Comments can be made using the Rem command
### Commands

#### Currently Supported Commands
##### REM
```
REM the rem command is used for comments
```
##### OUT
```
REM The out command is used to print to the console, using the method autoEval.gui.consolePane.log(Object s)
OUT This text will be printed to the screen
REM You can also print environmental variables, such as PROGRAM_ROOT using the @ prefix
OUT @PROGRAM_ROOT This will print out the program root
```
##### ALLOC
```
REM The alloc command is used to allocate space for user variables.  By default, there is one variable 
REM that has been allocated.  This command works by setting the userVar VariableStack (GUI.consolePane.controller.userVar)
REM using the ____ method.  Note that this will remove all variables previously set.
ALLOC 3
REM This will allocate space for 3 variables
```
##### EXE
```
REM The EXE command is used to execute a script.  This uses the Controller.exec(Script s) method.  This method first stores
REM the callers execution stack (exec.oldExecStack) and the callers instruction pointer (exec.oldIP), and then executes each command 
REM through a for loop.  When this has been completed, the callers execution stack is restored (execStack.FLUSH(); execStack.pushAll(exec.oldExecStack)))
REM and the callers instruction pointer is restored (execStack.JMP(exec.oldIP))
REM This command allows a called script to call another script from within it.
REM WARNING: From a called script, do not attempt to call the caller from within that script, this will crash the program, and use a lot of memory.
REM The standard file extension for an auto eval script is aesc (To prevent ambiguity between .aes)
EXE /scripts/testscript.aesc
```
##### EXE Example
Script 1 (/scripts/script1)
```
OUT Hello, I am script 1
OUT Calling script 2
EXE /scripts/script2
OUT Thank you script 2
```
Script 2(/scripts/script2)
```
OUT Hello, I am script 2
OUT Back to you, script 1!
```
The output of this program when script 1 is called:
```
Hello I am script 1
Calling script 2
Hello, I am script 2
Back to you, script 1!
Thank you script 2
```

This list is not complete, and will be updated as more commands are added

### Technical details about the scripting engine and expandability
The scripting engine is very expandable, and will be available in a standalone verison soon.

Commands are made up of 2 classes, a precondensed command, and a condensed command.  An entry in the CommandFactory class is also required.  

#### PrecondensedCommand class setup
The precondensed command must extend abstract classclass console.command.precondensed.PrecondensedCommand. Additionaly 
there should be a constructor containing a string with any arguments that the command needs.  It may be useful to split these
into an array using String.split(String arg0).  The method lex() also must be implemented, and all parsing of arguments 
should be done there.  

YOU SHOULD NOT BE PARSING ANY STRINGS OR ARGUMENTS IN THE CONDENSED CLASS!!!

#### CondensedCommand class
The CondensedCommand class of your command should contain a constructor containing any arguments (Which should already be in their final form)
And must implement the interface ExecutableCommand.  The actions taken by the command should be performed in the execute() method, and may be 
any arbatrary code.  The method must return a status code, with 0 being failed, and 1 being success.

#### Executing Commands
Execution of commands can be handled by the 4 execution methods in the controller class, but there are only 2 that are important, exec(String exec), which 
can execute a command based on a string, and exec(Script s) which executes a script, which is obtained from a Script class.  Use the Script 
class javadoc for more information on how to instantiate Script.  The string exec uses the included quickExec method in the 
ExecutionStack, (see console.ExecutionStack.quickExec(ExecutableCommand c)).

Script execution uses the full capabilities of the ExecutionStack class.  Before the script is loaded into the stack, the old stack is saved
so it can be restored after the script is finished.  The instruction pointer is also saved.  The execution stack is then cleared, and the 
instruction pointer is jumped to 0.  the pushAll method then loads all the commands that are stored in the Script class (Script.getCommandArray())

A for loop then runs the RUN() method and INC() method for each command (See the ExecutionStack class javadoc), and on completion, 
the stack is flushed, and the original stack is restored, and the instruction pointer is jumped to the callers instruction pointer.

#### Misc information
##### Naming convention
You should name your commands in uppercase, with needless abb. IE. AllocateVariableStack is changed to ALLOC

If you need inspirtation for your naming, look at assembly.
