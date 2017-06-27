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