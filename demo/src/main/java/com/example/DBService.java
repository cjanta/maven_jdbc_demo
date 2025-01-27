package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBService {
    // 1. Erstelle eine Datenklasse Task{int id, String text, boolean done}

    private static final String DB_URL = "jdbc:sqlite:mission.db";
    public final String TASKS_TABLE_NAME = "tasks";
    private Connection connection ;

    public DBService() {
        createTableIfNotExists();
    }
    
    private void createTableIfNotExists(){
        // todo erstelle Datenbank mit Tabelle falls nicht vorhanden
         try {
            connection = DriverManager.getConnection(DB_URL);
            if (connection != null) {
                System.out.println("conncected");
    
                String sql = "CREATE TABLE IF NOT EXISTS " + TASKS_TABLE_NAME + " (\n"
                        + " id integer PRIMARY KEY,\n"
                        + " description text NOT NULL,\n"
                        + " done boolean\n"
                        + ");";
    
                try (Statement stmt = connection.createStatement()) {
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
        if (connection != null) {
            String updateSQL = String.format("UPDATE '%s' SET description = '%s', done = %b WHERE id = %d", TASKS_TABLE_NAME, newText, newDone, id);
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(updateSQL);
                Task task = getTaskById(id);
                System.out.println(" Task wurde geändert: "+ task.toString());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            createTableIfNotExists();
            updateTask(id, newText, newDone);
        }
    }

    // Delete
    public void deleteTask(int id) {
        if (connection != null) {
            String deleteSQL = String.format("DELETE From '%s' WHERE id = %d", TASKS_TABLE_NAME, id);
            try (Statement stmt = connection.createStatement()) {
                Task task = getTaskById(id);
                int deletedRows = stmt.executeUpdate(deleteSQL);
                if (task != null && deletedRows > 0){
                    System.out.println(" Task wurde aus der Datenbank geloescht: "+ task.toString());
                }
                else{
                    System.out.println(" Es konnte nichts gelöscht werden geloescht: ");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            createTableIfNotExists();
            deleteTask(id);
        }
    }

    public void addTask(Task task) {
    // CREATE: Füge Datensätze in die Tabelle ein 
        if (connection != null) {
            String insert =  String.format("INSERT INTO '%s' (description, done) VALUES ('%s', %b);", TASKS_TABLE_NAME, task.getDescription(), task.isDone());
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(insert);
                System.out.println("Added Task: "+ task.toString());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            createTableIfNotExists();
            addTask(task);
        }
    }    

   // Read
   public Task getTaskById(int id) {
    Task task = null;
    if (connection != null) {
        String selectSQL = String.format("SELECT * FROM %s Where id = %d", TASKS_TABLE_NAME, id);
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(selectSQL); 
            task = new Task(rs.getInt("id"), rs.getString("description"), rs.getBoolean("done"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  
    }
    else{
        createTableIfNotExists();
        return getTaskById(id);
    }
    return task;
   }

   // Read all tasks
   public ArrayList<Task> getAllTasks() {
       ArrayList<Task> tasks = new ArrayList<>();
       if (connection != null) {
            String selectSQL = String.format("SELECT * FROM %s", TASKS_TABLE_NAME);
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(selectSQL);
                while (rs.next()) {
                    tasks.add(new Task(rs.getInt("id"), rs.getString("description"), rs.getBoolean("done")));
                }                           
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }  
        }
        else{
            createTableIfNotExists();
            return getAllTasks();
        }
        return tasks;
    }
}
