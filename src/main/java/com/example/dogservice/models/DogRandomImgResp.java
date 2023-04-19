package com.example.dogservice.models;

import java.util.Objects;

public class DogRandomImgResp {
    private String message;
    private String status;

    public DogRandomImgResp() {
    }

    public DogRandomImgResp(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DogRandomImgResp message(String message) {
        setMessage(message);
        return this;
    }

    public DogRandomImgResp status(String status) {
        setStatus(status);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DogRandomImgResp)) {
            return false;
        }
        DogRandomImgResp dogRandomImgResp = (DogRandomImgResp) o;
        return Objects.equals(message, dogRandomImgResp.message) && Objects.equals(status, dogRandomImgResp.status);
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