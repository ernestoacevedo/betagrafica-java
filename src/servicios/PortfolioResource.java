package servicios;

import modelos.Portfolio;

import org.javalite.activejdbc.LazyList;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;


public class PortfolioResource extends DBResource {

	@Get
	public String mostrar(){
		String username = getRequest().getAttributes().get("username").toString();
		LazyList<Portfolio> lista_portafolios = Portfolio.where("autor = ?",username);
		if(lista_portafolios!=null){
			return lista_portafolios.toJson(true,"id","nombre","fecha");
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
	
	@Post
	public String crear(Representation entity){
		Form form = new Form(entity);
		Portfolio pfl = new Portfolio();
		pfl.set("title",form.getFirstValue("title"));
		pfl.set("created",form.getFirstValue("created"));
		pfl.set("author",form.getFirstValue("author"));
		if(pfl.saveIt()){
			return pfl.toJson(true,"title");
		}
		else{
			JSONObject json = new JSONObject();
			try {
				json.put("error", "No se pudo guardar el portafolio");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				return "{error:"+e.getMessage()+"}";
			}
			return json.toString();
		}
	}

}
