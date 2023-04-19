package com.example.dogservice.models;

public class UpdateDogResp extends Dog{
    public UpdateDogResp() {
        super();
    }

    public UpdateDogResp(Long id, String name, String breed) {
        super(id, name, breed);
    }
}
