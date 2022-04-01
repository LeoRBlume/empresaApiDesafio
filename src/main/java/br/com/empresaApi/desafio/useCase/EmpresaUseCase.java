package br.com.empresaApi.desafio.useCase;

import br.com.empresaApi.desafio.model.Empresa;
import br.com.empresaApi.desafio.repository.EmpresaRepository;
import br.com.empresaApi.desafio.service.EmpresaCnpjService;
import br.com.empresaApi.desafio.service.form.EmpresaForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmpresaUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaUseCase.class);

    @Autowired
    EmpresaCnpjService empresaService;

    @Autowired
    EmpresaRepository repository;

    public Empresa obterEmpresaPorCnpj(String cnpj) {

        LOGGER.info("Entrando no metodo para obter uma empresa por um CNPJ...");

        String amazon_cnpj = "15436940000103";

        try {

            Optional<Empresa> empresaOptional = repository.findById(cnpj);

            if (empresaOptional.isPresent()) {
                LOGGER.info("Empresa ja cadastrada, buscando no banco de dados, retornando ela!");
                return empresaOptional.get();

            } else {
                LOGGER.info("Empresa não cadastrada, acessando o serviço para buscar uma empresa pelo CNPJ: " + cnpj);
                Optional<EmpresaForm> empresaFormOptional = empresaService.getEmpresa(cnpj);
                LOGGER.info("Consulta do CNPJ feita");

                if (empresaFormOptional.get().getNome() != null) {
                    LOGGER.info("O CNPJ consultado é valido");
                    Empresa empresa = new Empresa(empresaFormOptional.get());
                    LOGGER.info("Cadastrando a empresa...");
                    repository.save(empresa);
                    LOGGER.info("Empresa cadastrada!");
                    return empresa;
                } else {
                    LOGGER.info("Nenhuma empresa encontrada com o CNPJ: " + cnpj);
                    return null;
                }
            }
        } catch (Exception e) {
            LOGGER.info("Um erro inesperado aconteceu");
            return null;
        }
    }
}
