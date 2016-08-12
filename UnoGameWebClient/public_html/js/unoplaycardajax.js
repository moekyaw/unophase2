$(function () {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/UnoGameWebServer/api/game/getcard',
        crossDomain: true,
        dataType: 'text',
        success: function (data) {
            
            var json = $.parseJSON(data);

            for (var i = 0; i < json.length; i++)
            {
                $('#image').append('<img src="' + json[i].image + '" />');
            }
        }

    });
});