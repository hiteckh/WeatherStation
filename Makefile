.PHONY: compile-all
compile-all:
	find src/ -name "*.java" | xargs javac -classpath .:classes:src/+libs/'*':WeatherStation

compile-jar:
	mkdir bin -p
	find src/ -name "*.java" | xargs javac -d bin/ -classpath .:classes:src/+libs/'*':WeatherStation
	cd bin && jar cvf WeatherStation.jar *
