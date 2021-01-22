package com.haydikodlayalim.events.event.listener;

import com.haydikodlayalim.events.event.ReservationCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReservationEventListener {

    @EventListener //ReservationCreatedEvent de eveti yakalamk için gerekli anatasyon
    public void reservationEventHandler(ReservationCreatedEvent reservationCreatedEvent) throws InterruptedException{
        Thread.sleep(5000L);//Burada yapılan işin uzun olduğunu düşünerek Thread koyduk.5 saniye-->Burada diğer 3 tane belirttiğimiz eventlar için geçen süre.
        System.out.println("EventListener-> " + reservationCreatedEvent.getSource().toString());
    }
}
