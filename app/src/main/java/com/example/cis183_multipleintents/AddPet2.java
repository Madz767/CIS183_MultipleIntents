package com.example.cis183_multipleintents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPet2 extends AppCompatActivity {

    Button btn_j_add_back;
    Button btn_j_add_add;
    Intent home;
    EditText et_j_add_name;
    EditText et_j_add_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_pet2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent cameFrom = getIntent();

        Bundle infoPassedToMe = cameFrom.getExtras();


        if(infoPassedToMe != null)
        {
            String data = infoPassedToMe.getString("InfoPassed");
        }


        btn_j_add_back = findViewById(R.id.btn_v_add_back);
        btn_j_add_add = findViewById(R.id.btn_v_add_add);
        et_j_add_name = findViewById(R.id.et_v_add_name);
        et_j_add_age = findViewById(R.id.et_v_add_age);

        home = new Intent(AddPet2.this, MainActivity.class);







        btnOnClickListener();


    }
    private void btnOnClickListener()
    {
        btn_j_add_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(home);
            }
        });
        btn_j_add_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPetDataFromTextBoxes();
            }
        });

    }


    private void getPetDataFromTextBoxes()
    {
        //get data from text boxes
        //make a pet
        //send pet to main
        //add pet to list
        String name = et_j_add_name.getText().toString();
        int age  = Integer.parseInt(et_j_add_age.getText().toString());

        Pet p = new Pet(name, age,Pet.PetType.petAt(4));

        home.putExtra("PetData",p);

        startActivity(home);

    }


}