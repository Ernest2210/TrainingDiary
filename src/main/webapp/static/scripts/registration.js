const form = document.getElementById("form")
let input = form.getElementsByTagName('input')
const password = document.getElementById("inputPassword")
const passwordConfirm = document.getElementById("inputPasswordConfirm")
const nonMatchMessage = document.getElementById("non-match-pass")
const easyPassMessage = document.getElementById("easy-pass")
const passwordRegexp = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$/
const loginInput = document.getElementById("inputLogin")
const nonUniqueLoginMessage = document.getElementById("non-unique-login")
const submitButton = document.getElementById("submit-button")

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
        if(!passwordRegexp.test(password.value)){
            password.classList.add('is-invalid')
            easyPassMessage.hidden = false
            isValid = false
        }
        if(password.value !== passwordConfirm.value){
            password.classList.add('is-invalid')
            passwordConfirm.classList.add('is-invalid')
            nonMatchMessage.hidden = false
            isValid = false
        }
        if (!isValid){
            e.preventDefault()
        }
    })
}

function printLoginError(data){
    if(!data.unique){
        loginInput.classList.add('is-invalid')
        nonUniqueLoginMessage.hidden = false
        submitButton.setAttribute('disabled', '')
    }else{
        loginInput.classList.remove('is-invalid')
        nonUniqueLoginMessage.hidden = true
        submitButton.removeAttribute('disabled')
    }

}

function uniqueLogin() {
    $.ajax({
        url: `./api/unique_login`,
        method: "post",
        data: {
            "login": loginInput.value
        },
        dataType: 'json',
        success: (data) => {
            printLoginError(data)
        }
    });
}

window.onload = () => {
    valid()
    loginInput.addEventListener("keyup", () => {
        uniqueLogin()
    })
}
