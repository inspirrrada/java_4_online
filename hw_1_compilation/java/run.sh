#!/bin/sh
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