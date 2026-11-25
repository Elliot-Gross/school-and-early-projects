import java.util.Scanner;

public class PlayfairCipher {
	
	String base;
	String tableKey;
	String tableString;
	
	/**
	 * PlayfairCipher - Constructor Method
	 * @param key - String used to generate encryption/decription table
	 */
	public PlayfairCipher(String tableKey) {
		this.tableKey = tableKey;
		this.base = "abcdefghiklmnopqrstuvwxyz";
		
		this.tableString = getCleanTableString();
	}
	
	/**
	 * main - This is the main method which puts all the other method to use
	 * @param args
	 * @return Nothing
	 */
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
			
		System.out.print("Table Key: ");
		String tableKey = in.nextLine();
		
		PlayfairCipher cipher = new PlayfairCipher(tableKey);
		
		System.out.println("Table (String): "+ cipher.tableString + "\n");
		
		System.out.print("To Encrypt: ");
		String toEncrypt = in.nextLine();
		String encryptedString = cipher.encryptMessage(toEncrypt);
		System.out.println("Encrypted String: " + encryptedString + "\n");
		
		
		System.out.print("To Decrypt: ");
		String toDecrypt = in.nextLine();
		String decryptedString = cipher.decryptMessage(toDecrypt);
		System.out.println("Decrypted String: " + decryptedString);

	}
	
	/**
	 * encryptMessage - This method, given a message, uses the string of the table used for encryption, and returns the 
	 * string of the encrypted message
	 * @param message - String to be encrypted
	 * @return encryptedString - A string of the encrypted message
	 */
	public String encryptMessage(String message) {
		String encryptedString = "";
		
		message = cleanMessage(message);
		int amountOfPairs = (message.length()/2) + message.length()%2;
	
		for(int i=0; i<amountOfPairs; i++) {
			
			String pair = getPair(i*2, message);
	
			char a = pair.charAt(0);
			char b = pair.charAt(1);
	
			encryptedString += this.encryptPair(a, b);
	
		}
		
		return encryptedString;
		
	}

	/**
	 * decryptMessage - This method, given, a message, uses the string of the table used for encryption, and returns the 
	 * string of the decrypted message
	 * @param message - String to be decrypted
	 * @return decryptedString - A string of the decrypted message
	 */
	public String decryptMessage(String message) {
		String encryptedString = "";
		
		message = cleanMessage(message);
		int amountOfPairs = (message.length()/2) + message.length()%2;
	
		for(int i=0; i<amountOfPairs; i++) {
			
			String pair = getPair(i*2, message);
	
			char a = pair.charAt(0);
			char b = pair.charAt(1);
	
			encryptedString += this.decryptPair(a, b);
	
		}
		
		return encryptedString;
		
	}

	/**
	 * cleanMessage - This method, given a string, returns the string with all spaces removed and all 
	 * "j"s replaced with "i"s.
	 * @param message - String to be cleaned
	 * @return - A cleaned string of message
	 */
	private String cleanMessage(String message) {
		return message.replaceAll(" ", "").replaceAll("j", "i"); //Removes all spaces and replaces "j" with "i"
	}

	/**
	 * getEncryptedIndicies - This method, given two indicies, finds the new indicies using the rules for the playfair cipher
	 * @param indexA - Index used to find new index
	 * @param indexB - Index used to find new Index
	 * @return int[] indicies - An array of the new indicies
	 */
	private int[] getEncryptedIndicies(int indexA, int indexB) {
		int newIndexA = (indexA - (indexA%5)) + (indexB%5); //Finds the 'row' it's in and the 'column' of B
		int newIndexB = (indexB - (indexB%5)) + (indexA%5); //Finds the 'row' it's in and the 'column' of A
		 
		if(indexA%5 == indexB%5) { //Same Column
			newIndexA = (indexA+5)%25; //Adds five to the original index and accounts for wrapping
			newIndexB = (indexB+5)%25; //Adds five to the original index and accounts for wrapping
			
		} else if(Math.floor(indexA/5) == Math.floor(indexB/5)) { //Same Row
			newIndexA = (indexA - indexA%5) + ((indexA%5+1)%5); //Finds the next index and accounts for wrapping
			newIndexB = (indexB - indexB%5) + ((indexB%5+1)%5); //Finds the next index and accounts for wrapping
		}
	
		int[] indicies = {newIndexA, newIndexB};
		return indicies;
		
	}

	/**
	 * getDecryptedIndicies - This method, given two indicies, finds the new indicies using the rules for the playfair cipher
	 * @param indexA - Index used to find new index
	 * @param indexB - Index used to find new Index
	 * @return int[] indicies - An array of the new indicies
	 */
	private int[] getDecryptedIndicies(int indexA, int indexB) {
		int newIndexA = (indexA - (indexA%5)) + (indexB%5); //Finds the 'row' it's in and the 'column' of B
		int newIndexB = (indexB - (indexB%5)) + (indexA%5); //Finds the 'row' it's in and the 'column' of A
		 
		if(indexA%5 == indexB%5) { //Same Column
			newIndexA = ((((indexA-5)%25)+25)%25); //Subtracts five to the original index and accounts for wrapping
			newIndexB = ((((indexB-5)%25)+25)%25); //Subtracts five to the original index and accounts for wrapping
			
		} else if(Math.floor(indexA/5) == Math.floor(indexB/5)) { //Same Row
			newIndexA = (indexA - indexA%5) + (((indexA%5-1)%5)+5)%5; //Finds the previous index and accounts for wrapping
			newIndexB = (indexB - indexB%5) + (((indexB%5-1)%5)+5)%5; //Finds the previous index and accounts for wrapping
		}

		int[] indicies = {newIndexA, newIndexB};
		return indicies;
		
	}
	
	/**
	 * encryptPair - This method, given two chars, uses the string of the table used for encryption, and returns the encrypted string
	 * of the two given chars.
	 * @param char a - char to be encrypted
	 * @param char b - char to be encrypted
	 * @return A string value - A string of the encrypted chars
	 */
	private String encryptPair(char a, char b) {
		int indexA = this.tableString.indexOf(a);
		int indexB = this.tableString.indexOf(b);
		
		int[] indicies = getEncryptedIndicies(indexA, indexB);
		
		String newA = Character.toString(this.tableString.charAt(indicies[0]));
		String newB = Character.toString(this.tableString.charAt(indicies[1]));
		
		return newA+newB;
	
	}

	/**
	 * decryptPair - This method, given two chars, uses the string of the table used for encryption, and returns the decrypted string
	 * of the two given chars.
	 * @param char a - char to be decrypted
	 * @param char b - char to be decrypted
	 * @return A string value - A string of the decrypted chars
	 */
	private String decryptPair(char a, char b) {
		int indexA = this.tableString.indexOf(a);
		int indexB = this.tableString.indexOf(b);
		
		int[] indicies = getDecryptedIndicies(indexA, indexB);
		
		String newA = Character.toString(this.tableString.charAt(indicies[0]));
		String newB = Character.toString(this.tableString.charAt(indicies[1]));
		
		return newA+newB;

	}
	
	/**
	 * getPair - This method, given an index and string, returns a string of the consecutive pair, starting from the given index, 
	 * and fixes the pair according to the playfair ciphers rules.
	 * @param currentIndex - Index to start pair
	 * @param toEncrypt - String to get pair from
	 * @return a+b, a String of the prepared pair
	 */
	private String getPair(int currentIndex, String toEncrypt) {
		String a = Character.toString(toEncrypt.charAt(currentIndex));
		String b = "x";
		
		if(currentIndex+1 < toEncrypt.length())  // Checks if there is another letter 
			if(!a.equals(Character.toString(toEncrypt.charAt(currentIndex+1)))) { // Checks if a equals the next letter
				b = Character.toString(toEncrypt.charAt(currentIndex+1)); // Sets b equal to the next letter
			}

		
		return a+b;
	}
	
	/**
	 * getCleanTableString - This method, using the class's key and base, returns a cleaned table used for encryption
	 * and decryption following the playfair cipher rules
	 * @return A string value - The cleaned table key plus the base
	 */
	private String getCleanTableString() {
		tableKey = tableKey.replaceAll(" ", ""); //Removes Spaces
		tableKey = tableKey.replaceAll("j", "i"); 
		
		return removeDuplicateLetters(tableKey+base);
				 
	}
	
	/**
	 * removeDuplicateLetters - This method returns the given string with duplicate letters removed
	 * @param s - String to remove letters
	 * @return str - The given string, s, but with duplicate letters removed
	 */
	private String removeDuplicateLetters(String s) { 
        String str = new String(); 

        for (int i = 0; i < s.length(); i++) { 
            char c = s.charAt(i); 
            if (str.indexOf(c) < 0) { //Checks if c exists in the reconstructed string
                str += c; 
            } 
        } 
        return str; 
    } 
	
	
	

}
