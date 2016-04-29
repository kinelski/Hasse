/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forster;

import java.util.ArrayList;

/**
 *
 * @author Gabriela
 */
public class Hasse {
    
    private int edges[][];
    private int topological[];
    private ArrayList<Integer> subgroups;

    public Hasse(ArrayList<Integer> a){
        subgroups = a;
        edges = new int[a.size()][a.size()];
        topological = new int[a.size()];
    }
    
    public boolean isContained(int sub1, int sub2){
        if ( (sub1&sub2) == sub2)
            return true;
        else
            return false;
    }
    
    public int[][] edgeMatrix(){
        
        for (int i=0; i < subgroups.size(); i++){
            for (int j=0; j < subgroups.size(); j++)
                edges[i][j]=0;
        }
        
        for (int i=0; i < subgroups.size(); i++){
            for (int j=0; j < subgroups.size(); j++){
                if (isContained(subgroups.get(i),subgroups.get(j)) && i!=j)
                    edges[i][j]=1;
            }
        }
        
        return edges;
    }
    
    /*public int[] topologicalSort(){
        
    }*/
    
    public int[][] hasseMatrix(){
        return edges;
    }
    
}
