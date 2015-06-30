(function() {
    $.ajax({
        url: 'rest/user/current',
        type: "GET",
        complete: function(data) {
            if (DO_NOT_EVER_WRITE_SUCH_CODE(data, 2) === "false") {
                window.location.replace("home.html");
            } else {
                return;
            }
        }
    });

    function DO_NOT_EVER_WRITE_SUCH_CODE(string, position) {
        return string.responseText.split(',')[position].split(':')[1].slice(1, (string.responseText.split(',')[position].split(':')[1].length));
    }
})();

$(document).ready(function() {
    var $Projections = $("#projection-select");
    $.getJSON("rest/projection/", function(data) {
        var aProjectionData = data.projection;

        for (var i = 0; i < aProjectionData.length; i++) {
            var oProjection = aProjectionData[i];
            $Projections.append('<option' + ' data-projectionId="' + oProjection.id + '"' + ' value="' + oProjection.movieTitle + '"' + (i === 0 ? " selected" : "") + ">" + oProjection.movieTitle + "</option>");
        }
    });

    $Projections.change(function() {
        alert('sort for projection');
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

    $('#search-user').click(function() {
        var $List = $('#booking-list');
        $.ajax({
            type: "GET",
            url: "rest/booking/userBookedTickets?email=" + $('#user-email').val(),
            async: true,
            complete: function(data) {
                var bookedTickets = JSON.parse(data.responseText);
                for (var i = 0; i < bookedTickets.length; i++) {
                    $List.append('<li class="booking-items" ' + 'data-ticket-type="' + 'ordered' + '"' + '>' +
                        '<input type="checkbox"' + 'data-projection-id="' + bookedTickets[i].projectionId + '" ' + 'data-seat="' + bookedTickets[i].seat + '"' + '>' +
                        '<span> Потрбител: ' + $('#user-email').val() + bookedTickets[i].movieTitle + ' в ' + bookedTickets[i].startTime + ' място: ' + (parseInt(bookedTickets[i].seat) + 1) + '</span>' +
                        '<span class="payed">Резервиран</span>' +
                        '</li>');
                }
            }
        });
    });
});
