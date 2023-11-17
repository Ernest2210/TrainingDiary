const form = document.getElementById("form")
let input = form.getElementsByTagName('input')

function valid () {
    form.addEventListener("submit", function (e) {

        let isValid = true
        for (let i = 0; i < input.length; i++) {
            if (input[i].value) {
                input[i].classList.remove('error')
            } else if (!input[i].value) {
                input[i].classList.add('error')
                isValid = false;
            }
        }
        if (!isValid){
            e.preventDefault()
        }
    })
}


window.onload = () => {
    valid()

    const url = new URL(window.location.href)
    const exerciseNotFound = url.searchParams.get("error")
    console.log(exerciseNotFound)
    if(exerciseNotFound !== null){
        $("#error-modal").modal('show');
    }
}
