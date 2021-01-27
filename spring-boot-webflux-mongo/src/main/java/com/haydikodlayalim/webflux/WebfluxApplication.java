package com.haydikodlayalim.webflux;

import com.haydikodlayalim.webflux.entity.Employee;
import com.haydikodlayalim.webflux.repo.EmployeeRepository;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class WebfluxApplication {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class) //Application start eventi gerçekleştiğinde çalışacak metodu bu anatasyonla belirtiyoruz.
    public void appStart() {//Veri tabanına kayıt eklemek için
        if (employeeRepository.count().block() == 0) { //Veri tabanından kayıt sayısını al (Mono bunu sağlıyor.) ve sorgu açlışana kadar bekle.(blockla)
            IntStream.range(0, 100) //0 dan 100 e kayıt bir döngü
                    .mapToObj(this::generate) //Her elemanı generate metodunda instancelarını set ediyoruz.
                    .map(employeeRepository::save)//Burada 100 tane kaydın her bir instance ı save metoduna gönderme isteği başlıyor.Henüz  kayıt olmadı.
                    .collect(Collectors.toList()) //İstekten bulunulan tüm instance ları bir listeye koyduk.Artık Mono ların olduğu bir listemiz var.
                    .forEach(item -> item.subscribe(System.out::println)); //Listedekilerin her bir item a subscribe ol.Burada Flow Apı döngüsü devreye giriyor.
            		//Biz veri tabanı ile ilgili bir işlem yaptığımızda Publisher interface indeki subscribe() metoduna bağlanıyoruz.
            		//veri tabanının sonucunu subscribe ile dinliyoruz.Ve eğer hata alırsa ikinci parametrede  bunu handle edebiliriz. 
            		//subscribe() metodu iki parametre alır.Burada ilk paramtrede sonuç başarılıysa elde edilen değer, ikinci paramere ise hata olursa elde edilecek değer.
            		//Burada  System.out::println =(System.out.println,System.out.println) eşittir.
            		//Burada subscribe() olduktan sonra Flow API’nin çalışma mantığı devreye giriyor.
            
            
         // Mono ve Flux Subscriber interfacenin implmenatasyonları.Mesela Subscribe da onSubscribe()  metodunu implemente ederek.biz subscribe() metoduna subscribe oluruz.
          //Subscribe içerisindeki onNext() ve onError() metodlarıda subscribe(onNext() ,onError())  şeklinde çalışır.
        }
    }

    private Employee generate(int i) { //Builder tasarım deseni kullanarak her bir insantaneın fieldlarını set ediyoruz.Ve employee geri dönüyor. 
       return Employee.builder() //Builder desing pattern , lombockdan geliyor.
               .name("Name" + i)
               .lastname("Lastname"+i)
               .birthDate(LocalDate.now())
               .build();
    }
}
