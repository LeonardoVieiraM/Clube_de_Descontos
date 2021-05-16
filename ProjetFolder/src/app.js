function beauty() {
  var x = document.getElementById("XXX");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

function food() {
  var x = document.getElementById("XXX");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
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

$("#cloth1").click(function(){
  $(this).find("i").toggleClass("fa-chevron-circle-down");
  $('#cloth2').toggle('1000');
});