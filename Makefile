# compile java
JC = javac

# Data Directory
DDIR := Data
# Backend Directory
BDIR := Backend
# Frontend Directory
FDIR := Frontend
# Testing Directory
TDIR := Tests


# All the different specefic group roles and the people to pick from, in the order that they are
# assigned so for Data Xiohan is DataWrangler1 and Allistar is DataWrangler2
#	Data
Xiohan = XiohanShen
Allistar = AllistarMascarenhas
#Backend
Benjamin = BenjaminWurster
YuanChen = YuanChen
#	Frontend
Justin = JustinQiao
Aiden = AidenGodfrey
#	TestEngineer
Alex = AlexanderPeseckis


# The overarching directory that has all /src and /bin files in it
APPDIR := .

V1DIR := $(APPDIR)/bin/V1
V2DIR := $(APPDIR)/bin/V2

# Runs both versions of the app, one after the other
all:
	RunAppOne

# Make the directory for all V1 compiled java code
V1DIR:
	mkdir -p "$(V1DIR)"

# Make the directory for all V2 compiled java code
V2DIR:
	mkdir -p "$(V2DIR)"

# Runs the first version of the app consisting of
# DataWrangler1 (Xiohan Shen), Backend1(Benjamin Wurster), and Frontend1(Justin Qiao)
RunAppOne: Data1 Backend1 Frontend1
	java $(V1DIR)/CommandLineDriver.java

# Compiles version 1 of the program
Data1: V1DIR
	$(JC) -d "$(V1DIR)" "$(APPDIR)/src/$(DDIR)/$(Xiohan)/"*.java

Backend1: V1DIR
	echo $(shell pwd)
	$(JC) -d "$(V1DIR)" "$(APPDIR)/src/$(BDIR)/$(Benjamin)/"*.java

Frontend1: V1DIR
	$(JC) -d "$(V1DIR)" "$(APPDIR)/src/$(FDIR)/$(Justin)/"*.java


# Runs the second version of the app consisting of
# DataWrangler2 (Allistar Mascarenhas), Backend2(Yuan Chen), and Frontend2(Aiden Godfrey)
# Note: since Aiden has yet to do his part, Justin's is used instead
RunAppTwo:

# Compiles the version 2 backend

clean:
	rm *.class
	rm -r bin/V1
	rm -r bin/V2

