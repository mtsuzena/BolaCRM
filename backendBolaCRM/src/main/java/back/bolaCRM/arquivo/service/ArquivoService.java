package back.bolaCRM.arquivo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import back.bolaCRM.arquivo.entity.Arquivo;
import back.bolaCRM.arquivo.repository.ArquivoRepository;

@Service
public class ArquivoService {

	@Autowired
	ArquivoRepository arquivoRep;

	public Arquivo findOneById(Long guidArquivo) {
		return arquivoRep.findById(guidArquivo).get();
	}

	public Arquivo save(Arquivo arquivo) {
		return arquivoRep.save(arquivo);
	}
	
}
