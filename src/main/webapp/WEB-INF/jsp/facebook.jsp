<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Welcome">
    <s:layout-component name="body">
        <p>Proyecto de Pruebas Social Facebook</p>
        <stripes:errors globalErrorsOnly="true"/>

        <p> Nick: ${actionBean.usuario.name} </p>

        <p>Apellidos: ${actionBean.usuario.last_name}</p>

        <p>Enlace: ${actionBean.usuario.link}</p>
        <p>2º Nombre 1º Apellido: ${actionBean.usuario.middle_name}</p>

    </s:layout-component>
</s:layout-render>
