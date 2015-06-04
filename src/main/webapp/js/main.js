$(document).ready(function() {
    var $LoginButton = $("#login-button"),
        $RegisterButton = $("#register-button"),
        oUserData = null,
        oRegisterData = null;

    $LoginButton.click(function() {
        oUserData = {
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
                },
                200: function() {
                    alert("Authentication is fine!");
                }
            }
        });
    });

    $RegisterButton.click(function() {
        oRegisterData = {
            user: $("#register-email").val(),
            firstName: $("#register-firstName").val(),
            lastName: $("#register-lastName").val(),
            password: $("#register-password").val()
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
