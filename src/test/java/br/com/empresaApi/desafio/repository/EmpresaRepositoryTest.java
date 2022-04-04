package br.com.empresaApi.desafio.repository;

import br.com.empresaApi.desafio.model.Empresa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmpresaRepositoryTest {

    @Autowired
    EmpresaRepository repository;

    @Test
    public void naoDeveriaRetornarUmaEmpresaPassandoUmCnpjInvalido() {
        Optional<Empresa> empresa = repository.findById("2");
        Assertions.assertFalse(empresa.isPresent());
    }

    @Test
    public void deveriaRetornarEmpresaAdmin() {
        Empresa empresa = repository.findById("1").get();
        Assertions.assertEquals("admin_root_admin@admin.com", empresa.getEmail());
    }

}
