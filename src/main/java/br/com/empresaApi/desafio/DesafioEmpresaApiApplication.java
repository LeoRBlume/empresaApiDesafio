package br.com.empresaApi.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafioEmpresaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioEmpresaApiApplication.class, args);
    }

}
