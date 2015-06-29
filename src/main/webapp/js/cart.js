$(document).ready(function() {
    var $List = $('#booking-list');

    $.ajax({
        type: "GET",
        url: "rest/user/current",
        async: true,
        dataType: "text",
        success: function(userObject) {
            var userEmail = DO_NOT_EVER_WRITE_SUCH_CODE(userObject, 0);

            $.ajax({
                type: "GET",
                url: "rest/viewtickets/user?mail=" + userEmail,
                async: true,
                success: function(data) {
                    console.log(data);
                    $List.append('<li class="booking-items">' +
                        '<input type="checkbox">' +
                        '<span>Ticket utre v 12 chasa v Plaza na Galena baby</span>' +
                        '<span class="payed">Платен</span>' +
                        '</li>');
                }
            });
        }
    });


    function DO_NOT_EVER_WRITE_SUCH_CODE(string, position) {
        return string.split(',')[position].split(':')[1].slice(1, (string.split(',')[0].split(':')[1].length));
    }

});


// done: function() {
// $List.append('<li class="booking-items">' +
//     '<input type="checkbox">' +
//     '<span>Ticket utre v 12 chasa v Plaza na Galena baby</span>' +
//     '<span class="payed">Платен</span>' +
//     '</li>');
// });
