

import java.util.ArrayList;
import java.util.Collections;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
	
	ArrayList<Integer> input = new ArrayList<Integer>();
	
    public int solution(int[] A) {
    	int result = 0;
    	boolean found = true;
    	boolean allNegative = false;
    	
    	for(int x = 0; x < A.length; x++) {
    		input.add(A[x]);
    	}
    	
    	int maximum = Collections.max(input);
    	if( maximum < 0)
    		maximum *= -1;
    	
		for( int i = 1; i <= maximum + 1; i++) {  
	    	for(int j = 0; j < input.size(); j++) {
	    			if(input.contains(1))
	        			allNegative = false;
	    			else
	    				allNegative = true;
	    			
	    			if(input.contains(i)) {
	    				result++;
	        			found = true;
	        			break;
	    			}
	    			
	        		if(j == input.size() - 1 && allNegative)
	        			return 1;
	        		
	        		else if(j == input.size() - 1 && !found)
	        			return result;
	        		
	        		else if(j == input.size() - 1 && found)
	        			return result + 1;
	        		
	    		}
 
    	}
    	return result;
    }
    
    public static void main(String[] args) {
    	Solution s = new Solution();
    	int[] input = {1,2,3};
    	System.out.println(s.solution(input));
    }
}


