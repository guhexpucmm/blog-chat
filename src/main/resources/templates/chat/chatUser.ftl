<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/js/jquery-1.12.4.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/chatSnippet.js"></script>
    <script src="/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="/js/chatUsuario.js"></script>
</head>
<body>

    <input type="hidden" id="usuario" value="${usuario.getUsername()}">
    <input type="hidden" id="usuario_destino" value="${administrador.getUsername()}">
<div class="container">
    <div id="chatbox" class="row  col-xs-5 col-md-5" id="chat_window_1" style="margin-left: 30%" >
        <div class="col-xs-12 col-md-12">
            <div class=" panel panel-default">
                <div class="panel-heading top-bar">
                    <div class="col-md-8 col-xs-8">
                        <h3 class="panel-title"><span class="glyphicon glyphicon-comment"></span> ${administrador.getNombre()}</h3>
                    </div>

                </div>
                <div class="panel-body  msg_container_base" >
                    <div id="chat_panel">

                    </div>

                </div>
            </div>
                <div class="panel-footer">
                    <div class="input-group">
                        <input id="btn-input" type="text" class="form-control input-sm chat_input" placeholder="Escriba su mensaje..." />
                        <span class="input-group-btn">
                        <button class="btn btn-primary btn-sm" id="btn-chat">Enviar</button>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    (function($){
        $(window).on("load",function(){
            $(".msg_container_base").mCustomScrollbar();
        });
    })(jQuery);
</script>

</html>

