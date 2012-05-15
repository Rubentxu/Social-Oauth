package com.rubentxu.service.provider;

import com.rubentxu.service.OAuthServiceConfig;
import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OAuthServiceProvider {

    private Logger logger = LoggerFactory.getLogger(OAuthServiceProvider.class);
    private OAuthServiceConfig config;

    public OAuthServiceProvider() {
    }

    public OAuthServiceProvider(OAuthServiceConfig config) {
        this.config = config;
    }

    public OAuthService getService() {
        logger.debug("Cofiguracion del servicio:  --->{}",config.toString());
        ServiceBuilder sb = new ServiceBuilder().provider(config.getApiClass())
                .apiKey(config.getApiKey())
                .apiSecret(config.getApiSecret());
        if(config.getCallback()!=null)sb.callback(config.getCallback());
        if(config.getScope()!=null)sb.scope(config.getScope());
        sb.debug();
        return sb.build();
    }

}