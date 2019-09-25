package br.com.controledespesa.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.entity.Usuario;
import br.com.controledespesa.enums.TipoLoginEnum;
import br.com.controledespesa.exception.DadosException;
import br.com.controledespesa.repository.UsuarioDao;

@RestController
public class GoogleAuthenticator {

	/*
	private static final String clientId = "1088944530376-4nlpaada5hd84okv9lsatk8a0028oe5a.apps.googleusercontent.com";
	private static final String tokenInfoUrl = "https://www.googleapis.com/oauth2/v3/tokeninfo";
	private static final String verificationUrl = "accounts.google.com";
	*/
	@Autowired
	private UsuarioDao usuarioDao;

	@RequestMapping(value = "/loginGoogle")
	public Usuario getLogin(@RequestParam(value = "nome") String nome, @RequestParam(value = "email") String email) throws DadosException {
		Usuario usuario = usuarioDao.getUsuarioByEmail(email, TipoLoginEnum.GOOGLE);
		if (usuario == null) {
			usuario = new Usuario(nome, email);
			usuario.setTipoLogin(TipoLoginEnum.GOOGLE.getChave());

			usuarioDao.save(usuario);
		}

		return usuario;
	}

	/*public ObjectNode extractUserNode(GoogleIdToken.Payload payload) {
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
	}*/
}
