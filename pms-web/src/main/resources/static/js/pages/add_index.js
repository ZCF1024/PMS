$(document).ready(function(){
    $(".myoperat").click(function(){
        var parent = $(this).parent().parent().parent().prev();
        // console.log(parent.html().match(".*</i>")[0]);
        var path = "<li><a href='index.html'><i class='icon fa fa-home'></i>主页</a></li><li class='active'>"
            + parent.html().match(".*</i>")[0] + parent.text() + "</li>"
            + "<li class='active'><i class='fa fa-anchor' aria-hidden='true'></i>" + this.innerText + "</li>";
        $("#headPath").html(path);
        $("#rightContent").text(this.innerText);
    });
    $('#myCarousel').carousel({interval:4000});
});