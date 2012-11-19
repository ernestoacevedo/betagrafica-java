

import org.javalite.instrumentation.Instrumentation;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

import servicios.AuthenticationResource;
import servicios.PortfolioResource;
import servicios.PortfoliosResource;
import servicios.SettingsResource;
import servicios.UserPortfoliosResource;
import servicios.UserResource;
import servicios.UsersResource;



public class Main extends Application {
	// Ubicación para la raíz del sitio.
	//public static final String ROOT_URI = "file:///home/gabo/Documentos/betagrafica-app/files";
	public static final String ROOT_URI = "file:///home/ernesto/workspace/betagrafica/files";
	//public static final String ROOT_URI = "file:///home/gustavo/workspace/betagrafica/files";
	
	public Restlet createInboundRoot(){
		Directory dir = new Directory(getContext(),ROOT_URI);
		Directory dashboard = new Directory(getContext(),ROOT_URI);
		Directory profile = new Directory(getContext(),ROOT_URI);
		Directory settings = new Directory(getContext(),ROOT_URI);
		Directory create = new Directory(getContext(),ROOT_URI);
		dashboard.setIndexName("dashboard");
		profile.setIndexName("profile");
		create.setIndexName("create");
		settings.setIndexName("settings");
		dir.setListingAllowed(true);
		Router router = new Router(getContext());
		router.attachDefault(dir);

		router.attach("/user/{username}",UserResource.class);
		router.attach("/user",UserResource.class);
		router.attach("/users",UsersResource.class);
		router.attach("/recents",PortfoliosResource.class);
		router.attach("/dashboard",dashboard);
		router.attach("/portfolio",PortfolioResource.class);
		router.attach("/settings",settings);
		router.attach("/profile/{username}",profile);
		router.attach("/create",create);
		router.attach("/login",AuthenticationResource.class);
		router.attach("/update",SettingsResource.class);
		router.attach("/fetch/{username}",UserPortfoliosResource.class);
		//router.attach("/upload",UploadResource.class);
		
		

		return router;
	}
	
	public static void main(String[] args) throws Exception {
		try{
			Instrumentation instrumentation = new Instrumentation();
			instrumentation.setOutputDirectory(ClassLoader.getSystemResource("").getPath());
			instrumentation.instrument();
		}catch(Exception e){
			e.printStackTrace();
		}
		Component component = new Component();
		component.getServers().add(Protocol.HTTP,9000);
		component.getClients().add(Protocol.FILE);
		component.getDefaultHost().attach(new Main());
		component.start();
	}
}
