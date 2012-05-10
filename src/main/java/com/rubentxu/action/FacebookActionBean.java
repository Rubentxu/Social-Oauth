package com.rubentxu.action;

import com.rubentxu.vo.UsuarioFacebookVO;
import net.sourceforge.stripes.validation.SimpleError;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rubentxu.service.provider.OAuthServiceProvider;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

import java.io.IOException;

@UrlBinding("/SocialPruebas/facebook/{$event}.htm")
public class FacebookActionBean extends BaseActionBean {
    Logger logger = LoggerFactory.getLogger(FacebookActionBean.class);

    @SpringBean("facebookServiceProvider")
    private OAuthServiceProvider facebookServiceProvider;
    private static final Token EMPTY_TOKEN = null;
    private String code;
    private String body;
    private UsuarioFacebookVO usuario;


    @HandlesEvent("loginFacebook")
    public Resolution loginFacebook() {
        logger.info("Entrando en LoginFacebook....");
        OAuthService service = facebookServiceProvider.getService();
        Token accessToken = (Token) getContext().getRequest().getSession().getAttribute(ATTR_OAUTH_ACCESS_TOKEN);
        if (accessToken == null) {
            logger.debug("El access Token es nulo, se crea uno nuevo.");

            getContext().getRequest().getSession().setAttribute(ATTR_OAUTH_REQUEST_TOKEN, EMPTY_TOKEN);

            String Url = service.getAuthorizationUrl(EMPTY_TOKEN);
            logger.debug("Se redirecciona a la pagina de facebook: {}", Url);
            // redirecciona a la pagina de login de facebook
            return new RedirectResolution(Url);
        }
        logger.debug("Forward a pagina inicial, el access token esta en sesion: {}", accessToken);

        // coge perfil usuario
        OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me");
        logger.debug("Se va a conectar al servicio de facebook: ");
        service.signRequest(accessToken, oauthRequest);
        Response oauthResponse = oauthRequest.send();
        this.body = oauthResponse.getBody();

        ObjectMapper mapper = new ObjectMapper();
        try {
            this.usuario = mapper.readValue(body, UsuarioFacebookVO.class);
            logger.debug("Usuario nombre es: {}",usuario.getName());
        } catch (IOException e) {
            logger.error("El mapeado de Json fallo : {}",e.getMessage());
            getContext().getValidationErrors().addGlobalError(new SimpleError("error.excepcion.jackson", e.getMessage()));
        }

        logger.debug("La respuesta  body: {}", this.body);
        return new ForwardResolution("/WEB-INF/jsp/facebook.jsp");

    }

    @HandlesEvent("callbackFacebook")
    public Resolution callback() {
        logger.debug("El codigo para verificar es: {}", this.code);
        // coge request token
        OAuthService service = facebookServiceProvider.getService();
        Token requestToken = (Token) getContext().getRequest().getSession().getAttribute(ATTR_OAUTH_REQUEST_TOKEN);

        // coge access token
        Verifier verifier = new Verifier(code);
        Token accessToken = service.getAccessToken(requestToken, verifier);

        logger.debug("El access token es: {}", accessToken.getRawResponse());

        // guarda access token en session
        getContext().getRequest().getSession().setAttribute(ATTR_OAUTH_ACCESS_TOKEN, accessToken);

        // coge perfil usuario
        OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me");
        logger.debug("Se va a conectar al servicio de facebook: ");
        service.signRequest(accessToken, oauthRequest);
        Response oauthResponse = oauthRequest.send();
        this.body = oauthResponse.getBody();

        ObjectMapper mapper = new ObjectMapper();
        try {
            this.usuario = mapper.readValue(body, UsuarioFacebookVO.class);
            logger.debug("Usuario nombre es: {}",usuario.getName());
        } catch (IOException e) {
            getContext().getValidationErrors().addGlobalError(new SimpleError("error.excepcion.jackson", e.getMessage()));
        }

        logger.debug("La respuesta  body: {}", oauthResponse.getBody());
        return new ForwardResolution("/WEB-INF/jsp/facebook.jsp");
    }

    public OAuthServiceProvider getFacebookServiceProvider() {
        return facebookServiceProvider;
    }

    public void setFacebookServiceProvider(OAuthServiceProvider facebookServiceProvider) {
        this.facebookServiceProvider = facebookServiceProvider;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public UsuarioFacebookVO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioFacebookVO usuario) {
        this.usuario = usuario;
    }

}
