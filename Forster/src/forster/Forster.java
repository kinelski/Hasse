package forster;

import java.util.ArrayList;

public class Forster {

    public static void main(String[] args) {
        Table t;
        
        try{
            t = new Table("table 8.txt");
            ArrayList<Integer> a = t.getSubgroups();
            Hasse h = new Hasse(a);
            OutputManager out = new OutputManager(h.hasseMatrix(), a);
            out.generateOutput();
        }
        catch (Exception e){}
        
        //for (int i=0; i<a.size(); i++)
            //System.out.println (a.get(i));
    }
    
}
