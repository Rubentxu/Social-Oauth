package com.rubentxu.action;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public class BaseActionBean implements ActionBean {
    private ActionBeanContext context;
    public static final String ATTR_OAUTH_REQUEST_TOKEN = "oauthRequestToken";
    public static final String ATTR_OAUTH_ACCESS_TOKEN = "oauthAccessToken";

    public ActionBeanContext getContext() {
        return context;
    }
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }
}
