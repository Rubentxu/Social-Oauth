<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Welcome">
  <s:layout-component name="body">
    <p>Proyecto de Pruebas Social Urls</p>
    <h1>
     URL devuelta: ${actionBean.authUrl}

    </h1>
  </s:layout-component>
</s:layout-render>
