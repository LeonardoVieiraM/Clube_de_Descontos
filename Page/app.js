const API_KEY = '00cbb1774cdb4ddcb9cafe6f84423ca4';


onload = () => {
    let xhr = new XMLHttpRequest();
    xhr.onload = exibeNoticias;
    xhr.open('GET', `https://newsapi.org/v2/top-headlines?country=br&category=&apiKey=${API_KEY}`);

    xhr.send();

    let TEC = document.getElementById("COVID19");
    TEC.onclick = () => {
        PesquisaSource("Covid19");
    }
    let POL = document.getElementById("ATRAÇÕES");
    POL.onclick = () => {
        PesquisaSource("Atrações");
    }
    let ESP = document.getElementById("PASSEIOS");
    ESP.onclick = () => {
        PesquisaSource("Passeios");
    }
    let ECO = document.getElementById("PASSAGENS");
    ECO.onclick = () => {
        PesquisaSource("PASSAGENS");
    }
}


function exibeNoticias() {
    let divTela = document.getElementById('tela');
    let texto = '';

    // Montar texto HTML das noticias
    let dados = JSON.parse(this.responseText);
    for (i = 0; i < 4; i++) {
        let noticia = dados.articles[i];
        let data = new Date(noticia.publishedAt);

        texto = texto + `
        
                <div class="box-noticia">
                  <img src="${noticia.urlToImage}" alt="" class="card-img-top" alt="...">
                  <span class="titulo">${noticia.title}</span><br>
                  <span class="creditos">${data.toLocaleDateString ()} - 
                  ${noticia.source.name} - 
                  ${noticia.author}</span><br>
                  <span class="text">
                  <span class="text">
                  ${noticia.content}  <a href="${noticia.url}" target="_blank">Leia mais ...</a>
                  </span>
                </div>    
        `;
    };

    // Preencher a DIV com o texto HTML
    divTela.innerHTML = texto;
}
//search
function executaPesquisa() {
    let query = document.getElementById('txtPesquisa').value;

    let xhr = new XMLHttpRequest();
    xhr.onload = exibeNoticias;
    xhr.open('GET', `https://newsapi.org/v2/everything?q=${query}&language=pt&apiKey=${API_KEY}`);
    xhr.send();
}

document.getElementById('btnPesquisa').addEventListener('click', executaPesquisa);

//Source
function PesquisaSource(query) {
    let xhr = new XMLHttpRequest();
    xhr.onload = exibeNoticias;
    xhr.open('GET', `https://newsapi.org/v2/everything?q=${query}&language=pt&apiKey=${API_KEY}`);
    xhr.send();
}