



window.onload = () => {

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

    //=======================================================


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