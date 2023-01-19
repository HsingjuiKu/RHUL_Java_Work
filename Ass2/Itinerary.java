import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;


public class Itinerary {
	final static String citytxt = "cities.txt";
	final static String countriestxt = "countries.txt";
	final static String heritagetxt = "heritage_sites.txt";
	static DestinationStore ds;
	static CityStore cs ;
	static HeritageSiteStore hs;

	
	public static void main(String[] args) {
		try {
			List<String> plan = null;
			if (args.length == 1) {
				ds = new DestinationStore(countriestxt);
				cs = new CityStore(citytxt);
				hs = new HeritageSiteStore(heritagetxt);
				plan = generate(args[0]);
			}else {
				int prefix = Integer.parseInt(args[1]);
				while(plan == null && prefix > 0){
					ds = new DestinationStore(countriestxt,prefix);
					cs = new CityStore(citytxt,prefix);
					hs = new HeritageSiteStore(heritagetxt,prefix);
					plan = generate(args[0],prefix);
					//System.out.println(plan.size());
					prefix-=1;
				}
			}
			Iterator it = plan.iterator();
			while(it.hasNext()){
				System.out.println(it.next());
			}
		}catch(IOException e) {
		}
	}
	static List<String> generate(String input){
		input = input.toLowerCase();
		int number = 1;
		int maxnumber = input.length();
		int nhs = 0;
		int ncs = 0;
		List<String> list = new ArrayList<String>();
		Random r = new Random();
		while(number<=maxnumber) {
			if (number == 1) {
				String destination = ds.getRandomDestination(Character.toString(input.charAt(number-1)));
				list.add(destination);
			}else if(number == 2) {
				String destination = hs.getRandomDestination(Character.toString(input.charAt(number-1)));
				list.add(destination);
				nhs+=1;
			}else if (number == 3) {
				String destination = cs.getRandomDestination(Character.toString(input.charAt(number-1)));
				list.add(destination);
				ncs+=1;
			}else {
				if (nhs>ncs) {
					int n = r.nextInt(2);
					if (n == 0) {
						String destination = ds.getRandomDestination(Character.toString(input.charAt(number-1)));
						list.add(destination);
					}else if(n == 1) {
						String destination = hs.getRandomDestination(Character.toString(input.charAt(number-1)));
						list.add(destination);
						nhs+=1;
					}else if(n == 2) {
						String destination = cs.getRandomDestination(Character.toString(input.charAt(number-1)));
						list.add(destination);
						ncs+=1;
					}
				}else if(nhs == ncs) {
					int n = r.nextInt(1);
					if (n == 0) {
						String destination = ds.getRandomDestination(Character.toString(input.charAt(number-1)));
						list.add(destination);
					}else if(n == 1) {
						String destination = hs.getRandomDestination(Character.toString(input.charAt(number-1)));
						list.add(destination);
						nhs+=1;
					}
				}
			}
			number += 1;
		}
		return list;
	}
	//Uses a function override let the method can be used in has prefix
	static List<String> generate(String input, int prefix) throws IOException{
		List<String> list = new ArrayList<String>();
		input = input.toLowerCase();
		char[] array = input.toCharArray();
		String head = input.substring(0, prefix);
		String substring = input.substring(prefix);
		int number = 1;
		int nhs = 0;
		int ncs = 0;
		Random r = new Random();	
		if (ds.containKey(head) || cs.containKey(head)|| hs.containKey(head)) {
			if (ds.containKey(head)) {
				String destination = ds.getRandomDestination(head);
				list.add(destination);
				ds = new DestinationStore(countriestxt);
				cs = new CityStore(citytxt);
				hs = new HeritageSiteStore(heritagetxt);
				list.addAll(newgenerate(0,0,substring));
			}else if(hs.containKey(head) && substring.length()>=1) {
				String destination = hs.getRandomDestination(head);
				list.add(destination);
				ds = new DestinationStore(countriestxt);
				cs = new CityStore(citytxt);
				hs = new HeritageSiteStore(heritagetxt);
				list.addAll(newgenerate(0,1,substring));
			}else if(cs.containKey(head) && substring.length()>=2) {
				String destination = cs.getRandomDestination(head);
				list.add(destination);
				ds = new DestinationStore(countriestxt);
				cs = new CityStore(citytxt);
				hs = new HeritageSiteStore(heritagetxt);
				list.addAll(newgenerate(1,0,substring));
			}
		}
		return list;
	}
	static List<String> newgenerate(int ncs,int nhs,String input){
		int maxnumber = input.length();
		List<String> list = new ArrayList<String>();
		int number = 1;
		Random r = new Random();
		if (nhs == 0 && ncs == 0 && maxnumber > 0) {
			String destination = hs.getRandomDestination(Character.toString(input.charAt(number-1)));
			number +=1;
			list.add(destination);
			if (maxnumber >= number) {
				destination = cs.getRandomDestination(Character.toString(input.charAt(number-1)));
				number+=1;
				list.add(destination);
				while(number <= maxnumber) {
					if (nhs>ncs) {
						int n = r.nextInt(2);
						if (n == 0) {
							destination = ds.getRandomDestination(Character.toString(input.charAt(number-1)));
							list.add(destination);
						}else if(n == 1) {
							destination = hs.getRandomDestination(Character.toString(input.charAt(number-1)));
							list.add(destination);
							nhs+=1;
						}else if(n == 2) {
							destination = cs.getRandomDestination(Character.toString(input.charAt(number-1)));
							list.add(destination);
							ncs+=1;
						}
					}else if(nhs == ncs) {
						int n = r.nextInt(1);
						if (n == 0) {
							destination = ds.getRandomDestination(Character.toString(input.charAt(number-1)));
							list.add(destination);
						}else if(n == 1) {
							destination = hs.getRandomDestination(Character.toString(input.charAt(number-1)));
							list.add(destination);
							nhs+=1;
						}
					}
					number += 1;
				}
			}
			
		}else if(nhs == 1 && ncs == 0) {
				String destination = ds.getRandomDestination(Character.toString(input.charAt(number-1)));
				list.add(destination);
				number += 1;
				if (number <= maxnumber) {
					destination = cs.getRandomDestination(Character.toString(input.charAt(number-1)));
					list.add(destination);
					number += 1;
					while(number <= maxnumber) {
						if (nhs>ncs) {
							int n = r.nextInt(2);
							if (n == 0) {
								destination = ds.getRandomDestination(Character.toString(input.charAt(number-1)));
								list.add(destination);
							}else if(n == 1) {
								destination = hs.getRandomDestination(Character.toString(input.charAt(number-1)));
								list.add(destination);
								nhs+=1;
							}else if(n == 2) {
								destination = cs.getRandomDestination(Character.toString(input.charAt(number-1)));
								list.add(destination);
								ncs+=1;
							}
						}else if(nhs == ncs) {
							int n = r.nextInt(1);
							if (n == 0) {
								destination = ds.getRandomDestination(Character.toString(input.charAt(number-1)));
								list.add(destination);
							}else if(n == 1) {
								destination = hs.getRandomDestination(Character.toString(input.charAt(number-1)));
								list.add(destination);
								nhs+=1;
							}
						}
						number += 1;
					}
				}
		}else if (nhs == 0 && ncs == 1) {
			String destination = hs.getRandomDestination(Character.toString(input.charAt(number-1)));
			list.add(destination);
			nhs+=1;
			destination = ds.getRandomDestination(Character.toString(input.charAt(number-1)));
			list.add(destination);
			number += 2;
			while(number <= maxnumber) {
				if (nhs>ncs) {
					int n = r.nextInt(2);
					if (n == 0) {
						destination = ds.getRandomDestination(Character.toString(input.charAt(number-1)));
						list.add(destination);
					}else if(n == 1) {
						destination = hs.getRandomDestination(Character.toString(input.charAt(number-1)));
						list.add(destination);
						nhs+=1;
					}else if(n == 2) {
						destination = cs.getRandomDestination(Character.toString(input.charAt(number-1)));
						list.add(destination);
						ncs+=1;
					}
				}else if(nhs == ncs) {
					int n = r.nextInt(1);
					if (n == 0) {
						destination = ds.getRandomDestination(Character.toString(input.charAt(number-1)));
						list.add(destination);
					}else if(n == 1) {
						destination = hs.getRandomDestination(Character.toString(input.charAt(number-1)));
						list.add(destination);
						nhs+=1;
					}
				}
				number += 1;
			}
		}
		return list;
	}
}
