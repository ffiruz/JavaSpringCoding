package com.haydikodlayalim.events.api;

import com.haydikodlayalim.events.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookingApi {

    @Autowired
    private ReservationService reservationService;

    @PostMapping//Client tarafından Apı ya bir hotel reservation isteği gönderiyoruz.
    public void bookHotel(@RequestBody HotelBookRequest hotelBookRequest){ //Parametre olarak HotelBookRequest den bir instance gönderiyoruz.
      
    	reservationService.publishReservationEvent(hotelBookRequest);
       
    	System.out.println(" Kullanici Istegi isleme alindi." + hotelBookRequest);
    }
    
    
    //Burada ilk API ya istek atıldığında , kullanıcıya bir mesaj dönüyoruz.Notification gibi.
    //Kullanıcıya cevap verildikten sonra bizim processler çalışacak.(Notification,Hotel Integration,Availibility Change)
    //İlk olarak Service katmanında asenkron bir metod çalışacak.
    //Bu service de ApplicationEventPublisher ile bizim eventimiz yayınlanacak.
    //Biz event paketi içerisinde ApplicationEventPublisher abstract classını extends ettiğimiz class da çalışacak.->ReservationCreatedEvent
    //Ve ReservationCreatedEvent da oluşturulan eventleri yakalamak için de evenr Listelenerlarımız olacak.-->ReservationEventListener 
    //Ve kullanıcıya sürecin bittiği mesajı dönecek.ReservationEventListener içerisinden
    
    
    //Aslında kullanıcıya hızlı şekilde processin başladığını bildirip , 10 saniye sonra sürec tamamlandıktan sonra kullanııya processin bittiğini bildiriyoruz.

   //Uzun süren işlemleri eventPublisher ile çözebiliriz.
}
