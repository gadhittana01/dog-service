package com.example.dogservice.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GetDogsResp {
    private Map<String, List<String>> response = new HashMap<>();

    public GetDogsResp() {
    }

    public GetDogsResp(Map<String,List<String>> response) {
        this.response = response;
    }

    public Map<String,List<String>> getResponse() {
        return this.response;
    }

    public void setResponse(Map<String,List<String>> response) {
        this.response = response;
    }

    public GetDogsResp response(Map<String,List<String>> response) {
        setResponse(response);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GetDogsResp)) {
            return false;
        }
        GetDogsResp dogResp = (GetDogsResp) o;
        return Objects.equals(response, dogResp.response);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(response);
    }

    @Override
    public String toString() {
        return "{" +
            " response='" + getResponse() + "'" +
            "}";
    }
    
    public boolean isShiba(String breed) {
        return breed.equals("shiba");
    }

    public boolean isSheepdog(String breed) {
        return breed.equals("sheepdog");
    }

    public boolean isTerrier(String breed) {
        return breed.equals("terrier");
    }

}
