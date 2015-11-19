package com.appia.techtest.poemgenerator.commands;

import com.appia.techtest.poemgenerator.RulesArgumentException;

public interface IRuleCommandFactory<T> {
	
	/**
	 * Generates a new rule command for storage in the rules processing map.
	 * 
	 * @param name - name of the rule (e.g. POEM)
	 * @param rule - the rule itself (e.g. <LINE> <LINE> <LINE> <LINE> <LINE>)
	 * @return returns a generic IRuleCommand depending upon what tyoe of object is returned when rule command is processed
	 * @throws RulesArgumentException if an invalid argument is specified.
	 */
	public IRuleCommand<T> newRuleCommand(String name, String rule) throws RulesArgumentException;

}
