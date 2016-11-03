package br.com.qrole.main.resources;

import java.io.Serializable;

import br.com.qrole.main.activities.LoginActivity;
import br.com.qrole.main.utilities.StringUtilities;

/**
 * A Message Resource is any kind of String mapped on the strings.xml file, may or not have an "|"
 * which is a title/message separator.
 */
public class MessageResource implements Serializable {

    private String title;

    private String message;

    public MessageResource(int resourceId) {
        this(LoginActivity.resources.getString(resourceId));
    }

    public MessageResource(String resource) {
        if (StringUtilities.isBlank(resource)) {
            throw new IllegalStateException("Resource must not be null!");
        }

        int pipeLocation = resource.indexOf('|');
        if (pipeLocation > 0) {
            title = resource.substring(0, pipeLocation);
            message = resource.substring(pipeLocation + 1, resource.length());
        } else {
            message = resource;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
