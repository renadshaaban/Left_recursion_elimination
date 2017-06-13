import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class main {
public static void main(String [] args) throws FileNotFoundException{
	String [] filesNames={"samplein.txt","sample2in.txt","sample3in.txt"};
	for(int z=0;z<filesNames.length;z++){
		Scanner scanner = new Scanner(new File("/Users/renadibrahim/Documents/GUC/Semester10/CompilerLab/Task4/"+filesNames[z]));
		//String test="rexprUrterm!rterm";
		ArrayList <rule> rules=new ArrayList <rule>();
		ArrayList <String> variables=new ArrayList <String>();
		ArrayList <rule> primes=new ArrayList<rule>();
		while (scanner.hasNextLine()) {
			rule r=new rule();  
			String line = scanner.nextLine();
			r.head=line;
			variables.add(line);
			line=scanner.nextLine();
			String[] lineSplitted=line.split("\\|");
			ArrayList <String> tail=new ArrayList<String>();
			for(int i=0;i<lineSplitted.length;i++)
				tail.add(lineSplitted[i]);
			r.tails=tail;
			rules.add(r);
		}
		
		for(int i=0;i<rules.size();i++){
			//loop on prevoius rules
			for(int j=0;j<i;j++){
				//loop on the production
				for(int k=0;k<rules.get(i).tails.size();k++){
					String AiProduction=rules.get(i).tails.get(k);
					if(AiProduction.startsWith(rules.get(j).head)){
						rules.get(i).is_recursive=true;
						rule AjRule=rules.get(j);
						String toBeConcatenated=rules.get(i).tails.get(k).substring(rules.get(j).head.length());
						rules.get(i).tails.remove(k);
						//loop on to be replaced production	
						for(int r=0;r<AjRule.tails.size();r++)
							rules.get(i).tails.add(AjRule.tails.get(r) + toBeConcatenated);
					}	
				}
			}
			//hena f loop law heya 
			ArrayList <String> newTail = new ArrayList <String>();
			rule prime=new rule();
			prime.head=rules.get(i).head+"'";
			for(int j=0;j<rules.get(i).tails.size();j++){
				if(rules.get(i).tails.get(j).startsWith(rules.get(i).head)){
					rules.get(i).is_recursive=true;
					String s=rules.get(i).tails.get(j).substring(rules.get(i).head.length());
					String s1=s+prime.head;		
					newTail.add(s1);
					rules.get(i).tails.remove(j);
				}
			}
			
			prime.tails=newTail;
			
			ArrayList <String> removed=new ArrayList<String>();
			if(rules.get(i).is_recursive){
				for(int j=0;j<rules.get(i).tails.size();j++)
					removed.add(rules.get(i).tails.get(j)+prime.head);
				rules.get(i).tails=removed;
			}
			
			
			if(prime.tails.size()>=1)
				prime.tails.add("!");
			primes.add(prime);
		}
		
		try{
		    PrintWriter writer = new PrintWriter("Output"+ z, "UTF-8");
		    
		    
		    
		    for(int i=0;i<rules.size();i++)
		    	writer.println(rules.get(i).head+"-->"+rules.get(i).tails);
			
			for(int i=0;i<primes.size();i++){
				if(primes.get(i).tails.size()!=0)
					writer.println(primes.get(i).head+"-->"+primes.get(i).tails);
			}
			writer.close();
		} catch (IOException e) {
		   // do something
		}
		
	}

	
	
	
	
	/*
	 	String test="(E)!id!number";
	String[] testSplitted = test.split("!");
	ArrayList <String> variables=new ArrayList<>();
	ArrayList <String> terminals=new ArrayList<>();
	variables.add("E");
	for(int j=0;j<testSplitted.length;j++){
		for(int i=0;i<variables.size();i++){
			if(testSplitted[j].contains(variables.get(i))){
				int start=testSplitted[j].indexOf(variables.get(i));
				int end=variables.get(i).length() + testSplitted[j].indexOf(variables.get(i));
				String s=testSplitted[j].substring(start, end);
				testSplitted[j]=testSplitted[j].replace(s,"");
			}
			
			terminals.add(testSplitted[j]);
		}
	}
	for(int i=0;i<terminals.size();i++){
		System.out.println("splitted aho "+ terminals.get(i));
	}
	*/
}
}
