$(document).ready(function() {
    var $Seats = $("#seats");

    for (var i = 0; i < 50; i++) {
        $Seats.append("<div class='seat'>Място номер: " + (i + 1) + "</div>");
    };
});
