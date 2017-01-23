package lymalm.handAlphabet.model;

import java.util.ArrayList;

/**
 * Represents the languages that are available as sign language. 
 * 
 * If you add a new language, be sure to add information in the following 
 * external places:
 * - RandomWordGenerator@setUpDictionary()
 * - Difficulty@getDescription(Language, Difficulty)
 *
 * @author Andreas Lymalm
 */
public enum Language {
	Swedish, 
	Japanese;
	
	public static ArrayList<String> getDescriptionList(){
		ArrayList<String> descriptionList = new ArrayList<String>();
    	for(Language lang : Language.values())
    		descriptionList.add(getDescription(lang));
    	
    	return descriptionList;
	}
	
	public static String getDescription(Language lang){
		if(lang == null)
			return "EMPTY";
		
		switch(lang){
    	case Swedish:
    		return "Svenskt teckenspråk";
    	case Japanese:
    		return "JSL - 日本手話";
    	default: 
    		return "No description available";
    	}
	}
}
