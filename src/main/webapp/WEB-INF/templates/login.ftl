<#include "base.ftl">
<#macro page_title>Вход</#macro>
<#macro page_body>
    <div class="container p-50 w-50" style="height: 100vh; padding-top:20%">
        <form id="form" action="./login" method="POST" >
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

    <div id="error-modal" class="modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Ошибка</h5>
                    <button id="close_button" type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p id="modal-error-text">Введены неверные данные</p>
                </div>
                <div class="modal-footer">
                    <button id="close_button" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="./static/scripts/login.js"/>
</#macro>

<@display_page/>