# --== CS400 File Header Information ==--
# Name: Alexander Peseckis
# Email: peseckis@wisc.edu
# Team: DC
# TA: Yulan BAO
# Lecturer: Florian Heimerl
# Notes to Grader: <optional extra notes>

ShowErrors = -Xlint:unchecked

run: Compile
	java CommandLineDriver

Compile:
	javac ./*.java

testData: Compile
	java TestData

testBackend: Compile
	java TestBackEnd

testFrontend: Compile
	java TestFrontEnd

clean:
	rm *.class
