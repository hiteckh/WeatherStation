.PHONY: compile-all
compile-all:
	find WeatherStation/ -name "*.java" | xargs javac -classpath .:classes:+libs/'*':WeatherStation
