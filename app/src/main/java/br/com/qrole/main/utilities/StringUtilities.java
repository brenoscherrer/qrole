package br.com.qrole.main.utilities;

/**
 * Utilities methods for String objects.
 */
public class StringUtilities {

    public static boolean isBlank(String string) {
        return string == null || string.isEmpty();
    }

    public static String blankIfNull(String string) {
        return string == null ? "" : string;
    }
}
