package br.com.empresaApi.desafio.useCase;

import br.com.empresaApi.desafio.config.security.TokenService;
import br.com.empresaApi.desafio.controller.dto.UsuarioDto;
import br.com.empresaApi.desafio.model.Empresa;
import br.com.empresaApi.desafio.model.Usuario;
import br.com.empresaApi.desafio.repository.UsuarioRepository;
import br.com.empresaApi.desafio.controller.form.AtualizacaoCadastroForm;
import br.com.empresaApi.desafio.controller.form.UsuarioForm;
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

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    EmpresaUseCase empresaUseCase;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TokenService tokenService;

    public ResponseEntity<String> cadastrarUsuario(UsuarioForm usuarioForm) {

        LOGGER.info("Iniciando metodo para cadastrar o usuario...");

        try {

            Usuario usuario = new Usuario();
            usuario.setNome(usuarioForm.getNome());

            if (!validandoEmail(usuarioForm.getEmail()))
                return new ResponseEntity<>("Formato do email invalido", HttpStatus.BAD_REQUEST);

            LOGGER.info("Formato de email valido, adcionando email");
            usuario.setEmail(usuarioForm.getEmail());

            Empresa empresa = empresaUseCase.obterEmpresaPorCnpj(usuarioForm.getCnpj());
            usuario.setEmpresa(empresa);


            LOGGER.info("Criptografando a senha no banco");
            usuario.setSenha(passwordEncoder.encode(usuarioForm.getSenha()));

            usuarioRepository.save(usuario);

            LOGGER.info("Fim do metodo para cadastrar usuario!");

            return new ResponseEntity<>("Usuario cadastrado com sucesso!", HttpStatus.OK);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Email ja cadastrado!!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
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
        if (usuarioOptional.isPresent()) {
            return new ResponseEntity<>(usuarioOptional.get(), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Empresa> obterDadosEmpresaUsuario(String tokenBearer) {

        Usuario usuario = obterUsuarioToken(tokenBearer);
        return ResponseEntity.ok(usuario.getEmpresa());
    }

    private Usuario obterUsuarioToken(String tokenBearer) {
        return usuarioRepository.getById(obterIdUsuario(tokenBearer));
    }

    private Long obterIdUsuario(String tokenBearer) {
        String token = tokenService.recuperarToken(tokenBearer);
        return tokenService.getIdUsuario(token);
    }

    public ResponseEntity<UsuarioDto> atualizarCadastro(String tokenBearer, AtualizacaoCadastroForm form) {
        LOGGER.info("Metodo para atualizar o cadastro do usuario");
        Usuario usuario = usuarioRepository.getById(obterIdUsuario(tokenBearer));
        UsuarioDto usuarioDto = new UsuarioDto();

        if (form.getNome() != null) {
            LOGGER.info("Atualizando o nome do usuario");
            usuario.setNome(form.getNome());
        }
        if (form.getEmail() != null) {
            LOGGER.info("Atualizando o email do usuario");
            usuario.setEmail(form.getEmail());
        }
        if (form.getCnpj() != null) {
            LOGGER.info("Atualizando empresa");
            usuario.setEmpresa(empresaUseCase.obterEmpresaPorCnpj(form.getCnpj()));
            System.out.println(usuario.getEmpresa());
        }
        if (form.getSenha() != null) {
            LOGGER.info("Atualizando a senha do usuario");
            usuario.setSenha(passwordEncoder.encode(form.getSenha()));
            usuarioDto.setSenha("Senha alterada com sucesso!");
        }

        usuarioRepository.save(usuario);

        usuarioDto.converterAtualizacoes(usuario);

        return ResponseEntity.ok(usuarioDto);
    }

    public ResponseEntity<UsuarioDto> obterUsuario(String token) {
        Usuario usuario = usuarioToken(token);
        UsuarioDto usuarioDto = UsuarioDto.converter(usuario);
        return ResponseEntity.ok(usuarioDto);
    }

    private Usuario usuarioToken(String token) {
        token = tokenService.recuperarToken(token);
        return usuarioRepository.getById(tokenService.getIdUsuario(token));
    }

    public ResponseEntity<UsuarioDto> mudarEmpresaUsuario(String token, String cnpj) {
        Usuario usuario = usuarioToken(token);
        LOGGER.info("Chamando metodo para obter empresa com o cnpj: " + cnpj);
        Empresa empresaNova = empresaUseCase.retornarEmpresaPorCnpj(cnpj);
        if(!(empresaNova.getNome().length() == 0)){
            LOGGER.info("Empresa encontrada, atribuindo empresa");
            usuario.setEmpresa(empresaNova);
            usuarioRepository.save(usuario);
            UsuarioDto usuarioDto = UsuarioDto.converter(usuario);
            return ResponseEntity.ok(usuarioDto);
        }
        LOGGER.info("Empresa não encontrada");
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
}
