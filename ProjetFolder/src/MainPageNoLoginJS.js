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
                alert("Usuario registrado com sucesso: " + httpRequest01.responseText);
                localStorage.setItem("currUserEmail", httpRequest01.responseText);
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
        
        var httpRequest03 = new XMLHttpRequest();
        let bodyStr = "";
        bodyStr += "email=" +  document.getElementById("exampleInputEmail1").value;
        bodyStr += "&senha=" + document.getElementById("exampleInputPassword1").value;
        let email = document.getElementById("exampleInputEmail1").value;

        httpRequest03.onload = function() {
            
            //alert(httpRequest01.status);
            if(httpRequest03.responseText == "true")
            {
                //alert("Usuario registrado com sucesso: " + httpRequest03.responseText);
                localStorage.setItem("currUserEmail", email);
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
        
        if(this.responseText != "")
        {
            //alert("! " + this.responseText);
            document.getElementById("trend2").innerHTML = this.responseText;
            document.getElementById("searched2").innerHTML = this.responseText;
            document.getElementById("sports2").innerHTML = this.responseText;
            document.getElementById("beauty2").innerHTML = this.responseText;
            document.getElementById("food2").innerHTML = this.responseText;
            setCardResponse();
            //let cuponsJson = JSON.parse(this.responseText);
        }
    }
    httpRequest01.open("GET", "http://localhost:4567/cupons/", true);
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