<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog Home - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/blog-home.css" rel="stylesheet">

    <!-- Temporary navbar container fix -->
    <style>
        .navbar-toggler {
            z-index: 1;
        }

        @media (max-width: 576px) {
            nav > .container {
                width: 100%;
            }
        }
    </style>

</head>

<body>

<!-- Navigation -->
<nav class="navbar fixed-top navbar-toggleable-md navbar-inverse bg-inverse">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarExample" aria-controls="navbarExample" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="container">
        <a class="navbar-brand" href="/inicio/"> <img width="75" height="75" src="/logopucmm.png"> Blog PUCMM
            Programacion
            Web 2017</a>
    </div>
</nav>

<div class = "panel panel-default" style="width: 40%; margin-left: 30%">
    <input type="hidden" value="${usuario.getUsername()}" name="nombre">
    <div class = "panel-body">
        <h1 class="my-4">&nbsp <small> </small></h1> <!-- ESPACIO VACIO -->
        <h2 style="margin-auto">Administradores y Autores Disponibles</h2>
        <hr>
        <ul>

        <#list administradoresautores as a>
            <li>
                <a href="chatRoom/${a.getUsername()}/${usuario.getUsername()}"><h4>${a.getNombre()}</h4></a>
            </li>
        </#list>
        </ul>
    </div>


</div>
</div>

<!-- /.row -->

<!-- /.container -->

<!-- Footer -->
<footer class="py-5 bg-inverse">
    <div class="container">
        <p class="m-0 text-center text-white">Pucmm &copy; Programacion Web 2017</p>
    </div>
    <!-- /.container -->
</footer>

<!-- Bootstrap core JavaScript -->
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/tether/tether.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>

</body>
<script>
    function meGusta() {
        var articulo
    }
</script>
</html>



