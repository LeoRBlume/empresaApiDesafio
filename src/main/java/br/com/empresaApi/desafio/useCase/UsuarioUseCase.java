package br.com.empresaApi.desafio.useCase;

import br.com.empresaApi.desafio.model.Empresa;
import br.com.empresaApi.desafio.model.Usuario;
import br.com.empresaApi.desafio.repository.UsuarioRepository;
import br.com.empresaApi.desafio.service.form.UsuarioForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import java.util.Optional;

@Component
public class UsuarioUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioUseCase.class);

    @Autowired
    EmpresaUseCase empresaUseCase;

    @Autowired
    UsuarioRepository usuarioRepository;

    public ResponseEntity<String> cadastrarUsuario(UsuarioForm usuarioForm) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        LOGGER.info("Iniciando metodo para cadastrar o usuario...");

        try {

            Usuario usuario = new Usuario();
            usuario.setNome(usuarioForm.getNome());

            if (!validandoEmail(usuarioForm.getEmail()))
                return new ResponseEntity<>("Formato do email invalido", HttpStatus.BAD_REQUEST);

            LOGGER.info("Formato de email valido, adcionando email");
            usuario.setEmail(usuarioForm.getEmail());

            Empresa empresa = empresaUseCase.obterEmpresaPorCnpj(usuarioForm.getCnpj());

            if (empresa == null)
                return new ResponseEntity<>("Nenhuma empresa foi encontrada com esse CNPJ", HttpStatus.NOT_FOUND);

            LOGGER.info("CNPJ valido, adcionando empresa");
            usuario.setEmpresa(empresa);

            LOGGER.info("Criptografando a senha no banco");
            usuario.setSenha(passwordEncoder.encode(usuarioForm.getSenha()));

            usuarioRepository.save(usuario);

            LOGGER.info("Fim do metodo para cadastrar usuario!");


            return new ResponseEntity<>("Usuario cadastrado com sucesso!", HttpStatus.OK);

        }
        catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Email ja cadastrado!!", HttpStatus.BAD_REQUEST);
        }

        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Algum erro inesperado aconteceu", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validandoEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public ResponseEntity<?> obterUsuarioPorEmail(String email) {
        if (!validandoEmail(email)) return new ResponseEntity<>("Formato de email invalido!!", HttpStatus.BAD_REQUEST);

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isPresent()){
            return new ResponseEntity<>(usuarioOptional.get(), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
