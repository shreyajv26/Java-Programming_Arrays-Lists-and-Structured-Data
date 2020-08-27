
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    void countWordLengths(FileResource resource, int[] counts){
        int k = 0;
        int index = 0;
        for(String word : resource.words()){
            int lengthofWord = word.length();
            if(lengthofWord == 1){
                char single = word.charAt(0);
                if(Character.isAlphabetic(single)){
                    lengthofWord = 1;
                }
                else{
                    lengthofWord = 0;
                }
            }
            //System.out.println(lengthofWord);
            if(lengthofWord > 1){
                char firstChar = word.charAt(0);
                char lastChar = word.charAt(lengthofWord - 1);
                if (! Character.isAlphabetic(firstChar)) {
                    lengthofWord--;
                }
                        
                if (! Character.isAlphabetic(lastChar)) { 
                    lengthofWord--;
                }
                        
                if (lengthofWord > counts.length) {
                    lengthofWord = counts.length;
                }
                        
                counts[lengthofWord] = counts[lengthofWord] + 1;
            }
        }
        
        for(k = 0; k < counts.length; k++){
            if(counts[k] == 0){
                continue;
            }
            else{
                    System.out.println(counts[k] + " words with " + k + " characters.");
            }
        }
    }
    
    int indexOfMax(int [] values){
    int index = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i] > values[index]){
                index = i;
            }
        }
    return index;
    }
    
    void testCountWordLengths(){
        String [] plays = {"manywords.txt"};
    int [] counts = new int[30];
    for(int k=0; k < plays.length; k++){
        FileResource resource = new FileResource("./data/" + plays[k]);
        countWordLengths(resource,counts);
        System.out.println("done with " + plays[k]);
    }
    int index = indexOfMax(counts);
        System.out.println(index);
    }
    
    void testindexOfMax(){
        int [] counts = {1,2,3,4,5,6,3,2,1};
        int index = indexOfMax(counts);
        System.out.println(index);
    }
}
