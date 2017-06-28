<!DOCTYPE html>
<html lang="en>

<head>

    <meta charset=" utf-8">
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


</head>

<body>
<div id="articulosp">
<#list articulos as articulo>
    <div class="card mb-4">
        <div class="card-block">
            <h2 class="card-title">${articulo.getTitulo()}</h2>
            <p class="card-text">${articulo.getResumen()}</p>
            <button onclick="location.href='/articulo/?id=${articulo.getId()}'"
                    class="btn btn-primary">Leer mas &rarr;
            </button>
        </div>
        <div class="card-footer text-muted">
        ${articulo.getFecha()} ${articulo.getAutor().getNombre()}
        </div>
    </div>
</#list>
</div>
</body>
</html>
