package servicios;

import modelos.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;


public class SettingsResource extends DBResource {
	
	@Post
	public String actualizar(Representation entity){
		Form form = new Form(entity);
		User u = (User)User.findFirst("username = ?",form.getFirstValue("username"));
		u.set("firstname",form.getFirstValue("firstname"));
		u.set("lastname",form.getFirstValue("lastname"));
		u.set("location",form.getFirstValue("location"));
		u.set("bio",form.getFirstValue("bio"));
		u.set("phone",form.getFirstValue("phone"));
		u.set("email",form.getFirstValue("email"));
		if(u.saveIt()){//Si se actualizo correctamente
			return u.toJson(true,"username","firstname","lastname","location","bio","phone","email");
		}else{
			JSONObject json = new JSONObject();
			try {
				json.put("error", "No se pudo guardar el usuario");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				return "false";
			}
			return json.toString();
		}
	}

}
