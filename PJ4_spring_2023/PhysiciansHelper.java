import java.util.*;
import java.io.*;

public class PhysiciansHelper
{
	// symptom to illnesses map 
	private Map<String, List<String>> symptomChecker;


	/* Constructor symptomChecker map using TreeMap */
	public PhysiciansHelper()
	{ 
		// use TreeMap, i.e. sorted order keys
		symptomChecker = new TreeMap<String,List<String>>();
	} // end default constructor


	/* Reads a text file of illnesses and their symptoms.
	   Each line in the file has the form
		Illness: Symptom, Symptom, Symptom, ...  
	   Store data into symptomChecker map */

	public void processDatafile()
	{
		// Step 1: read in a data filename from keybaord
		//         create a scanner for the file

		// Step 2: process data lines in file scanner
		// 2.1 for each line, split the line into a disease and symptoms
		//     make sure to trim() spaces and use toLowercase()
		// 2.2 for symptoms, split into individual symptom
		//     create a scanner for symptoms 
		//     useDelimeter(",") to split into individual symptoms 
		//     make sure to trim() spaces and use toLowercase()
		//     for each symptom  
		//        if it is already in the map, insert disease into related list
		//        if it is not already in the map, create a new list with the disease
		// Step 3: display symptomChecker map

		// implement here.....
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter filename: ");
		String filename = scanner.nextLine();
//		scanner.close();
		try{
//			System.out.println(filename);
			String pathFilename = "./PJ4_spring_2023/"+filename;
//			System.out.println(pathFilename);
			File file = new File(pathFilename);
			Scanner fileScanner = new Scanner(file);
			System.out.println("contents of sample data file:");
			while(fileScanner.hasNextLine()){
				String line = fileScanner.nextLine();
				System.out.println(line);
				String[] parts = line.split(":");
				String illness = parts[0].trim().toLowerCase(); // "key1"
				String symptoms = parts[1].trim().toLowerCase(); // "value1"
//				System.out.println(illness);
//				System.out.println(symptoms);
				Scanner symptomScanner = new Scanner(symptoms);
				symptomScanner.useDelimiter(",");

				while (symptomScanner.hasNext()) {
					String symptom = symptomScanner.next().trim().toLowerCase();
//					System.out.println(symptom);
					if(symptomChecker.containsKey(symptom)){
						symptomChecker.get(symptom).add(illness);
					}else{
						List<String> illnessList = new ArrayList<>();
						illnessList.add(illness);
						symptomChecker.put(symptom,illnessList);
					}
				}


			}
//			fileScanner.close();

//			print out symptomChecker map
			System.out.println("============================");
			System.out.println("symptomChecker map:");
			for (Map.Entry<String, List<String>> entry : symptomChecker.entrySet()) {
				System.out.println(entry.getKey() +"="+  entry.getValue() );
			}


		}catch (FileNotFoundException e){
			System.out.println("File not found: " + e.getMessage());
		}

	} // end processDatafile


	/*  Read patient's symptoms in a line and adds them to the list.
		Input format: Symptom, Symptom, Symptom,...
	    Shows diseases that match a given number of the symptoms. */

	public void processSymptoms()
	{
		// Step 1: get all possible symptoms from symptomChecker map
		//         and display them
		System.out.println("");
		System.out.println("============================");
		System.out.println("");
		System.out.println("The possible symptoms are:");
		for (String key : symptomChecker.keySet()) {
			System.out.println(key);
		}

		// Step 2: process patient symptoms, add to patientSymptoms list 
		//         read patient's symptoms in a line, separated by ','
		//         create a scanner for symptoms 
		//         UseDelimeter(",") to split into individual symptoms 
		//         make sure to trim() spaces and use toLowercase()
		//         add valid symptoms to patientSymptoms list
		//         display invalid/duplicate symptoms
//
//		System.out.println(System.in.available());
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a line of patient's symptoms:");
		String input = scanner.nextLine();
		System.out.println(input);

//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Enter filename: ");
//		String filename = scanner.nextLine();
//		scanner.close();


//		input = "cough,rash,cough,fever,blisterx,cough,runny NOSE";
		String[]parts = input.split(",");
		List<String> patientSymptoms = new ArrayList<>();
		HashMap<String,Integer> existingSymptoms = new HashMap<>();
//		System.out.println(existingSymptoms);
		for(String part: parts){
//			System.out.println(part);
			String cleanPart = part.trim().toLowerCase();
//			System.out.println(cleanPart);
//			if(symptomChecker.containsKey(cleanPart)){
//				System.out.println("valid symptom");
//			}else{
//				System.out.println("invalid symptom");
//			}
//			if(existingSymptoms.containsKey(cleanPart)){
//				System.out.println("existing symptom");
//			}else{
//				System.out.println("not existing symptom");
//			}
//			System.out.println("");
			if (symptomChecker.containsKey(cleanPart) && (!existingSymptoms.containsKey(cleanPart))){
				patientSymptoms.add(cleanPart);
				existingSymptoms.put(cleanPart,0);
			}else if (!symptomChecker.containsKey(cleanPart)){
				System.out.println("=>invalid symptom:" + cleanPart);
			} else if (existingSymptoms.containsKey(cleanPart)) {
				System.out.println("=>duplicate symptom:" + cleanPart);
			}
		}
		System.out.println("");



//		symptomsScanner.close();


		// Step 3: display patientSymptoms list
		System.out.println("===================");
		System.out.println("PatientSymptoms list: "+ patientSymptoms);
		System.out.println("");
   	        // Step 4: process illnesses to frequency count
		//         create a map of disease name and frequency count
		//         for each symptom in patientSymptoms list
		//              get list of illnesses from symptomChecker map
		//              for each illness in the list
		// 	            if it is already in the map, increase counter by 1
	        //	            if it is not already in the map, create a new counter 1
		//         ** need to keep track of maximum counter numbers
		HashMap<String,Integer> illRrequencyMap = new HashMap<>();
//		Map<String, Integer> illRrequencyMap = new TreeMap<>(Comparator.comparingInt(Map.Entry::getValue));

		int max = 1;
		for (String symptom: patientSymptoms) {
			for(String illness: symptomChecker.get(symptom)){
				if(illRrequencyMap.containsKey(illness)){
					int frequency = illRrequencyMap.get(illness);
					frequency++;
					illRrequencyMap.put(illness,frequency);
					if(frequency > max){
						max = frequency;
					}
				}else {
					illRrequencyMap.put(illness,1);
				}

			}
		}


//		System.out.println(illRrequencyMap);
//		System.out.println(max);   4
		// Step 5: display result
		//         for count i = maximum counter number downto 1
		//             display illness that has count i
		System.out.println("Possible illnesses that match any symptom are:");
		System.out.println("");
		for(int i = max; i > 0; i--){
			System.out.println("==> Disease(s) match " + i + " symptom(s)");
			for(Map.Entry<String, Integer> entry : illRrequencyMap.entrySet()){
				if(entry.getValue() == i){
					System.out.println("       " + entry.getKey());
				}
			}
			System.out.println("");

		}

		scanner.close();

		// implement here.....

	} // end processSymptoms 



	public static void main(String[] args)
	{

		PhysiciansHelper lookup = new PhysiciansHelper();
		lookup.processDatafile();
		lookup.processSymptoms();
	} // end main
} // end PhysiciansHelper
