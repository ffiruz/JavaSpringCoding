package com.haydikodlayalim.rc.api;

import com.haydikodlayalim.rc.model.KisiDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/test")
public class RestClientController {

    private static final String webUrl = "http://localhost:8081/kisi";  //:8081 portundaki uygulamaya client olacak.spring-data-postgresql uygulamasına client olacağız.

    @Autowired
    private RestTemplate restTemplate;  //RestTemplate default da gson ve jsckson apılarını sağlar.
    //Bir instance ını kullabilmek için IOC içerisine @Autowired ile çağrı yapıyoruz.
    //Not:Eğer bir metodu IOC içerisinde eklemek için @Bean kullanırız.Mtoddan dönen değeri IOC içerisine ekler.RestClientApplication dosyasında.
    //restTemplate işlemlerini service katmanında yapmak daha doğru.
    

    @GetMapping
    public ResponseEntity<List<KisiDto>> getKisiList(){  //:8081 uygulamasındaki kisi nesnesini bu uygulamada da dto olarak oluşturmalıyız.
        ResponseEntity<List> result = restTemplate.getForEntity(webUrl, List.class); //restTemplate içinde get,delete,post,put istekleri atabiliriz.
        //getForEntity(port,dönen nesneyi dönüştürülecek tip , eğer parametre varsa Map olarak ekleme) vs farklı implemantasyonları var.
        List<KisiDto> responseBody = result.getBody(); 
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<KisiDto> kaydet(@RequestBody KisiDto kisiDto){  //:8081  portundaki post metodunu çağırma 
        ResponseEntity<KisiDto> result = restTemplate.postForEntity(webUrl, kisiDto, KisiDto.class); //(url ,post edilecek parametre ,dönüştürülecek tip)
        KisiDto responseBody = result.getBody();
        return ResponseEntity.ok(responseBody);
    }
}


//Java Json'ı otomotik olarak java objesine dönüştürme işini yapmaz.
//Java objelerinin json datalarının serializing  veya deserializing yapmak için GSON ve Jackson API larını kullanırız.

//Java Serialization:
//--Ancak Java’da kullanılan nesneler, Java platformunda (JVM) hayat bulurlar. Platform dışında nesnelerin, hiçbir anlamı yoktur.
//Nesne yönelimli programlama paradigmasını destekleyen Java’da, tasarlanan nesnelerin tekrar kullanılabilmesi (reuse) önemli bir konu olduğuna göre, bu nesneleri Java platformu dışında da hayata geçirmek gerçekten önemlidir.
//Bahsedilen bu problem, Java Serialization API sayesinde çok kolay bir şekilde aşılabiliyor.
//Konuya bir de şu yönden bakarsak, herhangi bir nesne içerisindeki fieldları bir dosyaya yazdırdığımızda, bu verilerin sadece değerlerini (values) dosya içerisinde depolarız. 
//Halbuki bu verilerin sınıf tanımlaması içerisindeki tipleri de en az değerleri kadar önemlidir. Herhangi bir nesnenin fieldındaki değer 3 ise, bu değerin string mi int mi olduğunun dosya üzerinde bir anlamı yoktur.
//Java Serialization API sayesinde bir nesnenin birebir kopyasını, Java platformu dışında da depolayabiliriz. 
//Bu mekanizma ile daha sonra,  nesneyi depolanan yerden çekip, aynı durum (state) ve özellikleri ile kullanmaya devam edebiliriz.
//Tüm bu sisteme, Object Serialization (Nesne Serileştirme) adı verilir.