<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Welcome">
    <s:layout-component name="body">
        <p>Proyecto de Pruebas Social Google</p>
        <stripes:errors globalErrorsOnly="true"/>

        <p> Cuerpo JSON: ${actionBean.body} </p>



    </s:layout-component>
</s:layout-render>
