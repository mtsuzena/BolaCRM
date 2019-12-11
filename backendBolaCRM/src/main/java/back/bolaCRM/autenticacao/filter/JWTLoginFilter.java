package back.bolaCRM.autenticacao.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.ObjectMapper;

import back.bolaCRM.autenticacao.components.TokenAuthenticationService;
import back.bolaCRM.autenticacao.model.Autenticacao;
import back.bolaCRM.autenticacao.model.CustomUser;

@CrossOrigin("*")
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		
		Autenticacao credentials = new Autenticacao();
		credentials.setLogin(login);
		credentials.setSenha(senha);

		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
				credentials.getLogin(), credentials.getSenha(), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication auth) throws IOException, ServletException {

		Autenticacao autenticacao = new TokenAuthenticationService().getToken(response, auth.getName(), auth.getAuthorities());
		autenticacao.setId(((CustomUser)auth.getPrincipal()).getId());
		ObjectMapper parser = new ObjectMapper();
		String json = parser.writeValueAsString(autenticacao);

		response.addHeader("Content-Type", "application/json");
		response.getWriter().println(json);
	}

}