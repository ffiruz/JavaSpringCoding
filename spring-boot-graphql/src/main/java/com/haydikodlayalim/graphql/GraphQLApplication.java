package com.haydikodlayalim.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * GraphQL  de iki tip API var.
 * {@link com.coxautodev.graphql.tools.GraphQLQueryResolver} =>Bir API üzerinde sorgulama yapabilmemizi sağlayan interface.
 * {@link com.coxautodev.graphql.tools.GraphQLMutationResolver}=>Sunucumuzda verilerin değiştirilmesi vs.
 * 
 * Yani iki tür apı olacak.GraphQLMutationResolver ile veriler üzerinde değişiklik yapmak , GraphQLQueryResolver verileri sorgulamak için
 *
 */
@SpringBootApplication
public class GraphQLApplication {
    public static void main(String[] args) {
        SpringApplication.run(GraphQLApplication.class, args);
    }
}
