/**
 * Created by saleta on 6/1/2016.
 */
$('document').ready(function(){
    $('.row-click').click(function () {
        window.document.location = $(this).data("href");
    });
});