package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBService {
    // 1. Erstelle eine Datenklasse Task{int id, String text, boolean done}

    private static final String DB_URL = "jdbc:sqlite:mission.db";
    public final String TASKS_TABLE_NAME = "tasks";

    public DBService() {
        createTable();
    }
    
    private void createTable(){
        // todo erstelle Datenbank mit Tabelle falls nicht vorhanden
         try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                System.out.println("conncected");
    
                String sql = "CREATE TABLE IF NOT EXISTS " + TASKS_TABLE_NAME + " (\n"
                        + " id integer PRIMARY KEY,\n"
                        + " description text NOT NULL,\n"
                        + " done boolean\n"
                        + ");";
    
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(sql);
                    System.out.println("Table created.");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }      
    }

     // Update
    public void updateTask(int id, String newText, boolean newDone) {
        // todo aktualisier den Eintrag in der Datenbank
    }

    // Delete
    public void deleteTask(int id) {
        // todo löschen einen Eintrag aus der Datenbank
    }

//    // Create
   public void addTask(Task task) {
        // CREATE: Füge Datensätze in die Tabelle ein
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                String insert = "INSERT INTO "+ TASKS_TABLE_NAME +  "(description, done) VALUES (" + task.getDescription() + ", " + task.isDone() +  ")";
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate(insert);
                    System.out.println("Added Task: "+ task.toString());
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }        
    }



//
//    // Read
   public Task getTaskById(int id) {
       Task task = null;
       // todo returniere einen Task aus der Datenbank
       return task;
   }
//
//    // Read all tasks
   public ArrayList<Task> getAllTasks() {
       ArrayList<Task> tasks = new ArrayList<>();
       // todo returniere alle Einträge der Datenbank als Task-Objekte
       return tasks;
}
}
