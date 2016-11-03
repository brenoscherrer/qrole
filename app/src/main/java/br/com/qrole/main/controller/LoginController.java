package br.com.qrole.main.controller;

import java.util.Date;

import br.com.qrole.main.R;
import br.com.qrole.main.resources.MessageResource;
import br.com.qrole.main.resources.ViolationException;
import br.com.qrole.main.utilities.StringUtilities;

public class LoginController {

    public static boolean IS_USER_LOGGED_IN;

    public static String USER_ACCOUNT;

    public static Date LOGIN_START;

    private LoginController() {
    }

    /**
     * Log the user in.
     *
     * @param username The username.
     * @throws Exception Any exception.
     */
    public static void doLogin(String username) throws Exception {

        if (IS_USER_LOGGED_IN) {
            throw new ViolationException(new MessageResource(
                    R.string.Exception_Login_UsuarioJaLogado));
        }

        USER_ACCOUNT = username;
        IS_USER_LOGGED_IN = true;
        LOGIN_START = new Date();
    }
}
