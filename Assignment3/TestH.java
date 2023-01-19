import java.io.IOException;
import java.util.*;
class TestH{
    public static void main(String[] args) throws IOException{
        HollomonClient hc0 = new HollomonClient("netsrv.cim.rhul.ac.uk", 1812);
        hc0.login("pressure", "putbackoutside");
        System.out.println(hc0.getCredits());
    }
}