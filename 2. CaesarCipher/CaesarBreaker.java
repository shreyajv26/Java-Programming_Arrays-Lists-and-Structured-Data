
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int [] freqs = countLetters(encrypted);
        int maxDex = indexOfMax(freqs);
        //System.out.println("maxDex is " + maxDex);
        int dkey = maxDex - 4; // 4 is index of e
        if(maxDex < 4){
            dkey = 26 - (4 - maxDex); //wrap around 26 alphabets
        }
        //System.out.println("dkey is " + dkey);
        return cc.encrypt(encrypted,26-dkey);
    }
    
    public int [] countLetters(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        //message = "Xifqvximt tsdtlxzrx iijirvtl";
        int [] counts = new int[26];
        for(int k = 0; k < message.length(); k++){
            char ch = message.charAt(k);
            //System.out.println(ch);
            char ch_lower = Character.toLowerCase(ch);
            //System.out.println(ch_lower);
            int dex = alphabet.indexOf(ch_lower);
            if(dex != -1){
                counts[dex] +=1;
            }
        }
        return counts;
    }
    
    int indexOfMax(int [] values){
    int maxInd = 0;
    for (int k = 0; k<values.length; k++){
        if (values[k] > values[maxInd]){
            //System.out.println(values[k]);
            maxInd = k;
            //System.out.println("Max Ind: = " +maxInd);
        }
    }
    //System.out.println(maxInd);
    return maxInd;
    }
    
    public String halfOfString(String message, int start){
        String Part1 = "";
        
        for(int i = start; i < message.length(); i+=2){
                Part1 += message.charAt(i);
        }
        return Part1;
    }
    
    public int getKey(String s){
        //This function returns the key that was used to encrypt the message
        int [] counts = countLetters(s);
        int maxDex = indexOfMax(counts);
        //int mostCommonPos = 'e' - 'a';
        int ekey = maxDex - 4;
        if (maxDex < 4) {
            ekey = 26 - (4-maxDex);
        }
        return ekey;
    }
    
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String Part1 = halfOfString(encrypted,0);
        String Part2 = halfOfString(encrypted,1);
        int key1 = getKey(Part1);
        int dkey1 = 26 - key1;
        System.out.println("First Encryption Key is: " +key1);
        System.out.println("First Decryption Key is: " +dkey1);
        int key2 = getKey(Part2);
        int dkey2 = 26 - key2;
        System.out.println("Second Encryption Key is: " +key2);
        System.out.println("Second Decryption Key is: " +dkey2);
        return cc.encryptTwoKeys(encrypted,dkey1,dkey2);
        //return cc.encryptTwoKeys(encrypted,12,2);
    }
    
    public void testdecrypt(){
        String test = "First Legion";
        String output = decrypt(test);
        System.out.println(output);
    }
    
    public void testhalfOfString(){
    String output = halfOfString("Qbkm Zgis", 0);
    System.out.println(output);
    }
    
    public void testdecryptTwoKeys(){
        //String test = "Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. TTTT TTTTT!";
        //String test = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        //String output = decryptTwoKeys(test);
        //System.out.println(output);
        FileResource fr=new FileResource();
        String message=fr.asString();
        String decrypted=decryptTwoKeys(message);
        System.out.println("The decrypted message is:\n"+decrypted);
    }
}
