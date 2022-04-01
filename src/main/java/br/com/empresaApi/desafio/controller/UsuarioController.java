package br.com.empresaApi.desafio.controller;

import br.com.empresaApi.desafio.service.form.UsuarioForm;
import br.com.empresaApi.desafio.useCase.UsuarioUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    UsuarioUseCase useCase;

    @PostMapping
    public ResponseEntity<String> cadastrarUsuario(@RequestBody UsuarioForm usuarioForm) {
        LOGGER.info("Chamando endpoint para cadastrar usuario...");
        ResponseEntity<String> response = useCase.cadastrarUsuario(usuarioForm);
        LOGGER.info("Usuario cadastrado com sucesso!\n");
        return response;
    }
}
