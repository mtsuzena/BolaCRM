package back.bolaCRM.arquivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import back.bolaCRM.arquivo.entity.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

}
