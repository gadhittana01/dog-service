package com.example.dogservice.models;

import java.util.List;
import java.util.Objects;

public class DogImagesResp {
    private List<String> message;
    private String status;


    public DogImagesResp() {
    }

    public DogImagesResp(List<String> message, String status) {
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

    public DogImagesResp message(List<String> message) {
        setMessage(message);
        return this;
    }

    public DogImagesResp status(String status) {
        setStatus(status);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DogImagesResp)) {
            return false;
        }
        DogImagesResp dogImagesResp = (DogImagesResp) o;
        return Objects.equals(message, dogImagesResp.message) && Objects.equals(status, dogImagesResp.status);
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
