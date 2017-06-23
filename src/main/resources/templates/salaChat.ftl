<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog Home</title>

    <!-- Bootstrap core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="/css/chat.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>

<#--/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">-->

    <!-- Custom styles for this template -->
    <link href="/css/blog-home.css" rel="stylesheet">
    <link href="/css/chat.css">

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
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarExample" aria-controls="navbarExample" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="container">
        <a class="navbar-brand" href="/inicio/"> <img width="75" height="75" src="/logopucmm.png"> Blog PUCMM
            Programacion
            Web 2017</a>
    </div>
</nav>

<!-- Page Content -->
<div class="container">

    <div class="row">
        <!-- Blog Entries Column -->
        <div class="col-md-8" style="display: inline-block;">
            <h1 class="my-4">&nbsp
                <small></small>
            </h1> <!-- ESPACIO VACIO -->
            <!-- Blog Post -->

            <!--CHAT BOX-->
            <div class="card my-4 chat">
                <h5 class="card-header">Chat</h5>
                <div class="card-block">
                    <div id="chat" class="scrollable">

                    </div>
                    <div class="input-group">
                        <input class="form-control" type="text" id="mensaje" class="controles">
                        <button class="btn btn-primary" type="button" id="enviar">Enviar</button>
                    </div>
                </div>
            </div>

            <div>
                <ul class="scrollable">
                    <li>Usuario1</li>
                    <li>Usuario1</li>
                    <li>Usuario1</li>
                    <li>Usuario1</li>
                    <li>Usuario1</li>
                    <li>Usuario1</li>
                    <li>Usuario1</li>
                </ul>
            </div>

        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-3">


        </div>

    </div>
    <!-- /.row -->
</div>
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
<script src="/js/chat.js"></script>
</html>
