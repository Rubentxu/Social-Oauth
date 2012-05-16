package com.rubentxu.action;

import com.rubentxu.service.provider.OAuthServiceProvider;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

@UrlBinding("/SocialPruebas/google/{$event}.htm")
public class GoogleActionBean extends BaseActionBean {

    private Logger logger = LoggerFactory.getLogger(GoogleActionBean.class);
    @SpringBean("googleServiceProvider")
    private OAuthServiceProvider googleServiceProvider;
    private String oauth_verifier;
    private String oauth_token;
    Map<String, Object> respuestaJson;
    private static final String NETWORK_NAME = "Google";
    private static final String AUTHORIZE_URL = "https://www.google.com/accounts/OAuthAuthorizeToken?oauth_token=";
    private static final String PROTECTED_RESOURCE_URL = "https://www.google.com/m8/feeds/contacts/default/full?alt=json";
    private static final Token EMPTY_TOKEN = null;


    @HandlesEvent("loginGoogle")
    public Resolution loginGoogle() {
        logger.info("Entrando en LoginGoogle....");
        OAuthService service = googleServiceProvider.getService();
        logger.debug("OAuth service----->{}",service.getVersion());

        Token accessToken = (Token) getContext().getRequest().getSession().getAttribute(ATTR_OAUTH_ACCESS_TOKEN+"Google");
        if (accessToken == null) {
            logger.debug("El access Token es nulo, se crea uno nuevo.");
            Token requestToken = service.getRequestToken() ;
            getContext().getRequest().getSession().setAttribute(ATTR_OAUTH_REQUEST_TOKEN+"Google", requestToken);

            String url= AUTHORIZE_URL.concat(requestToken.getToken());
            logger.debug("Se redirecciona a la pagina de google: {}",url);
            return new RedirectResolution(url);
        }
        logger.debug("Forward a pagina inicial, el access token esta en sesion: {}", accessToken);

        // coge perfil usuario
        OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        logger.debug("Se va a conectar al servicio de google: ");
        service.signRequest(accessToken, oauthRequest);
        Response oauthResponse = oauthRequest.send();

        String body = oauthResponse.getBody();

        ObjectMapper mapper = new ObjectMapper();
        try {

           respuestaJson = mapper.readValue(body, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            logger.error("El mapeado de Json fallo : {}",e.getMessage());
            getContext().getValidationErrors().addGlobalError(new SimpleError("error.excepcion.jackson", e.getMessage()));
        }

        logger.debug("La respuesta  body: {}", body);
        return new ForwardResolution("/WEB-INF/jsp/google.jsp");

    }

    @HandlesEvent("callback")
    public Resolution callback() {
        logger.debug("El codigo para verificar es: {}", oauth_verifier);
        // coge request token
        OAuthService service = googleServiceProvider.getService();
        Token requestToken = (Token) getContext().getRequest().getSession().getAttribute(ATTR_OAUTH_REQUEST_TOKEN+"Google");

        // coge access token
        Verifier verifier = new Verifier(oauth_verifier);
        Token accessToken = service.getAccessToken(requestToken, verifier);

        logger.debug("El access token es: {}", accessToken.getRawResponse());

        // guarda access token en session
        getContext().getRequest().getSession().setAttribute(ATTR_OAUTH_ACCESS_TOKEN+"Google", accessToken);

        // coge perfil usuario
        OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        logger.debug("Se va a conectar al servicio de google: ");
        service.signRequest(accessToken, oauthRequest);
        Response oauthResponse = oauthRequest.send();
        String body = oauthResponse.getBody();

        ObjectMapper mapper = new ObjectMapper();
        try {
            respuestaJson = mapper.readValue(body, new TypeReference<Map<String, Object>>() {});

        } catch (IOException e) {
            getContext().getValidationErrors().addGlobalError(new SimpleError("error.excepcion.jackson", e.getMessage()));
        }

        logger.debug("La respuesta  body: {}", oauthResponse.getBody());
        return new ForwardResolution("/WEB-INF/jsp/google.jsp");
    }

    public OAuthServiceProvider getGoogleServiceProvider() {
        return googleServiceProvider;
    }

    public void setGoogleServiceProvider(OAuthServiceProvider googleServiceProvider) {
        this.googleServiceProvider = googleServiceProvider;
    }

    public String getOauth_token() {
        return oauth_token;
    }

    public void setOauth_token(String oauth_token) {
        this.oauth_token = oauth_token;
    }

    public String getOauth_verifier() {
        return oauth_verifier;
    }

    public void setOauth_verifier(String oauth_verifier) {
        this.oauth_verifier = oauth_verifier;
    }

    public Map<String, Object> getRespuestaJson() {
        return respuestaJson;
    }


}
