package Servicios;

import org.javalite.activejdbc.LazyList;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.resource.Get;

import Modelos.Portfolio;

public class PortfoliosResource extends DBResource{
	
	/* url: /recents */
	@Get
	public String recientes(){
		LazyList<Portfolio> lista_portafolios = Portfolio.findAll().limit(20).orderBy("created desc");
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
