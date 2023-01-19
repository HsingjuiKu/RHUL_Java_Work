import java.net.*;
import java.io.*;
import java.util.*;

public class HollomonClient {
	CardInputStream cis;
	BufferedWriter wr;
	Socket socket;
	public HollomonClient(String server,int port) throws IOException{
		this.socket = new Socket(server,port);
		this.wr = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
		this.cis = new CardInputStream(this.socket.getInputStream());
	}
	public List<Card> login(String username, String password) throws IOException{
		username = username.toLowerCase();
		wr.write(username);
		wr.newLine();
		wr.write(password);
		wr.newLine();
		wr.flush();
        List<Card> card = new ArrayList<Card>();
        

        String current = this.cis.readResponse();
        if (current.equals("User " + username + " logged in successfully.")){
            Card c = this.cis.readCard();
            while(c != null) {
                card.add(c);
                c = this.cis.readCard(); 
            }
            Sort.selectionSort(card);
            return card;
        }else{
            return null;
        }
		
	
	}
	public void close() throws IOException {
		this.cis.close();
		this.wr.close();
		this.socket.close();
	}
	public long getCredits() throws IOException {
        this.wr.write("CREDITS");
        this.wr.newLine();
		this.wr.flush();
		long credits = Long.parseLong(this.cis.readResponse());
		if (this.cis.readResponse().equals("OK")) {
			return credits;
		}else{
            return -1;
        }
	}
	public List<Card> getCards() throws IOException{
        this.wr.write("CARDS");
        this.wr.newLine();
		this.wr.flush();
		List<Card> card = new ArrayList<Card>();
        Card c = this.cis.readCard();
        while(c != null){
            card.add(c);
            c = this.cis.readCard();
        }
		Sort.selectionSort(card);
		return card;
	}
	public List<Card> getOffers() throws IOException{
        this.wr.write("OFFERS");
        this.wr.newLine();
		this.wr.flush();
		List<Card> card = new ArrayList<Card>();
		Card c = this.cis.readCard();
        while(c != null){
            card.add(c);
            c = this.cis.readCard();
        }
		Sort.selectionSort(card);
		return card;
	}
	public boolean buyCard(Card card) throws IOException{
		if(this.getCredits() > card.getPrice()) {
			String command = "BUY " + card.getID();
            this.wr.write(command);
            this.wr.newLine();
			this.wr.flush();
			if(this.cis.readResponse().equals("OK")) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	public boolean sellCard(Card card, long price) throws IOException{
		String offer = "SELL " + card.getID() + " " + price;
        this.wr.write(offer);
        this.wr.newLine();
		this.wr.flush();
		if (this.cis.readResponse().equals("OK")) {
			return true;
		}else {
			return false;
		}
		
	}
}