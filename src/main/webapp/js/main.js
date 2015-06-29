$(document).ready(function() {
    var $LoginButton = $("#login-button"),
        $RegisterButton = $("#register-button");
    $LoginButton.on("click", function() {
        callForLogin();
    });

    $('#login-password').keydown(function(e) {
        if (e.keyCode == 13) {
            callForLogin();
        }
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

        if (!validate()) {
            return;
        }

        $.ajax({
                url: 'rest/user/register',
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(oRegisterData)
            })
            .success(function(data) {
                alert("Вие успешно се регистрирахте в системата. Вече може да влезете с вашите email и парола");
            })
            .fail(function(data) {
                alert("Съжалявам, но въведените данни са невалидни или вече има потребител със същият email адрес");
            })
            .always(function() {
                $("#register-form").submit();
            });
    });

    function callForLogin() {

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
                    alert("Грешен email или парола");
                },
                200: function() {
                    alert("Вие влязохте успешно в системата!");
                    window.location.replace("home.html");
                }
            }
        });
    }

    function validate() {
        var $FirstName = $('#register-firstName'),
            $LastName = $('#register-lastName'),
            $Email = $('#register-email'),
            $ReEmail = $('#register-confirm-email'),
            $Password = $('#register-password');

        function validateEmail(email) {
            var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
            return re.test(email);
        }

        if (($FirstName.val().length === 0) || ($FirstName.val().length > 20) || ($LastName.val().length === 0) || ($LastName.val().length > 20) || (!validateEmail($Email.val())) || $ReEmail.val() !== $Email.val() || $Password.val().length < 8) {
            alert('Моля проверете отново данните, които сте въвели!');
            return false;
        }
        return true
    }
});
