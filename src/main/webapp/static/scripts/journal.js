const listElm = document.querySelector('#calendar-wrapper');
const addApproachButton = document.querySelector('#add-approach-button');
const suggestion = document.getElementById("suggestion-box")
const exerciseTitleInput = document.getElementById("exercise-title")
const approachHtml = `
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
`
let page = 1;

function getApproachHtml(approachList){
    var result = ``
    approachList.forEach((approach) => {
        result += `<span>${approach.weight} кг * ${approach.count} раз</span> | `
    })
    return result
}

function getCardHtml(exercise){
    return `
        <li style="list-style-type: none;">
            <div class="row mt-4">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">
                            <h3>${exercise.date}</h3>
                        </div>
                        <hr>
                        <div>
                            <h3><a href="./exercise?id=${exercise.exercise_type.id}">${exercise.exercise_type.title}</a></h3> 
                            <div>Ощущения после упражнения: ${exercise.comment}</div>
                            <div>Сложность упражнения: ${exercise.difficult}</div>
                            <span>Подходы: </span>
                            ${getApproachHtml(exercise.approaches)}
                        </div>
                        <hr>
                    </div>
                </div>
            </div>
        </li>
    `
}

function checkScroll() {
    if (window.innerHeight + window.pageYOffset >= document.body.offsetHeight) {
        loadMore();
    }
}

function printCard(data){
    page += 1;
    var exercisesList = data.exercises;
    if (exercisesList.length !== 0) {
        exercisesList.forEach((exercise) => {
            console.log(exercise);
            listElm.insertAdjacentHTML("beforeEnd", getCardHtml(exercise));
        });
    } else {
        console.log("removed")
        window.removeEventListener('scroll', checkScroll)
    }

}

 function loadMore() {
    console.log(page)
    $.ajax({
        url: `./api/training_data?page=${page}`,
        method: "get",
        dataType: 'json',
        success: (data) => {
            printCard(data)
        }
    });
}

function printIntoInput(event) {
    exerciseTitleInput.value = event.target.innerText;
    suggestion.innerHTML = "";
}

window.onload = () => {
    window.addEventListener('scroll', checkScroll);
    addApproachButton.onclick = () => {
        addApproachButton.insertAdjacentHTML("beforebegin", approachHtml)
    }
    loadMore();

    $(document).ready(function() {
        $("#exercise-title").keyup(function() {
            $.ajax({
                type: "GET",
                url: `./api/exercise-title?keyword=${$(this).val()}`,
                dataType: 'json',
                success: (response) => {
                    let data = response.exercises;

                    let html = ""
                    data.forEach((exercise) => {
                        html += `<li onclick="printIntoInput(event)">${exercise.title}</li>`
                    });
                    suggestion.innerHTML = html;
                }
            });
        });
    });
}