import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Random;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class DestinationStore {
	Map<String, List<String>> map;

	public DestinationStore() {
		map = new HashMap<String, List<String>>();
	}
	public DestinationStore(String filename) throws IOException {
		BufferedReader br = null;
		map = new HashMap<String, List<String>>();
		try {
			File file = new File(filename);
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			String value = br.readLine().toLowerCase();
			while (value != null) {
				value = value.toLowerCase();
				String head = Character.toString(value.charAt(0));
				if(map.containsKey(head)) {
					map.get(head).add(value);
				}else {
					List<String> list = new ArrayList<String>();
					list.add(value);
					map.put(head, list);
				}
				value = br.readLine();
			}
		}
		finally {
			if(br != null) {
				br.close();
			}
		}
	}
	public DestinationStore(String filename,int prefix) throws IOException {
		BufferedReader br = null;
		map = new HashMap<String, List<String>>();
		try {
			File file = new File(filename);
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			String value = br.readLine().toLowerCase();
			while (value != null) {
				value = value.toLowerCase();
				if (value.length()>prefix) {
					String head = "";
					for(int i = 0; i<prefix;i++) {
						head+=Character.toString(value.charAt(i));
					}
					if(map.containsKey(head)) {
						map.get(head).add(value);
					}else {
						List<String> list = new ArrayList<String>();
						list.add(value);
						map.put(head, list);
					}
					value = br.readLine();
				}
			}
		}
		finally {
			if(br != null) {
				br.close();
			}
		}
	}
	public boolean containKey(String key) {
		return map.containsKey(key);
	}
	public void put(String key, String destination) {
		if(map.containsKey(key)) {
			map.get(key).add(destination);
		} else {
			List<String> list = new ArrayList<String>();
			list.add(destination);
			map.put(key, list);
		}
	}
	public String getRandomDestination(String key) {
		if(map.containsKey(key)) {
			Random r = new Random();
			List<String> list = map.get(key);
			int size = list.size();
			int number = r.nextInt(size);
			String destination = list.get(number);
			String newkey = key.toUpperCase();
			String newdestination = destination.substring(newkey.length());
			destination = newkey + newdestination;
			return destination;
		}else {
			return null;
		}
	}

}
