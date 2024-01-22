var proxy_pathsuffix = context.getVariable('proxy.pathsuffix');
var request_verb = context.getVariable('request.verb');

var recurso = request_verb + proxy_pathsuffix;

var camposCifrarResponse = context.getVariable('flow.camposCifrar.response');

var camposCifrarRsaResponseRecurso = null;
var camposCifrarAesResponseRecurso = null;
var camposCifrarAes = context.getVariable('flow.camposCifrarAes.response');

var path;
var regex;

try {
    var jsonCamposCifrar = JSON.parse(camposCifrarResponse);

    print('recurso: ' + recurso);

    for (var key in jsonCamposCifrar) {

        print('key: ' + key);

        path = '^' + key + '$';
        path = path.replace(/\x2F/ig, '\\/');
        path = path.replace(/\x2A/ig, '\\w{1,}|\\-\\w{1,}');

        regex = new RegExp(path, "");
        print('expresion regular: ' + regex);
        if (regex.test(recurso)) {
            if (jsonCamposCifrar[key].rsa !== undefined && jsonCamposCifrar[key].rsa !== null) {
                camposCifrarRsaResponseRecurso = jsonCamposCifrar[key].rsa;
            } else if (!(jsonCamposCifrar[key] instanceof Object)){
                print(jsonCamposCifrar[key]);
                camposCifrarRsaResponseRecurso = jsonCamposCifrar[key];
            }
            
            if (jsonCamposCifrar[key].aes !== undefined && jsonCamposCifrar[key].aes !== null) {
                camposCifrarAesResponseRecurso = jsonCamposCifrar[key].aes;
            }
            
            break;

        } else {
            camposCifrarRsaResponseRecurso = null;
            camposCifrarAesResponseRecurso = null;
        }
    }

    if (camposCifrarAesResponseRecurso == null && camposCifrarAes !== undefined && camposCifrarAes !== null && camposCifrarAes !== ''){
        camposCifrarAesResponseRecurso = camposCifrarAes;
    }
   
    context.setVariable('flow.camposCifrar.response', camposCifrarRsaResponseRecurso);
    context.setVariable('flow.camposCifrarAes.response', camposCifrarAesResponseRecurso);
} catch (error) {
    context.setVariable('flow.camposCifrar.response', camposCifrarRsaResponseRecurso);
    context.setVariable('flow.camposCifrarAes.response', camposCifrarAesResponseRecurso);
}