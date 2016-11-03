package br.com.qrole.main.resources;

import br.com.qrole.main.utilities.StringUtilities;

/**
 * Defines a custom Exception thrown at Runtime by some illegal operation or disrespect to the
 * Business Rule.
 */
public class ViolationException extends RuntimeException {

    private MessageResource messageResource;

    private String message;

    public ViolationException(MessageResource messageResource) {
        this.messageResource = messageResource;
        this.message = StringUtilities.blankIfNull(messageResource.getTitle()) + ": "
                + messageResource.getMessage();
    }

    public MessageResource getMessageResource() {
        return messageResource;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
