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

echo 'run level 2 (include external libs)'

cd ./level_2

echo 'run include_libs_project who contains external library (jar)'
cd ./include_libs_project
javac -sourcepath ./src -d build/classes/ -cp ./lib/commons-lang3-3.12.0.jar src/ua/com/alevel/it/Info.java src/ua/com/alevel/Test.java
java -cp build/classes/:./lib/commons-lang3-3.12.0.jar ua.com.alevel.Test

cd ../

echo 'run jar_project which contains external library (jar)'
cd ./jar_project
rm -rf lib/org
rm -rf lib/META-INF
javac -sourcepath ./src -d build/classes/ -cp ./lib/commons-lang3-3.12.0.jar src/ua/com/alevel/it/Info.java src/ua/com/alevel/Test.java
cp -r lib/*.jar build/jar
jar cvfm build/jar/project.jar resources/MANIFEST.MF -C build/classes .
java -jar build/jar/project.jar

cd ../../

. ./remove-class.sh