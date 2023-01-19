import java.util.*;
import java.io.*;
public class CardInputStream extends InputStream{
	BufferedReader br;
	public CardInputStream(InputStream input) {
		this.br = new BufferedReader(new InputStreamReader(input));
	}
	@Override
	public void close() throws IOException {
		this.br.close();
	}
	public Card readCard() throws IOException {
        if (this.br.readLine().equals("CARD")){
            String id = this.br.readLine();
            long ID = Long.parseLong(id);
            String name = this.br.readLine();
            String rk = this.br.readLine();
            Rank rank = Rank.valueOf(rk);
            String pr = this.br.readLine();			
            Long price = Long.parseLong(pr);
            Card card = new Card(ID,name,rank);
            card.setPrice(price);
            //System.out.println(id + name + rk + pr);
            return card;
        }else{
            return null;
        }
	}
	public String readResponse() throws IOException {
		
		return this.br.readLine();
	}
	@Override
	public int read() throws IOException {
		return this.br.read();
	}
}