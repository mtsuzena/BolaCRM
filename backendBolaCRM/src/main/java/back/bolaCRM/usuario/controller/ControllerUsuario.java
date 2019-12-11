package back.bolaCRM.usuario.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import back.bolaCRM.usuario.entity.Usuario;
import back.bolaCRM.usuario.service.UsuarioService;


@RestController
@RequestMapping("/scp/private/usuario")
public class ControllerUsuario {

	@Autowired
	private UsuarioService usuarioService;


	@PostMapping
	public void salvar(@RequestBody @Valid Usuario usuario) throws Exception {
		usuarioService.salvar(usuario);
	}
	
	@PreAuthorize("hasRole('GERENCIAR_USUARIO')")
	@GetMapping("/listar")
	public List<Usuario> listar() {
		return usuarioService.listar();
	}
	
	@PreAuthorize("hasRole('GERENCIAR_USUARIO')")
	@GetMapping("/buscarPorID/{guidUsuario}")
	public Usuario BuscarPorID(@PathVariable("guidUsuario") final Long guidUsuario) {
		return usuarioService.buscarPorId(guidUsuario);
	}

	@DeleteMapping
	public void deleteUsuario(@RequestBody Usuario usuario) {
		usuarioService.excluir(usuario);
	}

	@GetMapping("/email/{email}")
	public Usuario BuscarPorEmail(@PathVariable(name = "email") String email) {
		return usuarioService.buscarPorEmail(email);
	}

	@GetMapping("/logar/{email}/{senha}")
	public List<Usuario> Logar(@PathVariable(name = "email") String email, @PathVariable(name = "senha") String senha) {
		return usuarioService.Logar(email, senha);
	}

	
	@GetMapping(value="/meuId/{id}")
	public Optional<Usuario> findByMydId(@PathVariable Long id) {
		return usuarioService.findByMyId(id);
	}
}
