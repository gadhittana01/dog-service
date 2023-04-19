package com.example.dogservice.models;

public class DeleteDogResp extends Dog{
    public DeleteDogResp() {
        super();
    }
    public DeleteDogResp(Long id, String name, String breed) {
        super(id, name, breed);
    }
}
