package lymalm.handAlphabet.model;

import java.util.ArrayList;

/**
 * Represents the difficulties that are available. 
 *
 * @author Andreas Lymalm
 */
public enum Difficulty {
	Easy, 
	Medium,
	Hard;

	public static ArrayList<String> getDescriptionList(Language lang){
		ArrayList<String> descriptionList = new ArrayList<String>();
		if(lang == null)
			descriptionList.add("");
		else{
			for(Difficulty difficulty : Difficulty.values())
				descriptionList.add(getDescription(lang, difficulty));
		}
    	return descriptionList;
	}

	public static String getDescription(Language lang, Difficulty difficulty){
		if(lang == null || difficulty == null)
			return "EMPTY";
		else{
			switch(lang){
			case Swedish:
				switch(difficulty){
				case Easy:
					return "Lätt";
				case Medium:
					return "Medel";
				case Hard:
					return "Svår"; 
				default:
					return "Not yet translated";
				}
			case Japanese:
				switch(difficulty){
				case Easy:
					return "簡単";
				case Medium:
					return "まあまあ";
				case Hard:
					return "難しい"; 
				default:
					return "Not yet translated";
				}
			default: 
				return "Not yet translated";
	    	}
		}
	}
}
