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
    private int topological[], indegree[], S[];
    private int firstS, lastS, lastT;
    private ArrayList<Integer> subgroups;

    public Hasse(ArrayList<Integer> a){
        subgroups = a;
        edges = new int[a.size()][a.size()];
        topological = new int[a.size()];
        lastT = 0;
        S = new int[a.size()];
        firstS = 0;
        lastS = 0;
        indegree = new int[a.size()];
    }
    
    public boolean isContained(int sub1, int sub2){
        if ( (sub1&sub2) == sub2)
            return true;
        else
            return false;
    }
    
    public int[][] edgeMatrix(){
        
        for (int i=0; i < subgroups.size(); i++){
            for (int j=0; j < subgroups.size(); j++){
                edges[i][j]=0;
                indegree[j]=0;
            }
        }
        
        for (int i=0; i < subgroups.size(); i++){
            for (int j=0; j < subgroups.size(); j++){
                if (isContained(subgroups.get(i),subgroups.get(j)) && i!=j){
                    edges[i][j]=1;
                    indegree[j]++;
                }
            }
        }
        
        return edges;
    }
    
    public int[] topologicalSort(){
        for (int i=0; i < subgroups.size(); i++){
            if (indegree[i]==0){
                S[lastS]=i;
                lastS++;
            }
        }
        
        while (firstS!=lastS){
            topological[lastT]=S[firstS];
            firstS++;
            lastT++;
            
            for (int j=0; j < subgroups.size(); j++){
                if (edges[topological[lastT]][j] == 1){
                    indegree[j]--;
                }
                if (edges[topological[lastT]][j]==0){
                    S[lastS]=j;
                    lastS++;
                }
            }
        }
        
        return topological;
    }
    
    public int[][] hasseMatrix(){
        for (int i=lastT-1; i >= 0; i--){
            int first = -1;
            
            for (int j=i-1; j >= 0; j--){
                if (first < 0){
                    if (edges[topological[i]][topological[j]] == 1)
                        first = j;
                } else {
                    if (edges[topological[first]][topological[j]] == 1)
                        edges[topological[i]][topological[j]] = 0;
                }
            }
        }
        
        return edges;
    }
    
}
