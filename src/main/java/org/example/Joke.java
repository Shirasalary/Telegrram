package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke {

    private String type;
    private String joke; // במקרה שהסוג הוא single
    private String setup; // במקרה שהסוג הוא twopart
    private String delivery; // במקרה שהסוג הוא twopart
    private String lang;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSetup() {
        return setup;
    }

    private String getJoke(){
        String result = "";
        if (this.type.equals(Constants.SINGLE_TYPE)) {

            result+=this.joke;
        } else if (this.type.equals(Constants.TWO_PARTS_TYPE)) {
            result+="Q: " + this.setup +" \n A: " + this.delivery;
        }
        return result;
    }

    public String toString(){
        return "Joke: \n" + getJoke() + "\n";
    }
}
