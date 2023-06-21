package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) //גורם לזה שאם חסר תכונות מקוד של הגיסון שיתעלם וימלא מה שיש
public class Country {

    private String name;
    private String alpha3Code;
    private String capital;
    private int population;
    private String region;
    private List<String> borders;

    private Language[] languages;
    private boolean independent;

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }


    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getRegion() {
        return region;
    }

    public Language[] getLanguages() {
        return languages;
    }

    public void setLanguages(Language[] languages) {
        this.languages = languages;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public boolean isIndependent() {
        return independent;
    }

    public void setIndependent(boolean independent) {
        this.independent = independent;
    }

    public List<String> getBorders() {
        return borders;
    }

    public int getPopulation() {
        return population;
    }

    public String getName() {
        return name;
    }

    private String getPrintLanguages(){
        String result = "";
        for (int i = 0; i<this.languages.length; i++){
            result += this.languages[i];
            if (i != this.languages.length -1){
                result +=" ,";
            }
        }

        return result;
    }

    public String toString(){
        return "name: " + this.name + "\n country 3 words code: " + this.alpha3Code +
                "\n capital: " + this.capital + "\n languages: " + getPrintLanguages()
                + "\n ";
    }

}
