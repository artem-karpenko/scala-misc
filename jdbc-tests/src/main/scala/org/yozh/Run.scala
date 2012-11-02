package org.yozh

import scala.slick.driver.MySQLDriver.simple._
import slick.lifted.ColumnOption.{AutoInc, PrimaryKey}

// Use the implicit threadLocalSession
import Database.threadLocalSession

/**
 *
 * @author Artem
 */
object Run {
   def main(args: Array[String]) {
      Database.forURL("jdbc:mysql://localhost/scala_tests?user=root", driver = "com.mysql.jdbc.Driver") withSession {
         //      createSchemaAndPopulate

         ( for( c <- Coffees; if c.price < 10.0 ) yield c.name ).list
         // or
         println(Coffees.filter(_.price > 9.0).map(_.name).list)
      }
   }

   def createSchemaAndPopulate {
      // Create the tables, including primary and foreign keys
      (Suppliers.ddl ++ Coffees.ddl).create

      // Insert some suppliers
      Suppliers.insert(101, "Acme, Inc.",      "99 Market Street", "Groundsville", "CA", "95199")
      Suppliers.insert( 49, "Superior Coffee", "1 Party Place",    "Mendocino",    "CA", "95460")
      Suppliers.insert(150, "The High Ground", "100 Coffee Lane",  "Meadows",      "CA", "93966")

      // Insert some coffees (using JDBC's batch insert feature, if supported by the DB)
      Coffees.insertAll(
         ("Colombian",         101, 7.99, 0, 0),
         ("French_Roast",       49, 8.99, 0, 0),
         ("Espresso",          150, 9.99, 0, 0),
         ("Colombian_Decaf",   101, 8.99, 0, 0),
         ("French_Roast_Decaf", 49, 9.99, 0, 0)
      )
   }
}

// Definition of the SUPPLIERS table
object Suppliers extends Table[(Int, String, String, String, String, String)]("SUPPLIERS") {
   def id = column[Int]("SUP_ID", PrimaryKey) // This is the primary key column
   def name = column[String]("SUP_NAME")
   def street = column[String]("STREET")
   def city = column[String]("CITY")
   def state = column[String]("STATE")
   def zip = column[String]("ZIP")
   // Every table needs a * projection with the same type as the table's type parameter
   def * = id ~ name ~ street ~ city ~ state ~ zip
}

// Definition of the COFFEES table
object Coffees extends Table[(String, Int, Double, Int, Int)]("COFFEES") {
   def name = column[String]("COF_NAME", PrimaryKey)
   def supID = column[Int]("SUP_ID")
   def price = column[Double]("PRICE")
   def sales = column[Int]("SALES")
   def total = column[Int]("TOTAL")
   def * = name ~ supID ~ price ~ sales ~ total
   // A reified foreign key relation that can be navigated to create a join
   def supplier = foreignKey("SUP_FK", supID, Suppliers)(_.id)
}