import java.util.*;

public class CardTest {

	public static void main(String[] args) {
		Card c0 = new Card(1, "card1", Rank.COMMON);
	    Card c1 = new Card(2, "card2", Rank.COMMON);
	    Card c2 = new Card(1, "card1", Rank.COMMON);
	    c2.setPrice(1);
	    int j = c0.compareTo(c2);
	    System.out.println(j);
	    HashSet<Card> hs = new HashSet<Card>();
	    hs.add(c0);
	    hs.add(c1);
	    hs.add(c2);
	    Iterator<Card> it = hs.iterator();
	    while(it.hasNext()) {
	    	Card c = it.next();
	    	System.out.println(c2 == c);
	    }
	    TreeSet<Card> ts = new TreeSet();
	    ts.add(c0);
	    ts.add(c1);
	    ts.add(c2);
	    for(it = ts.iterator();it.hasNext();) {
	    	System.out.println(it.next().toString());
	    }
	}

}