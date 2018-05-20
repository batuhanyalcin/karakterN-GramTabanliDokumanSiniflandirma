package BitterSweet;

import java.util.*;

public class nGram {

    // Pieces of fileText for the N-Gram
    public static HashMap calculateNGram(String fileText,int counter){
        HashMap<String, Integer> hmap = new HashMap<String, Integer>();



        for (int i = 0;i<(fileText.length()-counter);i++){
            for (int j = 0; j<counter;j++){
                //total += (fileText.substring(i,i+counter) +", ");
                if (hmap.get(fileText.substring(i,i+counter))==null){
                    hmap.put(fileText.substring(i,i+counter),1);
                    //System.out.println(hmap.get(fileText.substring(i,i+counter)));

                }else{
                    hmap.put(fileText.substring(i,i+counter),hmap.get(fileText.substring(i,i+counter))+1);
                    //System.out.println(hmap.get(fileText.substring(i,i+counter)));
                }
                //System.out.println(fileText.substring(i,j);



            }
        }


        //return total.toString().substring(0,(total.length()-2));
        return hmap;
    }
}
