package com.example.lenovo.gestionarticulosm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lenovo.gestionarticulosm.models.Category;
import com.example.lenovo.gestionarticulosm.models.CategoryStatus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarCategoryActivity extends AppCompatActivity {

    private EditText nameTc;
    private EditText descriptionTc;
    String id_user;
    ListView listCategories;

    List<String> listCategoriesCode = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_category);
        nameTc = (EditText)findViewById(R.id.nameTc);
        descriptionTc = (EditText)findViewById(R.id.descriptionTc);
        this.id_user = "1";
    }

    public void onAgregar(View view)
    {
        //Llama el webservice para registrar la categoria nueva
        addCategory(nameTc.getText().toString(),descriptionTc.getText().toString(),this.id_user);
    }

    private void addCategory(String name, String description, String id_user) {

        String user_id = JsonPreferences.getInstance(getApplicationContext()).getUser();
        Call<CategoryStatus> call = ApiAdapter.getApiService().postAddCategories(name, description, user_id);
        call.enqueue( new AgregarCategoryActivity.CategoryCallback());
    }

    class CategoryCallback implements Callback<CategoryStatus> {

        @Override
        public void onResponse(Call<CategoryStatus> call, Response<CategoryStatus> response) {


            if(response.isSuccessful()){
                CategoryStatus categoryStatus = response.body();
                if(categoryStatus.isStatus() == true){
                    Toast.makeText(AgregarCategoryActivity.this, "Categoria registrada con exito! ", Toast.LENGTH_SHORT).show();
                    callCategories();
                }
            }else{
                Toast.makeText(AgregarCategoryActivity.this, "Error en el formato de respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<CategoryStatus> call, Throwable t) {


        }
    }

    public void poblarSpinnerCategories(ArrayList<Category> categories){

        List<String> listCategoriesName = new ArrayList<String>();

        for(Category c : categories){
            this.listCategoriesCode.add(c.getId());
            listCategoriesName.add(c.getName());
        }
    }

    private void createListViewCategores(ArrayList<Category> categories){

        List<String> listCategoriesName = new ArrayList<String>();

        for(Category c : categories){
            this.listCategoriesCode.add(c.getId());
            listCategoriesName.add(c.getName());
        }

        listCategories = (ListView)findViewById(R.id.listVC);
        ArrayAdapter<String> ArrayProducts = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listCategoriesName);
        listCategories.setAdapter(ArrayProducts);

        /*listCategories.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callDetalleCategoryActivity(listCategoriesCode.get(position));
            }
        });*/
    }

    private void callCategories(){

        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }
}
