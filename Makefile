.PHONY: compile
compile:
	javac -classpath .:classes:lib/'*':src src/org/kentuni/WeatherStation/*.java*

.PHONY: compile-all
compile-all:
	find . -name "*.java" | xargs javac -classpath .:classes:lib/'*':src
