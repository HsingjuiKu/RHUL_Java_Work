public class Card implements Comparable<Card>{
	private long ID;
	private String name;
	private Rank rank;
	private long price;
	public Card(long ID, String name,Rank rank) {
		this.ID = ID;
		this.name = name;
		this.rank = rank;
		this.price = 0;
    }
    public void setPrice(long price) {
		this.price = price;
	}
	public long getID() {
		return this.ID;
	}
	public String getName() {
		return this.name;
	}
	public Rank getRank() {
		return this.rank;
	}
	public long getPrice() {
		return this.price;
	}
	@Override
	public String toString() {
		String total = (String) "("+this.ID+","+this.name+","+this.rank+","+this.price+")";
		return total;
	}
	@Override
	public boolean equals(Object o) {
		Card other = (Card)o;
		if (this.ID == other.getID() && this.name.equals(other.getName()) && this.rank.toString().equals(other.getRank().toString())) {
			return true;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return this.name.hashCode(); 
	}
	@Override
	public int compareTo(Card object) {
		int justify = this.rank.compareTo(object.getRank());
		if (justify == 0){
			justify = this.name.compareTo(object.getName());
			if (justify == 0) {
				long a = this.ID - object.getID();
				justify = Integer.parseInt(String.valueOf(a));
			}
		}
		return justify;
	}
}