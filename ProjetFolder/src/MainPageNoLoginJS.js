

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

    document.getElementById("registerButton").onclick = () => {
        window.location.href = "MainPageLogin.html";
    }


    document.getElementById("loginButton").onclick = () => {
        window.location.href = "MainPageLogin.html";
    }

}
  
$("#trend1").click(function(){
    $(this).find("i").toggleClass("fa-chevron-circle-down");
    $('#trend2').toggle('1000');
});
  
$("#food1").click(function(){
    $(this).find("i").toggleClass("fa-chevron-circle-down");
    $('#food2').toggle('1000');
});
  
$("#searched1").click(function(){
    $(this).find("i").toggleClass("fa-chevron-circle-down");
    $('#searched2').toggle('1000');
});

$("#sports1").click(function(){
    $(this).find("i").toggleClass("fa-chevron-circle-down");
    $('#sports2').toggle('1000');
});

$("#beauty1").click(function(){
    $(this).find("i").toggleClass("fa-chevron-circle-down");
    $('#beauty2').toggle('1000');
});