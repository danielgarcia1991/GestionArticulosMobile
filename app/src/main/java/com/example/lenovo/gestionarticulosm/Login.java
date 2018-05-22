package com.example.lenovo.gestionarticulosm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.gestionarticulosm.models.Category;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private final static String TAG = "Login";
    public static final String ID_USER = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toast toast1 = Toast.makeText(getApplicationContext(), "Debe diligenciar todos los campos", Toast.LENGTH_SHORT);
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

            getUser(user);
            callMain(1);
        }

    }

    private void getUser(String userEmail) {

        Call<LoginResponse> call = ApiAdapter.getApiService().getLogin(userEmail);
        call.enqueue( new Login.LoginCallback());

    }

    class LoginCallback implements Callback<LoginResponse> {

        @Override
        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

            Log.d(TAG, "Callback: successful");
            if(response.isSuccessful()){

                LoginResponse login = response.body();
                Log.d(TAG, response.body().toString());
                JsonPreferences.getInstance(getApplicationContext()).setUser(login.getUsers());

            }else{
                Toast.makeText(Login.this, "Error en el formato de respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<LoginResponse> call, Throwable t) {

            Toast.makeText(Login.this, "Error en el formato de respuesta: ", Toast.LENGTH_SHORT).show();
        }
    }

    private void callMain(int idUser) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(ID_USER, idUser);
        startActivity(intent);
    }
}
