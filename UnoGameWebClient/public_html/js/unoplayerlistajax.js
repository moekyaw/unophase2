$(function () {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/UnoGameWebServer/api/game/getplayer',
        crossDomain: true,
        dataType: 'text',
        success: function (data) {

            var json = $.parseJSON(data);
            

            for (var i = 0; i < json.length; i++)
            {
                $('#playerId').append(json[i].playerId);
                $('#playerName').append("<li>"+json[i].playerName+"</li>");

            }
        }
    });
});