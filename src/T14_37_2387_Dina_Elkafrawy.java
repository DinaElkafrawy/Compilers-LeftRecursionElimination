import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Actual name of the class is LRE
public class T14_37_2387_Dina_Elkafrawy {
	List<String> allRules = new ArrayList<String>();
	List<String> variables = new ArrayList<String>();
	List<String> newRules = new ArrayList<String>();
	
	ArrayList<String> alpha  = new ArrayList<String>();
	ArrayList<String> beta = new ArrayList<String>();
	ArrayList<String> changedVariables = new ArrayList<String>();
	ArrayList<String> previousVariables = new ArrayList<String>();
	ArrayList<String> variableToBeRemoved = new ArrayList<String>();
	ArrayList<String> finalChange = new ArrayList<String>();
	
	ArrayList<String> output = new ArrayList<String>();
	
	public T14_37_2387_Dina_Elkafrawy(String encoding) {
		String[] firstSplit = encoding.split(";");
		
		allRules = Arrays.asList(firstSplit);
		
		for(int i = 0; i < firstSplit.length; i++) {
			String[] secondSplit = firstSplit[i].split(",");
			
			variables.add(secondSplit[0]);
		}
		
		convert();
		
		for(int i = 0; i < alpha.size(); i++) {
			System.out.println("aphaa " + alpha.get(i));
		}
		for(int j = 0; j < beta.size(); j++) {
			System.out.println("beta " + beta.get(j));
		}
		
		String output = "";
		for(int jj = 0; jj < newRules.size(); jj++) {
			if(jj == newRules.size() - 1)
				output += newRules.get(jj);
			else
				output += newRules.get(jj) + ";";
		}

		System.out.println("Output : " + output);
	}
	
	public void convert() {
		boolean leftRecursion = false;
		String output = "";
		
		for(int i = 0; i < variables.size(); i++) {
			String currentRule = allRules.get(i);
			Boolean enterSecondLoop = true;
			
			String[] currentSplit = currentRule.split(",");

			if( i == 0 )
				enterSecondLoop = false;
			
			if(enterSecondLoop) {
				
				for(int j = 0; j < i; j++) {
					output = currentSplit[0];
					for(int y = 1; y < currentSplit.length; y++) {

						String o = replace(currentSplit[y], variables.get(j));
						
						if( !o.equals(""))
							output += "," + o;
						}
						
					currentSplit = output.split(",");
				}
				
				for(int k = 1; k < currentSplit.length; k++) {
					if(checkRecursion(currentSplit[k], variables.get(i)))
						leftRecursion = true;
				}
			}
			else {
				for(int k = 1; k < currentSplit.length; k++) {
					if(checkRecursion(currentSplit[k], variables.get(i)))
						leftRecursion = true;
				}
			}
			
			if(!enterSecondLoop && !leftRecursion){
				newRules.add(currentRule);
			}
			if(enterSecondLoop && !leftRecursion){
				newRules.add(output);
			}
			
			if(leftRecursion && !enterSecondLoop) {
				removeLeftRecursion(currentRule);
				leftRecursion = false;
			}
			else if(leftRecursion && enterSecondLoop) {
				removeLeftRecursion(output);
				leftRecursion = false;
			}
			else {
				previousVariables.add(currentSplit[0]);
			}
			
		}
	}
	
	public boolean checkRecursion(String current, String variable) {
		char firstCharacter = current.charAt(0);
		char currentVariable = variable.charAt(0);

		if(firstCharacter == currentVariable) 
			return true;
				
		else
			return false;
	}
	
	public void removeLeftRecursion(String current) {
		String[] currentSplit = current.split(",");
		
		for(int k = 1; k < currentSplit.length; k++) {
			if(checkRecursion(currentSplit[k], current)) 
				alpha.add(currentSplit[k].substring(1));
			else
				beta.add(currentSplit[k]);
		}
		
		String newCurrent = currentSplit[0];
		String newVariable = newCurrent + "'";
		String newRule = newVariable;
		
		for(int x = 0; x < beta.size(); x++) {
			newCurrent += "," + beta.get(x) + newVariable;
		}
		
		for(int y = 0; y < alpha.size(); y++) {
			 newRule += "," + alpha.get(y) + newVariable;
		}
		
		newRule += ",";
		previousVariables.add(currentSplit[0]);
		changedVariables.add(currentSplit[0]);
		newRules.add(newCurrent);
		newRules.add(newRule);
		
		alpha.clear();
		beta.clear();
	}
	
	public ArrayList<String> addAll(ArrayList<String> current){
		ArrayList<String> output = new ArrayList<String>();
		for(int i = 0; i < current.size(); i++) {
			output.add(current.get(i));
		}
		return output;
	}
	
	public String replace2(String var) {
		for(int i = 0; i < newRules.size(); i++) {
			String currentVar = newRules.get(i).split(",")[0];

			if(var.equals(currentVar)) {
				return newRules.get(i).substring(2);
			}
		}
		
		return "";
	}
	
	public String replace(String current, String var) {
		
		String currentReplacement = "";
		boolean firstComma = false;
		boolean firstComma2 = false;
		
		boolean noVariable = false;
		
		
		//for(int i =0; i < current.length(); i++) {
			if(!variables.contains(Character.toString(current.charAt(0))))
				noVariable = true;
			else
				noVariable = false;
		//}
		
		if(noVariable)
			return current;
		

		
		boolean noCurrentVariable = false;
		
		//for(int i =0; i < current.length(); i++) {
			if(Character.toString(current.charAt(0)).equals(var))
				noCurrentVariable = true;
			else
				noCurrentVariable = false;
		//}
		
		if(!noCurrentVariable)
			return current;
		

		for(int i = 0; i < current.length(); i++) {
			if(current.charAt(i) == var.charAt(0)) {
				String permutations = replace2(var);

				if(!firstComma) {
					currentReplacement += Integer.toString(i) + "," + permutations;
					firstComma = true;
				}
				else
					currentReplacement += ";" + Integer.toString(i) + "," + permutations;
			}
			output.add(Character.toString(current.charAt(i)));
		}
		
		String[] replacements = currentReplacement.split(";");
		String out = "";
		ArrayList<String> temp = new ArrayList<String>();
		
		for(int k = 0; k < replacements.length; k++) {
			String[] secondReplacement = replacements[k].split(",");
			
			//remove variable
			temp = addAll(output);

			if(secondReplacement[0] != ""){
				temp.remove(Integer.parseInt(secondReplacement[0]));
				
				for(int kk = 1; kk < secondReplacement.length; kk++) {
					int currentIndex = Integer.parseInt(secondReplacement[0]);
					//add replacements
					for(int xx = 0; xx < secondReplacement[kk].length(); xx++) {

						if(xx + 1 < secondReplacement[kk].length()  && Character.toString(secondReplacement[kk].charAt(xx + 1)).equals("'") ){
							String t = Character.toString(secondReplacement[kk].charAt(xx)) + Character.toString(secondReplacement[kk].charAt(xx + 1));
							temp.add(currentIndex, t);
							currentIndex++;
							xx++;
						}
						else {
							temp.add(currentIndex, Character.toString(secondReplacement[kk].charAt(xx)));
							currentIndex++;
						}
					}
					
					if(!firstComma2) {
						out = "";
						firstComma2 = true;
					}
						
					else
						out += ",";
					
					//output
					for(int j = 0; j < temp.size(); j++) {
						out += temp.get(j);
					}
					temp = addAll(output);
					temp.remove(Integer.parseInt(secondReplacement[0]));
					}	
				}
			}
		
		if(out.equals(""))
			return current;
		
		output.clear();
		return out;
	}
	
	
	public static void main(String[] args) {
		// No need to print output, it is printed in the constructor.
		new T14_37_2387_Dina_Elkafrawy("S,ScT,T;T,aSb,iaLb,i;L,SdL,S");

		// Other tests.
//		new LRE("S,SS+,SS*,a");
//		new LRE("A,BC,C;B,Bb,b;C,AC,a");
//		new LRE("A,0,T1;T,1,A0");
//		new LRE("S,Sab,cd");
//		new LRE("A,AB,a;B,Ca,Ab;C,BCA");

	}

}
