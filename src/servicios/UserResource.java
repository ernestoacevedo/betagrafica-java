package servicios;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import modelos.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;


public class UserResource extends DBResource{
	
	/* url: /user/username */
	@Get
	public String mostrar(){
		String username = getRequest().getAttributes().get("username").toString();
		User u = User.findFirst("username = ?",username);
		if(u!=null){
			return u.toJson(true,"username","firstname","lastname","location","bio","phone","email","imgpath","coverpath");
		}
		else{
			JSONObject json = new JSONObject();
			try {
				json.put("error", "El usuario no está registrado");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				return "{error:"+e.getMessage()+"}";
			}
			return json.toString();
		}
	} 
	
	/*
	@Get("html")
	public Representation mostrar(){
		String username = getRequest().getAttributes().get("username").toString();
		User u = User.findFirst("username = ?",username);
		String userdata = u.toJson(true,"username","name","lastname","bio","phone","email","imgpath","coverpath");
		if(u!=null){
			Map<String, Object> dataModel = new TreeMap<String, Object>();
			dataModel.put("username",null);
	        dataModel.put("contacts",null);
	        dataModel.put("resourceRef", getRequest().getResourceRef());
	        dataModel.put("rootRef", getRequest().getRootRef());

	        File templateDir = new File("/home/ernesto/workspace/betagrafica-app/files/templates");
            Configuration fmc = new freemarker.template.Configuration();
            
            TemplateRepresentation rep = new TemplateRepresentation("profile.html",fmc,dataModel, MediaType.TEXT_HTML); 
	        
	        return rep;
		}
		return null;
	}*/
	
	@Post
	public String crear(Representation entity){
		Form form = new Form(entity);
		User u = new User();
		u.set("username",form.getFirstValue("username"));
		u.set("password",encryptPassword(form.getFirstValue("password")));
		u.set("email",form.getFirstValue("email"));
		if(u.saveIt()){
			return u.toJson(true,"username");
		}
		else{
			JSONObject json = new JSONObject();
			try {
				json.put("error", "No se pudo guardar el usuario");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				return "{error:"+e.getMessage()+"}";
			}
			return json.toString();
		}
	}
	
	@Put
	public String actualizar(Representation entity){
		Form form = new Form(entity);
		User u = (User)User.findFirst("username = ?",form.getFirstValue("username"));
		if(u!=null){//Se encontró el usuario
			// actualiza cada campo
			User.update("firstname=?","username=?",form.getFirstValue("firstname"),form.getFirstValue("username"));
			User.update("lastname=?","username=?",form.getFirstValue("lastname"),form.getFirstValue("username"));
			User.update("location=?","username=?",form.getFirstValue("location"),form.getFirstValue("username"));
			User.update("bio=?","username=?",form.getFirstValue("bio"),form.getFirstValue("username"));
			User.update("phone=?","username=?",form.getFirstValue("phone"),form.getFirstValue("username"));
			User.update("email=?","username=?",form.getFirstValue("email"),form.getFirstValue("username"));
			return u.toJson(true,"firstname","lastname","location","bio","phone","email");
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
	
	// Encripta un String en SHA1
	private static String encryptPassword(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	@SuppressWarnings("resource")
	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    return formatter.toString();
	}
}
