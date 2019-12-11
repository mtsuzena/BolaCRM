package back.bolaCRM.autenticacao.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import back.bolaCRM.autenticacao.model.CustomUser;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDetailServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		return getCustomUser(userName);
	}

	private CustomUser getCustomUser(String userName) {

		 CustomUser customUser = jdbcTemplate.
		 queryForObject("select guid_usuario as id, email as login, senha from usuario where email=?",new
		 Object[]{userName},new UserRowMapper());
		 if(customUser != null){
		 customUser = new CustomUser(customUser.getUsername(),new
		 BCryptPasswordEncoder().encode(customUser.getPassword()),customUser.isEnabled(),customUser.isAccountNonExpired(),customUser.isCredentialsNonExpired(),
		 customUser.isAccountNonLocked(),getUserRoles(customUser.getId()), customUser.getId());
		 }

		return customUser;
	}

	private class UserRowMapper implements RowMapper<CustomUser> {
		@Override
		public CustomUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new CustomUser(rs.getString("login"), rs.getString("senha"), true, true, true, true,
					Collections.emptyList(), rs.getLong("id"));


		}
	}

	private List<GrantedAuthority> getUserRoles(Long ID) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		 List<String> roles = jdbcTemplate.queryForList("SELECT ROLE FROM perfil_permissao as p inner join tipo_perfil as t on p.guid_tipo_perfil = t.guid_tipo_perfil inner join usuario as u on u.guid_tipo_perfil = t.guid_tipo_perfil where u.guid_usuario=?",
		 new Object[] { ID }, String.class);
		 if (roles != null) {
		 roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
		 }

		return authorities;

	}
}