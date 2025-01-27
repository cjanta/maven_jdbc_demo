package com.example;

/*
vs-code settings:
    maven.executable.path
    pathTo\apache-maven-3.9.9\bin\mvn

    <dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
        <version>3.48.0.0</version>
    </dependency>
 */

// 2. fülle die Methoden mit den korrekten Methoden, so das die main() funktioniert

/**
 * 
 *
 */
public final class App {
    private App() {
    }
/**
 * 
 * @param args
 */
    public static void main(String[] args) {
        DBService service = new DBService();

        // Create
        service.addTask(new Task(1, "Task 1", true));
        service.addTask(new Task(2, "Task 2", false));
        service.addTask(new Task(3, "Task 3", true));

        // Read all tasks
        System.out.println("Alle Tasks in der Datenbank:");
        for (Task task : service.getAllTasks()) {
            System.out.println(task);
        }

        // Update
        service.updateTask(1, "Task 1 Updated", false);

        // Delete
        service.deleteTask(2);

        // Finaler Zustand aller Tasks
        System.out.println("Finale Liste aller Tasks:");
        for (Task task : service.getAllTasks()) {
            System.out.println(task);
        }
    }
}
