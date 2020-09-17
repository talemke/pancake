#!/bin/bash

# Update Repository
git fetch
git pull

# Make sure Maven and Java 1.9 are installed
# TODO

# Compile Sources
mvn clean compile assembly:single

# Launch
java -jar target/Pancake-1.0-SNAPSHOT-jar-with-dependencies.jar
