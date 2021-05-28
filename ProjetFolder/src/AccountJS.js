

window.onload = () => {
    var httpRequest = new XMLHttpRequest();
    let sessionId = localStorage.getItem("sessionId");
    //alert("123");
    if(sessionId == null)
        window.location.href = "MainPageNoLogin.html";

    httpRequest.open("GET", "http://localhost:4567/cliente/autenticar/" + sessionId, true);
    httpRequest.onreadystatechange = function() {
        
        if(this.status == 201)
        {
            afterAuthentication2();
        }
        else if(status == 202)
        {
            localStorage.setItem("sessionId", null);
            window.location.href = "MainPageNoLogin.html";
        }
        else 
            window.location.href = "MainPageNoLogin.html";
         
    };
    httpRequest.send();


}

function afterAuthentication2()
{

    function setCardResponse() {
        var cards = document.getElementsByClassName("card");
        for(i = 0; i < cards.length; ++i)
        {
            const str = cards[i].innerHTML;
            
            cards[i].onclick = () => 
            { 
                let str2 = str.replace('none', ';');
                let str3 = str2.slice(str2.search("cuponId:") + 8, str2.indexOf(";", str2.search("cuponId:")));
                
            
                let str4 = str2.replace('onclick=""', `onclick="
                    let httpRequest04 = new XMLHttpRequest();
                    let bodyStr = \'\';
                    bodyStr += \'codigo_cupom=\' + \'${str3}\';
                    bodyStr += \'&email_cliente=\' +  localStorage.getItem(\'currUserEmail\');


                    httpRequest04.onload = function() {
            
                    }
                    httpRequest04.open(\'POST\', \'http://localhost:4567/historico/add\', true);
                    httpRequest04.send(bodyStr);
                    alert(\'' + \'${str3}\' + \' adicionado ao historico\');"`);

                
                //alert(str2);
                document.getElementById("productInfo-modal03").innerHTML = 
                (`<div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">x</button>
                        </div>
                        <div class="modal-body01">` +
                            str4 +
                        `<br><br><br></div>
                    </div>
                </div>`);
                $("#productInfo-modal03").modal();
            };

        }
    }


    //======================================================
/*
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
*/
    //=======================================================

    document.getElementById("deleteButton").onclick = function(event) {
        //alert($("#registerForm").submit());
        //window.location.href = "MainPageLogin.html";
        
        var httpRequest02 = new XMLHttpRequest();
        let bodyStr = "";
        bodyStr = "" + localStorage.getItem("sessionId");
        
        httpRequest02.onload = function() {
            
            //alert(httpRequest02.readyState);
            alert(httpRequest02.responseText);
            if(httpRequest02.status == 201)
            {
                alert(httpRequest02.responseText);
                localStorage.setItem("sessionId", null);
                window.location.href = "MainPageNoLogin.html";
            }

        }

        httpRequest02.open("GET", "http://localhost:4567/cliente/delete/" + bodyStr, true);
        //httpRequest01.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        httpRequest02.send();
    }

    document.getElementById("logOutButton").onclick = function(event) {
        localStorage.setItem("sessionId", null);
        window.location.href = "MainPageNoLogin.html";
    }



    function loadCupons2()
    {        
        //alert(123);
        if(this.status == 201 && this.responseText != "")
        {
            document.getElementById("cupons").innerHTML = "";
            //alert("! " + this.responseText);
            let jsonObj = JSON.parse(this.responseText);
            let i = 0;
            for(; i < jsonObj.cupons.length; ++i)
            {
                document.getElementById("cupons").innerHTML += 
                "<div class=\"card col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\">" +                
                "<img class=\"bannerElem card-img-top\" src=\"../Assets/imgs/download (1).png\" alt=\"Card image cap\">" +
                "<div class=\"bannerElem card-body\">" +
                "<h5 class=\"bannerElem card-title\">" + jsonObj.cupons[i].codigo + " - " + jsonObj.cupons[i].desconto + "%</h5>" + 
                "<p class=\"bannerElem card-text\"></p>" +
                "<button style=\"text-align:center; margin: 0; display:none; background-color: rgb(75, 75, 75); color: white;\" onclick= >ir para o site da loja </button>" +
                "<!--cuponId:" + jsonObj.cupons[i].codigo + ";-->" +
                "</div>" +                       
                "</div>\n"
            }
            while(i % 4 != 0)
            {
                document.getElementById("cupons").innerHTML +=
                    "<div class=\"cardFill col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\"></div>"
                ++i;
            }
            
        }
    }

    var httpRequest01 = new XMLHttpRequest();
    
    //+ localStorage.getItem("sessionId")
    httpRequest01.open("GET", "http://localhost:4567/historico/" + localStorage.getItem("sessionId"), true);
    httpRequest01.onreadystatechange = loadCupons2;
    httpRequest01.send();

}