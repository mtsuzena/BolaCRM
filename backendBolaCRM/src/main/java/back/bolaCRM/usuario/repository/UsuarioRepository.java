package back.bolaCRM.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import back.bolaCRM.usuario.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByEmail(String email);

	@Query("select u from Usuario u where u.email = :email and u.senha = :senha")
	public List<Usuario> BuscarPorEmaileSenha(@Param("email") String email, @Param("senha") String senha);
}