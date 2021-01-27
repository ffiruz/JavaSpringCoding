package com.haydikodlayalim.webflux.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration //
@EnableReactiveMongoRepositories("com.haydikodlayalim.webflux")
public class MongoConfiguration extends AbstractReactiveMongoConfiguration { //AbstractReactiveMongoConfiguration abstract classını extends ederiz ve bize override etmesi için iki tane metot sunar.
	
    @Override
    public MongoClient reactiveMongoClient() {  //Client tanımlama 	metodu
        return MongoClients.create();//Reactive clienı kullandık.Default da localhost:27017'u kullanırız.
        //Eğer farklı bir konsigurasyon veya ıp tanımlaması yapacaksak MongoClients içerisinde ayarlamalar yapabiliriz.
    }

    @Override
    protected String getDatabaseName() { //Database ismi
        return "mydb"; //Application.property dosyasından da çekilebilirdi.Veya @value anatasyonu ile.
    }
}
