import java.util.ArrayList;
import java.util.List;

public class PalindromeService {

    /** Find palindromes in list words
       from view panel using isPalindrome method
      Return list of palindromes
    */
    public List<String> getPalindrome(List<String> words) {
        List<String> palindromes = new ArrayList<>();
        for (String s : words) {
            if (isPalindrome(s)) {
                palindromes.add(s);
            }
        }
        return palindromes;
    }


    private boolean isPalindrome(String str) {
        if (str == null) return false;
        String word = str.toLowerCase();
        if (word.length() == 1)
            return true;
        for (int i = 0; i < str.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i))
                return false;
        }
        return true;
    }
}
