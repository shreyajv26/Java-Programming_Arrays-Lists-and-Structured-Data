
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class tester {
    private int[] rome =  {17, 14, 12, 4};
    
    public void test_caesarCipher(){
        CaesarCipher cc = new CaesarCipher(1);
        FileResource fr = new FileResource();
        String message=fr.asString();
        String encrypted_msg = cc.encrypt(message);
        System.out.println("Encrypted message is: ");
        System.out.println(encrypted_msg);
        
        String decrypted_msg = cc.decrypt(encrypted_msg);
        System.out.println("Decrypted message is: ");
        System.out.println(decrypted_msg);
    }
    
    public void test_CaesarCracker(){
        CaesarCracker ccr = new CaesarCracker('a');
        FileResource fr = new FileResource();
        String message=fr.asString();
        int key = ccr.getKey(message);
        System.out.println("Key is: ");
        System.out.println(key);
    }
    
    public void test_VigenereCipher(){
        VigenereCipher vc = new VigenereCipher(rome);
        FileResource fr = new FileResource();
        String message=fr.asString();
        String encrypted_msg = vc.encrypt(message);
        System.out.println("Encrypted message is: ");
        System.out.println(encrypted_msg);
        
        String decrypted_msg = vc.decrypt(encrypted_msg);
        System.out.println("Decrypted message is: ");
        System.out.println(decrypted_msg);
    }
    
    public void test_VigenereBreaker(){
        VigenereBreaker vb = new VigenereBreaker();
        String output = vb.sliceString("abcdefghijklm", 0, 3);
        System.out.println("Sliced string is: "+output);
        
        output = vb.sliceString("abcdefghijklm", 1, 3);
        System.out.println("Sliced string is: "+output);
        
        output = vb.sliceString("abcdefghijklm", 2, 3);
        System.out.println("Sliced string is: "+output);
        
        output = vb.sliceString("abcdefghijklm", 0, 4);
        System.out.println("Sliced string is: "+output);
        
        output = vb.sliceString("abcdefghijklm", 1, 4);
        System.out.println("Sliced string is: "+output);
        
        output = vb.sliceString("abcdefghijklm", 2, 4);
        System.out.println("Sliced string is: "+output);
        
        output = vb.sliceString("abcdefghijklm", 3, 4);
        System.out.println("Sliced string is: "+output);
        
        output = vb.sliceString("abcdefghijklm", 0, 5);
        System.out.println("Sliced string is: "+output);
        
        output = vb.sliceString("abcdefghijklm", 1, 5);
        System.out.println("Sliced string is: "+output);
        
        output = vb.sliceString("abcdefghijklm", 2, 5);
        System.out.println("Sliced string is: "+output);
        
        output = vb.sliceString("abcdefghijklm", 3, 5);
        System.out.println("Sliced string is: "+output);
        
        output = vb.sliceString("abcdefghijklm", 4, 5);
        System.out.println("Sliced string is: "+output);
        
        FileResource fr = new FileResource();
        String message=fr.asString();
        int[] key = vb.tryKeyLength(message,4,'e');
        for(int i = 0; i < key.length; i++){
            System.out.println(key[i]);
        }
    }
    
    public void test_unknownKeyLength(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        HashSet<String> dict = vb.readDictionary(fr);
        
        String message = "undermining seminoles zulus collocate flashier tester shreya";
        int count = vb.countWords(message,dict);
        System.out.println("Max words in dict are: "+count);
        
    }
    
    public void test_breakForLanguage(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String message=fr.asString();
        HashSet<String> dict = vb.readDictionary(fr);
        String output = vb.breakForLanguage(message,dict);
        System.out.println(output);
    }
}
