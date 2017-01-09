.PHONY: compile
compile:
	javac -classpath .:classes:lib/'*':src src/org/kentuni/WeatherStation/*.java*
