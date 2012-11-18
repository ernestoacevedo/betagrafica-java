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


public class AuthenticationResource extends DBResource {

	@SuppressWarnings("unused")
	@Get
	public String isLoged(){
		String username = getRequest().getAttributes().get("username").toString();
		String id = getRequest().getAttributes().get("id").toString();
		System.out.println(getRequest().getCookies().toArray());
		return "{error:false,responde:true}";
	}

	@Post
	public String login(Representation entity) throws NoSuchAlgorithmException, JSONException{
		Form form = new Form(entity);
		String username = form.getFirstValue("username");
		String password = encryptPassword(form.getFirstValue("password"));
		User u = (User)User.findFirst("username = ? and password = ?", username,password);
		if(u != null){
			JSONObject json = new JSONObject();
			json.put("error","false");
			return json.toString();
		}else{
			//Usuario no existe o mal email y/o password
			JSONObject json = new JSONObject();
			try {
				json.put("error", "Nombre de usuario o contraseña errónea");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				return "{error:"+e.getMessage()+"}";
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
