import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
import java.util.*;

public class HeritageSiteStore extends DestinationStore{
	public HeritageSiteStore() {
		super();
	}
	public HeritageSiteStore(String filename) throws IOException {
		super(filename);
	}
	public HeritageSiteStore(String filename, int prefix) throws IOException {
		super(filename,prefix);
	}
	@Override
	public String getRandomDestination(String key) {
		if(map.containsKey(key)) {
			Random r = new Random();
			List<String> list = map.get(key);
			int size = list.size();
			int number = r.nextInt(size);
			String destination = list.get(number);
			String newdestination = destination.substring(key.length());
			String newkey = key.toUpperCase();
			destination = newkey + newdestination + "(UNESCO World Heritage Site)";
			return destination;
		}else {
			return null;
		}
	}

}
