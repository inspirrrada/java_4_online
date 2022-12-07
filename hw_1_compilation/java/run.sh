#!/bin/sh

echo 'run level 1'
cd ./level_1

echo 'run dir_1'
cd ./dir_1
javac Test.java
java Test

cd ../

echo 'run dir_2'
cd ./dir_2
javac ua/com/alevel/Test.java
java ua.com.alevel.Test

cd ../

echo 'run dir_3'
cd ./dir_3
javac ua/com/alevel/Test.java
java ua.com.alevel.Test

cd ../

echo 'run test_project_1'
cd ./test_project_1
javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/Test.java
java -cp build/classes ua.com.alevel.Test

cd ../

echo 'run test_project_2 and create first jar'
cd ./test_project_2
javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/Test.java
jar cvfm build/jar/test.jar resources/MANIFEST.MF -C build/classes .
java -jar build/jar/test.jar

cd ../../

. ./remove-class.sh