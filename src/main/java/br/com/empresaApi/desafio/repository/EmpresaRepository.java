package br.com.empresaApi.desafio.repository;

import br.com.empresaApi.desafio.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, String> {
}
