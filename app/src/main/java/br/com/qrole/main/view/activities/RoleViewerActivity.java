package br.com.qrole.main.view.activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import br.com.qrole.main.R;
import br.com.qrole.main.entities.Role;
import br.com.qrole.main.utilities.BitmapUtilities;
import br.com.qrole.main.utilities.StringUtilities;

public class RoleViewerActivity extends AppCompatActivity {

    private TextView textRoleTitle;

    private TextView textRoleDate;

    private TextView textAddress;

    private TextView textDescription;

    private ImageView imageRole;

    private Button buttonConfirmarPresenca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_viewer);

        textRoleTitle = (TextView) findViewById(R.id.textRoleTitle);
        textRoleDate = (TextView) findViewById(R.id.textRoleDate);
        textAddress = (TextView) findViewById(R.id.textAddress);
        textDescription = (TextView) findViewById(R.id.textDescription);
        imageRole = (ImageView) findViewById(R.id.imageRole);
        buttonConfirmarPresenca = (Button) findViewById(R.id.buttonConfirmarPresenca);

        buttonConfirmarPresenca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RoleViewerActivity.this, "Presença confirmada com sucesso!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();

        // Tentativa de acessar a activity sem um Intent
        if (intent == null) {
            throw new IllegalStateException("Proibido acessar a activity RoleViewer sem um intent");
        }

        Serializable entity = intent.getExtras().getSerializable("entity");
        if (!(entity instanceof Role)) {
            throw new IllegalStateException("Entidade inválida passada para o visualizador de roles");
        }

        Role role = (Role) entity;

        textRoleTitle.setText(role.getTitle());
        textRoleDate.setText(new SimpleDateFormat("dd/MM/yyyy - hh:mm").format(role.getRoleDateTime()));
        textAddress.setText(role.getAddress());
        textDescription.setText(role.getDescription());

        setTitle(role.getTitle());

        if (StringUtilities.isBlank(role.getImage())) {
            imageRole.setImageDrawable(getResources().getDrawable(R.drawable.noimage, null));
        } else {
            imageRole.setImageBitmap(BitmapUtilities.getBitmapFromBase64String(role.getImage()));
        }
    }
}
