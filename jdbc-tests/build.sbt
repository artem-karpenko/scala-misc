name := "jdbc-tests"

version := "1.0"

scalaVersion := "2.10.0-M7"

libraryDependencies ++= Seq("com.typesafe" % "slick_2.10.0-M7" % "0.11.1",
"org.slf4j" % "slf4j-nop" % "1.6.4",
"mysql" % "mysql-connector-java" % "5.1.9"
)