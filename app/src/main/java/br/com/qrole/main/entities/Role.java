package br.com.qrole.main.entities;

import java.util.Date;

/**
 * Defines a Rolê.
 */
public class Role extends Entity {

    private String title;

    private String description;

    private String image;

    private Date roleDateTime;

    private long latitude;

    private long longitude;

    private String address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getRoleDateTime() {
        return roleDateTime;
    }

    public void setRoleDateTime(Date roleDateTime) {
        this.roleDateTime = roleDateTime;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
