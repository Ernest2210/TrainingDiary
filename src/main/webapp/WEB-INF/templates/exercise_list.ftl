<#include "base.ftl">
<#macro page_title>Упражнения</#macro>

<#macro page_body>
    <div class="container text-center">

        <div class="row row-cols-4 mt-3">
            <#list exerciseTypeList as exerciseType>
                <div class="col">
                    <div class="card h-100">
                        <img src=".${exerciseType.getPhotoPath()}" class="card-img-top" style="max-height: 300px;">
                        <div class="card-body" style="max-height: 300px;">
                            <h5 class="card-title">${exerciseType.getTitle()}</h5>
                        </div>
                        <a href="./exercise?id=${exerciseType.getId()}" class="btn btn-primary">Подробнее</a>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</#macro>

<@display_page/>