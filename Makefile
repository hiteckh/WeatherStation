.PHONY: compile
compile:
	javac -classpath .:classes:deps/'*':java java/org/kentuni/WeatherStation/*.java
