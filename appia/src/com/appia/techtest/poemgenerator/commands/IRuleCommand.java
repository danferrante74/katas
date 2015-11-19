package com.appia.techtest.poemgenerator.commands;

import com.appia.techtest.poemgenerator.RulesArgumentException;

public interface IRuleCommand<T> {

	/**
	 * Executes a rule command.  A rule command processes a set of rules from 
	 * an input file and generates some output based on that processed rule
	 * @return
	 * @throws RulesArgumentException
	 */
	T execute();
	
	/**
	 * @return the name of the specific rule (e.g. POEM, LINE, ADJECTIVE, etc...)
	 */
	String getName();
	
	/**
	 * 
	 * @return the rule itself (e.g. <LINE> <LINE> <LINE> <LINE> <LINE>)
	 */
	String getRule();
	
}
