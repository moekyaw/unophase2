$(function () {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/UnoGameWebServer/api/game/discardcard',
        crossDomain: true,
        dataType: 'text',
        success: function (data) {

            var json = $.parseJSON(data);
            $('#game').append('<h2>' + json.gname + '</h2>');
            $('#game').append('<img src="' + json.image + '" />');

            $('#gid').val(json.gid);
            console.log(data);
        }
    });
});