import java.util.Scanner;

public class Solution {

  private static int length_originalMessage;
  private static int numberOfShifts;

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    length_originalMessage = scanner.nextInt();
    numberOfShifts = scanner.nextInt();
    char[] encodedMessage = scanner.next().toCharArray();
    scanner.close();
    
    System.out.println(decodeMessage(encodedMessage));
  }

/**
 * ----------------------------------------------------
 * Notes on method decodeMessage(char[] encodedMessage)
 * ----------------------------------------------------
 *
 * SAMPLE:
 * original message => 1 1 0 0 1
 * shift          => 0 1 1 0 0 1
 * shift 			    => 0 0 1 1 0 0 1
 * shift     		  => 0 0 0 1 1 0 0 1
 * shift     		  => 0 0 0 0 1 1 0 0 1 							
 * ---------------------------------------------------------------
 * coded message    => 1 0 0 0 1 0 1 1 1
 *
 * If A ^ B = C, then from the associative and self-inversive properties of XOR, 
 * A ^ (B ^ B) = C ^ B and A = C ^ B. 
 *
 * Thus, originalMessage[i] = xor_otherCharsInColumn ^ encodedMessage[i].
 * The formula for calculating xor_otherCharsInColumn is dependend on whether 
 * the current column is greater than/equal to, or less than, the total number of shifts.
 *
 * @return An array of chars, containing the original message.
 */
  private static char[] decodeMessage(char[] encodedMessage) {
  
    char[] originalMessage = new char[length_originalMessage];
    int xor_otherCharsInColumn = 0;

    for (int i = 0; i < length_originalMessage; i++) {
      originalMessage[i] = (char) ((xor_otherCharsInColumn ^ (encodedMessage[i] - '0') + '0'));
      xor_otherCharsInColumn ^= (originalMessage[i] - '0');

      if (i >= numberOfShifts - 1) {
        xor_otherCharsInColumn ^= (originalMessage[i - numberOfShifts + 1] - '0');
      }
    }
    return originalMessage;
  }
}
