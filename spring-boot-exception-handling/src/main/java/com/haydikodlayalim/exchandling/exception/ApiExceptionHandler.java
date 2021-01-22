package com.haydikodlayalim.exchandling.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  //Rest Controller olarak belirttiğimz tüm apı larda  bie exception aldığında buraya geleceğini  belirtiyoruz.
//@ControllerAdvice -->Controller anatasyonunun kullandığımız da burada exceptionları karşılayabiliriz.JSF vs projelerinde çalışıyorsak managedBeanin ismi burada yazılabilir vs.Mvc kullnacaksak view ile burada karşılayabiliriz.
public class ApiExceptionHandler {

    @ExceptionHandler({EntityNotfoundException.class}) //entityNotFound global olarak tanımladık.
    public String entityNotFound() {
        return "Record not found";  //Burada daha yapısal bir şeklilde JSON içerisinde de dönebiliriz.Sebebi ,hata kodu ,Localization vs yapılabilir.
    }

    @ExceptionHandler({IllegalArgumentException.class})//Farklı türde exceptionlar yazabiliriz.
    public String iaException() {
        return "Wrong parameter";
    }
}
