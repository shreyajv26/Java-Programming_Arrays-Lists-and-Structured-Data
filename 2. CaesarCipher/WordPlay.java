
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        char ch_lower = Character.toLowerCase(ch);
        if(ch_lower == 'a' || ch_lower == 'e' || ch_lower == 'i' || ch_lower == 'o' || ch_lower == 'u'){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder encrypted = new StringBuilder(phrase);
        for(int i = 0; i < encrypted.length(); i++){
            char ch1 = encrypted.charAt(i);
            boolean status = isVowel(ch1);
            if(status == true){
                encrypted.setCharAt(i,ch);
            }
        }
        return encrypted.toString();
    }
    
    public String emphasize(String phrase, char ch){
    StringBuilder encrypted = new StringBuilder(phrase);
        for(int i = 0; i < encrypted.length(); i++){
            char ch1 = encrypted.charAt(i);
            char ch_lower = Character.toLowerCase(ch1);
            if(ch_lower == ch){
                if(i == 0 || i%2 == 0){
                    encrypted.setCharAt(i,'*');
                }
                else{
                encrypted.setCharAt(i,'+');
                }
            }
        }
        return encrypted.toString();
    }
    
    public void testisVowel(){
    char ch = 'e';
    boolean test1 = isVowel(ch);
    System.out.println("Charecter " + ch + "is  " + test1);
    
    ch = 'I';
    test1 = isVowel(ch);
    System.out.println("Charecter " + ch + "is  " + test1);
    
    ch = 'F';
    test1 = isVowel(ch);
    System.out.println("Charecter " + ch + "is  " + test1);
    }
    
    public void testreplaceVowels(){
        String test = "Hello World";
        String encrypted = replaceVowels(test, '*');
        System.out.println(encrypted);
    }
    
    public void testemphasize(){
        String encrypted = emphasize("dna ctgaaactga", 'a');
        System.out.println(encrypted);
        
        String encrypted1 = emphasize("Mary Bella Abracadabra", 'a');
        System.out.println(encrypted1);
    }
}
