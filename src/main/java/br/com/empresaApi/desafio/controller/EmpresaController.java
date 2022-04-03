package br.com.empresaApi.desafio.controller;

import br.com.empresaApi.desafio.controller.form.AtualizacaoEmpresaForm;
import br.com.empresaApi.desafio.model.Empresa;
import br.com.empresaApi.desafio.useCase.EmpresaUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    EmpresaUseCase useCase;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaController.class);

    @PostMapping
    public ResponseEntity<?> cadastrarEmpresa(@RequestBody @Valid Empresa empresa){
        LOGGER.info("Chamando endpoint para cadastrar uma empresa...");
        ResponseEntity<?> response = useCase.cadastrarEmpresa(empresa);
        LOGGER.info("Endpoint para cadastro de empresa efetuado!!");
        return response;
    }

    @PutMapping
    public ResponseEntity<?> atualizandoEmpresa(@RequestHeader(value = "Authorization") String token, @RequestBody AtualizacaoEmpresaForm empresaForm){
        LOGGER.info("Chamando endpoint para cadastrar uma empresa...");
        ResponseEntity<?> response = useCase.atualizarEmpresa(token, empresaForm);
        LOGGER.info("Endpoint para cadastro de empresa efetuado!!");
        return response;
    }

}
