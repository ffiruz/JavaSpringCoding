package com.haydikodlayalim.exchandling.api;

import com.haydikodlayalim.exchandling.dto.Car;
import com.haydikodlayalim.exchandling.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequestMapping("/car") //Araçlarımızı listeyen bir api olduğunu düşünelim.
public class CarApi {

    @Autowired
    private CarService carService;

    @GetMapping   //http://localhost:8080/car?name=test
    public ResponseEntity<Car> getCar(@RequestParam String name) { //Client dan gelen name'e göre arama yapma.
        return ResponseEntity.ok(carService.getCar(name));
    }

    /**
    @ExceptionHandler({EntityNotfoundException.class})  //Eğer local olarak exceptionları yönetecksen ilgili class içinde belirtiriz.Ve bu exception sadece bu apı içinde geçerli olacak.
    public String entityNotFound() {
        return "Record not found";
    }
    **/  //@ExceptionHandler ->Paramtre olarak verdiğimiz exception.classıo verildiğinde (EntityNotfoundException) , entityNotFound metodu çalışacak.
}


//Tüm apı larımızı ortak olarak kullnacağı bir exception yapsı için ApiExceptionHandler.java dosyası  içerisinde @RestControllerAdvice anatasyonu ile global olarak tanımlayabiliriz.