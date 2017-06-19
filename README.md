# AutoEval
AutoEval is a program for automatically parsing surveys and data that may not have been designed to be automatically graded.  

# Installation
## Dependencies
* JRE 8 or later
* NodeJS if you are using the automatic installer
* JDK if you are manually building 
## Automatic Installation
Run the install.js, with the first parameter being the path to install in\.
```
node install.js PATH_GOES_HERE
```

## Manual Installation
Clone the repository, and compile the class files using javac.
Create MANIFEST.MF, it should look something like this
```
Main-Class: EntryPoint

```
Use jar.exe, specifying the manifest file you created, to create an executable jar file.