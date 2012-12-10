package servicios;

import modelos.Image;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;



public class ImageResource extends DBResource{
	
	@Post
	public String create(Representation entity){
		Form f = new Form(entity);
		Image img = new Image();
		img.set("path",f.getFirstValue("path"));
		img.set("root_id",f.getFirstValue("root_id"));
		if(img.saveIt()){
			return img.toJson(true,"id");
		}
		else{
			JSONObject json = new JSONObject();
			try {
				json.put("error", "No se pudo guardar la imagen");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				return "{error:"+e.getMessage()+"}";
			}
			return json.toString();
		}
	}

}
