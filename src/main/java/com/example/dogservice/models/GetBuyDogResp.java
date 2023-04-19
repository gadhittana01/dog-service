package com.example.dogservice.models;

import java.util.List;
import java.util.Objects;

public class GetBuyDogResp {
    private List<Dog> dogs;


    public GetBuyDogResp() {
    }

    public GetBuyDogResp(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public List<Dog> getDogs() {
        return this.dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public GetBuyDogResp dogs(List<Dog> dogs) {
        setDogs(dogs);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GetBuyDogResp)) {
            return false;
        }
        GetBuyDogResp getBuyDogResp = (GetBuyDogResp) o;
        return Objects.equals(dogs, getBuyDogResp.dogs);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dogs);
    }

    @Override
    public String toString() {
        return "{" +
            " dogs='" + getDogs() + "'" +
            "}";
    }
    

}
