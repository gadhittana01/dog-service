package com.example.dogservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dogservice.models.CreateDogResp;
import com.example.dogservice.models.DeleteDogResp;
import com.example.dogservice.models.Dog;
import com.example.dogservice.models.GetBuyDogResp;
import com.example.dogservice.models.GetDogsResp;
import com.example.dogservice.models.UpdateDogResp;
import com.example.dogservice.services.DogServices;

@RestController
@RequestMapping("api/v1/dog")
public class DogController {
    private final DogServices dogServices;

    @Autowired
    public DogController(DogServices dogServices) {
        this.dogServices = dogServices;
    }

    @GetMapping
    public GetDogsResp getDogs(){
        return dogServices.getDogs();
    }
    
    @GetMapping("/buy")
    public GetBuyDogResp getBuyDogs(){
        return dogServices.getBuyDogs();
    }
    
    @PostMapping("/buy")
    public CreateDogResp buyDog(@RequestBody Dog dog){
        return dogServices.createDog(dog);
    }
    
    @PutMapping("/buy/{dogID}")
    public UpdateDogResp updateDog(@PathVariable("dogID") Long dogID, @RequestBody Dog dog){
        return dogServices.updateDog(dog, dogID);
    }
    
    @DeleteMapping("/buy/{dogID}")
    public DeleteDogResp deleteDog(@PathVariable("dogID") Long dogID){
        return dogServices.deleteDog(dogID);
    }
    
}
