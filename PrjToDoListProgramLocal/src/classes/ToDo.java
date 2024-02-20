package src.classes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ToDo {

    /**
     * ATTRIBUTES
     */
    private int position;
    private String title;
    private String description;
    private LocalDate creationDate = LocalDate.now();
    private LocalDateTime creationTime = LocalDateTime.now();
    private LocalDate endDate;
    private LocalDateTime endTime;
    private boolean isDone = false;

    /**
     * CONSTRUCTOR
     */
    public ToDo(int position, String title, String description,
            LocalDate endDate, LocalDateTime endTime) {
        this.position = position;
        this.title = title;
        this.description = description;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    /**
     * METHODS
     */
    /**
     * GET POSITION METHOD
     * 
     * @return int return the position
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * SET POSITION METHOD
     * 
     * @param newPosition the position to set
     */
    public void setPosition(int newPosition) {
        this.position = newPosition;
    }

    /**
     * GET TITLE METHOD
     * 
     * @return String return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * SET TITLE METHOD
     * 
     * @param newTitle the title to set
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    /**
     * GET DESCRIPTION METHOD
     * 
     * @return String return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * SET DESCRIPTION METHOD
     * 
     * @param newDescription the description to set
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * GET CREATION DATE METHOD
     * 
     * @return LocalDate return the creationDate
     */
    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    /**
     * @return LocalDateTime return the creationTime
     */
    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    /**
     * GET END DATE METHOD
     * 
     * @return LocalDate return the endDate
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * SET END DATE METHOD
     * 
     * @param newEndDate the endDate to set
     */
    public void setEndDate(LocalDate newEndDate) {
        this.endDate = newEndDate;
    }

    /**
     * @return LocalDateTime return the endTime
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * GET IS DONE METHOD
     * 
     * @return boolean return the todoDone
     */
    public boolean isTodoDone() {
        return this.isDone;
    }

    /**
     * SET IS DONE METHOD
     * 
     * @param newIsDone the todoDone to set
     */
    public void setTodoDone(boolean newIsDone) {
        this.isDone = newIsDone;
    }

    /**
     * toString METHOD
     */
    public String toString() {
        return "Posición: " + this.getPosition() + "\n" +
                "Título: " + this.getTitle() + "\n" +
                "Descripción: " + this.getDescription() + "\n" +
                "Día de creación: " + this.getCreationDate() + "\n" +
                "Hora de creación: " + this.getCreationTime() + "\n" +
                "Día de finalización: " + this.getEndDate() + "\n" +
                "Hora de finalización: " + this.getEndTime() + "\n" +
                "La tarea está finalizada? " + this.isTodoDone();
    }
}