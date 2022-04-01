package br.com.empresaApi.desafio.repository;

import br.com.empresaApi.desafio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> getByEmail(String email);

}
