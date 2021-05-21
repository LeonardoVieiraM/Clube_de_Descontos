

window.onload = () => {

    var cards = document.getElementsByClassName("card");
    for(i = 0; i < cards.length; ++i)
    {
        cards[i].onclick = () => 
        { 
            document.getElementById("productInfo-modal03").innerHTML = 
            (`<div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">x</button>
                    </div>
                    <div class="modal-body01">                

                        <img src="../Assets/imgs/mainPageImg03-Prod.jpg" 
                        alt="img3"
                        height="300px"
                        width="250px"
                        class="modalElem1">
                        <p class="modalElem1">Lorem ipsum dolor sit amet consectetur adipisicing elit. Aliquam, vitae unde culpa alias labore aliquid pariatur laborum consectetur sunt dignissimos asperiores?</p>
                        <br><br><br><button type="button" class="btn btn-secondary">Ir para a Loja</button>                                            
                        <br><br>
                    </div>
                </div>
            </div>`);
            $("#productInfo-modal03").modal();
        };

    }

    //=======================================================

    document.getElementById("deleteButton").onclick = function(event) {
        //alert($("#registerForm").submit());
        //window.location.href = "MainPageLogin.html";
        
        var httpRequest01 = new XMLHttpRequest();
        let bodyStr = "";
        bodyStr = "" + localStorage.getItem("currUserEmail");
        
        httpRequest01.onload = function() {
            
            //alert(httpRequest01.readyState);
            alert(httpRequest01.responseText);
            if(httpRequest01.status == 201)
            {
                alert(httpRequest01.responseText);
                localStorage.setItem("currUserEmail", null);
                window.location.href = "MainPageNoLogin.html";
            }

        }

        httpRequest01.open("GET", "http://localhost:4567/cliente/delete/" + bodyStr, true);
        //httpRequest01.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        httpRequest01.send();
    }

    document.getElementById("logOutButton").onclick = function(event) {
        localStorage.setItem("currUserEmail", null);
        window.location.href = "MainPageNoLogin.html";
    }

}