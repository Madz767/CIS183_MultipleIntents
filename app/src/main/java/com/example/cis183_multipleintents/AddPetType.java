package com.example.cis183_multipleintents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPetType extends AppCompatActivity {


    EditText et_j_addType_newType;
    TextView tv_j_addType_error;
    Button btn_j_addType_add;
    Button btn_j_addType_back;
    Intent intent_j_addPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_pet_type);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_j_addType_newType = findViewById(R.id.et_v_addType_newType);
        tv_j_addType_error = findViewById(R.id.tv_v_addType_error);
        btn_j_addType_add = findViewById(R.id.btn_v_petType_add);
        btn_j_addType_back = findViewById(R.id.btn_v_petType_back);

        intent_j_addPet = new Intent(AddPetType.this, AddPet2.class);

        setAllClickListeners();
    }

    private void setAllClickListeners()
    {

        btn_j_addType_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(intent_j_addPet);
            }
        });

        btn_j_addType_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int errorCode = checkUserInput();

                if(errorCode == 1)
                {
                    tv_j_addType_error.setText("Error: You need to fillout the textbox!");
                    tv_j_addType_error.setVisibility(View.VISIBLE);
                }
                else if(errorCode == 2)
                {
                    tv_j_addType_error.setText("Error: This type already exists!");
                    tv_j_addType_error.setVisibility(View.VISIBLE);
                }
                else if(errorCode == 0)
                {
                    Pet.PetType.addPet(et_j_addType_newType.getText().toString());
                    startActivity(intent_j_addPet);
                }
            }
        });

    }

    private int checkUserInput()
    {
        //0: Good Input
        //1: No Data/Invalid Input
        //2: If It Already Exists
        String input = et_j_addType_newType.getText().toString();

        if(input.isEmpty())
        {
            return 1;
        }
        else
        {
            //if the input was not null
            //but contains a digit return 1
            for (char c: input.toCharArray())
            {
                if(Character.isDigit(c))
                {
                   return 1;
                }
            }
            //if the input was not null
            //but is already in our list return 2
            for(int i = 0; i < Pet.PetType.getAllPetTypes().size(); i++)
            {
                if(input.equalsIgnoreCase(Pet.PetType.petAt(i)))
                {
                    //this type that was just keyed in already exists
                    return 2;
                }
            }
        }
        //if i have made it to this statement the input is good
        return 0;
    }

}