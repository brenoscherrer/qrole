package br.com.qrole.main.entities;

import java.util.List;

/**
 * Created by Andry on 09/11/16.
 */
public class User extends Entity {
    private String firstName;
    private String LastName;
    private String image;
    private String description;
    private List<Role> myRoles;
    private List<Role> subscribedRoles;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public List<Role> getMyRoles() {
        return myRoles;
    }

    public void setMyRoles(List<Role> myRoles) {
        this.myRoles = myRoles;
    }

    public List<Role> getSubscribedRoles() {
        return subscribedRoles;
    }

    public void setSubscribedRoles(List<Role> subscribedRoles) {
        this.subscribedRoles = subscribedRoles;
    }
}
