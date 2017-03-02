.PHONY: compile-all
compile-all:
	find . -name "*.java" | xargs javac -classpath .:classes:+libs/'*':WeatherStation
