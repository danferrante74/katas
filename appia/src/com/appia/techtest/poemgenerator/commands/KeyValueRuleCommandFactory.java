package com.appia.techtest.poemgenerator.commands;

import com.appia.techtest.poemgenerator.RulesArgumentException;

public class KeyValueRuleCommandFactory implements IRuleCommandFactory<String> {


	@Override
	public IRuleCommand<String> newRuleCommand(String name, String rule) throws RulesArgumentException
	{
		return new KeyValueRuleCommand( name, rule );
	}
}
