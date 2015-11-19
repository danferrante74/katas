package com.appia.techtest.poemgenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;

import com.appia.techtest.poemgenerator.commands.IRuleCommand;
import com.appia.techtest.poemgenerator.commands.KeyValueRuleCommandFactory;

public class KeyValueRulesManager {

	
	private static final String RULE_COULD_NOT_BE_LOCATED = Messages.getString("KeyValueRulesManager.0"); //$NON-NLS-1$
	private static final String ERROR_KEY_VALUE_PAIR = Messages.getString("KeyValueRulesManager.1"); //$NON-NLS-1$

	private HashMap<String, IRuleCommand<String>> ruleProcessingMap;	
	
	private static KeyValueRulesManager instance;
	private static final Object lock = new Object();
	
	/**
	 * Singleton to obtain the rules manager as many times
	 * as needed as the same object.
	 * 
	 * DCL is good for our concerns in this feature as it's
	 * not running in a multi-threaded, multi-processor 
	 * environment.  
	 * 
	 * @return RulesManager to manage rules from an input file or database.
	 */
	public static KeyValueRulesManager getRulesManager()
	{
		if( instance == null )
		{
			synchronized( lock )
			{
				if( instance == null )
				{
					instance = new KeyValueRulesManager();
				}
			}
		}
		return instance;
	}
	
	private KeyValueRulesManager()
	{
		this.ruleProcessingMap = new HashMap<String, IRuleCommand<String>>();
	}
	
	
	/**
	 * Queries the rule map for the appropriate rule that needs processing.
	 * @param name of the rule
	 * @return IRuleCommand which processes and executes the rule.
	 */
	public IRuleCommand<String> getRule( String name )
	{
		return ruleProcessingMap.get( name );
	}
	
	/**
	 * Processes a rule that has been added to this rule manager
	 * @return String containing random words which follow the rule.
	 */
	public String processRules( String name ) throws RulesArgumentException
	{
		IRuleCommand<String> command = ruleProcessingMap.get( name );
		if( command == null )
		{
			throw new RulesArgumentException( MessageFormat.format( RULE_COULD_NOT_BE_LOCATED, new Object[]{name} ) );
		}
		return command.execute();
	}
	
	/**
	 * Loads a set of key-value rules from an input file.
	 *   
	 * @param rulesInputFile File to load rules from
	 * 
	 * @throws RulesArgumentException if rule argument is found to be invalid
	 * @throws FileNotFoundException if rules file passed in does not exist
	 * @throws IOException if rules file could not be read from or otherwise accessed
	 */
	public void loadRules( File rulesInputFile ) throws RulesArgumentException, FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader( new FileReader( rulesInputFile ) );
		try
		{
			String line = null;
			while( (line = reader.readLine()) != null )
			{
				String[] ruleContainer = line.split(PoemGeneratorConstants.RULENAME_RULE_DELIMITER); //$NON-NLS-1$
				if( ruleContainer.length < 2 )
				{
					throw new RulesArgumentException( ERROR_KEY_VALUE_PAIR );
				}
				String ruleNameString = ruleContainer[0];
				String ruleString = ruleContainer[1].trim();
				addRule( ruleNameString, ruleString );
			}			
		}
		finally
		{
			reader.close();
		}		
	}
	
	/**
	 * Adds a rule to a current table of rules.  
	 * The rule string is parsed into a IRuleCommand.
	 * 
	 * @param name Name of the rule we are adding
	 * @param rule string representation of the rule.  converts to a IRuleType object
	 */
	public void addRule( String name, String rule ) throws RulesArgumentException
	{
		IRuleCommand<String> command = new KeyValueRuleCommandFactory().newRuleCommand( name, rule );
		ruleProcessingMap.put( name, command );
	}
	
	/**
	 * Clears any rules from the map.  Just used for test cases.
	 */
	public void clearRules()
	{
		ruleProcessingMap.clear();
	}
		
	
}
