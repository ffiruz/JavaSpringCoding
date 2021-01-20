package com.haydikodlayalim.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect //Aop yi yapacak classların alması gereken anatasyon.Yani bu aspect bir yerleri dinleyecek.service paketi altındaki tüm metot çağrımlarını yakalayacak ve işlem yapacak.
//(Loglama , yetkilendirme , metrikler(metod ne kadar sürede cevap verdi) gibi bir çok işlem için biz araya gireceğiz.araya girmek(cross cutting concern)

@Component//Buclassı initialize etmek için(IOC CONTAİNER içinde bir instance oluşturma)
public class ServiceAspect {

    @Before("execution(* com.haydikodlayalim.aop.service.MessageService.mesajVer(..))")  //İlgili uzantıdaki metot çalıştırılırsa onu yakala. (*) ile tüm paket altında vs diyebilriiz.
    //mesajVer() metodu çalışmadan önce  mesajVerMetodundanOnce() metodunun çalışmasını istiyoruz
    public void mesajVerMetodundanOnce(JoinPoint joinPoint) { //JoinPoint : Anlık statetimizi yakalar.(Çağrılacak metot , parametre vs.)
        System.out.println("Mesaj ver metodundan önce parametre yakalandi param: " + joinPoint.getArgs()[0]); //joinpoint bir çok metoda sahiptir.Paramtreyi ekrana yazdırma yapıyoruz burada.
        System.out.println(joinPoint.getSignature());
    } 
    //@before anatasyonu metot başlamadan önce çalışır.Ve içindeki tüm statementlar çalıştıktan sonra mesajVer() metodu çalışır.O nedenle performans açısından @before ve @after anatasyonlarını doğru kullanmak gerekir. 
    
    //@yetkilendirme için @before , logşama için @after mantıklı olabilir.
  
    
    
    @After("execution(* com.haydikodlayalim.aop.service.*.*(..))")//Burada  * diyerek ilgili paket atındaki tüm class diyoruz.Buradaki * önemli.
    //mesajVer() metodu çalıştıktan sonra mesajVerMetodundanOnce() metodunun çalışmasını istiyoruz
    public void mesajVerMetodundanSonra(JoinPoint joinPoint) {
        System.out.println("Mesaj ver metodundan sonra parametre yakalandi param: " + joinPoint.getArgs()[0]);
        System.out.println(joinPoint.getSignature());
    }
}


//@AfterReturning:Geri dönüş yapıldıktan sonra
//@AfterThrowing:Bir exception throw edildikten sonra vs anatasyıonlar kullanılabilir.