(function() {
    $.ajax({
        url: 'rest/user/current',
        type: "GET",
        complete: function(data) {
            if (DO_NOT_EVER_WRITE_SUCH_CODE(data, 2) === "true") {
                window.location.replace("admin.html");
            } else {
                return;
            }
        }
    });
})();

function DO_NOT_EVER_WRITE_SUCH_CODE(string, position) {
    return string.responseText.split(',')[position].split(':')[1].slice(1, (string.responseText.split(',')[position].split(':')[1].length));
}
$(document).ready(function() {
    var $List = $('#booking-list');

    $.ajax({
        type: "GET",
        url: "rest/user/current",
        async: true,
        dataType: "text",
        complete: function(userObject) {
            var userEmail = DO_NOT_EVER_WRITE_SUCH_CODE(userObject, 0);

            $.ajax({
                type: "GET",
                url: "rest/booking/userBookedTickets?email=" + userEmail,
                async: true,
                complete: function(data) {
                    var bookedTickets = JSON.parse(data.responseText);
                    for (var i = 0; i < bookedTickets.length; i++) {
                        $List.append('<li class="booking-items" ' + 'data-ticket-type="' + 'ordered' + '"' + '>' +
                            '<input type="checkbox"' + 'data-projection-id="' + bookedTickets[i].projectionId + '" ' + 'data-seat="' + bookedTickets[i].seat + '"' + '>' +
                            '<span>' + bookedTickets[i].movieTitle + ' в ' + bookedTickets[i].startTime + ' място: ' + (parseInt(bookedTickets[i].seat) + 1) + '</span>' +
                            '<span class="payed">Резервиран</span>' +
                            '</li>');
                    }
                    $.ajax({
                        type: "GET",
                        url: "rest/viewtickets/user?email=" + userEmail,
                        async: true,
                        complete: function(data) {
                            var bookedTickets = JSON.parse(data.responseText);
                            for (var i = 0; i < bookedTickets.length; i++) {
                                $List.append('<li class="booking-items" ' + 'data-ticket-type="' + 'bought' + '"' + '>' +
                                    '<input type="checkbox">' +
                                    '<span>' + bookedTickets[i].movieTitle + ' в ' + bookedTickets[i].startTime + ' място: ' + (parseInt(bookedTickets[i].seat) + 1) + '</span>' +
                                    '<span class="payed">Платен</span>' +
                                    '</li>');
                            }
                        }
                    });
                }
            });
        }
    });

    $('#buy-ticket-button').click(function() {

        $('#booking-list input:checked').each(function() {

            var projectionId = $(this).attr('data-projection-id');
            var seat = $(this).attr('data-seat');

            $.ajax({
                type: "POST",
                url: "rest/projection/buy?projectionId=" + projectionId + "&place=" + seat,
                async: true,
                complete: function() {
                    alert("Благодаря, че закупихте този билет!")
                }
            });
        });
    });

    $('#logout-button').click(function() {
        $.ajax({
            url: 'rest/user/logout',
            type: "GET",
            statusCode: {
                204: function() {
                    alert("Успешно излизане от системата!");
                    window.location.replace("index.html");
                }
            }
        });
    });

    $('#shopping-cart-button').click(function() {
        window.location.replace("shopping-cart.html");
    });

});
