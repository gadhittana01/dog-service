package com.example.dogservice.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.dogservice.config.RestTemplateAllConfig;
import com.example.dogservice.config.RestTemplateBreedConfig;
import com.example.dogservice.models.CreateDogResp;
import com.example.dogservice.models.DeleteDogResp;
import com.example.dogservice.models.Dog;
import com.example.dogservice.models.DogBreedResp;
import com.example.dogservice.models.DogImagesResp;
import com.example.dogservice.models.DogListResp;
import com.example.dogservice.models.DogRandomImgResp;
import com.example.dogservice.models.GetBuyDogResp;
import com.example.dogservice.models.GetDogsResp;
import com.example.dogservice.models.UpdateDogResp;
import com.example.dogservice.repositories.DogRepository;

@SpringBootTest
public class DogServicesTest {
    @Mock
    private RestTemplateBreedConfig restTemplateBreed;

    @Mock
    private RestTemplateAllConfig restTemplateAll;
    
    @Mock
    private DogRepository dogRepository;

    @Test
    void testSuccessGetDogs(){
        GetDogsResp want = new GetDogsResp();
        Map<String, List<String>> dogRes = new HashMap<String, List<String>>();
        dogRes.put("pom", Arrays.asList("black", "pink"));
        dogRes.put("shiba", Arrays.asList("link2"));
        dogRes.put("sheepdog-black", Arrays.asList());
        dogRes.put("sheepdog-pink", Arrays.asList());
        dogRes.put("terrier-black", Arrays.asList("link1","link2"));
        dogRes.put("terrier-pink", Arrays.asList("link1","link2"));
        want.setResponse(dogRes);
        
        Map<String, List<String>> dogResHTTP = new HashMap<String, List<String>>();
        dogResHTTP.put("pom", Arrays.asList("black", "pink"));
        dogResHTTP.put("shiba", Arrays.asList("black", "pink"));
        dogResHTTP.put("sheepdog", Arrays.asList("black", "pink"));
        dogResHTTP.put("terrier", Arrays.asList("black", "pink"));
        BDDMockito.given(restTemplateAll.HTTPRequest(BDDMockito.any(), BDDMockito.any()))
            .willReturn(new ResponseEntity(new DogListResp(dogResHTTP, "success"), HttpStatus.OK));
        
        BDDMockito.given(restTemplateBreed.HTTPRequest(BDDMockito.any(), BDDMockito.any()))
            .willReturn(new ResponseEntity(new DogImagesResp(Arrays.asList("link1", "link2", "link3"), "success"), HttpStatus.OK))
            .willReturn(new ResponseEntity(new DogBreedResp(Arrays.asList("black", "pink"), "success"), HttpStatus.OK))
            .willReturn(new ResponseEntity(new DogBreedResp(Arrays.asList("black", "pink"), "success"), HttpStatus.OK))
            .willReturn(new ResponseEntity(new DogImagesResp(Arrays.asList("link1", "link2"), "success"), HttpStatus.OK))
            .willReturn(new ResponseEntity(new DogImagesResp(Arrays.asList("link1", "link2"), "success"), HttpStatus.OK));

        DogServices mock = new DogServices(restTemplateAll, restTemplateBreed, dogRepository);
        Assertions.assertThat(mock.getDogs()).isEqualTo(want);
    }
    
    @Test
    void testSuccessGetBuyDogs(){
        GetBuyDogResp want = new GetBuyDogResp();
        want.setDogs(new ArrayList<>(){
            {
                add(new Dog(1L, "mochi", "shiba"));
                add(new Dog(2L, "boba", "terrier"));
            }
        });
        
        BDDMockito.given(dogRepository.findAll())
            .willReturn(new ArrayList<>(){
                {
                    add(new Dog(1L, "mochi", "shiba"));
                    add(new Dog(2L, "boba", "terrier"));
                }
            });

        DogServices mock = new DogServices(restTemplateAll, restTemplateBreed, dogRepository);
        Assertions.assertThat(mock.getBuyDogs()).isEqualTo(want);
    }

    @Test
    void testSuccessCreateDog(){
        CreateDogResp want = new CreateDogResp();
        want.setBreed("shiba");
        want.setId(1L);
        want.setName("mochi");
        Dog request = new Dog(1L, "mochi","shiba");
        BDDMockito.given(restTemplateBreed.HTTPRequest(BDDMockito.any(), BDDMockito.any()))
            .willReturn(new ResponseEntity(new DogRandomImgResp("message","success"), HttpStatus.OK));
            
        BDDMockito.given(dogRepository.save(new Dog(request.getId(), request.getName(), request.getBreed())))
            .willReturn(new Dog(1L, "mochi","shiba"));

        DogServices mock = new DogServices(restTemplateAll, restTemplateBreed, dogRepository);
        Assertions.assertThat(mock.createDog(request)).isEqualTo(want);
    }
    
    @Test
    void testErrorCreateDog(){
        CreateDogResp want = new CreateDogResp();
        want.setBreed("shiba");
        want.setId(1L);
        want.setName("mochi");
        Dog request = new Dog(1L, "mochi","shiba");

        BDDMockito.given(restTemplateBreed.HTTPRequest(BDDMockito.any(), BDDMockito.any()))
            .willReturn(new ResponseEntity(new DogRandomImgResp("message","error"), HttpStatus.INTERNAL_SERVER_ERROR));
        
        DogServices mock = new DogServices(restTemplateAll, restTemplateBreed, dogRepository);
        Assertions.assertThatThrownBy(() -> mock.createDog(request))
            .isInstanceOf(IllegalStateException.class);

    }

    @Test
    void testSuccessUpdateDog(){
        UpdateDogResp want = new UpdateDogResp();
        want.setBreed("shiba");
        want.setId(1L);
        want.setName("mochi");
        Dog request = new Dog(1L, "mochi","shiba");
        Long requestID = 1L;
        
        BDDMockito.given(dogRepository.findById(1L))
            .willReturn(Optional.of(new Dog(1L, "mochi","shiba")));
            
        DogServices mock = new DogServices(restTemplateAll, restTemplateBreed, dogRepository);
        Assertions.assertThat(mock.updateDog(request, requestID)).isEqualTo(want);
    }
    
    @Test
    void testSuccessDeleteDog(){
        DeleteDogResp want = new DeleteDogResp();
        want.setBreed("shiba");
        want.setId(1L);
        want.setName("mochi");
        Long requestID = 1L;
        
        BDDMockito.given(dogRepository.findById(1L))
            .willReturn(Optional.of(new Dog(1L, "mochi","shiba")));
            
        DogServices mock = new DogServices(restTemplateAll, restTemplateBreed, dogRepository);
        Assertions.assertThat(mock.deleteDog(requestID)).isEqualTo(want);
    }
}
