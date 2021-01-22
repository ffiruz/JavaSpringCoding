package com.haydikodlayalim;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//Swaggerın tek kötü tarafı kodumuzda kod kalabalığı oluşturması.Anatasyonlarla.

//Eğer Apı doğru çalışıyorsa , dökümantasyon adresini loglar içinde bulabilriz.--> /localhost:8081/v2/api-docs

//Swagger-ui ile de springfox un ui implemantasyonunu bize sağlar.localhost:8081/swagger-ui.html
//Aynı zamanda bu ui asyesinde apıler test de edilebilir.

@RestController
@RequestMapping("/pet")
@Api(value = "Benim Pet API dökumantasyonum") //Bir api classını Swaggera tanıtıyoruz.
public class PetController {

    private List<Pet> petList = new ArrayList<>();

    @PostConstruct //Bu class ilk init olduğunda 
    public void init() {
        petList.add(new Pet(1, "Test Pet", new Date()));
    }

    @PostMapping
    @ApiOperation(value = "Yeni Pet Ekleme metodu", notes = "Bu metodu dikkatli kullan") //htttp operastyonlarını swaggera tanıtıyoruz.
    public ResponseEntity<Pet> kaydet(@RequestBody @ApiParam(value = "hayvan") Pet pet) { //metoda gelen parametreyi bu şekilde bilgilendirebiliriz.
        petList.add(pet); //kayıt ettiğimiz pet instancenı listeye ekliyoruz.Database yok.
        return ResponseEntity.ok(pet);
    }

    @GetMapping
    @ApiOperation(value = "Pet listesi metodu", notes = "Bu metod tümünü getirir")
    public ResponseEntity<List<Pet>> tumunuListele() {
        return ResponseEntity.ok(petList);
    }
}
