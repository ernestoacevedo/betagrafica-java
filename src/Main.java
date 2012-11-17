

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

import Servicios.AuthenticationResource;
import Servicios.PortfoliosResource;
import Servicios.SettingsResource;
import Servicios.UserResource;
import Servicios.UsersResource;


public class Main extends Application {
	// Ubicación para la raíz del sitio.
	//public static final String ROOT_URI = "file:///home/gabo/Documentos/betagrafica-app/files";
	//public static final String ROOT_URI = "file:///home/ernesto/workspace/betagrafica-app/files";
	public static final String ROOT_URI = "file:///home/gustavo/workspace/betagrafica-app/files";
	
	public Restlet createInboundRoot(){
		Directory dir = new Directory(getContext(),ROOT_URI);
		Directory dashboard = new Directory(getContext(),ROOT_URI);
		Directory portfolio = new Directory(getContext(),ROOT_URI);
		Directory profile = new Directory(getContext(),ROOT_URI);
		Directory settings = new Directory(getContext(),ROOT_URI);
		settings.setIndexName("settings");
		portfolio.setIndexName("portfolio");
		dashboard.setIndexName("dashboard");
		profile.setIndexName("profile");
		dir.setListingAllowed(true);
		Router router = new Router(getContext());
		router.attachDefault(dir);

		router.attach("/user/{username}",UserResource.class);
		router.attach("/user",UserResource.class);
		router.attach("/users",UsersResource.class);
		router.attach("/recents",PortfoliosResource.class);
		router.attach("/dashboard",dashboard);
		router.attach("/portfolio",portfolio);
		router.attach("/settings",settings);
		router.attach("/profile/{username}",profile);
		router.attach("/login",AuthenticationResource.class);
		//router.attach("/upload",UploadResource.class);
		router.attach("/update",SettingsResource.class);

		return router;
	}
	
	public static void main(String[] args) throws Exception {
		MakeInstrumentation.make();
		Component component = new Component();
		component.getServers().add(Protocol.HTTP,9000);
		component.getClients().add(Protocol.FILE);
		component.getDefaultHost().attach(new Main());
		component.start();
	}
}
