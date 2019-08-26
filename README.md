# Sudoku Solver 

## Code status
[![codecov](https://codecov.io/gh/farzonl/sudoku/branch/master/graph/badge.svg)](https://codecov.io/gh/farzonl/sudoku)
[![Build Status](https://travis-ci.com/farzonl/sudoku.svg?branch=master)](https://travis-ci.com/farzonl/sudoku)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/farzonl/sudoku.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/farzonl/sudoku/alerts/)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/farzonl/sudoku.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/farzonl/sudoku/context:java)

## Demo
![](https://raw.githubusercontent.com/farzonl/sudoku/master/demo.svg?sanitize=true)

## Gradle Plugins
- java
- jacoco
- application
- checkstyle
- findbugs
- find-sec-bugs-plugin
- errorprone

## Misc Dep
- git spell check (used for pre-commit spell checking)

See instructions here: https://github.com/mprpic/git-spell-check

## how to build?
```
gradle build
```

## how to run
```
gradle run
```
or
```
java -jar build/libs/sudoku-0.0.1.jar resources/test1.csv 
```

## how to generate documentation
```
gradle javadoc
```
## how to auto-format
```
gradle googleJavaFormat
```

## code analytics
reports on code quality can be found in:
```
build/reports
```
There you should see:
- checkstyle (linter results for style conformance)
- findbugs (static code analysis results)
- jacoco (code coverage results)
- tests (junit test results)