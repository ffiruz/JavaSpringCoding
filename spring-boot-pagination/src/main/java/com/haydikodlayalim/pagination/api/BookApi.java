package com.haydikodlayalim.pagination.api;

import com.haydikodlayalim.pagination.model.Book;
import com.haydikodlayalim.pagination.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookApi {
    private final BookRepository bookRepository;

    @GetMapping
    public Page<Book> pagination(@RequestParam Integer pageSize, //manuel olarak paramtre vererek pagination reqoesti gönderebiliriz.
                                 @RequestParam Integer page){ 
        Pageable pageable = PageRequest.of(page, pageSize); //3.parametre olarak sorted parametresinide kullanabilirdik.
        return bookRepository.findAll(pageable);
    }  //http://localhost:8080/books?pageSize=5&page=0 ----> (5 tane kayıt getir, 1.sayfadan) defaultda page 0 dan başlar.

    @GetMapping("/p")
    public Page<Book> pagination(Pageable pageable){ //Pagable interfacinde pageSize ve page değerlerini dinamik olarak vererek requestimiz çalışır.
    												//Arka planda Pageable pageable = PageRequest.of(page, pageSize,sorted); çalışır.
        // totalElements count query
        return bookRepository.findAll(pageable);    //(int page , int size , Sort sort)  
    }                                               //http://localhost:8080/books/p?size=1&page=0  -->(1 kayıt getir),(1.sayfadan)
    												//http://localhost:8080/books/p?size=3&page=0&sort=id -->(1 kayıt getir),(1.sayfadan),(id ye göre sırala)

    
    
    @GetMapping("/s")
    public Slice<Book> slice(Pageable pageable){  //Slice:Yeni bir yapıdır.Performansı iyileştirir.Veri tabanındaki toplam kayıt ile ilgilenmiyor.
        return bookRepository.findAll(pageable);  //Normalde Page de veri tabanından tüm sorguyu çekip , bizim istediğimiz kadar sayfalama yapıyor.
        										  //Slice da ise paramtre olarak verdiğimiz kayıt sayısından 1 kayıt fazla getirerek , performansı arttırır.
        											//Yani tüm kayıdı getirmektense , istediğimiz kayıt sayısından bir kayıt fazla getirir.
        											//Çok yüksek verilerde iyi olabilir.
    }
}
