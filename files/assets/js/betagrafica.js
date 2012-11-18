$(document).ready(function(){
	var createUser = function(user,mail,pwd){
		$.ajax({
			type: 'POST',
			url: '/user',
			data: {username: user,email: mail, password: pwd}
		}).done(function(){
			console.log('Datos insertados');
			$('#join').trigger('reveal:close');
		});
	};

	var logUser = function(user,pwd){
		$.ajax({
			type: 'POST',
			url: '/login',
			data: {username: user,password: pwd}
		}).done(function(data){
			// Crea una cookie con el nombre de usuario, que dura 1 semana y es válida para todo el sitio
			data = JSON.parse(data);
			if(data.error=="false"){
				$.cookie('User_username',user, { expires: 7, path: '/' });			
				location.href= window.location+"dashboard";
				$('#login').trigger('reveal:close');
			}
			else{
				console.log("Contraseña errónea");
			}
		});
	};

	$('#join input').keypress(function(e){
		if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
			$('#join-button').click();
			return true;
		}
	});

	$('#login input').keypress(function(e){
		if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
			$('#login-button').click();
			return true;
		}
	});

	$('#join-button').click(function(e){
		e.preventDefault();
		e.stopPropagation();
		var username = $('#join-chart_name').val();
		var email = $('#join-email').val();
		var password = $('#join-password').val();
		createUser(username,email,password);
	});

	$('#login-button').click(function(e){
		e.preventDefault();
		e.stopPropagation();
		var username = $('#login-username').val();
		var password = $('#login-password').val();
		logUser(username,password);
	});

	$('#logout').click(function(e){
		e.preventDefault();
		e.stopPropagation();
		var redi = $.removeCookie('User_username',{ path: '/' });
		if (redi){
			location.href = "/";
		}		
	});
});
