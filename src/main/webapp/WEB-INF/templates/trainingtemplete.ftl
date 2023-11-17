<#include "base.ftl">
<#macro page_title>${training.getTitle()}</#macro>

<#macro page_body>
    <style>
        .media-body .author {
            display: inline-block;
            font-size: 1rem;
            color: #000;
            font-weight: 700;
        }
        .media-body .metadata {
            display: inline-block;
            margin-left: .5rem;
            color: #777;
            font-size: .8125rem;
        }
        .footer-comment {
            color: #777;
        }
        .vote.plus:hover {
            color: green;
        }
        .vote.minus:hover {
            color: red;
        }
        .vote {
            cursor: pointer;
        }
        .comment-reply a {
            color: #777;
        }
        .comment-reply a:hover, .comment-reply a:focus {
            color: #000;
            text-decoration: none;
        }
        .devide {
            padding: 0px 4px;
            font-size: 0.9em;
        }
        .media-text {
            margin-bottom: 0.25rem;
        }
        .title-comments {
            font-size: 1.4rem;
            font-weight: bold;
            line-height: 1.5rem;
            color: rgba(0, 0, 0, .87);
            margin-bottom: 1rem;
            padding-bottom: .25rem;
            border-bottom: 1px solid rgba(34, 36, 38, .15);
        }
    </style>
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
        <div class="comments">
            <h3 class="title-comments">Комментарии</h3>
            <textarea id="comment-area" rows="5" style="width: 100%;" <#if user??>placeholder="Напишите комментарий..." <#else> placeholder="Войдите чтобы написать коментарий." disabled</#if>></textarea>
            <input id="send-comment" type="button" value="Отправить" <#if user??> <#else>disabled</#if>>
            <small id="count-symbols">0/1000</small>
            <hr>
            <ul id="comments_wrapper" class="media-list">

            </ul>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="./static/scripts/comments.js"/>
</#macro>

<@display_page/>