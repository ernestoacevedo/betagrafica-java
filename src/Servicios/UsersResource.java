package Servicios;

import org.javalite.activejdbc.LazyList;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.resource.Get;

import Modelos.User;

public class UsersResource extends DBResource{
	
	@Get
	public String mostrar(){
		LazyList<User> listaUsuarios = User.findAll();
		if(listaUsuarios != null){
			return listaUsuarios.toJson(true,"username");
		}else{
			JSONObject json = new JSONObject();
			try {
				json.put("error", "No hay usuarios :(");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				return "{error:"+e.getMessage()+"}";
			}
			return json.toString();
		}
	}

}
