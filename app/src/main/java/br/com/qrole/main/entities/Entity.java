package br.com.qrole.main.entities;

import java.io.Serializable;

/**
 * Defines an Entity.
 */
public abstract class Entity implements Serializable {

    protected int ID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        return ID == entity.ID;
    }

    @Override
    public int hashCode() {
        return ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
