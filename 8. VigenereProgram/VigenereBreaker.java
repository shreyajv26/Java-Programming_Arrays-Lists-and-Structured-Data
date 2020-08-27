import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i+= totalSlices){
            sb.append(message.charAt(i));
        }
        String sb_final = sb.toString();
        //System.out.println(sb_final);
        return sb_final;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        //System.out.println(encrypted);
        for (int i=0;i<klength;i++)
        {
            String S1=sliceString(encrypted, i, klength);
            //System.out.println(S1);
            int key1=cc.getKey(S1);
            key[i]=key1;
            //System.out.println(key1);
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dict = new HashSet<String>();
        for(String s : fr.lines()){
            String line = s.toLowerCase();
            dict.add(line);
        }
        //System.out.println(dict);
        return dict;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        int length = message.length();
        String[] words = new String[length];
        words = message.split("\\W");
        for(int i = 0; i < words.length; i++){
            //System.out.println(words[i]);
            String word = words[i].toLowerCase();
            if(dictionary.contains(word)){
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage_old(String encrypted, HashSet<String> dictionary){
        int count = 0;
        String final_output = "";
        int[] final_key = new int[101];
        CaesarCracker cc = new CaesarCracker();
        char e = cc.mostCommon;
        for(int i = 1; i <= 101; i++){
            int[] key = tryKeyLength(encrypted,i,e);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int total = countWords(decrypted,dictionary);
            if(total > count){
                count = total;
                final_output = decrypted;
                final_key = key;
            }
        }
        for(int i = 0; i < final_key.length; i++){
            System.out.println(final_key[i]);
        }
        System.out.println("Length of key is: "+final_key.length);
        System.out.println("Count" +count);
        return final_output;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        String decrypto = "";
        int words = 0;
        int maxWord =0;
        int keyLength = 0;
        int[] final_key = new int[101];
        for(int i = 0; i<=100; i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            for(int j = 0; j<keys.length; j++){
                VigenereCipher vc  = new VigenereCipher(keys);
                String decrypted = vc.decrypt(encrypted);
                words= countWords(decrypted, dictionary);
                if(words > maxWord){
                    maxWord = words;
                    keyLength = keys.length;
                    decrypto = decrypted;
                    final_key = keys;
                }
            }
        }
        System.out.println(maxWord);
        System.out.println("Key length is: " +keyLength);
        System.out.println("Key is: ");
        for(int i = 0; i < final_key.length; i++){
            System.out.println(final_key[i]);
        }
        return decrypto;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> letterCounts = new HashMap<Character,Integer>();
        for(String word : dictionary){
            word = word.toLowerCase();
            for(char ch : word.toCharArray()) {
                if(!letterCounts.containsKey(ch)) {
                    letterCounts.put(ch, 1);
                }
                else {       
                    int freq = letterCounts.get(ch);
                    letterCounts.put(ch,freq +1);
                }
            }
        }
        char maxCh = highestChar(letterCounts);
        return maxCh;
    }
    
    private char highestChar(HashMap<Character, Integer> map){
        char maxCh ='\0';
        int maxLetter = 0;
        for(char ch : map.keySet()){
            int value = map.get(ch);
            if(maxLetter == 0){
                maxLetter = value;
                maxCh = ch;
            }
            else if(value > maxLetter){
                    maxLetter = value;
                    maxCh = ch;
                }
        }
        return maxCh;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        int countMaxWords = 0;//maxwords for al languages;
        int maxWords = 0;
        String decrypted = "";
        String language = "";
        for(String s : languages.keySet()){
            HashSet<String> dict = languages.get(s);
            String decrypto =  breakForLanguage(encrypted, dict);
            maxWords = countWords(decrypto, dict);//this will give you maxWord in breakForLanguage
            if(maxWords > countMaxWords){
                countMaxWords = maxWords;
                decrypted = decrypto;
                language = s;
            }
        }
        System.out.println(language);
        return decrypted;
    }
    
    public void breakVigenere_old () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource("messages/secretmessage2.txt");
        String encrypted = fr.asString();
        System.out.println(encrypted);
        CaesarCracker cc = new CaesarCracker();
        char e = cc.mostCommon;
        FileResource file = new FileResource();
        HashSet dictionary = readDictionary(file);
        //int[] keys = tryKeyLength(encrypted, 4, e);
        //VigenereCipher vv = new VigenereCipher(keys);
        //String decrypted = vv.decrypt(encrypted);
        String decrypted = breakForLanguage(encrypted,dictionary);
        System.out.println(decrypted);
    }
    
    public void breakVigenere() {
        FileResource fr = new FileResource();// remember call file given  here
        String encrypted = fr.asString();
        
        String[] langDict = new String[]{"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
        HashMap<String,HashSet<String>> myMap = new HashMap<String,HashSet<String>>();
        for(int i = 0; i < langDict.length; i++){
            String lang = langDict[i];
            FileResource fri = new FileResource("dictionaries/"+lang); // fri = fr1, fr2,fr3
            HashSet<String> dicWordi = readDictionary(fri);
            myMap.put(lang,dicWordi);
        }
        String decrypted = breakForAllLangs(encrypted, myMap);
        System.out.println(decrypted);
    }
    
}
