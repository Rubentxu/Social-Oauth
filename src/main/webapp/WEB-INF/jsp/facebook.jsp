<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Welcome">
    <s:layout-component name="body">
        <p>Proyecto de Pruebas Social Facebook</p>

        <p>
            Token code devuelto: ${actionBean.code}
            Cuerpo de Datos JSon devuelto: ${actionBean.body}
        </p>

    </s:layout-component>
</s:layout-render>
