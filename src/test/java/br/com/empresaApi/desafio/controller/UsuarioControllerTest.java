package br.com.empresaApi.desafio.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void naoDeveriaPermitirCadastrarUmUsuarioPassandoUmFormatoDeEmailInvalido() throws Exception {
        URI uri = new URI("/usuario");
        String json = "{\"nome\": \"nomeTeste\",\"email\": \"teste@te@te.com\",\"senha\":\"1234567\",\"cnpj\": \"11.111.111/1111-11\"}";

        mockMvc.
                perform(MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.
                        status().is(400));

    }
    @Test
    public void naoDeveriaPermitirCadastrarUmaSenhaComMenosDe7Caracteres() throws Exception {
        URI uri = new URI("/usuario");
        String json = "{\"nome\": \"nomeTeste\",\"email\": \"teste@teste.com\",\"senha\":\"123456\",\"cnpj\": \"11.111.111/1111-11\"}";

        mockMvc.
                perform(MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.
                        status().is(400));

    }
}
