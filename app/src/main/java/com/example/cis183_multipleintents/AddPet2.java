package com.example.cis183_multipleintents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
    Spinner sp_j_add_type;
    TextView tv_j_add_newtype;
    Intent intent_j_addType;


    //we can use an array adapter for the spinner
    ArrayAdapter<String> adapter;

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
        sp_j_add_type = findViewById(R.id.sp_v_add_type);
        tv_j_add_newtype = findViewById(R.id.tv_v_add_newtype);

        home = new Intent(AddPet2.this, MainActivity.class);
        intent_j_addType = new Intent(AddPet2.this, AddPetType.class);




        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Pet.PetType.getAllPetTypes());
        sp_j_add_type.setAdapter(adapter);

        btnOnClickListener();
        addNewTypeEventListener();


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

        Pet p = new Pet(name, age,sp_j_add_type.getSelectedItem().toString());

        home.putExtra("PetData",p);

        startActivity(home);

    }

    private void addNewTypeEventListener()
    {
        tv_j_add_newtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(intent_j_addType);
            }
        });
    }


}