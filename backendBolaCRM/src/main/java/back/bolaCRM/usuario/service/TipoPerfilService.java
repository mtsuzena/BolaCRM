package back.bolaCRM.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import back.bolaCRM.common.exception.BusinessException;
import back.bolaCRM.common.exception.BusinessExceptionCode;
import back.bolaCRM.usuario.entity.TipoPerfil;
import back.bolaCRM.usuario.entity.Usuario;
import back.bolaCRM.usuario.repository.TipoPerfilRepository;
import back.bolaCRM.usuario.repository.UsuarioRepository;

@Service
public class TipoPerfilService {

	@Autowired
	private TipoPerfilRepository tipoPerfilRepository;

	@Transactional
	public void salvar(TipoPerfil tipoPerfil) {
		tipoPerfilRepository.save(tipoPerfil);
	}

	public void excluir(TipoPerfil tipoPerfil) {
		tipoPerfilRepository.delete(tipoPerfil);
	}

	public List<TipoPerfil> listar() {
		return tipoPerfilRepository.findAll();
	}

	public TipoPerfil BuscarId(final Long guidTipoPerfil) {
		TipoPerfil tipoperfil = Optional.ofNullable(tipoPerfilRepository.findById(guidTipoPerfil).orElse(null))
				.orElseThrow(() -> new BusinessException(BusinessExceptionCode.ERR001));
		return tipoperfil;
	}

}


