package com.example.rattasartpc.healthy.Weight;

public class WeightAsset {
    float weight;
    String date;
    String status;
    public WeightAsset(){

    }
    public WeightAsset(float weight, String date, String status) {
        this.weight = weight;
        this.date = date;
        this.status = status;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
