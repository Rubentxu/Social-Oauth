package com.rubentxu.action;

import net.sourceforge.stripes.action.*;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

@UrlBinding("/SocialPruebas/Home.htm")
public class HomeActionBean extends BaseActionBean {

    private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1/statuses/update.json";
    private String authUrl;
    private String oauth_token;

    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution("/WEB-INF/jsp/home.jsp");
    }

    @HandlesEvent("loginTwiter")
    public Resolution loginTwiter() {
        OAuthService service = new ServiceBuilder()
                .provider(TwitterApi.class)
                .apiKey("6icbcAXyZx67r8uTAUM5Qw")
                .apiSecret("SCCAdUUc6LXxiazxH3N0QfpNUvlUy84mZ2XZKiv39s")
                .build();

        Token requestToken = service.getRequestToken();
        this.authUrl = service.getAuthorizationUrl(requestToken);

        return new ForwardResolution("/WEB-INF/jsp/twiter.jsp");

    }
    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }
    public String getOauth_token() {
        return oauth_token;
    }

    public void setOauth_token(String oauth_token) {
        this.oauth_token = oauth_token;
    }

}
