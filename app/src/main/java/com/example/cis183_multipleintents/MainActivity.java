package com.example.cis183_multipleintents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static private ArrayList<Pet> listOfPets;

    static int numberTesting = 50;
    static boolean firstLoad = true;

    //this is going to be used for testing purposes only
    //just to show on listview interact with arrays
    String[] test = {"Hello","Hi","Hola"};
    ListView lv_j_listOfPets;

    PetListAdapter plAdapter;

    Button btn_j_addPet;
    Intent intent_j_addNewPet;
    Intent intent_j_displayUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent cameFrom = getIntent();

        if(cameFrom.getSerializableExtra("PetData") != null)
        {
            Pet petData = (Pet)cameFrom.getSerializableExtra("PetData");
            listOfPets.add(petData);
        }

        if(firstLoad)
        {
            //I need a list to house all pets for the vet clinic
            listOfPets = new ArrayList<>();
            Pet pet = new Pet();
            pet.setName("Tito");
            pet.setAge(12);
            pet.setType("Dog");
            //add the new pet to our list
            listOfPets.add(pet);
            Pet anotherpet = new Pet("Willow", 5, "Dog");

            //add the new pet to our list
            listOfPets.add(anotherpet);

            addDummyDataToArrayList();

            firstLoad = false;
        }

        //GUI Connection
        lv_j_listOfPets = findViewById(R.id.lv_v_listOfPets);
        btn_j_addPet = findViewById(R.id.btn_v_addPet);
        intent_j_displayUpdate = new Intent(MainActivity.this, PetDisplayUpdate.class);
        intent_j_addNewPet = new Intent(MainActivity.this, AddPet2.class);

        //we need an adapter to be used with the listview
        //if the cells require more than one string being displayed
        // a different color, different color text, etc.
        //you must create your own custom adapter
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, test);
        //lv_j_listOfPets.setAdapter(adapter);






        displayAllPetData();
        fillListView();
        addButtonListener();

        //Log.d("Pet Dat:",pet.getName() + " is a " + pet.getType() + " and is " + pet.getAge() + " years old");
    }

    private void addDummyDataToArrayList()
    {
        //create a new pet object
        //fill in all pet information
        //add that pet to the arraylist
        Pet newPet = new Pet("Baxter",7,Pet.PetType.petAt(0));
        listOfPets.add(newPet);
        newPet = new Pet("Mellow",3,Pet.PetType.petAt(1));
        listOfPets.add(newPet);
        newPet = new Pet("Whiskers",9,Pet.PetType.petAt(1));
        listOfPets.add(newPet);




    }

    private void displayAllPetData()
    {
        for(int i = 0; i < listOfPets.size(); i++)
        {
            Log.d("pet Info",listOfPets.get(i).getName());
        }
    }

    private void fillListView()
    {
        plAdapter = new PetListAdapter(this,listOfPets);
        lv_j_listOfPets.setAdapter(plAdapter);
    }


    private void addButtonListener()
    {
        btn_j_addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //first parameter is name of "variable"
                //second parameter is the data to pass to this intent
                intent_j_addNewPet.putExtra("InfoPassed","hello from main");
                startActivity(intent_j_addNewPet);


            }
        });
    }
    public void addPet(Pet p)
    {
        listOfPets.add(p);
    }
}