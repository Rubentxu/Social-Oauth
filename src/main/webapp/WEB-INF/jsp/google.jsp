<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Welcome">
    <s:layout-component name="body">
        <p>Proyecto de Pruebas Social Google</p>
        <stripes:errors globalErrorsOnly="true"/>

        <p> Nombre Usuario: ${actionBean.respuestaJson.feed.author[0].name.$t} </p>
        <p> Email Usuario: ${actionBean.respuestaJson.feed.author[0].email.$t} </p>



    </s:layout-component>
</s:layout-render>
