package com.example.aapp;

public class Questionnaire {
    public Questionnaire() {

    }

    public String questions[] = {
            //question 1
            "What kind of user are you?",
            //question 2
            "Are you interested in incorperating family members/ friends in your experience?",
            //question 3
            "On average how many days a week do you drink?",
            //question 4
            "Of the days you drink, how many are week days?(Monday-Friday)"

    };


    public String choices[][] = {
            //Q1-buttons
            {"Looking for help", "Former problem drinker", "Friend or Family", "Professional help"},
            //Q2-buttons
            {"Yes", "No", "Unsure", "Possibly down the line"},
            //Q3-buttons
            {"1", "1-2", "3-4", "5+"},
            //Q4-buttons
            {"0", "1", "2-3", "4-5"}
    };

    public String getQuestion(int a) {
        String question = questions[a];
        return question;
    }

    public String getchoice1(int a) {
        String choice = choices[a][0];
        return choice;
    }

    public String getchoice2(int a) {
        String choice = choices[a][1];
        return choice;
    }

    public String getchoice3(int a) {
        String choice = choices[a][2];
        return choice;
    }

    public String getchoice4(int a) {
        String choice = choices[a][3];
        return choice;
    }

}


