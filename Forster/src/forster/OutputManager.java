package forster;

import java.io.PrintWriter;
import java.util.ArrayList;

public class OutputManager {
    
    private int[][] hasse;
    private ArrayList<Integer> array;
    
    public OutputManager (int[][] hasse, ArrayList<Integer> array){
        this.hasse = hasse;
        this.array = array;
    }
    
    public char intToChar (int i){
        if (i == 0) return '1';
        else return (char)(i + 'a' - 1);
    }
    
    public String intToString (int sub){
        String s = "\"";
        int bit = 0;
        
        while (sub > 0){
            if (sub%2 == 1)
                s = s + intToChar(bit);
            
            bit++;
            sub /= 2;
        }
        s = s + "\"";
        
        return s;
    }
    
    public void generateOutput(){
        
        try{
            PrintWriter writer = new PrintWriter ("output.txt");
            
            writer.println("digraph G{");
            writer.println();
            
            for (int i=0; i<array.size(); i++){
                for (int j=0; j<array.size(); j++){
                    
                    if (hasse[i][j] == 1){
                        writer.print("\t");
                        writer.print(intToString(array.get(i)));
                        writer.print(" -> ");
                        writer.print(intToString(array.get(j)));
                        writer.println(";");
                    }
                    
                }
            }
            
            writer.println();
            writer.print("}");
            
            writer.close();
        }
        catch(Exception e){}
        
    }
    
}
