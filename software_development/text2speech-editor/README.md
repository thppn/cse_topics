# Text2SpeechEditor
### An application for writing documents and transforming them to audio.
### In collaboration with [fedonch](https://github.com/fedonch).

Purpose
-----------
The objective of this project is to develop a simple editor that allows preparing text documents and
transforming them to audio. Such an application can be of much help for people who have speaking
problem. Moreover, it could be a useful tool for preparing presentations that are automatically played
without a physical speaker. The application consists of a graphical user interface, a back-end that
enables the text editing process and the part that transforms text to speech, which will be based on
external text to speech libraries.

Functional Requirements / User Stories
-----------
1. As a user, I want to create a new empty document, by giving its title and author. The
application should automatically record the creation date.

2. As a user, I want to edit the contents of the document, via the application's user interface.

3. As a user, I want to save the contents of the document to disk by providing a particular
filename. The application should automatically record the save date.

4. As a user, I want to open the contents of an existing document from disk by providing a

5. As a user, I want to transform the contents of the document to speech.

6. As a user, I want to select a line and transform it to speech.

7. As a user I want to transform the contents of the document to speech in reverse, i.e. play the
last word of the last line first and so on.

8. As a user I want to select a line and transform it to speech in reverse, i.e. and play the last
word first and so on.

9. As a user I want to encode the contents of the document and then transform them to speech.

10. As a user I want to select a line, encode it and transform it to speech.

11. As a user I want to tune the text encoding technique. In particular the application should
support at least the following encoding strategies:

  * Atbash: The Atbash cipher is formed by taking the alphabet and mapping it to its reverse, so
that the first letter becomes the last letter, the second letter becomes the second to last
letter, and so on.
  * Rot-13: Rot-13 is a letter substitution cipher that replaces a letter with the 13th letter after
it, in the alphabet. Rot-13 is a special case of the Caesar cipher, which was developed in
ancient Rome.

12. As a user I want to be able to tune the audio parameters, i.e., the volume, the speech rate

13. As a user I want to be able to store a sequence of actions/commands (e.g. open file, edit
contents, play contents, play line, save file) that I have performed in main memory and re-execute
them multiple times.

Non Functional Requirements
-----------

1. **Extensibility**: In software engineering, extensibility is a design principle where the implementation
takes future growth into consideration. In the case of this project, extensibility concerns the text to
speech API that is going to be used. In particular, the application should work with a specific API but it
should be easy to change the API for another, without having to make extensive changes to the
implementation. Extensibility further concerns the encoding strategies that are supported. Specifically, it
should be possible to easily add new encoding strategies to the application, without having to make
extensive changes to the implementation. To achieve these extensibility aspects the application should
be designed according to the well known open-closed principle and exploit GoF design patterns [1] like
Factory, Adapter, Strategy, Template Method, etc.

2. **Usability**: In software engineering usability concerns the ease of use and learnability. In the context
of this project the application should provide a simple and user-intuitive interface. The application should also provide help, in the form of user guidelines, concerning its main functionalities, and the
contents of the different pattern templates.

3. **Performance**: Performance concerns the time to transform text to speech. At least 9 times out of
10, the transformation of a regular word with < 10 characters should not take more than 2 secs to be
transformed to speech.

IDE, Java and External API Requirements/Constraints
-----------

* Eclipse
* JUnit
* Java 11
* Swing
* FreeTTS
