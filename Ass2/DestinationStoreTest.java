import java.io.IOException;

public class DestinationStoreTest {

	public static void main(String[] args) throws IOException {
		DestinationStore store = new DestinationStore();
		store.put("a", "argentina");
		store.put("ba", "bahrain");
		store.put("ba", "baku");
		store.put("a", "angkor wat");
		DestinationStore ds = new DestinationStore("cities");
	}
}
