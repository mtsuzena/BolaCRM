package back.bolaCRM.arquivo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import back.bolaCRM.arquivo.entity.Arquivo;
import back.bolaCRM.arquivo.service.ArquivoService;

@RestController
@RequestMapping("/scp/public/arquivo")
public class ArquivoController {
	
	@Autowired
	ArquivoService arquivoService;
	
	@PostMapping(consumes = "multipart/form-data")
	public Arquivo salvar(@RequestParam("arquivo") MultipartFile file) {
		Arquivo arquivo = new Arquivo();
		try {			
			arquivo.setContentType(file.getContentType());
			arquivo.setNome(file.getOriginalFilename());
			arquivo.setConteudo(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return arquivoService.save(arquivo);
	}
	
	@GetMapping("{guidArquivo}")
	public Arquivo findOneById(@PathVariable("guidArquivo") Long guidArquivo) {
		return arquivoService.findOneById(guidArquivo);
	}
	
	@GetMapping("/download/{guidArquivo}")
	public ResponseEntity<Resource> download(@PathVariable("guidArquivo") Long guidArquivo) {
		Arquivo arquivo = arquivoService.findOneById(guidArquivo);

		Resource resource = new ByteArrayResource(arquivo.getConteudo());

		String contentType = arquivo.getContentType();

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION)
				.body(resource);

	}

}
