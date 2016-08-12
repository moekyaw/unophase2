$(function () {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/UnoGameWebServer/api/game/getgame',
        crossDomain: true,
        dataType: 'text',
        success: function (data) {


            var json = $.parseJSON(data);

            for (var i = 0; i < json.length; i++)
            {
                $('#gamelist').append('<option value=' + json[i].gid + ">" + json[i].title + "(" + json[i].gid + ")" + '</option>');
            }
        }

    });

});