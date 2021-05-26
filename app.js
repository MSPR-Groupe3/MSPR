$(document).ready(function(){
    $(".hamburger .fas").click(function(){
        $(".wrapper").addClass("active");
    });

    $(".close").click(function(){
        $(".wrapper").removeClass("active");
    });
});