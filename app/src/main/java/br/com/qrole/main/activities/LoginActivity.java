package br.com.qrole.main.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import br.com.qrole.main.R;
import br.com.qrole.main.controller.LoginController;
import br.com.qrole.main.utilities.DefaultExceptionHandler;
import br.com.qrole.main.utilities.StringUtilities;

public class LoginActivity extends AppCompatActivity {

    /**
     * Resources used by another classes that may have need to access string resources and do not
     * inherit a Context.
     *
     * @see br.com.qrole.main.resources.MessageResource
     */
    public static Resources resources;

    private static final int REQUEST_PICK_ACCOUNT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        resources = getResources();

        pickUserAccount();
        init();
    }

    private void init() {
        TextView textLogin = (TextView) findViewById(R.id.textLogin);

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickUserAccount();
            }
        });
    }

    private void pickUserAccount() {
        if (!LoginController.IS_USER_LOGGED_IN) {
            Intent intent = AccountManager.newChooseAccountIntent(null, null, new String[]{
                    "com.google"}, null, null, null, null);

            startActivityForResult(intent, REQUEST_PICK_ACCOUNT);
        }
    }

    private void showMainScreen(){
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (REQUEST_PICK_ACCOUNT == requestCode) {
            if (resultCode == RESULT_OK) {
                String accountName = data.getExtras().getString(AccountManager.KEY_ACCOUNT_NAME);
                if (!StringUtilities.isBlank(accountName)) {
                    try {
                        LoginController.doLogin(accountName);

                        Toast.makeText(this, "Login efetuado com sucesso! Aguarde...",
                                Toast.LENGTH_LONG).show();
                    } catch (Exception ex) {
                        DefaultExceptionHandler.handleException(ex, this);
                    }
                }
            }
        }
    }

}
