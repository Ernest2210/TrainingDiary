<#include "base.ftl">
<#macro page_title>Главная</#macro>
<#macro page_body>
    <style>
        body{
            background-image: url("./static/img/background.jpg");
            background-size: cover;
            color: white;
        }
        body:after {
            content: '';
            position: absolute;
            top: 55px;
            left: 0;
            right: 0;
            bottom: 0;
            background: rgba(0,0,0,.5);
            z-index: 0;
        }
    </style>


    <div class="container" style="height: 100vh">
        <div class="row" style="height: 100vh">
            <div class="col text-center" style="margin-top: 30%">
                <h3>Дневник тренировок TrainingDiary</h3>
            </div>
        </div>
    </div>

</#macro>

<@display_page/>