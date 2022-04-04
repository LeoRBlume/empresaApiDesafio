package br.com.empresaApi.desafio.repository;

import br.com.empresaApi.desafio.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Test
     public void deveriaRetornarUmUsuarioDadoSeuEmail(){
         String email = "admin@root.com";
         Usuario usuario = repository.findByEmail(email).get();
         Assertions.assertNotNull(usuario);
         Assertions.assertEquals(email, usuario.getEmail());
     }

     @Test
    public void naoDeveriaRetornarUmUsuarioPassandoUmEmailInvalido(){
        String email = "email@email@teste.com@br.";
        Optional<Usuario> usuario = repository.findByEmail(email);
        Assertions.assertFalse(usuario.isPresent());

     }

}
