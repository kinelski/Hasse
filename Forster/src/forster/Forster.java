package forster;

import java.util.ArrayList;

public class Forster {

    public static void main(String[] args) {
        Table t = new Table("table 6.txt");
        ArrayList<Integer> a = t.getSubgroups();
        
        //for (int i=0; i<a.size(); i++)
            //System.out.println (a.get(i));
    }
    
}
