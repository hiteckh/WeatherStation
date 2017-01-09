.PHONY: compile
compile:
	javac -classpath .:classes:lib/'*':java java/org/kentuni/WeatherStation/*.java*
