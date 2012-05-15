package com.rubentxu.service;

import org.scribe.builder.api.Api;

public class OAuthServiceConfig {

    private String apiKey;
    private String apiSecret;
    private String callback;
    private Class apiClass;
    private String scope;

    public OAuthServiceConfig() {
    }

    public OAuthServiceConfig(String apiKey, String apiSecret, String callback,
                              Class apiClass) {
        super();
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.callback = callback;
        this.apiClass = apiClass;
    }

    public OAuthServiceConfig(String apiKey, String apiSecret,String callback,
                              Class apiClass,String scope) {
        super();
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.callback = callback;
        this.apiClass = apiClass;
        this.scope=scope;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public Class getApiClass() {
        return apiClass;
    }

    public void setApiClass(Class apiClass) {
        this.apiClass = apiClass;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return String.format("OAuthServiceConfig{ apiKey= %s , apiSecret=  %s, callback= %s, apiClass= %s ,scope= %s"
                ,apiKey , apiSecret ,callback ,apiClass,scope );
    }
}