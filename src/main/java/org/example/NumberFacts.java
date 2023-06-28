package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NumberFacts {
    private int number;
    private String text;

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String toString(){
        return "Number:"+this.number +"\n"+"The fact of number is:"+this.text+"\n";

    }
}
