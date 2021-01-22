package com.haydikodlayalim.events.service;

import com.haydikodlayalim.events.api.HotelBookRequest;
import com.haydikodlayalim.events.event.ReservationCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher; //Eventi yayınlayabilmek için gerekli.Instance default da spring context içerisinde var.

    
    //Alında burada publishReservationEvent metodu API dan bağımsız olarak çalışan bir thread gibi.Bunun @Async anatasyonunu kullnarak yapıyoruz.
    //Bunu kullanabilmek için EventApplication dosyası içerisine @EnableAsync anatasyonu ile enable ediyoruz.
    
    @Async//Burada uzun süren işlemler olduğu için asenkron çalışmasını sağlarız.
    public void publishReservationEvent(HotelBookRequest hotelBookRequest){
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        applicationEventPublisher.publishEvent(new ReservationCreatedEvent(hotelBookRequest));//HotelBookRequest instnaceını gönderiyoruz.Ve yayınlanacak.
   
        //parametre oalrak ApplicationEventi extend ettiğimiz classı veriyoruz.
    }
}
