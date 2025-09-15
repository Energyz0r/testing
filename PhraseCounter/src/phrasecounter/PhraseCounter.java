/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package phrasecounter;

/**
 *
 * @author 44785
 */
public class PhraseCounter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        phraseCount();
        
    }
    
    public static void phraseCount() {
        String phrase = "The UK has been inhabited continuously since the Neolithic. In AD 43 the Roman conquest of Britain began. The Roman departure was followed by Anglo-Saxon settlement. In 1066 the Normans conquered England. With the end of the Wars of the Roses the Kingdom of England stabilised and began to grow in power, resulting by the 16th century in the annexation of Wales and the establishment of the British Empire. Over the course of the 17th century the role of the British monarchy was reduced, particularly as a result of the English Civil War.";
        char[] phraseChars = phrase.toCharArray();
        int countChars = 0;
        char[] matchingLetter = {'a', 'A'};
        
        System.out.println(phraseChars.length);
        
        for(int i = 0; i < phraseChars.length; i++) {
            //System.out.println(phraseChars[i]);
            for(int j = 0; j < matchingLetter.length; j++) {
               if(phraseChars[i] == matchingLetter[j]) {
               //if(phraseChars[i] == matchingLetter) {
                   countChars++;
               }
            }

        }
        
        System.out.println("Total (a) chars :" + countChars);
        
        
    
    }
    
}
