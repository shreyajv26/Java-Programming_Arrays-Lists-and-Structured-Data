
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
    private int [] countLetters(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        //message = "Xifqvximt tsdtlxzrx iijirvtl";
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
    
    public void simpleTests(){
        FileResource fr=new FileResource();
        String message=fr.asString();
        //CaesarCipher cc = new CaesarCipher(18);
        //String encrypted = cc.encrypt(message);
        //System.out.println(encrypted);
        //String decrypted = cc.decrypt(encrypted);
        //System.out.println(decrypted);
        
        breakCaesarCipher(message);
        
    }
    
    public void breakCaesarCipher(String input){
        int [] counts = countLetters(input);
        int maxDex = maxIndex(counts);
        int ekey = maxDex - 4;
        if (maxDex < 4) {
            ekey = 26 - (4-maxDex);
        }
        int dkey = 26 - ekey;
        System.out.println("Encryption Key is: " +ekey);
        System.out.println("Decryption Key is: " +dkey);
        
        CaesarCipher cc = new CaesarCipher(dkey);
        String decrypted = cc.decrypt(input);
        System.out.println(decrypted);
    }
}
