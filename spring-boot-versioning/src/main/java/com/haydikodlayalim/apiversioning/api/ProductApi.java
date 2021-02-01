package com.haydikodlayalim.apiversioning.api;

import com.haydikodlayalim.apiversioning.dto.ProductV1;
import com.haydikodlayalim.apiversioning.dto.ProductV2;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductApi {

    // URI Versioning

    // http://localhost:8080/api/v1/product  //Bu kötü örnek  olarak düşünülür.Url üzerinden versiyonlama doğru bir yaklaşım olmayabilir.
    @GetMapping(value = "/v1/product")//v1 versiyonu
    public ResponseEntity<ProductV1> pathVersioningProductV1() {
        return ResponseEntity.of(Optional.of(new ProductV1("HP Laptop"))); //Client burayı çağırdığından "Hp Laptop" dönüş değerini ,Optional ile sarmalayıp ,Response Entity ile response olacak.
    }

    // http://localhost:8080/api/v2/product
    @GetMapping(value = "/v2/product") //v2 versiyonu
    public ResponseEntity<ProductV2> pathVersioningProductV2() {
        return ResponseEntity.of(Optional.of(new ProductV2("HP Laptop", BigDecimal.TEN)));
    }

    //ProductV1 ile Product V2 dto lar ,Product objesinden versiyonlanmıştır.

    // Param Versioning
    // http://localhost:8080/api/param/product?apiVersion=1  //parametreyi ? ile ekliyoruz  "?params_name"
    @GetMapping(value = "/param/product", params = "apiVersion=1") //params ile birbirinden ayırıyoruz.Avantaj olarak sürekli url lerle uğraşmak zorunda kalmayacağız.
    public ResponseEntity<ProductV1> paramVersioningProductV1() {
        return ResponseEntity.of(Optional.of(new ProductV1("HP Laptop")));
    }

    // http://localhost:8080/api/param/product?apiVersion=2
    @GetMapping(value = "/param/product",  params = "apiVersion=2")
    public ResponseEntity<ProductV2> paramVersioningProductV2() {
        return ResponseEntity.of(Optional.of(new ProductV2("HP Laptop", BigDecimal.TEN)));
    }

    // Header Versioning  
    // http://localhost:8080/api/header/product
    @GetMapping(value = "/header/product", headers = "X-API-VERSION=1")   //heaaders ile birbirinden ayırıyoruz.Header içerisinde belirtiyoruz
    public ResponseEntity<ProductV1> headerVersioningProductV1() {
        return ResponseEntity.of(Optional.of(new ProductV1("HP Laptop")));
    }
    
    
    //Headerlar yaygın olarak X-API-VERSION-1 vs. şeklinde kullanılır.

    // http://localhost:8080/api/header/product
    @GetMapping(value = "/header/product",  headers = "X-API-VERSION=2")
    public ResponseEntity<ProductV2> headerVersioningProductV2() {
        return ResponseEntity.of(Optional.of(new ProductV2("HP Laptop", BigDecimal.TEN)));
    }
}


//Bir client ProductV1 ile çalışırken başka bir cleint ProductV2 ile  çalışabilir.
//Eğer nesne değişime ihtiyaç duyarsa (mesela yeni bir field eklenecek) gibi durumlarda  dto classlarında veersiyonlamak gerekebilir.
//Çünkü başka bir client yapılan değişiklikten etkilenmemesi için.
