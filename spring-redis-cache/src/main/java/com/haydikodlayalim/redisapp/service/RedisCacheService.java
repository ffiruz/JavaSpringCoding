package com.haydikodlayalim.redisapp.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {

    @Cacheable(cacheNames = "mySpecialCache")
    public String longRunnigMethod() throws InterruptedException {
        Thread.sleep(5000L); //5 saniyelik bir iş yapıldığını düşünelim.Ardından return olarak "metod Calisti" stringe dönecek.
        return "method calisti";
    }
    
    
    //Bir metodunun cachlemek için @Cacheable anatasyonunu kullanıyoruz.ve cache name ismini veriyoruz.

    @CacheEvict(cacheNames = "mySpecialCache") ////Bir metodunun cachlni silmek için  @CacheEvict anatasyonunu kullanıyoruz.ve cache name ismini veriyoruz.
    public void clearCache(){
        System.out.println("cache temizledi");
    }
}
