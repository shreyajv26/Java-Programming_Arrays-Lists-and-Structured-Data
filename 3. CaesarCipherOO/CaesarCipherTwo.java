
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char currUpper = Character.toUpperCase(currChar);
            int idx = alphabet.indexOf(currUpper);
            
            if(idx != -1 && (i%2 == 0)){
                char newChar = shiftedAlphabet1.charAt(idx);
                if(Character.isLowerCase(currChar)){
                    newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }
                else{
                    encrypted.setCharAt(i, newChar);
                }
            }
            else if(idx != -1 && (i%2 == 1)){
                char newChar = shiftedAlphabet2.charAt(idx);
                if(Character.isLowerCase(currChar)){
                    newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }
                else{
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc.encrypt(input);
    }
}
