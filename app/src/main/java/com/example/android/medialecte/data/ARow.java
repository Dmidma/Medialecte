package com.example.android.medialecte.data;

/**
 * Created by dmidma on 12/9/17.
 *
 *
 * A class that acts like a structure.
 * It will hold information from dialect row
 */

public class ARow {


    private int id;
    private String frenchWord;
    private String arabicWord;

    public ARow(int id, String frenchWord, String arabicWord) {
        this.id = id;
        this.frenchWord = frenchWord;
        this.arabicWord = arabicWord;
    }

    public int getId() {
        return this.id;
    }

    public String getFrenchWord() {
        return this.frenchWord;
    }

    public String getArabicWord() {
        return this.arabicWord;
    }


}
