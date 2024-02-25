JAVAC = javac
JAVA = java
JAVA_SRCDIR = src
JAVA_BINDIR = bin
CLASSPATH = $(JAVA_BINDIR):configDB/mysql-connector-j-8.2.0.jar:configDB/bcrypt-0.10.2.jar

# List your source files here
JAVA_FILES = src/admin/operation/frames/*.java src/customer/frames/*.java src/customer/info/*.java src/database/operations/*.java src/employee/frames/*.java src/generators/*.java src/login/frames/*.java src/main/pack/*.java src/validators/*.java

all: $(JAVA_FILES:$(JAVA_SRCDIR)/%.java=$(JAVA_BINDIR)/%.class)

$(JAVA_BINDIR)/%.class: $(JAVA_SRCDIR)/%.java
	$(JAVAC) -d $(JAVA_BINDIR) -classpath $(CLASSPATH) $<

run: all
	$(JAVA) -cp $(CLASSPATH) main.pack.Main

clean:
	rm -rf $(JAVA_BINDIR)/*

.PHONY: all run clean
