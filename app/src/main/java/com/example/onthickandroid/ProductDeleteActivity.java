package com.example.onthickandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class ProductDeleteActivity extends AppCompatActivity {
    private Button btnyes,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutxacnhanxoa);
        AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"onThiRoomDB1")
                .allowMainThreadQueries()
                .build();
        ProductDao dao = db.productDao();
        btnyes = findViewById(R.id.btnYes);
        btnCancel = findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListProductActivity.arraylist.clear();
                Intent intent = new Intent(ProductDeleteActivity.this, ListProductActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bundle = intent.getBundleExtra("BUNDLE1");
                int id = bundle.getInt("id");
                dao.deleteProduct(dao.getProductByID(id));
                Toast.makeText(ProductDeleteActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(ProductDeleteActivity.this, ListProductActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}
