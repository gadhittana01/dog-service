package com.example.dogservice.models;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DogListResp {
    private Map<String, List<String>> message;
    private String status;

    public DogListResp() {
    }

    public DogListResp(Map<String,List<String>> message, String status) {
        this.message = message;
        this.status = status;
    }

    public Map<String,List<String>> getMessage() {
        return this.message;
    }

    public void setMessage(Map<String,List<String>> message) {
        this.message = message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DogListResp message(Map<String,List<String>> message) {
        setMessage(message);
        return this;
    }

    public DogListResp status(String status) {
        setStatus(status);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DogListResp)) {
            return false;
        }
        DogListResp dogListResp = (DogListResp) o;
        return Objects.equals(message, dogListResp.message) && Objects.equals(status, dogListResp.status);
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
