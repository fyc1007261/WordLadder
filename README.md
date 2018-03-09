## Word Ladder Based on Gradle, JAVA	

By Cai Yifan, Student ID 516030910375, Class 3, SE, SJTU.

### 1. Introduction

This program is to search for the shortest word ladder between two words.

### 2. Build and use

###### 1. Build the project

```bash
# JAVA and gradle should be correctly installed in system beforehand.
# In file://
gradle build
```

###### 2. Run the project

There are two ways to run the project. 

You can directly run the program by java,

```bash
cd build\classes\java\main
java Main <FILE> <WORD1> <WORD2>
# e.g. java Main dict.txt code data
```

You can also run the program using gradle,

```
gradle run -PappArgs="[<FILE>, <WORD1>, <WORD2>]"
# e.g. gradle run -PappArgs="['dict.txt', 'code', 'data']"
```

