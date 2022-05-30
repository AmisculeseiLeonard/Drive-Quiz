package com.example.drivingquiz.utils;

import com.example.drivingquiz.R;

import java.util.ArrayList;
import java.util.Random;

public class QuestionRandomizer {

    private int size;
    private ArrayList<Integer> usedValues;
    Random random;

    public QuestionRandomizer(int size) {
        this.size = size;
        random = new Random();
        usedValues = new ArrayList<>();
    }

    public int getIndex() {
        int randomNum = randomNum = random.nextInt(size);
//        do{
//            randomNum = random.nextInt(size);
//        }while (!usedValues.contains(randomNum));
        usedValues.add(randomNum);
        return randomNum;
    }


}
