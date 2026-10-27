JAVAC ?= javac
JAVA ?= java
BUILD_DIR := build/classes
SOURCES := $(wildcard src/*.java)

.PHONY: build run clean

build:
	mkdir -p $(BUILD_DIR)
	$(JAVAC) --release 17 -encoding UTF-8 -d $(BUILD_DIR) $(SOURCES)

run: build
	$(JAVA) -cp $(BUILD_DIR) Main

clean:
	rm -rf build
