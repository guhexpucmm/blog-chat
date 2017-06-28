$(document).ready(function () {
    var totalPaginas = parseInt($('#totalPaginas').text());
    var paginated = false;

    for (var i = 1; i <= totalPaginas; i++) {
        if (paginated == false) {
            $("#pagin").append('<button class="pNumber">' + (i) + '</button>');
        }
    }

    paginated = true;

    $.ajax({
        url: '/articulos/' + "/1", success: function (data) {
            $('.principal').html(data);
        }
    });

    $('.pNumber').click(function () {
        $.ajax({
            url: '/articulos/' + "/" + $(this).text(), success: function (data) {
                $('.principal').html(data);
            }
        });
    });

});