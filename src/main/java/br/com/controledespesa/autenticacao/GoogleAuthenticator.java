package br.com.controledespesa.autenticacao;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import br.com.controledespesa.entity.Usuario;
import br.com.controledespesa.repository.UsuarioDaoImpl;

@RestController
public class GoogleAuthenticator {

	private static final String clientId = "1088944530376-4nlpaada5hd84okv9lsatk8a0028oe5a.apps.googleusercontent.com";
	private static final String tokenInfoUrl = "https://www.googleapis.com/oauth2/v3/tokeninfo";
	private static final String verificationUrl = "accounts.google.com";

	@Autowired
	private UsuarioDaoImpl usuarioDao;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Usuario getLogin(@RequestBody String token) {
		try {
			//TODO Corrigir
			@SuppressWarnings("unused")
			GoogleIdToken googleIdToken = new GoogleAuthenticator().verify(token);
			//Usuario usuario = usuarioDao.getByProperty("email", googleIdToken.getPayload().getEmail());

			Usuario usuario = usuarioDao.getByProperty("email", "everton.ap.cardoso@gmail.com");
			if (usuario == null) {
				usuario = new Usuario();
				//usuario.setEmail(googleIdToken.getPayload().getEmail());
				usuario.setEmail("everton.ap.cardoso@gmail.com");

				//String name = (String) googleIdToken.getPayload().get("name");
				usuario.setNome("Everton Cardoso");

				usuarioDao.save(usuario);
			}

			return usuario;
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ObjectNode extractUserNode(GoogleIdToken.Payload payload) {
		String userId = payload.getSubject();
		String email = payload.getEmail();

		boolean emailVerified = payload.getEmailVerified();

		String name = (String) payload.get("name");
		String pictureUrl = (String) payload.get("picture");
		String locale = (String) payload.get("locale");
		String familyName = (String) payload.get("family_name");
		String givenName = (String) payload.get("given_name");

		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("userId", userId);
		node.put("givenName", givenName);
		node.put("familyName", familyName);
		node.put("name", name);
		node.put("email", email);
		node.put("emailVerified", emailVerified);
		node.put("locale", locale);
		node.put("pictureUrl", pictureUrl);

		return node;
	}

	public GoogleIdToken verify(String tokenId) throws GeneralSecurityException, IOException {
		JsonFactory jsonFactory = new JacksonFactory();
		HttpTransport transport = new NetHttpTransport();

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory).setAudience(Collections.singletonList(GoogleAuthenticator.clientId))
				.setIssuer(GoogleAuthenticator.verificationUrl).build();

		return verifier.verify(tokenId);
	}

	public JsonNode getTokenInfo(String accessToken) {
		GenericUrl url = new GenericUrl(GoogleAuthenticator.tokenInfoUrl);

		Credential credential = new Credential(BearerToken.queryParameterAccessMethod());
		credential.setAccessToken(accessToken);
		HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory(credential::initialize);

		try {
			HttpRequest request = requestFactory.buildGetRequest(url).setConnectTimeout(5000);
			HttpResponse response = request.execute();
			return new ObjectMapper().readTree(response.parseAsString());
		} catch (IOException e) {
			return null;
		}
	}
}
