var express = require('express');
var app = express();

app.set('port', (process.env.PORT || 5000));

var bodyParser = require("body-parser");
app.use(bodyParser.json()); //soporte para codificar json
app.use(bodyParser.urlencoded({ extended: true })); //Soporte para decodificar las url


var firebase = require("firebase");
firebase.initializeApp({
  serviceAccount: "petagram-2c4f9-firebase-adminsdk-iy62r-a5839b4371.json",
  databaseURL: "https://petagram-2c4f9.firebaseio.com"
});

app.use(express.static(__dirname + '/public'));

// views is directory for all template files
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');

/*
var FCM = require('fcm-push');
*/
app.get('/android', function(request, response) {
  response.render('pages/index');
});

//URL: https://gabypetsnotify.herokuapp.com/registrar-usuario
var uri_servicio = '/registrar-usuario';
//app.post(uri_servicio, function(request, response) 
app.post('/registrar-usuario', function(request, response) {

   // obtiene parametros POST
	var id_dispositivo 	      = request.body.id_dispositivo;
	var id_usuario_instagram 	= request.body.id_usuario_instagram;
   
   // guarda parametros en base de datos
	var db = firebase.database();
	var db_Ref = db.ref(uri_servicio).push();
	db_Ref.set({
		id_dispositivo:         id_dispositivo,
      id_usuario_instagram:   id_usuario_instagram
	});	

  console.log('RAMA Servicio https://serene-peak-24462.herokuapp.com/registrar-usuario corriendo en puerto', app.get('port'));
  console.log('RAMA id_dispositivo', id_dispositivo);
  console.log('RAMA id_usuario_instagram', id_usuario_instagram);
  //response.send(request.body.id_dispositivo);

  // obtiene identificador de registro
 	var path = db_Ref.toString(); //https://project-4284821003924177471.firebaseio.com/id_dispositivo-devices/-KJlTaOQPwP-ssImryV1
	var pathSplit = path.split(uri_servicio + "/")
	var idAutoGenerado = pathSplit[1];

   // regresa respuesta en formato JSON
	var respuesta = generarRespuestaJSON(db, idAutoGenerado);
	response.setHeader("Content-Type", "application/json");
   response.send(JSON.stringify(respuesta));

});

function generarRespuestaJSON(db, idAutoGenerado) {
	var respuesta = {};
	var usuario = "";
	var db_Ref = db.ref(uri_servicio);
	db_Ref.on("child_added", function(snapshot, prevChildKey) {
		usuario = snapshot.val();
		respuesta = {
			id_registro: idAutoGenerado,
			id_dispositivo: usuario.id_dispositivo,
			id_usuario_instagram: usuario.id_usuario_instagram
		};
	});
  console.log('GABY4 RESPUESTA', respuesta);
	return respuesta;
}

app.listen(app.get('port'), function() {
  console.log('GABY5 Node app is running on port', app.get('port'));
});