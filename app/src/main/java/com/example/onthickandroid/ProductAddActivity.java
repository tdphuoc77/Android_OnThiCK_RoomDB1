package com.example.onthickandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class ProductAddActivity extends AppCompatActivity {
    private EditText txttype,txtPrice,txtCountry;
    private Button btnCreate,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_addproduct);
        txttype = findViewById(R.id.txtTypeAdd);
        txtPrice = findViewById(R.id.txtPriceAdd);
        txtCountry = findViewById(R.id.txtCountryAdd);
        btnCreate = findViewById(R.id.btnCreate);
        btnBack = findViewById(R.id.btnBackAdd);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductAddActivity.this, ManagerActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"onThiRoomDB1")
                .allowMainThreadQueries()
                .build();
        ProductDao dao = db.productDao();
        List<Product> list= dao.getAll();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = new Product(list.get(list.size()-1).getId()+1 ,txttype.getText().toString(),Double.parseDouble(txtPrice.getText().toString()),txtCountry.getText().toString());
                dao.insertProduct(product);
                Toast.makeText(ProductAddActivity.this, "Success"  , Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(ProductAddActivity.this,ListProductActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    }
