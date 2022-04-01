package br.com.empresaApi.desafio.repository;

import br.com.empresaApi.desafio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
