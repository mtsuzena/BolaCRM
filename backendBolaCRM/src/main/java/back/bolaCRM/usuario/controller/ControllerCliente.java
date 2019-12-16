package back.bolaCRM.usuario.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import back.bolaCRM.usuario.entity.Permissao;
import back.bolaCRM.usuario.entity.TipoPerfil;
import back.bolaCRM.usuario.entity.TipoUsuario;
import back.bolaCRM.usuario.entity.Usuario;
import back.bolaCRM.usuario.service.UsuarioService;


@RestController
@RequestMapping("/scp/public/register")
public class ControllerCliente {
		@Autowired
		private UsuarioService usuarioService;
	
		
		@PostMapping
		public void salvar(@RequestBody Usuario usuario) {
			TipoPerfil tipoperfil = new TipoPerfil();
		    List<Permissao> permissoes = new ArrayList<Permissao>();
			permissoes.add(Permissao.ACESSO);
			tipoperfil.setGuidTipoPerfil((long) 2);
			tipoperfil.setPermissoes(permissoes);
			tipoperfil.setDescricao("Cliente");
			usuario.setPerfil(tipoperfil);
			usuarioService.salvarCliente(usuario);
		}
		
		

		@GetMapping
		public List<Usuario> listar() {
			return usuarioService.listar();
		}


		@DeleteMapping
		public void deleteUsuario(@RequestBody Usuario usuario) {
			usuarioService.excluir(usuario);
		}
	
}
