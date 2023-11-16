const listElem = document.getElementById("trainings-wrapper");
let page = 1

function checkScroll() {
    if (window.innerHeight + window.pageYOffset >= document.body.offsetHeight) {
        loadMore();
    }
}

function getCardHtml(training){
    return `
    <li style="list-style-type: none;">
        <div class="row mt-4">
            <div class="card">
                <div class="card-body">
                    <div class="card-title">
                        <h3><a href="./program?id=${training.id}">${training.title}</a></h3>
                    </div>
                        <hr>
                        <div>
                            <div>Сложность: ${training.difficult}</div>
                            <div>Затратит калорий: ${training.calories}</div>
                            <div>Занимает времени: ${training.time} минут</div>
                        </div>
                        <hr>
                    </div>
                </div>
            </div>
        </li>
`
}

function printCard(data){
    page += 1;
    let trainingsList = data.trainings;
    if (trainingsList.length !== 0) {
        trainingsList.forEach((training) => {
            console.log(training);
            listElem.insertAdjacentHTML("beforeEnd", getCardHtml(training));
        });
    } else {
        console.log("removed")
        window.removeEventListener('scroll', checkScroll)
    }

}

function loadMore() {
    console.log(page)
    $.ajax({
        url: `./api/training-template-data?page=${page}`,
        method: "get",
        dataType: 'json',
        success: (data) => {
            printCard(data)
        }
    });
}

window.onload = () => {
    window.addEventListener('scroll', checkScroll);
    loadMore();
}