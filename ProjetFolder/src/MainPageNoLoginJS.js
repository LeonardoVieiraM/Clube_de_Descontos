window.onload = () => {

    function setCardResponse() {
        var cards = document.getElementsByClassName("card");
        for(i = 0; i < cards.length; ++i)
        {
            const str = cards[i].innerHTML;
            
            cards[i].onclick = () => 
            { 
                let str2 = str.replace('none', ';');;
            
                //alert(str2);
                document.getElementById("productInfo-modal03").innerHTML = 
                (`<div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">x</button>
                        </div>
                        <div class="modal-body01">` +
                            str2 +
                        `<br><br><br></div>
                    </div>
                </div>`);
                $("#productInfo-modal03").modal();
            };

        }
    }
    //=======================================================


    document.getElementById("registerButton").onclick = function(event) {
        
        //document.getElementById("registerForm").submit()
        window.location.href = "MainPageLogin.html";
        //event.preventDefault();
    }



    document.getElementById("registerButton").onclick = function(event) {
        //alert($("#registerForm").submit());
        //window.location.href = "MainPageLogin.html";
        
        if(document.getElementById("regName").value == "" || !document.getElementById("regName").value)
        {
            alert("Nome de usuario invalido");
            event.preventDefault();
            return;
        }
        else if(document.getElementById("regEmail").value == "" || 
                !document.getElementById("regEmail").value || 
                document.getElementById("regEmail").value.search('@') == -1)
        {
            alert("Email invalido");
            event.preventDefault();
            return;
        }
        else if(document.getElementById("regSenha").value == "" || !document.getElementById("regSenha").value) 
        {
            alert("Senha invalida");
            event.preventDefault();
            return;
        }



        var httpRequest01 = new XMLHttpRequest();
        let bodyStr = "";
        bodyStr += "nome=" + document.getElementById("regName").value;
        bodyStr += "&email=" +  document.getElementById("regEmail").value;
        bodyStr += "&senha=" + document.getElementById("regSenha").value;
    
        let assinaturaVal = "0";
        if(document.getElementById("exampleRadios2").checked)
            assinaturaVal = "1";
        else if(document.getElementById("exampleRadios3").checked)
            assinaturaVal = "2";
        bodyStr += "&id_assinatura=" + assinaturaVal;
        
        httpRequest01.onload = function() {
            
            //
            //alert(httpRequest01.readyState);
            if(httpRequest01.status == 201)
            {
                alert("Usuario registrado com sucesso: ");
                localStorage.setItem("sessionId", httpRequest01.responseText);
                window.location.href = "MainPageLogin.html";
            }
            else if(httpRequest01.status == 202)
                alert("Email invalido");
            else if(httpRequest01.status == 203)
                alert("Erro no servidor");
        }

        httpRequest01.open("POST", "http://localhost:4567/cliente", true);
        //httpRequest01.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        httpRequest01.send(bodyStr);
    }

    //=====================================================

    document.getElementById("loginButton").onclick = function(event) {

        if(document.getElementById("exampleInputEmail1").value == "")
        {
            alert(document.getElementById("exampleInputEmail1").value);
            return;
        }
        else if(document.getElementById("exampleInputPassword1").value == "")
        {
            alert(document.getElementById("exampleInputPassword1").value);
            return;
        }

        var httpRequest03 = new XMLHttpRequest();
        let bodyStr = "";

        bodyStr += "email=" +  document.getElementById("exampleInputEmail1").value;
        bodyStr += "&senha=" + document.getElementById("exampleInputPassword1").value;
        let email = document.getElementById("exampleInputEmail1").value;

        httpRequest03.onload = function() {
            
            //alert(httpRequest01.status);
            if(httpRequest03.status == 201)
            {
                //alert("Usuario registrado com sucesso: " + httpRequest03.responseText);
                localStorage.setItem("sessionId", httpRequest03.responseText);
                window.location.href = "MainPageLogin.html";
            }
            else
                alert("Email ou senha invalidos");
        }

        httpRequest03.open("POST", "http://localhost:4567/cliente/login", true);
        //httpRequest01.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        httpRequest03.send(bodyStr);
    }

    //=====================================================

    var httpRequest01 = new XMLHttpRequest();
    

    function loadCupons()
    {
        
        if(this.status == 201 && this.responseText != "")
        {
            document.getElementById("trend2").innerHTML = "";
            //alert("! " + this.responseText);
            let jsonObj = JSON.parse(this.responseText);
            let i = 0;
            for(; i < jsonObj.cupons.length; ++i)
            {
                document.getElementById("trend2").innerHTML += 
                "<div class=\"card col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\" onclick=\"" +
                //"document.getElementById('cardTitle').innerHTML = \'" + jsonObj.cupons[i].codigo + " - " + jsonObj.cupons[i].desconto + "%\';" + 
                //"document.getElementById('cupomLinkButton').onclick = () => {" +
                    //"alert(123);" + 
                    //"let httpRequest04 = new XMLHttpRequest();" + 
                    //"let bodyStr = '';" + 
                    //"bodyStr += 'codigo_cupom=' + '" + jsonObj.cupons[i].codigo + "';" +
                    //"bodyStr += '&sessionId=' + '" + localStorage.getItem('sessionId') + "';" + 
                    //"httpRequest04.onload = function() {};" +
                    //"httpRequest04.open('POST', 'http://localhost:4567/historico/add', true);" +
                    //"httpRequest04.send(bodyStr);" +
                    //"alert('' + '" + jsonObj.cupons[i].codigo + "' + ' adicionado ao historico');" + 
                    //"};" +
                //"$(\'#productInfo-modal03\').modal();" + 
                "\">" +        
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
                document.getElementById("trend2").innerHTML +=
                    "<div class=\"cardFill col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\"></div>"
                ++i;
            }
            

        }
    }
    httpRequest01.open("GET", "http://localhost:4567/cupons/-1", true);
    httpRequest01.onreadystatechange = loadCupons;
    httpRequest01.send();
    

    
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

/*
$(document).ready( function(){
    $.ajax({
        url: "http://localhost:4567/cupons/",
        type: 'GET',
        success: function(res) {
            console.log(res);
            alert(res);
        }
    });

});
*/