package Servicios;
import org.javalite.activejdbc.Base;
import org.restlet.resource.ServerResource;


public class DBResource extends ServerResource{
	public DBResource(){
		if(!Base.hasConnection()){
			Base.open("com.mysql.jdbc.Driver","jdbc:mysql://localhost/betagrafica","root"," ");
			//Base.open("com.mysql.jdbc.Driver","jdbc:mysql://localhost/betagrafica","root","gera6942");
			//Base.open("com.mysql.jdbc.Driver","jdbc:mysql://localhost/betagrafica","root","1979bkn");
		}
	}
}
