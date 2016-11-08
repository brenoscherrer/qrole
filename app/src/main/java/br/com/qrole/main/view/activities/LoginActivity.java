package br.com.qrole.main.view.activities;

import android.accounts.AccountManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onResume() {
        if (LoginController.IS_USER_LOGGED_IN) {
            showMainScreen();
        }

        super.onResume();
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
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            startActivityForResult(intent, REQUEST_PICK_ACCOUNT);
        }
    }

    private void showMainScreen() {
        Intent intent = new Intent(this, MainScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
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

                        showMainScreen();
                    } catch (Exception ex) {
                        DefaultExceptionHandler.handleException(ex, this);
                    }
                }
            }
        }
    }

}
