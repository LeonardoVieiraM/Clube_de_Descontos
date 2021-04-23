function remover () {
    var rmv = document.getElementById("produto");
    rmv.remove();
  }


$('body').on("click", '.add-btn', function() {
    var copyContent = $("#item1").clone();
    $('.grid-item1').append(copyContent);
});