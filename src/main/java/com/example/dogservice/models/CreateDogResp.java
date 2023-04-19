package com.example.dogservice.models;

public class CreateDogResp extends Dog{
    public CreateDogResp() {
        super();
    }
    public CreateDogResp(Long id, String name, String breed) {
        super(id, name, breed);
    }
}
