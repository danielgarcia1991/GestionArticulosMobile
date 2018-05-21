package com.example.lenovo.gestionarticulosm;

import com.example.lenovo.gestionarticulosm.models.Category;

import java.util.ArrayList;

public class CategoryResponse {

    private ArrayList<Category> categories;
    private boolean error;


    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
