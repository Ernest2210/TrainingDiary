<#include "base.ftl">
<#macro page_title>${training.getTitle()}</#macro>

<#macro page_body>
    <div class="container">
        <div class="container text-center px-4">
            <h1 class="p-3">${training.getTitle()}</h1>
            <div class="row align-items-center p-3">
                <div class="col-sm-2">
                    <h3>Описание</h3>
                </div>
                <div class="col border-start border-secondary-subtle">
                    ${training.getDescription()}
                </div>
            </div>
            <hr>
            <div class="row align-items-center p-3">
                <div class="col-sm-2">
                    <h3>Сложность</h3>
                </div>
                <div class="col border-start border-secondary-subtle">
                    ${training.getDifficult()} / 10
                </div>
            </div>
            <hr>
            <div class="row align-items-center p-3">
                <div class="col-sm-2 ">
                    <h3>Калории</h3>
                </div>
                <div class="col border-start border-secondary-subtle">
                    ${training.getCalories()} калорий
                </div>
            </div>
            <hr>
            <div class="row align-items-center p-3">
                <div class="col-sm-2">
                    <h3>Время выполнения</h3>
                </div>
                <div class="col border-start border-secondary-subtle">
                    ${training.getTime()} минут
                </div>
            </div>
            <hr>
            <div class="row align-items-center p-3">
                <div class="col-sm-2">
                    <h3>Упражнения</h3>
                </div>
                <div class="col border-start border-secondary-subtle">
                    <ul>
                        <#list exercises as exercise>
                            <li><a href="./exercise?id=${exercise.getId()}">${exercise.getTitle()}</a></li>
                        </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</#macro>

<@display_page/>