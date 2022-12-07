# About compilation #

## C (example) ##

### gcc ###
`gcc test.c -o test`\
`./test`

## Level 1 (without libs) ##

### go to java/level_1/dir_1 ###
`javac Test.java`\
`java Test`

### go to java/level_1/dir_2 ###
`javac ua/com/alevel/Test.java`\
`java ua.com.alevel.Test`

### go to java/level_1/dir_3 ###
`javac ua/com/alevel/Test.java`\
`java ua.com.alevel.Test`

### go to java/level_1/test_project_1 ###
`javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/Test.java`\
`java -cp build/classes ua.com.alevel.Test`

### go to java/level_1/test_project_2 ###
`javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/Test.java`\
`jar cvfm build/jar/test.jar resources/MANIFEST.MF -C build/classes .`\
`java -jar build/jar/test.jar`