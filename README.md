# code-formatter
This project will try to eliminate changes caused by a different code formatting when comparing two files using [KDiff3](http://kdiff3.sourceforge.net/).

When comparing two files, mostly the diff-application shows a o lot of nasty-looking changes. You may enable **ignore whitespaces** in your diff-application but this may be not enough in more cases. After some moments you notice that many of those nasty-looking changes are a result of a different source code formatting.

How it works? More of the diff applications have some additional options to controll the diff process. The most common option is to ignore whitespace characters while comparing files but this will not help if the files were formatted with different code formatters. [Kdiff3 differ](http://kdiff3.sourceforge.net/) introduces a feature that is called a [preprocessor command](http://kdiff3.sourceforge.net/doc/preprocessors.html):
>When any file is read, it will be piped through this external command. The output of this command will be visible instead of the original file. You can write your own preprocessor that fulfills your specific needs. Use this to cut away disturbing parts of the file, or to automatically correct the indentation etc.

This is an entry point to put an external **code-formatter** in action to format the files (with the same formatter) before they will be compared. Sounds good!

### Some facts on how **code-formatter** works:
 * it is easy to integrate with [KDiff3](http://kdiff3.sourceforge.net/)
 * it formats Java code only
 * it is not *yet another one* code formatter: under the hood it uses [velo/maven-formatter-plugin](https://github.com/velo/maven-formatter-plugin) to format Java source code
 * distribution provides **bat** or **sh** executable; ready to be executed by [KDiff3](http://kdiff3.sourceforge.net/)


### Instructions on how to use it:
* on your computer in your user home directory create a folder named **.formatter**
 * download the sources of **code-fromatter** from **master** branch
 * build with gradle
   > gradle clean build
 * in your **code-fromatter** you will find a distribution file **code-formatter\build\distributions\code-formatter.zip**. Unpack this into a **.formatter** folder in your user home directory
 * configure KDiff3 application
   * open KDiff3 applicatino
   * in menu choose **Settings -> Configure KDif3...**
   * in **Configure** dialog select **Diff** tab
   * in **Preprocessor command** enter the fullpath to the **code-formatter** binary in your **.formatter** folder (you may choose **bat** or **sh** script)
   * Click **Ok** button to accept changes
 
