rm -rf target; mkdir target; javac src/*/*.java -d target
java -cp target:WEB-INF/lib/mysql-connector-java-5.0.8-bin.jar proj3.SaxParser

