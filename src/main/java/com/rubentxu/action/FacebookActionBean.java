package com.rubentxu.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rubentxu.service.provider.OAuthServiceProvider;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

@UrlBinding("/SocialPruebas/facebook/{$event}.htm")
public class FacebookActionBean extends BaseActionBean{
    Logger logger = LoggerFactory.getLogger( FacebookActionBean.class);

    @SpringBean("facebookServiceProvider")
    private OAuthServiceProvider facebookServiceProvider;
    private static final Token EMPTY_TOKEN = null;
    private String code;
    private String body;


    @HandlesEvent("loginFacebook")
    public Resolution loginFacebook() {
        logger.info("Entrando en LoginFacebook....");
        OAuthService service = facebookServiceProvider.getService();
        Token accessToken = (Token) getContext().getRequest().getSession().getAttribute(ATTR_OAUTH_ACCESS_TOKEN);
        if(accessToken == null) {
            logger.debug("El access Token es nulo, se crea uno nuevo.");

            getContext().getRequest().getSession().setAttribute(ATTR_OAUTH_REQUEST_TOKEN, EMPTY_TOKEN);

            String Url=service.getAuthorizationUrl(EMPTY_TOKEN);
            logger.debug("Se redirecciona a la pagina de facebook: {}",Url);
            // redirecciona a la pagina de login de facebook
            return new RedirectResolution(Url);
            }
        logger.debug("Forward a pagina inicial, el access token esta en sesion: {}",accessToken);

        // coge perfil usuario
        OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me");
        logger.debug("Se va a conectar al servicio de facebook: ");
        service.signRequest(accessToken, oauthRequest);
        Response oauthResponse = oauthRequest.send();
        this.body=oauthResponse.getBody();
        logger.debug("La respuesta es: {}",oauthResponse.getCode());
        logger.debug("La respuesta Stream: {}",oauthResponse.getStream());
        logger.debug("La respuesta es exitosa: {}",oauthResponse.isSuccessful());
        logger.debug("La respuesta  string: {}",oauthResponse.toString());
        logger.debug("La respuesta  body: {}",oauthResponse.getBody());
        return new ForwardResolution("/WEB-INF/jsp/facebook.jsp");

    }
    @HandlesEvent("callbackFacebook")
    public Resolution callback(){
        logger.debug("El codigo para verificar es: {}",this.code);
        // coge request token
        OAuthService service = facebookServiceProvider.getService();
        Token requestToken = (Token) getContext().getRequest().getSession().getAttribute(ATTR_OAUTH_REQUEST_TOKEN);

        // coge access token
        Verifier verifier = new Verifier(code);
        Token accessToken = service.getAccessToken(requestToken, verifier);

        logger.debug("El access token es: {}",accessToken.getRawResponse());

        // guarda access token en session
        getContext().getRequest().getSession().setAttribute(ATTR_OAUTH_ACCESS_TOKEN, accessToken);

        // coge perfil usuario
        OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me");
        logger.debug("Se va a conectar al servicio de facebook: ");
        service.signRequest(accessToken, oauthRequest);
        Response oauthResponse = oauthRequest.send();
        body=oauthResponse.getBody();
        logger.debug("La respuesta es: {}",oauthResponse.getCode());
        logger.debug("La respuesta Stream: {}",oauthResponse.getStream());
        logger.debug("La respuesta es exitosa: {}",oauthResponse.isSuccessful());
        logger.debug("La respuesta  string: {}",oauthResponse.toString());
        logger.debug("La respuesta  body: {}",oauthResponse.getBody());
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


}
