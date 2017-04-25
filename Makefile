.PHONY: compile-all
compile-all:
	find src/ -name "*.java" | xargs javac -classpath .:classes:src/+libs/'*':WeatherStation
