package com.haydikodlayalim.flyway.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Auto increment
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

}





//Serilization:Java object oriented bir dil.Yani primitive tipler dışında her şey nesne.Ve biz bu nesneleri bazen JVM dışında kullanmak gerekebilir
//Fakat dışarıda kullandığımız bir nesnemizi tekrar içeride kullanmak istediğimizde nesne içinde kullanılan değerlerin hangi tipte olduğunu öğrenemiyoruz. 
//Yani herhangi bir sınıftan bir nesne üretip, bunu bir dosyaya yazdırıp onu tekrar dosyadan okuduğumuzda değerlerin tip bilgisini bilememe problemimiz var.
//İşte tam bu durumda Java Serialization API yardımımıza koşuyor..