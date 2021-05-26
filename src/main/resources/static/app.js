$(document).ready(function(){
    let open = true

    $(".hamburger .fas").click(function(){
        if (open){
            $(".wrapper").addClass("active");
            open = false;
        } else {
            $(".wrapper").removeClass("active");
            open = true;
        }

    });

    $(".close").click(function(){
        $(".wrapper").removeClass("active");
    });
});