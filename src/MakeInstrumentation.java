import org.javalite.instrumentation.Instrumentation;


public class MakeInstrumentation {
	public static void make(){
		try{
			Instrumentation instrumentation = new Instrumentation();
			instrumentation.setOutputDirectory(ClassLoader.getSystemResource("").getPath());
			instrumentation.instrument();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
