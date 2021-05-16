

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

 

}