#!/bin/sh

mvn clean package
java -jar target/six_degrees_separation.jar