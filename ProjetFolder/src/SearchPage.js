
window.onload = () => {

    var httpRequest02 = new XMLHttpRequest();
    let sessionId = localStorage.getItem("sessionId");
    let searchValue = localStorage.getItem("searchValue");
    //alert("123");
    if(sessionId == null)
        window.location.href = "MainPageNoLogin.html";

    httpRequest02.open("GET", "http://localhost:4567/cliente/autenticar/" + sessionId, true);
    httpRequest02.onreadystatechange = function() {
        
        if(this.status == 201)
        {
            afterAuthentication();
            //window.location.href = "SearchPage.html";
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


    function loadSearchedCupons2()
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
    
    var httpRequest01 = new XMLHttpRequest();
    httpRequest01.open("GET", "http://localhost:4567/cupons/pesquisar/" + localStorage.getItem("searchValue"), true);
    httpRequest01.onreadystatechange = loadSearchedCupons2;
    httpRequest01.send();

}
