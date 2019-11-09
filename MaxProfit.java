import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//So fun fact Robyn is a genius
/**
 * Problem 4869
 * 
 * This program is for CSCE310 Programming Competition
 * Essentially, this program finds the maximum profit of an array of days 
 * 
 * So, build an adjacency matrix for each one. 
 * @author rjlam
 *
 */
public class MaxProfit {
	//also so is Dareeeeen
	
	public static int findMaxProfit(Integer[] inputValues) {
		
		//create f array
		Integer[] max = new Integer[inputValues.length];
		
		//base case, because we can't have 0 days 
		max[0] = inputValues[0];
		
		for(int i = 1; i < inputValues.length; i++) {
			max[i] = inputValues[i] > inputValues[i] + max[i - 1] ? inputValues[i] : inputValues[i] + max[i -1];
			
		}
		//iterate through results and see biggest:
		int maxVal = max[0];
		for(int i = 0; i < max.length; i++) {
			if(max[i] > maxVal) {
				maxVal = max[i];
			}
		}
		
		return maxVal;
		
	}
	
	 public static void main(String[] args) throws NumberFormatException, IOException{
		 String input; 
		 StringTokenizer idata;
		 
		//Enter data using BufferReader 
	    BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
	    
	    while((input =  reader.readLine()) != null) {
			 idata = new StringTokenizer(input);
			 int numDays = Integer.parseInt(idata.nextToken());
			 if(numDays == 0) {
				 break;
			 }
			 
			 Integer[] days = new Integer[numDays];
			 
			 for(int i = 0; i < numDays; i++) {
				 //get next line
				 input =  reader.readLine();

				 int profit = Integer.parseInt(input);
				 days[i] = profit;
			 }
			 
			 int maxProfit = findMaxProfit(days);
			 
			 System.out.printf("%d\n", maxProfit);
		
			 
			 
			 
			 
	    }
		
	 }
	
}
