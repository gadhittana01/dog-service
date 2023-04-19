package com.example.dogservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dogservice.models.CreateDogResp;
import com.example.dogservice.models.DeleteDogResp;
import com.example.dogservice.models.Dog;
import com.example.dogservice.models.GetBuyDogResp;
import com.example.dogservice.models.GetDogsResp;
import com.example.dogservice.models.UpdateDogResp;
import com.example.dogservice.services.DogServices;

@SpringBootTest
public class DogControllerTest {
    @Mock
    private DogServices dogServices;
 
    @Test
    void testSuccessGetDogImages(){
        GetDogsResp want = new GetDogsResp();
        Map<String, List<String>> dogRes = new HashMap<String, List<String>>();
        dogRes.put("pom", Arrays.asList("black", "pink"));
        want.setResponse(dogRes);

        Map<String, List<String>> dogResHTTP = new HashMap<String, List<String>>();
        dogResHTTP.put("pom", Arrays.asList("black", "pink"));
        BDDMockito.given(dogServices.getDogs())
            .willReturn(new GetDogsResp(dogResHTTP));
        
        DogController mock = new DogController(dogServices);
        Assertions.assertThat(mock.getDogs()).isEqualTo(want);
    }
    
    @Test
    void testGetBuyDogs(){
        GetBuyDogResp want = new GetBuyDogResp();
        want.setDogs(new ArrayList<>(){
            {
                add(new Dog(1L, "mochi", "shiba"));
                add(new Dog(2L, "boba", "terrier"));
            }
        });

        BDDMockito.given(dogServices.getBuyDogs())
            .willReturn(new GetBuyDogResp(new ArrayList<>(){
                {
                    add(new Dog(1L, "mochi", "shiba"));
                    add(new Dog(2L, "boba", "terrier"));
                }
            }));
        
        DogController mock = new DogController(dogServices);
        Assertions.assertThat(mock.getBuyDogs()).isEqualTo(want);
    }
    
    @Test
    void testSuccessBuyDog(){
        CreateDogResp want = new CreateDogResp();
        want.setBreed("shiba");
        want.setId(1L);
        want.setName("mochi");
        Dog request = new Dog(1L,"mochi","shiba");

        BDDMockito.given(dogServices.createDog(new Dog(1L,"mochi","shiba")))
            .willReturn(new CreateDogResp(1L, "mochi", "shiba"));
        
        DogController mock = new DogController(dogServices);
        Assertions.assertThat(mock.buyDog(request)).isEqualTo(want);
    }
    
    @Test
    void testSuccessUpdateDog(){
        UpdateDogResp want = new UpdateDogResp();
        want.setBreed("shiba");
        want.setId(1L);
        want.setName("mochi");
        Dog request = new Dog(1L, "mochi","shiba");
        Long requestID = 1L;

        BDDMockito.given(dogServices.updateDog(new Dog(1L,"mochi","shiba"), 1L))
            .willReturn(new UpdateDogResp(1L, "mochi", "shiba"));
        
        DogController mock = new DogController(dogServices);
        Assertions.assertThat(mock.updateDog(requestID, request)).isEqualTo(want);
    }
    
    @Test
    void testSuccessDeleteDog(){
        DeleteDogResp want = new DeleteDogResp();
        want.setBreed("shiba");
        want.setId(1L);
        want.setName("mochi");
        Long requestID = 1L;

        BDDMockito.given(dogServices.deleteDog(1L))
            .willReturn(new DeleteDogResp(1L, "mochi", "shiba"));
        
        DogController mock = new DogController(dogServices);
        Assertions.assertThat(mock.deleteDog(requestID)).isEqualTo(want);
    }
}
