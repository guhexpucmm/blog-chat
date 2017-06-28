$(document).ready(function () {
    var totalPaginas = parseInt($('#totalPaginas').text());

    for (var i = 1; i <= totalPaginas; i++) {
        $("#pagin").append('<button class="pNumber">' + (i) + '</button>');
    }

    $('.pNumber').click(function () {
        $.ajax({
            url: '/articulos/' + "/" + $(this).text(), success: function (data) {
                $('.principal').html(data);
            }
        });
    });

});