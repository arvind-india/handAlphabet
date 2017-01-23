package lymalm.handAlphabet.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * A model class for a wordbook.
 *
 * @author Andreas Lymalm
 */
public class RandomWordGenerator {

	private Language language;
	private int minWordLength, maxWordLength;
	private ArrayList<String> dictList;
	
	/**
	 * Default constructor.
	 */
	public RandomWordGenerator(){
		this(Language.Swedish, 0, 100);
	}
	
	/**
	 * @param lang Which language the dictionary should be in.
	 * @param minWordLength Minimum word length.
	 * @param maxWordLength Maximum word length.
	 */
	public RandomWordGenerator(Language language, int minWordLength, int maxWordLength){
		this.language = language;
		this.minWordLength = minWordLength;
		this.maxWordLength = maxWordLength;
		dictList = new ArrayList<String>();
		setUpDictionary();
	}

	/**
	 * Locates the dictionary for the specified language and puts the words
	 * within the specified length range in a referenceable list.
	 */
	private void setUpDictionary(){
		// Make sure language is not null.
		if(language == null){
			System.out.println("No language selected!");
			return;
		}
		
		// Locate dictionary file.
		String dictPath = "resources/dictionaries/";
		switch(language){
		case Swedish:
			dictPath += "swedish";
			break;
		case Japanese:
			dictPath += "japanese";
			break;
		default:
			System.out.println("You have not yet set up this language.\n" 
				+ "Do this in \"RandomWordGenerator@setUpDictionary\".\n"
				+ "Defaulting to Swedish.");
			dictPath += "swedish";
			break;
		}
		dictPath += "/djur.txt";
		
		// Put words in specified length range in a list.
		try(Stream<String> lines = Files.lines(Paths.get(dictPath), StandardCharsets.UTF_8))
		{
			boolean withinRange = false;
		    for(String line : (Iterable<String>)lines::iterator){
		    	// check if line is an integer. 
		        if(line.matches("\\d+")){	
		        	int lengthCategory = Integer.valueOf(line);
		        	if(lengthCategory >= minWordLength && lengthCategory <= maxWordLength)
		        		withinRange = true;
		        	else
		        		withinRange = false;
		        }
		        else{
		        	if(withinRange)
		        		dictList.add(line);
		        }
		    }
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return a new random word from the dictionary within the specified length range. 
	 */
	public String getNewWord(){
		int pos = (int)(Math.random() * dictList.size());
		
		return dictList.get(pos);
	}
	
	/**
	 * @return the dictionary language.
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * @param language Which language the dictionary should be in.
	 */
	public void setLanguage(Language language) {
		this.language = language;
		setUpDictionary();
	}

	/**
	 * @return the minimum word length.
	 */
	public int getMinWordLength() {
		return minWordLength;
	}

	/**
	 * @param minWordLength The minimum word length.
	 */
	public void setMinWordLength(int minWordLength) {
		this.minWordLength = minWordLength;
		setUpDictionary();
	}

	/**
	 * @return the maximum word length.
	 */
	public int getMaxWordLength() {
		return maxWordLength;
	}

	/**
	 * @param maxWordLength The maximum word length.
	 */
	public void setMaxWordLength(int maxWordLength) {
		this.maxWordLength = maxWordLength;
		setUpDictionary();
	}
	
}
