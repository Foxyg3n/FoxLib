echo %1
@RD /S /Q "me/foxyg3n/FoxLib/%1/"
"C:/Program Files/Maven/apache-maven-3.6.3/bin/mvn.cmd" install:install-file -DgroupId=me.foxyg3n -DartifactId=FoxLib -Dversion=%1 -Dfile=../FoxLib/target/FoxLib-%1.jar -Dpackaging=jar -DgeneratePom=true -DlocalRepositoryPath=. -DcreateChecksum=true
"C:/Program Files/Maven/apache-maven-3.6.3/bin/mvn.cmd" install:install-file -DgroupId=me.foxyg3n -DartifactId=FoxLib -Dversion=%1 -Dfile=../FoxLib/target/FoxLib-%1-sources.jar -Dpackaging=jar -DgeneratePom=true -DlocalRepositoryPath=. -DcreateChecksum=true