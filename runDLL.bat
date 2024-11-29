cd src/main/java/com/convocraft/commandProcessor
call javac profanityFilter.java
call javac -h . profanityFilter.java
move  "com_convocraft_commandProcessor_profanityFilter.h" "../../../../cpp"
cd ../../../../cpp
call g++ -shared -o ../java/profanityFilter.dll profanityFilter.cpp -I"%JAVA_HOME%/include" -I"%JAVA_HOME%/include/win32"
cd ../java
call java com.convocraft.commandProcessor.profanityFilter
