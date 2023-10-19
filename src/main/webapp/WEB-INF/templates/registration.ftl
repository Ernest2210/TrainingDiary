<#include "base.ftl">

<#macro page_body>
    <div class="container p-50 w-50" style="height: 100vh; padding-top:15%">
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
                    <input type="password" class="form-control <#if passwordError == true>is-invalid</#if>" id="inputPassword" name="password">
                    <#if passwordError == true>
                        <div class="invalid-feedback">Пароли не совпадпют</div>
                    </#if>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputPasswordConfirm" class="col-sm-4 col-form-label">Повторите пароль</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control <#if passwordError == true>is-invalid</#if>" id="inputPasswordConfirm" name="password_confirm">
                    <#if passwordError == true>
                        <div class="invalid-feedback">Пароли не совпадпют</div>
                    </#if>
                </div>
            </div>

            <div class="mb-3 row">
                <label for="inputName" class="col-sm-4 col-form-label">Имя</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="inputName" name="name">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputSurname" class="col-sm-4 col-form-label">Фамилия</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="inputSurname" name="surname">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputBirthday" class="col-sm-4 col-form-label">Дата рождения</label>
                <div class="col-sm-8">
                    <input type="date" class="form-control" id="inputBirthday" name="birthday">
                </div>
            </div>
            <div class="mb-3 row">
                <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
            </div>
        </form>
        <div class="text-center">Уже есть аккаунт? <a href="./login">Войдите</a></div>
    </div>
</#macro>

<@display_page/>