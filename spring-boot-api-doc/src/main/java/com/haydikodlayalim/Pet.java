package com.haydikodlayalim;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Benim Pet nesnem", description = "Benim Pet" ) //Bir apının modelini Swaggera tanıtıyoruzç
public class Pet {

    @ApiModelProperty(value = "Pet nesnesinin tekil Id alani") //Fiedlları swaggera tanıtıyoruz.
    private int id;

    @ApiModelProperty(value = "Pet nesnesinin Adi alani")
    private String name;

    @ApiModelProperty(value = "Pet nesnesinin Tarih alani")
    private Date date;
    
}
