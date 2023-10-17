<#include "base.ftl">
<#macro page_title>Вход</#macro>
<#macro page_body>
    <div class="container p-50 w-50" style="height: 100vh; padding-top:20%">
        <form action="./login" method="GET" >
            <div class="mb-3 row">
                <label for="inputLogin" class="col-sm-2 col-form-label">Логин</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputLogin" name="login">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputPassword" class="col-sm-2 col-form-label">Пароль</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="inputPassword" name="password">
                </div>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="flexCheckDefault" name="remember">
                <label class="form-check-label" for="flexCheckDefault">
                    Запомнить меня
                </label>
            </div>
            <div class="mb-3 row">
                <button type="submit" class="btn btn-primary">Войти</button>
            </div>
        </form>
        <div class="text-center">Ещё нет аккаунта? <a href="./registration">Зарегестрируйтесь</a></div>
    </div>
</#macro>

<@display_page/>