$(function () {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/UnoGameWebServer/api/game/getwaitingplayer',
        crossDomain: true,
        dataType: 'text',
        success: function (data) {


            var json = $.parseJSON(data);

            $('#gname').append("Waiting For " + json.gname + " to start");
            $('#gid').val(json.gid);
            $('#playerId').val(json.playerId);
            console.log(data);

        }

    });

});