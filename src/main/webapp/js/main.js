$(document).ready(function() {
    var $LoginButton = $("#login-button"),
        $RegisterButton = $("#register-button");
    $LoginButton.on("click", function() {
        // TODO VALIDATION
        var oUserData = {
            user: {
                email: $("#login-email").val(),
                password: $("#login-password").val()
            }
        };

        $.ajax({
            url: 'rest/user/login',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(oUserData),
            statusCode: {
                401: function() {
                    alert("Authentication failed");
                    // TODO CLEAR
                },
                200: function() {
                    alert("Authentication is fine!");
                    
                    var id = 0;
                    $.getJSON("rest/projection/", function(data){
                    	id = data.projection[0].id;
                    	
                        $.ajax({
                            url: 'rest/projection/buy' + "?" + "projectionId=" + id + "&place=4,1,2,3,8",
                            type: "POST"
                        });
                    });
                }
            }
        });
    });

    $RegisterButton.click(function() {
        var oRegisterData = {
            user: {
                email: $("#register-email").val(),
                firstName: $("#register-firstName").val(),
                lastName: $("#register-lastName").val(),
                password: $("#register-password").val()
            }
        };

        $.ajax({
                url: 'rest/user/register',
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(oRegisterData)
            })
            .success(function(data) {
                alert("Party brat");
            })
            .fail(function(data) {
                alert("Sorry brat");
            })
            .always(function() {
                $("#register-form").submit();
            });
    });
});
