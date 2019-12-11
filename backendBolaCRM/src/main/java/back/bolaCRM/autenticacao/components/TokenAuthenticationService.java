package back.bolaCRM.autenticacao.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import back.bolaCRM.autenticacao.model.Autenticacao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {
	
	// EXPIRATION_TIME = 10 dias
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET = "SCP.01";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	public Autenticacao getToken(HttpServletResponse response, String username, Collection<? extends GrantedAuthority> authorities) {
		ArrayList<String> authsList = new ArrayList<>(authorities.size());

        for (GrantedAuthority authority : authorities) {
            authsList.add(authority.getAuthority());
        }
		
		String token = Jwts.builder()
				.claim("PERMISSOES", authsList)
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();		
		
		Autenticacao autenticacao = new Autenticacao();
		autenticacao.setToken(token);
		autenticacao.setLogin(username);
		autenticacao.setPermissoes(authsList);
		
		return autenticacao;
	}
	
	@SuppressWarnings("unchecked")
	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			// faz parse do token
			Claims claims = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody();
			
			if (claims != null && claims.getSubject() != null) {				
				ArrayList<String> authsList = (ArrayList<String>) claims.get("PERMISSOES");
				
				List<GrantedAuthority> authorities = new ArrayList<>(authsList.size());

				for (String role : authsList) {
					authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
				}
				
				return new UsernamePasswordAuthenticationToken(claims.getSubject(), authorities, authorities);
			}
		}
		return null;
	}
	
}
