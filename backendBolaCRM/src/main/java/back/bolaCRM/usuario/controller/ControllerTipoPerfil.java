package back.bolaCRM.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import back.bolaCRM.usuario.entity.TipoPerfil;
import back.bolaCRM.usuario.service.TipoPerfilService;

@RestController
@RequestMapping("/scp/private/tipoperfil")
public class ControllerTipoPerfil {

	@Autowired
	private TipoPerfilService tipoperfilService;

	@PostMapping
	public void salvar(@RequestBody TipoPerfil tipoperfil) {
		tipoperfilService.salvar(tipoperfil);
	}

	@GetMapping
	public List<TipoPerfil> listar() {
		return tipoperfilService.listar();
	}

	@PostMapping("/Excluir")
	public void excluir(@RequestBody TipoPerfil tipoPerfil) {
		tipoperfilService.excluir(tipoPerfil);
	}

	@GetMapping("/BuscarId/{guidTipoPerfil}")
	public TipoPerfil BuscarId(@PathVariable("guidTipoPerfil") final Long guidTipoPerfil) {
		return tipoperfilService.BuscarId(guidTipoPerfil);
	}

}

