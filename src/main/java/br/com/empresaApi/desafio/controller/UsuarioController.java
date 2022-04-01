package br.com.empresaApi.desafio.controller;

import br.com.empresaApi.desafio.service.form.UsuarioForm;
import br.com.empresaApi.desafio.useCase.UsuarioUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

    private boolean logado = false;

    @Autowired
    UsuarioUseCase useCase;

    @GetMapping
    public ResponseEntity<?> obterUsuarioPorEmail(String email) {
        LOGGER.info("Chamando endpoint para obter um usuario por um email...");
        ResponseEntity<?> response = useCase.obterUsuarioPorEmail(email);
        LOGGER.info("Busca do concluida!\n");
        return response;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm) {
        LOGGER.info("Chamando endpoint para cadastrar usuario...");
        ResponseEntity<String> response = useCase.cadastrarUsuario(usuarioForm);
        LOGGER.info("Usuario cadastrado com sucesso!\n");
        return response;
    }


}
