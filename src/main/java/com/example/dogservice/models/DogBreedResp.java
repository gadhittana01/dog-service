package com.example.dogservice.models;

import java.util.List;
import java.util.Objects;

public class DogBreedResp {
    private List<String> message;
    private String status;
    

    public DogBreedResp() {
    }

    public DogBreedResp(List<String> message, String status) {
        this.message = message;
        this.status = status;
    }

    public List<String> getMessage() {
        return this.message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DogBreedResp message(List<String> message) {
        setMessage(message);
        return this;
    }

    public DogBreedResp status(String status) {
        setStatus(status);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DogBreedResp)) {
            return false;
        }
        DogBreedResp dogBreedResp = (DogBreedResp) o;
        return Objects.equals(message, dogBreedResp.message) && Objects.equals(status, dogBreedResp.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, status);
    }

    @Override
    public String toString() {
        return "{" +
            " message='" + getMessage() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }

}
