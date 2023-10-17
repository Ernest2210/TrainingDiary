<#include "base.ftl">

<#macro page_body>
    <div class="container p-50 w-50" style="height: 100vh; padding-top:20%">
        <form action="./registration" method="POST" >
            <div class="mb-3 row">
                <label for="inputLogin" class="col-sm-4 col-form-label">Логин</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="inputLogin" name="login">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputPassword" class="col-sm-4 col-form-label">Пароль</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" id="inputPassword" name="password">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputPasswordConfirm" class="col-sm-4 col-form-label">Повторите пароль</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" id="inputPasswordConfirm" name="password_confirm">
                </div>
            </div>
            <div class="mb-3 row">
                <button type="submit" class="btn btn-primary">Войти</button>
            </div>
        </form>
        <div class="text-center">Уже есть аккаунт? <a href="./login">Войдите</a></div>
    </div>
</#macro>

<@display_page/>