<#include "base.ftl">
<#macro page_title>Журнал тренировок</#macro>

<#macro page_body>
    <div class="container">

        <ul id="calendar-wrapper">
            <div class="row mt-4" data-bs-toggle="modal" data-bs-target="#exampleModal" style="cursor: cell;">
                <div class="card" style="cursor: cell; background-color: mediumspringgreen;">
                    <div class="card-body" style="cursor: cell; background-color: mediumspringgreen;">
                        <div class="card-title">
                            <h3>Добавить упражнение</h3>
                        </div>
                    </div>
                </div>
            </div>
        </ul>

        <div class="modal fade modal-dialog-scrollable" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Добавить упражнение</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                    </div>
                    <form action="#" method="post">
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="exercise-title" class="col-form-label"><h3>Упражнение:</h3></label>
                                <input class="form-control" id="exercise-title" name="exercise-title"/>
                                <div id="suggesstion-box"></div>
                            </div>
                            <div class="mb-3">
                                <label for="comment" class="col-form-label"><h3>Коментарий:</h3></label>
                                <input class="form-control" id="comment" name="comment"/>
                            </div>
                            <div class="row mb-3">
                                <h3>Подход:</h3>
                                <div class="col">
                                <label for="count-1" class="col-form-label">Количество:</label>
                                <input type="number" class="form-control"  name="count">
                                </div>
                                <div class="col">
                                <label for="weight-1" class="col-form-label">Вес:</label>
                                <input type="number" class="form-control"  name="weight">
                                </div>
                            </div>
                            <div class="mb-3">
                                <button id="add-approach-button" type="button" class="btn btn-success">Добавить подход</button>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                            <button type="submit" class="btn btn-primary">Добавить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="./static/scripts/journal.js"/>
</#macro>

<@display_page/>