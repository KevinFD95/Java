package src.todolistprogram.database.classes;

import java.time.LocalDate;

public class Person {

    /**
     * ATTRIBUTES
     */
    private String name;
    private String surname;
    private LocalDate birthday;
    private String signature;
    private User user;

    /**
     * CONSTRUCTOR
     */
    public Person(String name, String surname, LocalDate birthday, String signature, User user) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.signature = signature;
        this.user = user;
    }

    /**
     * METHODS
     */

    /**
     * GET NAME METHOD
     * Method used to get the name of the person
     * 
     * @return String return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * SET NAME METHOD
     * Method used to set a new name for the person
     * 
     * @param newName the new name to set
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * GET SURNAME METHOD
     * Method used to get the surname of the person
     * 
     * @return String return the surname
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * @param newSurname the surname to set
     */
    public void setSurname(String newSurname) {
        this.surname = newSurname;
    }

    /**
     * @return LocalDate return the birthday
     */
    public LocalDate getBirthday() {
        return this.birthday;
    }

    /**
     * @param newBirthday the birthday to set
     */
    public void setBirthday(LocalDate newBirthday) {
        this.birthday = newBirthday;
    }

    /**
     * @return String return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @param signature the signature to set
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

}