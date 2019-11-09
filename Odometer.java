import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Odometer {

	
	/**
	 * Returns amount need to add to string odometer to make it a palindrome
	 * @param odo
	 * @return
	 */
	public static int findPalindrome(String odo) {
		char[] odoC = odo.toCharArray();
		int numDigits = odoC.length;
		
		//int odoI = Integer.parseInt(odo);
		
		char[] odoUpper = new char[ (numDigits + 1)/2]; //dont want middle
	//	char[] odoLower = new char[numDigits/2];
		
		int j =0;
		for(int i = 0; i < (numDigits + 1)/2; i++) {
			odoUpper[i] = odoC[i];
		}

		//convert to ints and see which is bigger
		String temp = new String(odoUpper);
		int odoUInt = Integer.parseInt(temp);
		//int odoLInt = Integer.parseInt(odoLower.toString());
		
		//if the top is bigger
		//miror left onto right
		//if mirrored guy is return and done (return diff its closest palindrome)
		//else if the mirrored is smaller, increment middle two by one and return difference
		
		//Find the mirrored number 
		char[] mirror = new char [ numDigits];
		for(int i = 0; i < numDigits / 2; i++) {
			//make mirror to bottom
			mirror[i] = odoC[i];
			mirror[numDigits - 1 - i ] = odoC[i];
		}
		if(numDigits % 2 != 0) { //take care of middle guy for odd
			mirror[((numDigits + 1)/ 2 ) - 1] = odoC[((numDigits + 1) / 2) -1];
		}
		
		temp = new String(mirror);
		int mInt = Integer.parseInt(temp);
		temp = new String(odoC);

		int oInt = Integer.parseInt(temp);
		
		if(mInt >= oInt) {
			//know that we found smallest palindrome
			return mInt - oInt;
		}else {
			//need to increment the middle two or middle one (depending on num digits)
		//	if(numDigits % 2 == 0) { //it's even number of digits
					//need to add one to the upper, and then remirror it. 
				odoUInt += 1;
				//convert back to string
				odoUpper = Integer.toString(odoUInt).toCharArray();
				//pad in zeros as needed 
				int numTaken = odoUpper.length;
				int numNeeded = ((numDigits + 1 )/2) - numTaken;
				
				//pad on zeros 
				temp = new String(odoUpper);

				for(int i = 0; i < numNeeded; i++) {
					temp = "0" + temp;
				}
				odoUpper = temp.toCharArray();
				
				
				//make the new mirror:
				for(int i = 0; i < (numDigits + 1) / 2; i++) {
					mirror[i] = odoUpper[i];
					mirror[numDigits - 1 - i] = odoUpper[i];
				}
					//convert it back to a number
					temp = new String(mirror);
					mInt = Integer.parseInt(temp);
					return mInt - oInt;
				
		//	}else {
				//there are an odd number of digits 
					//shouldn't matter
				
		//	}
		}
		

	}
	
	 public static void main(String[] args) throws NumberFormatException, IOException{
		 String input; 
		 StringTokenizer idata;
		 
		//Enter data using BufferReader 
	    BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
	    
	    while((input =  reader.readLine()) != null) {
			 idata = new StringTokenizer(input);
			 if(Integer.parseInt(idata.nextToken()) == 0 && input.length() == 1) {
				 break;
			 }
			 
			 int diff = findPalindrome(input); //will this preserve newline?
		
			 System.out.printf("%d\n", diff);
	 
	    }
	 }
}
