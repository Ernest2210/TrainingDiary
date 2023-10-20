<#include "base.ftl">
<#macro page_title>${exercise.getTitle()}</#macro>

<#macro page_body>
    <div class="container text-center px-4">
        <h1 class="p-3">${exercise.getTitle()}</h1>
        <div class="row p-3 align-items-center">
            <div class="col-sm-3">
                <img src=".${exercise.getPhotoPath()}" class="img-fluid" style="min-width: 300px; height: auto"/>
            </div>
            <div class="col-sm-9">
                <div class="row align-items-center justify-content-end">
                    <div class="col-3">
                        <h3>Группы мышц</h3>
                    </div>
                    <div class="col-3">
                        <ul class="list-group">
                            <#list muscules as muscule>
                                <li class="list-group-item">${muscule.getTitle()}</li>
                            </#list>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="row align-items-center p-3">
            <div class="col-sm-2 border-end border-secondary-subtle">
                <h3>Описание</h3>
            </div>
            <div class="col">
                ${exercise.getDescription()}
            </div>
        </div>
        <hr>
        <div class="row align-items-center p-3">
            <div class="col-sm-2 border-end border-secondary-subtle">
                <h3>Техника выполнения</h3>
            </div>
            <div class="col">
                ${exercise.getTechnique()}
            </div>
        </div>
    </div>
</#macro>

<@display_page/>