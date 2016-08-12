$(function () {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/UnoGameWebServer/api/game/getstartgame',
        crossDomain: true,
        dataType: 'text',
        success: function (data) {
            
            var json = $.parseJSON(data);

            $('#startgameId').val(json.gid);
            $('#gameid').append(json.gid);
            $('#startgameName').append(json.name);

        }

    });

});