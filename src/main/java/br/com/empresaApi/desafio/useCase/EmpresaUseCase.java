package br.com.empresaApi.desafio.useCase;

import br.com.empresaApi.desafio.config.security.TokenService;
import br.com.empresaApi.desafio.controller.form.AtualizacaoEmpresaForm;
import br.com.empresaApi.desafio.model.Empresa;
import br.com.empresaApi.desafio.model.Usuario;
import br.com.empresaApi.desafio.repository.EmpresaRepository;
import br.com.empresaApi.desafio.repository.UsuarioRepository;
import br.com.empresaApi.desafio.service.EmpresaCnpjService;
import br.com.empresaApi.desafio.service.form.EmpresaForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmpresaUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaUseCase.class);

    @Autowired
    EmpresaCnpjService empresaService;

    @Autowired
    EmpresaRepository repository;

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Empresa obterEmpresaPorCnpj(String cnpj) {

        LOGGER.info("Entrando no metodo para obter uma empresa por um CNPJ...");

        try {

            Optional<Empresa> empresaOptional = repository.findById(cnpj);

            if (empresaOptional.isPresent()) {
                LOGGER.info("Empresa ja cadastrada, buscando no banco de dados, retornando ela!");
                return empresaOptional.get();

            } else {
                LOGGER.info("Empresa não cadastrada, acessando o serviço para buscar uma empresa pelo CNPJ: " + cnpj);
                Optional<EmpresaForm> empresaFormOptional = empresaService.getEmpresa(cnpj.replaceAll("[^0-9]", ""));
                LOGGER.info("Consulta do CNPJ feita");

                if (empresaFormOptional.get().getNome() != null) {
                    LOGGER.info("O CNPJ consultado é valido");
                    Empresa empresa = new Empresa(empresaFormOptional.get());
                    LOGGER.info("Cadastrando a empresa...");
                    repository.save(empresa);
                    LOGGER.info("Empresa cadastrada!");
                    return empresa;
                } else {
                    LOGGER.info("Nenhuma empresa encontrada com o CNPJ, criando uma do zero");
                    Empresa empresa = Empresa.init(cnpj);
                    repository.save(empresa);
                    return empresa;
                }
            }
        } catch (Exception e) {
            LOGGER.info("Um erro inesperado aconteceu");
            return null;
        }
    }

    public ResponseEntity<?> cadastrarEmpresa(Empresa empresa) {
        Optional<Empresa> empresaOptional = repository.findById(empresa.getCnpj());
        if (empresaOptional.isPresent()) {
            return new ResponseEntity<>("CNPJ ja cadastrado", HttpStatus.CONFLICT);
        } else repository.save(empresa);
        return new ResponseEntity<>("Empresa cadastrada com sucesso!!", HttpStatus.OK);
    }

    public ResponseEntity<?> atualizarEmpresa(String token, AtualizacaoEmpresaForm empresaForm) {

        token = tokenService.recuperarToken(token);
        Usuario usuario = usuarioRepository.getById(tokenService.getIdUsuario(token));
        Empresa empresaRepository = usuario.getEmpresa();

        if (empresaForm.getNome() != null) {
            LOGGER.info("Atualizando Nome da empresa");
            empresaRepository.setNome(empresaForm.getNome());
        }
        if (empresaForm.getTelefone() != null) {
            LOGGER.info("Atualizando Telefone da empresa");
            empresaRepository.setTelefone(empresaForm.getTelefone());
        }
        if (empresaForm.getEmail() != null) {
            LOGGER.info("Atualizando Email da empresa");
            empresaRepository.setEmail(empresaForm.getEmail());
        }
        if (empresaForm.getSituacao() != null) {
            LOGGER.info("Atualizando Situação da empresa");
            empresaRepository.setSituacao(empresaForm.getSituacao());
        }
        if (empresaForm.getNumero() != null) {
            LOGGER.info("Atualizando Numero da empresa");
            empresaRepository.setNumero(empresaForm.getNumero());
        }
        if (empresaForm.getCep() != null) {
            LOGGER.info("Atualizando Cep da empresa");
            empresaRepository.setCep(empresaForm.getCep());
        }
        if (empresaForm.getFantasia() != null) {
            LOGGER.info("Atualizando Fantasia da empresa");
            empresaRepository.setFantasia(empresaForm.getFantasia());
        }
        if (empresaForm.getCapital_social() != null) {
            LOGGER.info("Atualizando Capital social da empresa");
            empresaRepository.setCapital_social(empresaForm.getCapital_social());
        }
        repository.save(empresaRepository);
        return ResponseEntity.ok(empresaRepository);
    }

    public Empresa retornarEmpresaPorCnpj(String cnpj) {
        LOGGER.info("Metodo para mudar o vinculo da empresa do usuario");
        Optional<Empresa> empresaOptional = repository.findById(cnpj);

        if (empresaOptional.isPresent()) {
            LOGGER.info("Empresa cadastrada no banco");
            return empresaOptional.get();
        }

        LOGGER.info("Verificando empresa online");
        Optional<EmpresaForm> empresaFormOptional = empresaService.getEmpresa(cnpj.replaceAll("[^0-9]", ""));
        if (empresaFormOptional.get().getNome() != null) {
            LOGGER.info("Retornando empresa do service");
            repository.save(new Empresa(empresaFormOptional.get()));
            return new Empresa(empresaFormOptional.get());
        }
        LOGGER.info("Nenhuma empresa cadastrada");
        return new Empresa("123", "", "", "", "", "", "", "", 0.0);
    }

    public ResponseEntity<?> listarEmpresas() {
        List<Empresa> empresas = repository.findAll();
        empresas.remove(repository.getById("1"));
        return ResponseEntity.ok(empresas);
    }
}
