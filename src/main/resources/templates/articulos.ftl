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