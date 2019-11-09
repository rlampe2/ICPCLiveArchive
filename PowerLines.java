import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem 4872
 * 
 * This program is for CSCE310 Programming Competition
 * Essentially, there are points in xy and we want the minimum amount of "cable" to connect them
 * 
 * So, build an adjacency matrix for each one. 
 * @author rjlam
 *
 */
public class PowerLines {
	
	
	

	/**
	 * This function generates an adjacency matrix for a set of cartesian points. 
	 * @param xCoord
	 * @param yCoord
	 * @param numPairs
	 * @return
	 */
	public static Double[][] generateAdjMatrix(Integer[] xCoord, Integer[] yCoord, int numPairs){
		Double[][] adjM = new Double[numPairs][numPairs];
		
		for(int i = 0; i < numPairs; i++) {
			for(int j = 0; j < numPairs; j++) { //could be more efficient considering it's symmetric
				if(i == j) {
					adjM[i][j] = 0.0;
				}else {
					//find the distances and treat it as the weight. 
					adjM[i][j] = Math.sqrt(  Math.pow((xCoord[i] - xCoord[j] ), 2) + Math.pow((yCoord[i] - yCoord[j]), 2) );
				}
				
			}
		}
		
		return adjM;
		
	}
	
	
	/**
	 * This method takes in an adjacency matrix of doubles and finds
	 * the minimum spanning tree
	 * @param graph
	 * @return
	 */
	public static double genMST(Double[][] graph) {
		
		//keep track of the total weight of our MST
		double totalWeight = 0;
		//Find number of vertices in the graph
		int numV = graph[0].length;
		//Find the number of edges in the graph
		int reqMSTEdges = numV - 1;
		
		//first, make sure 0s in matrix are set to tiny infinity!
		for(int i = 0; i < graph[0].length; i++) {
			for(int j = 0; j < graph[0].length; j++) {
				if(graph[i][j] == 0.0) {
					graph[i][j] = Double.MAX_VALUE;
				}
			}
		}
		
		
		
		
		//Create a list to hold the vertices currently in our MST
		List <Integer> V_T = new ArrayList<Integer>();
		//Create a list to hold the vertices not in our MST
		//Some would call this a compliment set of V_T
		//Note that we don't add at i = 0, because that vertex will be our starting 
		//vertex
		List <Integer> complimentV_T = new ArrayList<Integer>();
		for(int i = 1; i < numV; i++) {
			complimentV_T.add(i);
		}

		
		//Add in our starting vertex of 0;
		V_T.add(0);
		
		//These get arbitrarily set to -1 so that eclipse 
		//doesn't complain about initialization
		int i= -1;
		int j = -1;
		
		//System.out.printf("Vertices   Weight\n");
		
		for(int k = 0; k < reqMSTEdges; k++) {
			
			// FIND MIN EDGE
			double min = Double.MAX_VALUE;

			for(int v : V_T) {
				//for all the vertices that are in our tree, 
				//check all their corresponding edges and find the min, 
				//making sure it doesn't connect to 
				//a vertex in our list
				for(int v2 : complimentV_T) {
					if(graph[v][v2] < min) {
						min = graph[v][v2];
						//keep track of the v/v2 we end up using.
						i = v;
						j = v2;
					}
				}
				
			}
				
			//add to our total weight
				totalWeight += min;
				//now that v2 is connected to the MST, 
				//put it in the list, and take it out of the compliment
				V_T.add(j);
				complimentV_T.remove(Integer.valueOf(j)); //need this to remove the object, not at the index value 
	
		}
		
		
		return totalWeight;
	}
	
	
	 public static void main(String[] args) throws NumberFormatException, IOException {

		 String input; 
		 StringTokenizer idata;
		 
		//Enter data using BufferReader 
	        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
		 
		 while((input =  reader.readLine()) != null) {
			 idata = new StringTokenizer(input);
			 int numPoints = Integer.parseInt(idata.nextToken());
			 if(numPoints == 0) {
				 break;
			 }
			 
			 
				 //build arrays to hold them:
				 Integer[] xCoord = new Integer[numPoints];
				 Integer[] yCoord = new Integer[numPoints];
				 
				 //actually process the input
				 for(int i = 0; i < numPoints; i++) {
					 //get next line
					 input =  reader.readLine();

					 String[] tokens = input.split(" ");
					 int x = Integer.parseInt(tokens[0]);
					 int y = Integer.parseInt(tokens[1]);
					 xCoord[i] = x;
					 yCoord[i] = y;
				 }
				 
				 //process current coordinate arrays and output
				 Double[][] matrix = generateAdjMatrix(xCoord, yCoord, numPoints);
				 double minWeight = genMST(matrix);
				 System.out.printf("%.2f\n", minWeight);
				 
				 //get next line for numPoints
			 
			 
		 }
	
	}
	 
	}
