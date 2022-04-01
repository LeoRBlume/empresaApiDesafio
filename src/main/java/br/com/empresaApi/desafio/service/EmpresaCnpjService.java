package br.com.empresaApi.desafio.service;

import br.com.empresaApi.desafio.service.form.EmpresaForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "empresaCnpj", url = "https://receitaws.com.br/v1/cnpj")
public interface EmpresaCnpjService {

    @RequestMapping(method = RequestMethod.GET, value = "/{cnpj}")
    Optional<EmpresaForm> getEmpresa(@PathVariable String cnpj);

}
