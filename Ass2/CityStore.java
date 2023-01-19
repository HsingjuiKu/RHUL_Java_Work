import java.io.IOException;
import java.lang.Character;
import java.util.List;
import java.util.Random;
import java.lang.Integer;
public class CityStore extends DestinationStore{

	public CityStore() {
		super();
	}
	public CityStore(String filename) throws IOException {
		super(filename);
	}
	public CityStore(String filename, int prefix) throws IOException {
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
			String[] array = destination.split(" ");
			String value = "";
			int l = array.length;
			int population = Integer.parseInt(array[l-1]); 
			for(int i = 0; i < l-1; i++) {
				value = value + array[i] + " ";
			}
			String newkey = key.toUpperCase();
			value = value.substring(key.length());
			value = newkey + value;
			if(population >= 10000000) {
				return value + "(megacity)";
			} else if(population < 10000000 && population >= 5000000) {
				return value + "(very large city)";
			}else {
				return value + "(large city)";
			}
		}else {
			return null;
		}
	}
}