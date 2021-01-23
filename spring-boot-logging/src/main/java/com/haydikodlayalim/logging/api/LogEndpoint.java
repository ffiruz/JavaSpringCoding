package com.haydikodlayalim.logging.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/log")
public class LogEndpoint {
	
	
	//Logger logger=LoggerFactory.getLogger(this.getClass()); LogEndpoint classındaki tüm logları logger içerisinde handle et.
	//Ancak bunu loglayacağımız her class için bunu yazmamız gerekiyor.
	//Bizi bundan kurtracak çözüm , Lombock içeriisndeki slf4j anatasyonu.

    @GetMapping
    public String getDetails() {
        log.info("get details methodu basladi"); //slf4j bir log adında bir instance bizim için oluşturuyor.Bir çok levelı var.
        return internalLogDetail();
    }

    private String internalLogDetail() {
        try {
            log.debug("internalLogDetail methodu basladi"); //Debug ederken requestin bu metoda uğrayı uğramadığının loglanması için debug kullanıyoruz.
            Thread.sleep(1000);//Bazı işlemlerin yapıldığını düşünelim.
            return "API Mesaj";
        } catch (InterruptedException e) { //Genel exceptionların hepsini yakalamak sağlıklı değil.Burada checkedException olarak belirtmek daha doğru.
            log.error("Hata : {}", e);//Hata mesajını logluyoruz.	
        }
        return "";
    }

    /*
    - Loglama Asenkron olmalidir.Loglama performansı bir nebze düşürü.Ancak hata olduğund akynağını öğrenebilmek için gerekli.Ayrıca 
    	requesti blocklamadan arka planda asenkron çalışması doğru olan.
   
    - printStackTrace ve System.out.println() kullanilmamalidir (format).Çünkü Log formalarını bozar.
    
    - Sensitive data olmamalidir.Müşteri bilgisi , kullanıcı id si, password gibi hassas bilgiler kullanılmamalıdır.Veri güvenliğ sorunu oluşabilir.
    
    - Tum loglar merkezi toplanmalidir, belli formatta.
    
    - Level dikkatli kullanilmalidir.Kullanıcı sisteme login olduğunda error log verilmesi vs hatalara dikkat edilmeli.	
    
    - Farkli levellar için farkli appendarlar kullanilabilir.Levelların yönetimini daha iyi olmasını sağlar.
     */
}
  