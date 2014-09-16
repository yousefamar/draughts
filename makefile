all:
	javac -d bin -sourcepath src src/GameOfDraughtsGUI.java

clean :
	find . -name "*.class" -type f -delete
