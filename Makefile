SRC = $(shell find src/main/java -name "*.java")
TEST = $(shell find src/test/java/lang/ -name "*.java")

OUT = out
LIB = libs/junit-platform-console-standalone-1.10.2.jar

build:
	mkdir -p $(OUT)
	javac --release 8 -d $(OUT) $(SRC)

run: build
	java -cp $(OUT) lang.Main

run-file: build
	java -cp $(OUT) lang.Main $(file)

clean:
	rm -rf $(OUT)

test-build: build
	javac --release 8 -cp "$(OUT):$(LIB)" -d $(OUT) $(TEST)

test: test-build
	java -jar $(LIB) -cp $(OUT) --scan-class-path --details tree