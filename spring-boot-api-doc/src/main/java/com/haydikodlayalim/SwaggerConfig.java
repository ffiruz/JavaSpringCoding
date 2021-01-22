package com.haydikodlayalim;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 //Swagger konfigurasyon  buradan yönlendireceğimizi belirtiypruz.->Dökümantasyonun yayınlanabilmesi için gerekli.
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.haydikodlayalim")) //Uygulamada dökümantasyonunun yapılmasının istediğin classların bulunduğu package belirtiyoruz.Çünkü bazı apı lar içim dökümantasyonun oluşturulması istenmeyebilir.
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());

    }


    private ApiInfo apiEndPointsInfo() { //API genel bilgisini buradan belirtiyoruz.
        return new ApiInfoBuilder().title("Spring Boot Swagger Örnegi") //Genel başlık
                .description("Pet Api Dokümantasyonu")//Genel açıklama
                .contact(new Contact("Haydi Kodlayalim", "", ""))//Şirket bilgileri,  email vs.
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")//
                .version("1.12.3") //API versiyonu.
                .build();
    }

}
