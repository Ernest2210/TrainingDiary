<#macro page_title>Page Title</#macro>
<#macro page_body></#macro>

<#macro display_page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="./static/styles/style.css">
        <title><@page_title/></title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="./">Training Diary</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="./">Главная</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="./diary">Журнал тренировок</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Упражнения
                            </a>
                            <ul class="dropdown-menu">
                                <#list musculeTypes as type>
                                    <li><a class="dropdown-item" href="./exercises?muscle=${type.getId()}">${type.getTitle()}</a></li>
                                </#list>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="./programs">Готовые программы тренировок</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="./body">Замеры тела</a>
                        </li>
                    </ul>

                    <#if user??>
                        <a href="./profile" class="btn btn-outline-success">${user.getName()}</a>
                    <#else>
                        <a href="./login" class="btn btn-outline-succes">Вход</a>
                    </#if>
                </div>
            </div>
        </nav>
        <@page_body/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>