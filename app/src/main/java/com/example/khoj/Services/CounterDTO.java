package com.example.khoj.Services;

public class CounterDTO {
    String counter;

    public CounterDTO(){

     }

    public CounterDTO(String counter){
        this.counter = counter;

    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }
}
