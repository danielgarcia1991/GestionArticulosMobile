package com.example.lenovo.gestionarticulosm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    public static final String ID_USER = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onAcceder(View view)
    {
        EditText userT = findViewById(R.id.userT);
        EditText claveT = findViewById(R.id.claveT);

        String user = userT.getText().toString();
        String clave = claveT.getText().toString();

        if(user.equals("") || clave.equals("")){
            Toast toast1 = Toast.makeText(getApplicationContext(), "Debe diligenciar todos los campos", Toast.LENGTH_SHORT);
            toast1.show();
        }else{
            //verificacion del web service para saber si llama el menu o no

            callMain(1);
        }

    }

    private void callMain(int idUser) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(ID_USER, idUser);
        startActivity(intent);
    }
}
