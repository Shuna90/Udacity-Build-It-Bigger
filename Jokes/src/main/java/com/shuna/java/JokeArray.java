package com.shuna.java;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JokeArray{
    private Random randomGenerator = new Random();
    private List<String> joke = Arrays.asList(
            "How do all the oceans say hello to each other?  \nThey wave!",
            "What did one wall say to the other wall?  \nI’ll meet you at the corner!",
            "What do you call a bear with no teeth? \nA gummy bear!",
            "Where do cows go for entertainment?\nTo the moo-vies!",
            "How do you know if there’s an elephant under your bed?\nYour head hits the ceiling!",
            "What do you call a cow with no legs?\nGround beef!"
    );

    public String tellAHandCraftedJoke(){
        int next = randomGenerator.nextInt(joke.size());
        return joke.get(next);
        //return "This is a joke";
    }
}

