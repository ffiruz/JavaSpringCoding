package com.haydikodlayalim.rc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestClientApplication {

    @Bean  //Uygulama ilk ayağa kalktığından sonra @bean anatasyonu sayesinde IOC içerisine ekleme yaptık.
    public RestTemplate restTemplate(){
        return new RestTemplate(); //Constructor içerisinde defaultda hangi url ile çalışacağımızı veya jackson, gson konfigurasyonlarını burada belirtiyoruz.Ve uygulama boyunca kullanımı olacak.
    }

    public static void main(String[] args) {
        SpringApplication.run(RestClientApplication.class, args);
    }
}
