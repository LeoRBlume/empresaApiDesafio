package br.com.empresaApi.desafio.controller;

import br.com.empresaApi.desafio.controller.dto.UsuarioDto;
import br.com.empresaApi.desafio.controller.form.AtualizacaoCadastroForm;
import br.com.empresaApi.desafio.controller.form.CnpjForm;
import br.com.empresaApi.desafio.controller.form.UsuarioForm;
import br.com.empresaApi.desafio.model.Empresa;
import br.com.empresaApi.desafio.useCase.UsuarioUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    UsuarioUseCase useCase;

    @GetMapping
    public ResponseEntity<UsuarioDto> obterUsuarioLogado(@RequestHeader(value = "Authorization") String token) {
        LOGGER.info("Metodo para retornar o usuario...");
        ResponseEntity<UsuarioDto> response = useCase.obterUsuario(token);
        LOGGER.info("Usuario retornado!");
        return response;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm) {
        LOGGER.info("Chamando endpoint para cadastrar usuario...");
        ResponseEntity<String> response = useCase.cadastrarUsuario(usuarioForm);
        LOGGER.info("Metodo para cadastrar o usuario encerrado!\n");
        return response;
    }

    @PutMapping
    public ResponseEntity<UsuarioDto> atualizarCadastroUsuario(@RequestHeader(value = "Authorization") String token, @RequestBody AtualizacaoCadastroForm form) {
        LOGGER.info("Chamando endpoint para atualizar cadastro do usuario...");
        ResponseEntity<UsuarioDto> response = useCase.atualizarCadastro(token, form);
        LOGGER.info("Metodo para cadastrar o usuario encerrado!");
        return response;
    }

    @GetMapping
    @RequestMapping("/empresa")
    public ResponseEntity<Empresa> obterDadosEmpresaUsuario(@RequestHeader(value = "Authorization") String token) {
        LOGGER.info("Chamando endpoint para obter dados da empresa do usuario...");
        ResponseEntity<Empresa> response = useCase.obterDadosEmpresaUsuario(token);
        LOGGER.info("Dados da empresa do usuario retornado!");
        return response;
    }

    @PutMapping
    @RequestMapping("/mudarVinculo")
    public ResponseEntity<?> mudarVinculoEmpresa(@RequestHeader(value = "Authorization") String token, @Valid @RequestBody CnpjForm cnpj) {
        LOGGER.info("Chamando endpoint para mudar a empresa do usuario...");
        ResponseEntity<?> response = useCase.mudarEmpresaUsuario(token, cnpj.getCnpj());
        LOGGER.info("Metodo para mudarar a empresa do usuario finalizado!");
        return response;
    }
    @GetMapping
    @RequestMapping("/listar")
    public ResponseEntity<?> listarTodosUsuario(){
        LOGGER.info("Endpoint para listar todos os usuarios...");
        ResponseEntity<?> response = useCase.listarTodosUsuario();
        LOGGER.info("Metodo para listar todos os usuarios finalizado!");
        return response;
    }
}
