package com.example.dogservice.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dogservice.config.RestTemplateAllConfig;
import com.example.dogservice.config.RestTemplateBreedConfig;
import com.example.dogservice.models.CreateDogResp;
import com.example.dogservice.models.DeleteDogResp;
import com.example.dogservice.models.Dog;
import com.example.dogservice.models.DogBreedResp;
import com.example.dogservice.models.GetDogsResp;
import com.example.dogservice.models.UpdateDogResp;
import com.example.dogservice.repositories.DogRepository;

import jakarta.transaction.Transactional;

import com.example.dogservice.models.DogImagesResp;
import com.example.dogservice.models.DogListResp;
import com.example.dogservice.models.DogRandomImgResp;
import com.example.dogservice.models.GetBuyDogResp;

@Service
public class DogServices {
    @Value("${dogservice.address}")
    private String DOG_SERVICE_URL;

    private RestTemplateAllConfig restTemplateAll;
    private RestTemplateBreedConfig restTemplateBreed;
    private DogRepository dogRepository;

    @Autowired
    public DogServices(RestTemplateAllConfig restTemplateAll, RestTemplateBreedConfig restTemplateBreed, DogRepository dogRepository) {
        this.restTemplateAll = restTemplateAll;
        this.restTemplateBreed = restTemplateBreed;
        this.dogRepository = dogRepository;
    }

    public GetDogsResp getDogs() {
        GetDogsResp dogResp = new GetDogsResp();
        DogListResp resDogs = (DogListResp) restTemplateAll.HTTPRequest(DOG_SERVICE_URL+"/breeds/list/all", DogListResp.class).getBody();

        for(Map.Entry<String,List<String>> entry : resDogs.getMessage().entrySet()){
            String breed = entry.getKey().toString();
            if(dogResp.isShiba(breed)){
                DogImagesResp resImages = (DogImagesResp) restTemplateBreed.HTTPRequest(DOG_SERVICE_URL+"/breed/"+breed+"/images", DogImagesResp.class).getBody();
                List<String> images = new ArrayList<String>();
                if(resImages.getMessage().size() > 0){
                    for(int i=0;i<resImages.getMessage().size();i++){
                        if(i%2 == 1){
                            images.add(resImages.getMessage().get(i));
                        }
                    }
                }
                dogResp.getResponse().put(breed, images);

            }else if(dogResp.isSheepdog(breed)){
                DogBreedResp resBreeds = (DogBreedResp) restTemplateBreed.HTTPRequest(DOG_SERVICE_URL+"/breed/"+breed+"/list", DogBreedResp.class).getBody();
                if(resBreeds.getMessage().size() > 0){
                    for(int i=0;i<resBreeds.getMessage().size();i++){
                        dogResp.getResponse().put(breed+"-"+resBreeds.getMessage().get(i), new ArrayList<>());
                    }
                }
            }else if(dogResp.isTerrier(breed)){
                DogBreedResp resBreeds = (DogBreedResp) restTemplateBreed.HTTPRequest(DOG_SERVICE_URL+"/breed/"+breed+"/list", DogBreedResp.class).getBody();
                if(resBreeds.getMessage().size() > 0){
                    for(int i=0;i<resBreeds.getMessage().size();i++){
                        String subBreed = resBreeds.getMessage().get(i);
                        DogImagesResp resImages = (DogImagesResp) restTemplateBreed.HTTPRequest(DOG_SERVICE_URL+"/breed/"+breed+"/"+subBreed+"/images", DogImagesResp.class).getBody();
                        List<String> images = new ArrayList<String>();
                        if(resImages.getMessage().size() > 0){
                            for(int j=0;j<resImages.getMessage().size();j++){
                                images.add(resImages.getMessage().get(j));
                            }
                        }
                        dogResp.getResponse().put(breed+"-"+subBreed, images);
                    }
                }
            }else{
                dogResp.getResponse().put(breed, entry.getValue());
            }
        }

        return dogResp;
    }

    public GetBuyDogResp getBuyDogs(){
        GetBuyDogResp dogResp = new GetBuyDogResp();
        dogResp.setDogs(dogRepository.findAll());
        return dogResp;
    }
    
    public CreateDogResp createDog(Dog dog){
        CreateDogResp dogResp = new CreateDogResp();
        DogRandomImgResp resImages = (DogRandomImgResp) restTemplateBreed.HTTPRequest(DOG_SERVICE_URL+"/breed/"+dog.getBreed()+"/images/random", DogRandomImgResp.class).getBody();
        System.out.println("Rest Images : "+resImages);
        if(resImages.getStatus().equals("error")){
            throw new IllegalStateException(resImages.getMessage());
        }
       
        Dog newDog = dogRepository.save(dog);
        System.out.println("newDog : "+newDog);
        dogResp.setId(newDog.getId());
        dogResp.setName(newDog.getName());
        dogResp.setBreed(newDog.getBreed());
        return dogResp;
    }
    
    @Transactional
    public UpdateDogResp updateDog(Dog dog, Long dogID){
        UpdateDogResp dogResp = new UpdateDogResp();
        Dog existDog = dogRepository.findById(dogID)
            .orElseThrow(() -> new IllegalStateException(
                "dog with id "+dogID+" doesn't exist"
        ));
        System.out.println("Exist Dog : "+existDog);
        if(!dog.getName().equals("")){
            existDog.setName(dog.getName());
        }
        dogResp.setId(existDog.getId());
        dogResp.setName(existDog.getName());
        dogResp.setBreed(existDog.getBreed());
        return dogResp;
    }
    
    public DeleteDogResp deleteDog(Long dogID){
        DeleteDogResp dogResp = new DeleteDogResp();
        Dog existDog = dogRepository.findById(dogID)
            .orElseThrow(() -> new IllegalStateException(
                "dog with id "+dogID+" doesn't exist"
        ));
        dogRepository.deleteById(dogID);
        dogResp.setId(existDog.getId());
        dogResp.setName(existDog.getName());
        dogResp.setBreed(existDog.getBreed());
        return dogResp;
    }
}
