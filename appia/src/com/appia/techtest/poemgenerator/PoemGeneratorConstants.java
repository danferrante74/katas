package com.appia.techtest.poemgenerator;

public class PoemGeneratorConstants {

	//constants for key actions.
	public static final String POEM_CONSTANT_NAME = "POEM";
	public static final String LINEBREAK_KEYWORD = "$LINEBREAK";
	public static final String END_KEYWORD = "$END";
	
	//used for processing rules.
	public static final String LINEBREAK_ACTION = System.getProperty( "line.separator" );
	public static final String END_ACTION = " ";
	public static final String RULE_WORD_DELIMITER = "\\|";
	public static final String RULE_OPEN = "<";
	public static final String RULE_CLOSE = ">";
	public static final String RULENAME_RULE_DELIMITER = ":";
}
