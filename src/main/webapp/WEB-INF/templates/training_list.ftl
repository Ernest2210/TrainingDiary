<#include "base.ftl">
<#macro page_title>Тренировки</#macro>

<#macro page_body>
    <div class="container">
        <ul id="trainings-wrapper">
            <div class="row mt-4" style="cursor: cell;">
                <div class="card" style="cursor: cell; background-color: mediumspringgreen;">
                    <div class="card-body" style="cursor: cell; background-color: mediumspringgreen;">
                        <div class="card-title">
                            <h3>Добавить тренировку</h3>
                        </div>
                    </div>
                </div>
            </div>
        </ul>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="./static/scripts/training_list.js"/>
</#macro>

<@display_page/>