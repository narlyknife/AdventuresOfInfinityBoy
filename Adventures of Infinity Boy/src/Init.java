import java.awt.List;
import java.util.ArrayList;

public class Init {
	
	//Variable holding different Frames Per Second
	ArrayList<Integer> fps = new ArrayList<Integer>();
	final int PLATFORM_COUNT;
	
	Init() {
	//The Main threads FPS
	fps.add(60);
	
	// Variable for maximum amount of platforms that will appear on screen
	PLATFORM_COUNT = 10;
	}
	
}