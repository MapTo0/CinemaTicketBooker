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
});
