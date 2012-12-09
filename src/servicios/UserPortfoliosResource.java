package servicios;

import modelos.Portfolio;

import org.javalite.activejdbc.LazyList;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.resource.Get;

public class UserPortfoliosResource extends DBResource{
	@Get
	public String listaPortafolios(){
		String username = getRequest().getAttributes().get("username").toString();
		LazyList<Portfolio> lista_portafolios = Portfolio.where("author=?",username);
		if(lista_portafolios!=null){
			return lista_portafolios.toJson(true,"id","title","author","created","cover");
		}
		else{
			JSONObject json = new JSONObject();
			try {
				json.put("error", "No hay portafolios creados");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				return "{error:"+e.getMessage()+"}";
			}
			return json.toString();
		}
	}
}
