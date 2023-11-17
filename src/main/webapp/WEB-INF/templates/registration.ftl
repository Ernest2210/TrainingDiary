<#include "base.ftl">
<#macro page_title>Регистрация</#macro>

<#macro page_body>
    <div class="container p-50 w-50" style="height: 100vh; padding-top:15%">
        <form id="form" action="./registration" method="POST" >
            <div class="mb-3 row">
                <label for="inputLogin" class="col-sm-4 col-form-label">Логин</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="inputLogin" name="login">
                    <div id="non-unique-login" class="invalid-feedback" hidden>Пользователь с таким логином уже существует</div>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputPassword" class="col-sm-4 col-form-label">Пароль</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control <#if passwordError == true>is-invalid</#if>" id="inputPassword" name="password">
                    <div id="easy-pass" class="invalid-feedback" hidden>Пароль должен содержать хотя бы одну цифру, большую и маленькую букву, хотя бы один из символов (@, #, $, %, ^, &, +, =) и быть не менее 8 символов </div>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputPasswordConfirm" class="col-sm-4 col-form-label">Повторите пароль</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control <#if passwordError == true>is-invalid</#if>" id="inputPasswordConfirm" name="password_confirm">

                    <div id="non-match-pass" class="invalid-feedback" hidden>Пароли не совпадпют</div>

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
                <button id="submit-button" type="submit" class="btn btn-primary">Зарегистрироваться</button>
            </div>
        </form>
        <div class="text-center">Уже есть аккаунт? <a href="./login">Войдите</a></div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="./static/scripts/registration.js"/>
</#macro>

<@display_page/>