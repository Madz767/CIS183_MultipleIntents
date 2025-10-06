package com.example.cis183_multipleintents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Pet implements Serializable
{
    //a pet is comprised of the following information
    //name, age and type
    private String name;
    private int age;
    private String type;

    //default constructor
    //called when we make a new pet object
    public Pet()
    {

    }

    //overloaded constructor
    //called when we make a new pet object
    //and we pass data for the given pet
    public Pet(String n, int a, String t)
    {
        name = n;
        age = a;
        type = t;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    static class PetType
    {
        String test;
        static ArrayList<String> typeOfPet = new ArrayList<>(Arrays.asList("Dog","Cat","Snake","Chicken","Hamster"));

        public static String petAt(int i)
        {
            return typeOfPet.get(i);
        }

        public static ArrayList<String> getAllPetTypes()
        {
            return typeOfPet;
        }

        public static void addPet(String t)
        {
            typeOfPet.add(t);
        }
    }
}
