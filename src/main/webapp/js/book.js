$(document).ready(function() {
    var $Seats = $("#seats"),
        $Projections = $("#projection-select"),
        $Poster = $("#poster"),
        $BookingBtn = $("#booking-btn"),
        aProjectionData = null;

    $.getJSON("rest/projection/", function(data) {
        aProjectionData = data.projection;
        $Poster.attr("src", aProjectionData[0].posterUrl);

        for (var i = 0; i < aProjectionData.length; i++) {
            var oProjection = aProjectionData[i];
            $Projections.append('<option' + ' data-projectionId="' + oProjection.id + '"' + ' value="' + oProjection.movieTitle + '"' + (i === 0 ? " selected" : "") + ">" + oProjection.movieTitle + "</option>");
            if (i === 0) {
                $.getJSON("rest/projection/" + oProjection.id, function(aData) {
                    fillSeats(aData);
                });
            }
        };

        $Projections.change(function() {
            var that = this;
            aProjectionData.forEach(function(item) {
                if (item.movieTitle === that.selectedOptions.item().value) {
                    $Poster.attr("src", item.posterUrl);
                    $.getJSON("rest/projection/" + item.id, function(aData) {
                        fillSeats(aData);
                    });
                }
            });
        });

        $BookingBtn.click(function() {
            var seatsForBooking = $('#seats > .booked'),
                seatId = "";

            if (seatsForBooking.length === 0) {
                alert("Моля маркирайте местата, които искате да запазите!");
                return;
            }
            $.each(seatsForBooking, function(el) {
                seatId += $(seatsForBooking[el]).attr('data-seatid');
                el === (seatsForBooking.length - 1) ? "" : seatId += ",";
            });

            $.ajax({
                url: 'rest/booking/book?projectionId=' + $('#projection-select').find(":selected").attr('data-projectionid') + '&seat=' + seatId,
                type: "POST",
                statusCode: {
                    401: function() {
                        alert("За съжаление някой вече е запазил тези места, моля изберете други.");
                    },
                    200: function() {
                        alert("Успешна резервация на билети! Моля преминете към количка, за да ги платите.");
                    }
                }
            });
        });

        function fillSeats(aData) {
            for (var i = 0; i < aData.length; i++) {
                var $Button = $('<button/>', {
                    text: "Място номер: " + (i + 1),
                    class: "seat" + " " + (!aData[i] ? "taken" : ""),
                    "data-booked": aData[i],
                    "data-seatId": i,
                    click: function(oEvent) {
                        $(oEvent.target).toggleClass('booked');
                    }
                });
                i === 0 ? $Seats.html($Button) : $Seats.append($Button);
            };
        }
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
