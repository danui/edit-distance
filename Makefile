default: build

SRC_FILES=$(shell find src -type f)

todo:
	@find src -name "*.java" -exec grep -n TODO \{\} \+; exit 0;

build: pom.xml $(SRC_FILES)
	mvn clean package

check:
	mvn test

clean:
	find . -name "*~" -exec rm -vf \{\} \+
	rm -rf target

.PHONY: default todo build check clean
