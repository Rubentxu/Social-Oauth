<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-definition>

    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"[]>
    <html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="es-ES" xml:lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css">
        <!--[if IE 6]>
        <link rel="stylesheet" href="${contextPath}/style.ie6.css" type="text/css" media="screen"/><![endif]-->
        <!--[if IE 7]>
        <link rel="stylesheet" href="${contextPath}/style.ie7.css" type="text/css" media="screen"/><![endif]-->


        <s:layout-component name="head">
        </s:layout-component>
    </head>
    <body>
    <div id="page-background-glare">
        <div id="page-background-glare-image"></div>
    </div>
    <div id="main">
        <div class="cleared reset-box"></div>
        <div class="sheet">
            <div class="sheet-tl"></div>
            <div class="sheet-tr"></div>
            <div class="sheet-bl"></div>
            <div class="sheet-br"></div>
            <div class="sheet-tc"></div>
            <div class="sheet-bc"></div>
            <div class="sheet-cl"></div>
            <div class="sheet-cr"></div>
            <div class="sheet-cc"></div>
            <div class="sheet-body">
                <div class="nav">
                    <div class="nav-l"></div>
                    <div class="nav-r"></div>
                    <div class="nav-outer">
                        <ul class="hmenu">
                            <li>
                                <a href="./new-page.html" class="active"><span class="l"></span><span
                                        class="r"></span><span
                                        class="t">PaginaUno</span></a>
                            </li>
                            <li>
                                <a href="#"><span class="l"></span><span class="r"></span><span
                                        class="t">PaginaDos</span></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="cleared reset-box"></div>
                <div class="header">
                    <div class="header-clip">
                        <div class="header-center">
                            <div class="header-jpeg"></div>
                        </div>
                    </div>
                    <div class="logo">
                        <h1 class="logo-name"><a href="./index.html">sOCIAL-OAUTH</a></h1>

                        <h2 class="logo-text">APLICACION SOCIAL PRUEBAS</h2>
                    </div>
                </div>
                <div class="cleared reset-box"></div>
                <div class="content-layout">
                    <div class="content-layout-row">
                        <div class="layout-cell content">
                            <div class="post">
                                <div class="post-body">
                                    <div class="post-inner article">

                                        <h2 class="postheader">
                                            <img src="./images/postheadericon.png" width="32" height="32" alt=""/>
                                                ${tituloPost}
                                        </h2>

                                        <div class="cleared"></div>
                                        <div class="postcontent">

                                            <s:layout-component name="body">
                                            </s:layout-component>

                                        </div>
                                        <div class="cleared"></div>
                                    </div>

                                    <div class="cleared"></div>
                                </div>
                            </div>

                            <div class="cleared"></div>
                        </div>
                        <div class="layout-cell sidebar1">
                            <div class="layout-bg"></div>
                            <div class="block">
                                <div class="block-body">
                                    <div class="blockcontent">
                                        <div class="blockcontent-body">

                                            <p>Enter Block content here...</p>

                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam pharetra,
                                                tellus sit amet congue vulputate, nisi erat iaculis nibh, vitae feugiat
                                                sapien ante eget mauris. </p>


                                            <div class="cleared"></div>
                                        </div>
                                    </div>
                                    <div class="cleared"></div>
                                </div>
                            </div>

                            <div class="cleared"></div>
                        </div>
                    </div>
                </div>
                <div class="cleared"></div>
                <div class="footer">
                    <div class="footer-t"></div>
                    <div class="footer-l"></div>
                    <div class="footer-b"></div>
                    <div class="footer-r"></div>
                    <div class="footer-body">
                        <a href="#" class="rss-tag-icon" title="RSS"></a>

                        <div class="footer-text">

                            <p><a href="#">Link1</a> | <a href="#">Link2</a> | <a href="#">Link3</a></p>

                            <p>Copyright © 2012. All Rights Reserved Rubentxu</p>


                        </div>
                        <div class="cleared"></div>
                    </div>
                </div>
                <div class="cleared"></div>
            </div>
        </div>
        <div class="cleared"></div>
        <p class="page-footer"><a href="http://www.artisteer.com/?p=website_templates">Website Template</a> created
            with
            Artisteer.</p>

        <div class="cleared"></div>
    </div>


    </body>
    </html>

</s:layout-definition>