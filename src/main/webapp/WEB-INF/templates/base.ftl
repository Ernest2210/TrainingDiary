<#macro page_title>Page Title</#macro>
<#macro page_body></#macro>

<#macro display_page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="./static/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
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
                                <li><a class="dropdown-item" href="./exercise?muscle=breast">Грудь</a></li>
                                <li><a class="dropdown-item" href="./exercise?muscle=shoulders">Плечи</a></li>
                                <li><a class="dropdown-item" href="./exercise?muscle=biceps">Бицепс</a></li>
                                <li><a class="dropdown-item" href="./exercise?muscle=triceps">Трицепс</a></li>
                                <li><a class="dropdown-item" href="./exercise?muscle=forearm">Предплечье</a></li>
                                <li><a class="dropdown-item" href="./exercise?muscle=trapezoid">Трапеция</a></li>
                                <li><a class="dropdown-item" href="./exercise?muscle=latissimus">Широчайшие</a></li>
                                <li><a class="dropdown-item" href="./exercise?muscle=back">Поясница</a></li>
                                <li><a class="dropdown-item" href=./exercise?muscle=press>Пресс</a></li>
                                <li><a class="dropdown-item" href="./exercise?muscle=buttocks">Ягодицы</a></li>
                                <li><a class="dropdown-item" href="./exercise?muscle=quadriceps">Квадрицепс</a></li>
                                <li><a class="dropdown-item" href="./exercise?muscle=biceps_hamstrings">Бицепс бедра</a></li>
                                <li><a class="dropdown-item" href="./exercise?muscle=hip_adductors">Приводящие бедра</a></li>
                                <li><a class="dropdown-item" href="./exercise?muscle=shin">Голень</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="./programs">Готовые программы тренировок</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="./body">Замеры тела</a>
                        </li>
                    </ul>

                    <#if user_authecated == true>
                        <a href="./profile" class="btn btn-outline-success">${user_name}</a>
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