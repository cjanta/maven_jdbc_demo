package com.example;

public class Task {
    
    private int id;
    private String description;
    private boolean isDone;
    
    public Task(int id, String description, boolean isDone){
        this.id = id;
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description, boolean isDone){
        this.id = -1;
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description){
        this.id = -1;
        this.description = description;
        this.isDone =false;
    }
    
    public int getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }


    public boolean isDone() {
        return isDone;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString(){
        return "ID: " + id + " description: " + description + " isDone: " + isDone; 
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + (isDone ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (id != other.id)
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (isDone != other.isDone)
            return false;
        return true;
    }


}
