



window.onload = () => {

    var httpRequest02 = new XMLHttpRequest();
    let sessionId = localStorage.getItem("sessionId");
    //alert("123");
    if(sessionId == null)
        window.location.href = "MainPageNoLogin.html";

    httpRequest02.open("GET", "http://localhost:4567/cliente/autenticar/" + sessionId, true);
    httpRequest02.onreadystatechange = function() {
        
        if(this.status == 201)
        {
            afterAuthentication();
        }
        else if(this.status == 202)
        {
            localStorage.setItem("sessionId", null);
            window.location.href = "MainPageNoLogin.html";
        }
        else
            window.location.href = "MainPageNoLogin.html";
    };
    httpRequest02.send();
}

function afterAuthentication()
{

    function setCardResponse() {
        var cards = document.getElementsByClassName("card");
        for(i = 0; i < cards.length; ++i)
        {
            const str = cards[i].innerHTML;
            
            cards[i].onclick = () => 
            { 
                /*
                let str2 = str.replace('none', ';');
                let str3 = str2.slice(str2.search("cuponId:") + 8, str2.indexOf(";", str2.search("cuponId:")));
                
            
                let str4 = str2.replace('onclick=""', `onclick="
                    let httpRequest04 = new XMLHttpRequest();
                    let bodyStr = \'\';
                    bodyStr += \'codigo_cupom=\' + \'${str3}\';
                    bodyStr += \'&sessionId=\' +  localStorage.getItem(\'sessionId\');


                    httpRequest04.onload = function() {
            
                    }
                    httpRequest04.open(\'POST\', \'http://localhost:4567/historico/add\', true);
                    httpRequest04.send(bodyStr);
                    alert(\'' + \'${str3}\' + \' adicionado ao historico\');"`);

                
                //alert(str2);
*/
                
                `document.getElementById("productInfo-modal03").innerHTML =
                (\`<div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">x</button>
                        </div>
                        <div class="modal-body01">` +
                            "<div class=\"card col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\">" +                
                                "<img class=\"bannerElem card-img-top\" src=\"../Assets/imgs/download (1).png\" alt=\"Card image cap\">" +
                                "<div class=\"bannerElem card-body\">" +
                                    "<h5 class=\"bannerElem card-title\">" + jsonObj.cupons[i].codigo + " - " + jsonObj.cupons[i].desconto + "%</h5>" + 
                                    "<p class=\"bannerElem card-text\"></p>" +
                                    "<button style=\"text-align:center; margin: 0; display:none; background-color: rgb(75, 75, 75); color: white;\" " + 
                                    `onclick="
                                        let httpRequest04 = new XMLHttpRequest();
                                        let bodyStr = \'\';
                                        bodyStr += \'codigo_cupom=\' + \'${str3}\';
                                        bodyStr += \'&sessionId=\' +  localStorage.getItem(\'sessionId\');


                                        httpRequest04.onload = function() {
                                
                                        }
                                        httpRequest04.open(\'POST\', \'http://localhost:4567/historico/add\', true);
                                        httpRequest04.send(bodyStr);
                                        alert(\'' + \'${str3}\' + \' adicionado ao historico\');"` + 
                                        ">ir para o site da loja </button>" +
                                    "<!--cuponId:" + jsonObj.cupons[i].codigo + ";-->" +
                                "</div>" +                       
                            "</div>\n"
                            `<br><br><br></div>
                        </div>
                </div>\`);
                $(\"#productInfo-modal03\").modal(); `;
            };

        }
    }

    //=======================================================


    
    

    function loadRecomendedCupons()
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
                "document.getElementById('cardTitle').innerHTML = \'" + jsonObj.cupons[i].codigo + " - " + jsonObj.cupons[i].desconto + "%\';" + 
                "document.getElementById('cupomLinkButton').onclick = () => {" +
                    "let httpRequest04 = new XMLHttpRequest();" + 
                    "let bodyStr = '';" + 
                    "bodyStr += 'codigo_cupom=' + '" + jsonObj.cupons[i].codigo + "';" +
                    "bodyStr += '&sessionId=' + '" + localStorage.getItem('sessionId') + "';" + 
                    "httpRequest04.onload = function() {};" +
                    "httpRequest04.open('POST', 'http://localhost:4567/historico/add', true);" +
                    "httpRequest04.send(bodyStr);" +
                    "alert('' + '" + jsonObj.cupons[i].codigo + "' + ' adicionado ao historico');" + 
                    "};" +
                "$(\'#productInfo-modal03\').modal();" + 
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

    //===============================================

    function loadSearchedCupons()
    {
        if(this.status == 201 && this.responseText != "")
        {
            document.getElementById("searched2").innerHTML = "";
            //alert("! " + this.responseText);
            let jsonObj = JSON.parse(this.responseText);
            let i = 0;
            for(; i < jsonObj.cupons.length; ++i)
            {
                document.getElementById("searched2").innerHTML += 
                "<div class=\"card col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\" onclick=\"" +
                "document.getElementById('cardTitle').innerHTML = \'" + jsonObj.cupons[i].codigo + " - " + jsonObj.cupons[i].desconto + "%\';" + 
                "document.getElementById('cupomLinkButton').onclick = () => {" +
                    "let httpRequest04 = new XMLHttpRequest();" + 
                    "let bodyStr = '';" + 
                    "bodyStr += 'codigo_cupom=' + '" + jsonObj.cupons[i].codigo + "';" +
                    "bodyStr += '&sessionId=' + '" + localStorage.getItem('sessionId') + "';" + 
                    "httpRequest04.onload = function() {};" +
                    "httpRequest04.open('POST', 'http://localhost:4567/historico/add', true);" +
                    "httpRequest04.send(bodyStr);" +
                    "alert('' + '" + jsonObj.cupons[i].codigo + "' + ' adicionado ao historico');" + 
                    "};" +
                "$(\'#productInfo-modal03\').modal();" + 
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
                document.getElementById("searched2").innerHTML +=
                    "<div class=\"cardFill col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\"></div>"
                ++i;
            }
            

        }
    }

    //====================================================

    function loadSportCupons()
    {
        if(this.status == 201 && this.responseText != "")
        {
            document.getElementById("sports2").innerHTML = "";
            //alert("! " + this.responseText);
            let jsonObj = JSON.parse(this.responseText);
            let i = 0;
            for(; i < jsonObj.cupons.length; ++i)
            {
                document.getElementById("sports2").innerHTML += 
                "<div class=\"card col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\" onclick=\"" +
                "document.getElementById('cardTitle').innerHTML = \'" + jsonObj.cupons[i].codigo + " - " + jsonObj.cupons[i].desconto + "%\';" + 
                "document.getElementById('cupomLinkButton').onclick = () => {" +
                    "let httpRequest04 = new XMLHttpRequest();" + 
                    "let bodyStr = '';" + 
                    "bodyStr += 'codigo_cupom=' + '" + jsonObj.cupons[i].codigo + "';" +
                    "bodyStr += '&sessionId=' + '" + localStorage.getItem('sessionId') + "';" + 
                    "httpRequest04.onload = function() {};" +
                    "httpRequest04.open('POST', 'http://localhost:4567/historico/add', true);" +
                    "httpRequest04.send(bodyStr);" +
                    "alert('' + '" + jsonObj.cupons[i].codigo + "' + ' adicionado ao historico');" + 
                    "};" +
                "$(\'#productInfo-modal03\').modal();" + 
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
                document.getElementById("sports2").innerHTML +=
                    "<div class=\"cardFill col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\"></div>"
                ++i;
            }
            

        }
    }

    //=========================================

    function loadBeautyCupons()
    {
        if(this.status == 201 && this.responseText != "")
        {
            document.getElementById("beauty2").innerHTML = "";
            //alert("! " + this.responseText);
            let jsonObj = JSON.parse(this.responseText);
            let i = 0;
            for(; i < jsonObj.cupons.length; ++i)
            {
                document.getElementById("beauty2").innerHTML += 
                "<div class=\"card col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\" onclick=\"" +
                "document.getElementById('cardTitle').innerHTML = \'" + jsonObj.cupons[i].codigo + " - " + jsonObj.cupons[i].desconto + "%\';" + 
                "document.getElementById('cupomLinkButton').onclick = () => {" +
                    "let httpRequest04 = new XMLHttpRequest();" + 
                    "let bodyStr = '';" + 
                    "bodyStr += 'codigo_cupom=' + '" + jsonObj.cupons[i].codigo + "';" +
                    "bodyStr += '&sessionId=' + '" + localStorage.getItem('sessionId') + "';" + 
                    "httpRequest04.onload = function() {};" +
                    "httpRequest04.open('POST', 'http://localhost:4567/historico/add', true);" +
                    "httpRequest04.send(bodyStr);" +
                    "alert('' + '" + jsonObj.cupons[i].codigo + "' + ' adicionado ao historico');" + 
                    "};" +
                "$(\'#productInfo-modal03\').modal();" + 
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
                document.getElementById("beauty2").innerHTML +=
                    "<div class=\"cardFill col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\"></div>"
                ++i;
            }
            

        }
    }

    //==============================================

    function loadFoodCupons()
    {
        if(this.status == 201 && this.responseText != "")
        {
            document.getElementById("food2").innerHTML = "";
            //alert("! " + this.responseText);
            let jsonObj = JSON.parse(this.responseText);
            let i = 0;
            for(; i < jsonObj.cupons.length; ++i)
            {
                document.getElementById("food2").innerHTML += 
                "<div class=\"card col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\" onclick=\"" +
                "document.getElementById('cardTitle').innerHTML = \'" + jsonObj.cupons[i].codigo + " - " + jsonObj.cupons[i].desconto + "%\';" + 
                "document.getElementById('cupomLinkButton').onclick = () => {" + 
                    "let httpRequest04 = new XMLHttpRequest();" + 
                    "let bodyStr = '';" + 
                    "bodyStr += 'codigo_cupom=' + '" + jsonObj.cupons[i].codigo + "';" +
                    "bodyStr += '&sessionId=' + '" + localStorage.getItem('sessionId') + "';" + 
                    "httpRequest04.onload = function() {};" +
                    "httpRequest04.open('POST', 'http://localhost:4567/historico/add', true);" +
                    "httpRequest04.send(bodyStr);" +
                    "alert('' + '" + jsonObj.cupons[i].codigo + "' + ' adicionado ao historico');" + 
                    "};" +
                "$(\'#productInfo-modal03\').modal();" + 
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
                document.getElementById("food2").innerHTML +=
                    "<div class=\"cardFill col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\"></div>"
                ++i;
            }
            

        }
    }

    

    var httpRequest01 = new XMLHttpRequest();
    httpRequest01.open("GET", "http://localhost:4567/cupons/" + localStorage.getItem("sessionId"), true);
    httpRequest01.onreadystatechange = loadRecomendedCupons;
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