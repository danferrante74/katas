package com.appia.techtest.poemgenerator.commands;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.appia.techtest.poemgenerator.RulesArgumentException;

public class KeyValueRuleCommandFactoryTest {

	private static final String VALID_RULE_NAME = "POEM";
	private static final String VALID_RULE = "TEST|TEST1|TEST2|TEST3|TEST4";
	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNewRuleCommandValidInputs() throws RulesArgumentException {
		IRuleCommand<String> command = new KeyValueRuleCommandFactory().newRuleCommand( VALID_RULE_NAME, VALID_RULE );
		assertNotNull( command );
		String output = command.execute();
		assertTrue( output.indexOf( "TEST" ) >= 0 || output.indexOf( "TEST1" ) >= 0 || output.indexOf( "TEST2" ) >= 0 || output.indexOf( "TEST3" ) >= 0 || output.indexOf( "TEST4" ) >= 0);
	}
	
	@Test(expected = RulesArgumentException.class)
	public void testNewRuleCommandInValidNameInputs() throws RulesArgumentException {
		new KeyValueRuleCommandFactory().newRuleCommand( null, VALID_RULE );
	}
	
	@Test(expected = RulesArgumentException.class)
	public void testNewRuleCommandInValidRuleInputs() throws RulesArgumentException {
		new KeyValueRuleCommandFactory().newRuleCommand( VALID_RULE_NAME, null );
	}
	
}
