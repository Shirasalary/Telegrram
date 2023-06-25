package org.example;

public class Exchange {

    String currency_pair;
    double exchange_rate;

    public String getCurrency_pair() {
        return currency_pair;
    }

    public void setCurrency_pair(String currency_pair) {
        this.currency_pair = currency_pair;
    }

    public double getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(double exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    private String swapCurrency(){
        String result ="";
        int start = this.currency_pair.indexOf("_");
        result+= this.currency_pair.substring(start+1);
        result+=" to "+ this.currency_pair.substring(0,start);
        return result;
    }

    public String toString(){
        return swapCurrency() + ": " + this.exchange_rate +"\n";
    }
}
