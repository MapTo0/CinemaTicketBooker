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
            // alert('CALL to the service for booking tickets here');

            var seatsForBooking = $('#seats > .booked'),
                seatId = "";
            $.each(seatsForBooking, function(el) {
                seatId += $(seatsForBooking[el]).attr('data-seatid');
                el === (seatsForBooking.length - 1) ? "" : seatId += ",";
            });

            console.log(seatId);

            $.ajax({
                url: 'rest/book?projectionid=' + $('#projection-select').find(":selected").attr('data-projectionid') +'&seat=' + seatId,
                type: "POST",
                statusCode: {
                    401: function() {
                        alert("basi typoto brat");
                    },
                    200: function() {
                        alert("vsichko e ok");
                        window.location.replace("shopping-cart.html");
                    }
                }
            });
            // window.location.replace("shopping-cart.html");
        })

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
});
