package com.jm.projet.filrouge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@EnableSwagger2WebMvc
// TODO check impact of commenting out the following annotation
// Google : @pageabledefault parameters name error
// https://github.com/springfox/springfox/issues/2623
// cf post from MiguelAngelLV on 12/12/18
public class FilrougeApplication {

    public static void main(String[] args) {
        SpringApplication.run (FilrougeApplication.class, args);
    }

}
