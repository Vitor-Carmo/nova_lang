SRC = $(shell find src/main/java -name "*.java")
OUT = out

build:
	mkdir -p $(OUT)
	javac --release 8 -d $(OUT) $(SRC)

run: build
	java -cp $(OUT) main.java.lang.Main

run-file: build
	java -cp $(OUT) main.java.lang.Main $(file)

clean:
	rm -rf $(OUT)