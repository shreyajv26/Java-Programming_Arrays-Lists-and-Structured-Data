import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char currUpper = Character.toUpperCase(currChar);
            
            int idx = alphabet.indexOf(currUpper);
            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
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
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet_key1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabet_key2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char currUpper = Character.toUpperCase(currChar);
            
            int idx = alphabet.indexOf(currUpper);
            //System.out.println(idx);
            if(idx != -1 && (i%2 == 0)){
                char newChar = shiftedAlphabet_key1.charAt(idx);
                //System.out.println(newChar);
                
                if(Character.isLowerCase(currChar)){
                    newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }
                else{
                    encrypted.setCharAt(i, newChar);
                }
            }
            else if(idx != -1 && (i%2 == 1)){
                char newChar = shiftedAlphabet_key2.charAt(idx);
                //System.out.println(newChar);
                
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
    
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    
    public void testencrypt(){
        String encrypted = encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15);
        System.out.println(encrypted);
        
        String encrypted1 = encrypt("First Legion", 23);
        System.out.println(encrypted1);
    }
    
    public void testencryptTwoKeys(){
        //String encrypted = encryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko", 22, 19);
        //System.out.println(encrypted);
        //FileResource fr=new FileResource();
        //String message=fr.asString();
        String decrypted=encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8);
        System.out.println(decrypted);
    }
}

