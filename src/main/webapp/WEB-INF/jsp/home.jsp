<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Welcome">
    <s:layout-component name="body">
        <p>Proyecto de Pruebas Social</p>

        <p>
            <s:link beanclass="com.rubentxu.action.FacebookActionBean" event="loginFacebook">
                Login Facebook...
            </s:link>
        </p>
        <p>
            <s:link beanclass="com.rubentxu.action.FacebookActionBean" event="loginFacebook">
                Login Twiter...
            </s:link>
        </p>
    </s:layout-component>
</s:layout-render>
