const url = new URL(window.location.href)
const training_template_id = url.searchParams.get("id")
const listElem = document.getElementById("comments_wrapper");
const sendCommentButton = document.getElementById("send-comment");
const commentArea = document.getElementById("comment-area")
const countSymbolsBox = document.getElementById("count-symbols")
let page = 1
const maxCommentLen = 1000

function checkScroll() {
    if (window.innerHeight + window.pageYOffset >= document.body.offsetHeight) {
        loadMore();
    }
}

function getCardHtml(comment){

    return `
    <li class="media" style="list-style-type: none;">
      <div class="media-body">
        <div class="media-heading">
          <div class="author">${comment.user_name}</div>
            <div class="metadata">
              <span class="date">${comment.date}</span>
            </div>
          </div>
          <div class="media-text text-justify">${comment.text}</div>
    </li>
    <hr>
`
}

function printCard(data){
    page += 1;
    let commentsList = data.comments;
    if (commentsList.length !== 0) {
        commentsList.forEach((comment) => {
            console.log(comment);
            listElem.insertAdjacentHTML("beforeEnd", getCardHtml(comment));
        });
    } else {
        console.log("removed")
        window.removeEventListener('scroll', checkScroll)
    }

}

function loadMore() {
    console.log(page)
    $.ajax({
        url: `./api/comment?page=${page}&training=${training_template_id}`,
        method: "get",
        dataType: 'json',
        success: (data) => {
            printCard(data)
        }
    });
}

function appendComment(data){
    listElem.insertAdjacentHTML("afterbegin", getCardHtml(data))
    commentArea.value = ""
}

function sendComment(){
    $.ajax({
        url: `./api/comment`,
        method: "post",
        data: {
            "training": training_template_id,
            "text": commentArea.value
        },
        dataType: 'json',
        success: (data) => {
            appendComment(data)
        }
    });
}

function countSymbols(){
    let countOfSymbols = commentArea.value.length
    if (countOfSymbols > maxCommentLen){
        commentArea.value = commentArea.value.slice(0, commentArea.value.length-1)
    }else{
        countSymbolsBox.innerHTML = `${countOfSymbols}/${maxCommentLen}`
    }
}

window.onload = () => {
    window.addEventListener('scroll', checkScroll);
    sendCommentButton.addEventListener('click', sendComment)
    commentArea.addEventListener('keyup', countSymbols)
    loadMore();
}