package forster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Table{
    private char table[][];
    private char inv[];
    private int size;
    
    public Table (String filename) throws Exception {
        
        BufferedReader buffer = null;
        String input = "", line;
        
        size = 0;
        
        try {
            buffer = new BufferedReader (new FileReader(filename));
            
            line = buffer.readLine();
            while (line != null){
                input = input + line;
                line = buffer.readLine();
                size++;
            }
            
            table = new char[size][size];
            inv = new char[size];
            
            for (int i=0; i<size; i++)
                inv[i] = '0';
            
            for (int i=0, cont=0; i<size; i++)
                for (int j=0; j<size; j++){
                    table[i][j] = input.charAt(cont++);
                    
                    if (table[i][j] == '1')
                        inv[i] = intToChar(j);
                }
            
            //Inverso
            for (int i=0; i<size; i++)
                if (inv[i] == '0'){
                    System.out.println ("Entrada invalida. Nao satisfaz as propriedades de grupo.");
                    try{
                        if (buffer != null) buffer.close();
                    }
                    catch (Exception e){}
                    
                    throw new Exception();
                }
            
            //Elemento nulo
            for (int i=0; i<size; i++)
                if (op('1', intToChar(i)) != intToChar(i)){
                    System.out.println ("Entrada invalida. Nao satisfaz as propriedades de grupo.");
                    try{
                        if (buffer != null) buffer.close();
                    }
                    catch (Exception e){}
                    
                    throw new Exception();
                }
            
            //Fechamento
            for (int i=0; i<size; i++)
                for (int j=0; j<size; j++)
                    if (charToInt(op(intToChar(i), intToChar(j))) >= size){
                        System.out.println ("Entrada invalida. Nao satisfaz as propriedades de grupo.");
                        try{
                            if (buffer != null) buffer.close();
                        }
                        catch (Exception e){}
                    
                        throw new Exception();
                    }
            
            //Transitividade
            for (int i=0; i<size; i++)
                for (int j=0; j<size; j++)
                    for (int k=0; k<size; k++){
                        char left = op( op(intToChar(i), intToChar(j)), intToChar(k) );
                        char right = op( intToChar(i), op(intToChar(j), intToChar(k)) );
                        
                        if (left != right){
                            System.out.println("i = " + i);
                            System.out.println("j = " + j);
                            System.out.println("k = " + k);
                            System.out.println ("Entrada invalida. Nao satisfaz as propriedades de grupo.");
                            try{
                                if (buffer != null) buffer.close();
                            }
                            catch (Exception e){}
                    
                            throw new Exception();
                        }
                    }
                    
        }
        
        catch (Exception e){
            e.printStackTrace();
        }
        
        finally {
            try{
                if (buffer != null) buffer.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public int charToInt (char c){
        if (c == '1') return 0;
        else return (int)(c - 'a' + 1);
    }
    
    public char intToChar (int i){
        if (i == 0) return '1';
        else return (char)(i + 'a' - 1);
    }
    
    public char[] getSubgroupElements (int sub){
        char[] el = new char[size+1];
        int cont = 0, bit = 0;
        
        while (sub > 0){
            if (sub%2 == 1)
                el[cont++] = intToChar(bit);
            
            sub /= 2;
            bit++;
        }
        el[cont] = '0'; //Indica fim do array
        
        return el;
    }
    
    //TALVEZ MUDAR ORDEM DAS DIMENSOES
    public char op (char i, char j){
        return table[charToInt(i)][charToInt(j)];
    }
    
    public boolean isInSubgroup (char c, int sub){
        int toInt;
        
        toInt = charToInt(c);
        return (sub >> toInt)%2 == 1;
    }
    
    public boolean isSubgroup (int sub){
        char[] el;
        
        //Elemento neutro
        if (!isInSubgroup('1', sub))
            return false;
        
        el = getSubgroupElements(sub);
        
        //Inverso
        for (int i=0; el[i]!='0'; i++)
            if (!isInSubgroup(inv[charToInt(el[i])], sub))
                return false;
        
        //Fechamento
        for (int i=0; el[i]!='0'; i++)
            for (int j=0; el[j]!='0'; j++)
                if (!isInSubgroup(op(el[i], el[j]), sub))
                    return false;
        
        System.out.print ("EL:");
        for (int i=0; el[i]!='0'; i++)
            System.out.print (" " + el[i]);
        System.out.println();
        
        return true;
    }
    
    public ArrayList<Integer> getSubgroups(){
        int max = (1 << size);
        ArrayList<Integer> subgroups = new ArrayList<Integer>();
        
        for (int sub=0; sub<max; sub++)
            if (isSubgroup(sub))
                subgroups.add(sub);
        
        return subgroups;
    }
}