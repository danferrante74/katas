package com.appia.techtest.poemgenerator.commands;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.appia.techtest.poemgenerator.RulesArgumentException;

public class KeyValueRuleCommandTest {

	private String VALID_RULE_NAME = "POEM";
	private String VALID_RULE = "My|His|Her $END";
	
	private String VALID_SINGLE_WORD_RULE = "foo";
	private String EMPTY_STRING_WORD_RULE = "";
	
	private KeyValueRuleCommand command;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecuteValidInputs() throws RulesArgumentException 
	{
		command = new KeyValueRuleCommand( VALID_RULE_NAME, VALID_RULE );
		String output = command.execute();
		assertNotNull( output );
		assertTrue( output.indexOf( "My" ) >= 0 || output.indexOf( "His" ) >= 0 || output.indexOf( "Her" ) >= 0);
	}
	
	@Test
	public void testExecuteValidSingleWordInputs() throws RulesArgumentException 
	{
		command = new KeyValueRuleCommand( VALID_RULE_NAME, VALID_SINGLE_WORD_RULE );
		String output = command.execute();
		assertNotNull( output );
		assertTrue( output.indexOf( "foo" ) >= 0 );
	}
	
	
	@Test(expected = RulesArgumentException.class)
	public void testExecuteInvalidName() throws RulesArgumentException 
	{
		command = new KeyValueRuleCommand( null, VALID_RULE );
	}

	@Test(expected = RulesArgumentException.class)
	public void testExecuteInvalidRule() throws RulesArgumentException 
	{
		command = new KeyValueRuleCommand( VALID_RULE_NAME, null );
	}

	@Test(expected = RulesArgumentException.class)
	public void testExecuteEmptRule() throws RulesArgumentException 
	{
		command = new KeyValueRuleCommand( VALID_RULE_NAME, EMPTY_STRING_WORD_RULE );
	}
	

}
