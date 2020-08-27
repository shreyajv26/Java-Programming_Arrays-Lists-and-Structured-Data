
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    private int [] countLetters(String message){
        //Helper method. Hence private
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int [] counts = new int[26];
        for(int k = 0; k < message.length(); k++){
            char ch = message.charAt(k);
            char ch_lower = Character.toLowerCase(ch);
            int dex = alphabet.indexOf(ch_lower);
            if(dex != -1){
                counts[dex] +=1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int [] values){
        int maxInd = 0;
        for (int k = 0; k<values.length; k++){
            if (values[k] > values[maxInd]){
                maxInd = k;
            }
        }
        return maxInd;
    }
    
    public String halfOfString(String message, int start){
        String Part1 = "";
        for(int i = start; i < message.length(); i+=2){
                Part1 += message.charAt(i);
        }
        return Part1;
    }
    
    public void simpleTests(){
        FileResource fr=new FileResource();
        String message=fr.asString();
        //CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        //String encrypted = cc.encrypt(message);
        //System.out.println(encrypted);
        //String decrypted = cc.decrypt(encrypted);
        //System.out.println(decrypted);
        breakCaesarCipher(message);
        
    }
    
    public int getKey(String s){
        //This function returns the key that was used to encrypt the message
        int [] counts = countLetters(s);
        int maxDex = maxIndex(counts);
        //int mostCommonPos = 'e' - 'a';
        int ekey = maxDex - 4;
        if (maxDex < 4) {
            ekey = 26 - (4-maxDex);
        }
        return ekey;
    }
    
    public void breakCaesarCipher(String input){
        String Part1 = halfOfString(input,0);
        String Part2 = halfOfString(input,1);
        
        int ekey1 = getKey(Part1);
        int dkey1 = 26 - ekey1;
        System.out.println("Encryption Key 1 is: " +ekey1);
        System.out.println("Decryption Key 1 is: " +dkey1);
        
        int ekey2 = getKey(Part2);
        int dkey2 = 26 - ekey2;
        System.out.println("Encryption Key 2 is: " +ekey2);
        System.out.println("Decryption Key 2 is: " +dkey2);
        
        CaesarCipherTwo cc = new CaesarCipherTwo(dkey1,dkey2);
        String decrypted = cc.decrypt(input);
        System.out.println(decrypted);
    }
}
