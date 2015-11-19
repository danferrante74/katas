package com.appia.techtest.poemgenerator.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.appia.techtest.poemgenerator.KeyValueRulesManager;
import com.appia.techtest.poemgenerator.PoemGeneratorConstants;
import com.appia.techtest.poemgenerator.RulesArgumentException;

public class KeyValueRuleCommand implements IRuleCommand<String> {

	private static final String RULE_CANNOT_BE_EMPTY_OR_NULL = Messages.getString("KeyValueRuleCommand.0"); //$NON-NLS-1$
	private static final String INVALID_NAME_OR_RULE_SUPPLIED = Messages.getString("KeyValueRuleCommand.1"); //$NON-NLS-1$
	
	
	private String name;
	private String rule;

	
	private List<String[]> randomWordsList;
	private Random random;
	
	
	public KeyValueRuleCommand( String name, String rule ) throws RulesArgumentException 
	{
		if( name == null || rule == null ) throw new RulesArgumentException( INVALID_NAME_OR_RULE_SUPPLIED );
		this.randomWordsList = new ArrayList<String[]>();
		this.name = name;
		this.loadRule( rule );
		this.random = new Random();
	}
	
	@Override
	public String execute() {
		StringBuffer buffer = new StringBuffer();
		for( int i = 0; i < randomWordsList.size(); i++ )
		{
			String[] wordsOrRules = randomWordsList.get(i);
			int index = wordsOrRules.length;
			if( index > 0 )
			{
				index = this.random.nextInt( wordsOrRules.length );
			}
			String wordOrRule = stripOutAnyBrackets( wordsOrRules[ index ] );			
			KeyValueRulesManager manager = KeyValueRulesManager.getRulesManager();
			IRuleCommand<String> command = manager.getRule( wordOrRule );
			if( command != null )
			{
				buffer.append( command.execute() );
			}
			else
			{
				if( PoemGeneratorConstants.LINEBREAK_KEYWORD.equals( wordOrRule ) )
				{
					buffer.append( PoemGeneratorConstants.LINEBREAK_ACTION );
				}
				else if( PoemGeneratorConstants.END_KEYWORD.equals( wordOrRule ) )
				{
					buffer.append( PoemGeneratorConstants.END_ACTION );
					break;
				}
				else
				{
					buffer.append(" ").append( wordOrRule ); //$NON-NLS-1$
				}
			}
		}
		return buffer.toString();
	}

	@Override	
	public String getName() {
		return name;
	}

	@Override	
	public String getRule() {
		return rule;
	}
	
	private void loadRule(String rule) throws RulesArgumentException {
		if( rule == null || "".equals(rule) ) throw new RulesArgumentException( RULE_CANNOT_BE_EMPTY_OR_NULL); //$NON-NLS-1$
		this.rule = rule.trim();
		parse();
	}
	
	private void parse()
	{
		String[] groupings = rule.split(" "); //$NON-NLS-1$
		for (String group : groupings) {
			String[] words = group.split(PoemGeneratorConstants.RULE_WORD_DELIMITER);
			randomWordsList.add( words );
		}
	}
	
	private String stripOutAnyBrackets( String wordOrRule )
	{
		if( wordOrRule == null )
			return wordOrRule;
		
		if( wordOrRule.startsWith( PoemGeneratorConstants.RULE_OPEN ) && wordOrRule.endsWith( PoemGeneratorConstants.RULE_CLOSE ) ) //$NON-NLS-1$ //$NON-NLS-2$
		{
			 wordOrRule = wordOrRule.substring( wordOrRule.indexOf( PoemGeneratorConstants.RULE_OPEN ) + 1, wordOrRule.indexOf( PoemGeneratorConstants.RULE_CLOSE ) ); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return wordOrRule;
	}

}
