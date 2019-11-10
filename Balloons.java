import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Balloons {
	
	public static class Ratio implements Comparable<Ratio>{
		//Member variables:
		
		//Need number of balloons needed by that team
		int balloonsNeeded;
		//Ratio
		double ratio;
		//Which room is farther
		//1 indicates a is farther, 2 indicates b is farther
		char closerRoom;
		
		//disncatne;
		int disA;
		int disB;
		
		//constructor
		public Ratio(int distanceToA, int distanceToB, int numBalloonsNeeded) {
		 this.balloonsNeeded = numBalloonsNeeded;
		 if(distanceToA > distanceToB) {
			 this.ratio = distanceToB / (double) distanceToA;
			 this.closerRoom = 'B';
		 }else {
			 this.ratio = distanceToA / (double) distanceToB;
			 this.closerRoom = 'A';
		 }
		 
		 this.disA = distanceToA;
		 this.disB = distanceToB;
		 
		}
		
		
		//Make them comparable
		public int compareTo(Ratio otherGuy) {
			if(this.ratio < otherGuy.ratio) {
				return -1;
			}else if(this.ratio > otherGuy.ratio) {
				return 1;
			}else {
				return 0;
			}
		}
		
	}
	

	
	
	public static int findMinDistance(ArrayList<Ratio> ratioArray, int balloonsInA, int balloonsInB) {
		int minDistance = 0;
		
		//sort the array by ratio
		Collections.sort(ratioArray);
		
		
		//move through the list
		for(int i = 0; i < ratioArray.size(); i++) {
			//what room they want
			if(ratioArray.get(i).closerRoom == 'A') {
				if(balloonsInA >= ratioArray.get(i).balloonsNeeded) {
					minDistance += ratioArray.get(i).balloonsNeeded * ratioArray.get(i).disA;
					balloonsInA -= ratioArray.get(i).balloonsNeeded;
				}else {
					minDistance += (ratioArray.get(i).balloonsNeeded - balloonsInA) * ratioArray.get(i).disB;
					balloonsInB -= ratioArray.get(i).balloonsNeeded - balloonsInA;
					minDistance += (balloonsInA) * ratioArray.get(i).disA;
					balloonsInA = 0;
				}
			}else {
				//b is the closer room
				

				if(balloonsInB >= ratioArray.get(i).balloonsNeeded) {
					minDistance += ratioArray.get(i).balloonsNeeded * ratioArray.get(i).disB;
					balloonsInB -= ratioArray.get(i).balloonsNeeded;
				}else {
					minDistance += (ratioArray.get(i).balloonsNeeded - balloonsInB) * ratioArray.get(i).disA;
					balloonsInA -= ratioArray.get(i).balloonsNeeded - balloonsInB;
					minDistance += (balloonsInB) * ratioArray.get(i).disB;
					balloonsInB = 0;
				}

			}
			
			
			
		}
		
		
		
		
		
		return minDistance;
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		//
		 String input; 
		 StringTokenizer idata;
		 
		//Enter data using BufferReader 
	    BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
		 
		 while((input =  reader.readLine()) != null) {
			 
			 
			 //get first digit
			 idata = new StringTokenizer(input);
			 int numTeams = Integer.parseInt(idata.nextToken());
			 
			 int balloonsInA = Integer.parseInt(idata.nextToken());
			 int balloonsInB = Integer.parseInt(idata.nextToken());
			 
			 //terminating condition
			 if(numTeams == 0 && balloonsInA == 0 && balloonsInB == 0) {
				 break;
			 }
			 
			 ArrayList<Ratio> rArray = new ArrayList<Ratio>();
			 
			 
			 //build the array
			 for(int i = 0; i < numTeams; i++) {
				 //read in next line:
				 input = reader.readLine();
				 
				 String[] tokens = input.split(" ");

				 rArray.add(new Ratio(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[0])));
				 
				 
			 }
				 
				 
				
				 //process array coordinate arrays and output
				int minDistance = findMinDistance(rArray, balloonsInA, balloonsInB);
				 System.out.printf("%d\n", minDistance);
				 
				 //get next line for numPoints
			 
			 
		 }
	
	
	}

}
