package com.haydikodlayalim.exchandling.service;

import com.haydikodlayalim.exchandling.dto.Car;
import com.haydikodlayalim.exchandling.exception.EntityNotfoundException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private static List<Car> carList = new ArrayList<>();

    @PostConstruct //Bu classın bir instance'ı oluşturulduğunda , bu metod bir kereye mahsus çalışacak.@Service anatasyonu kullanıldığından IOC container içerisine CarService instanceı Spring oluşturmuş olacak.Ve PostConstruct anatasyonu da bie kereliğine çalışmış olcak.
    public void init() {
        carList.add(new Car("Car A", "Brand 1"));
        carList.add(new Car("Car B", "Brand 2"));
        carList.add(new Car("Car C", "Brand 3"));
    }

    public Car getCar(String name) {
        if (name.startsWith("1")) { //Eğer name 1 ile başlarsa IllegalArgumentException atacak.
            throw new IllegalArgumentException(); //Eğer burası çalıştığında ApıExceptionHandler.java dosyası  içinde iaException() metodumuz çalışacak.Ve oradaki geri dönüş değeri
        }
        return carList.stream().filter(item -> item.getName().equals(name)).findFirst() //Java 8 ile gelen Stream ile liste üzerinde filreleme yapıyoruz.Ve liste içerisindeki herhangi bir nesnenin name i bizim parametre olarak verdiğimiz name 'e eşitse findFirst ile elemanı filtreliyoruz.
                .orElseThrow(() -> new EntityNotfoundException(name)); //Eğer datayı bulamazsa yazdığımız custom exception'ı fırlatacak.
    }
}


//Exceptionları oluşturma case'ini servis üzerinde yapmamız daha doğru. 