Markdown2HTML
=============

An extremely simple Markdown to HTML converter,
powered by [MarkdownJ](http://code.google.com/p/markdownj/).

Available in command line and GUI mode.

It is licensed under the BSD license.


Requirements
------------

You need Java JVM 1.6 or newer installed on your machine.


Usage
-----

### GUI version ###

`java -jar Markdown2HTML.jar`

### Command line version ###

`java -jar Markdown2HTML.jar markdownFile [- header headerFile.html] [-footer footerFile.html] [-out [file.html]] [-d [directory]]`

#### Options for the command line version ####

- `markdownFile`: the text file which will be converted (compulsory field).
- `-header headerFile.html`: the path of an existing HTML header file.
Its content will be prepended to the converted `markdownFile` file.
- `-footer footerFile.html`: the path of an existing HTML footer file.
Its content will be appended to the converted `markdownFile` file.
- `-out`: enter this to specify that the program should create a new file
which contains the same name as the `markdownFile`, with the .html extension.
- `-out file.html`: enter this to specify the name of the converted file.
- `-d directory`: enter this to specify the directory of the converted files.

GUI Screenshot
--------------

<img src="http://nilhcem.github.com/screenshots/markdown2html.png" width="814" height="564" />


Building it
-----------

`mvn package`, that's all!
