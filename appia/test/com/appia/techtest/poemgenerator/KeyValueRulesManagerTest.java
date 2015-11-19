package com.appia.techtest.poemgenerator;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.appia.techtest.poemgenerator.commands.IRuleCommand;

public class KeyValueRulesManagerTest {

	private static final String CURRENT_DIRECTORY = System.getProperty( "user.dir" );
	private static final File NON_EXISTANT_RULES_FILE = new File( "/non-existance.txt" );
	private static final File INVALID_RULES_FILE = new File( CURRENT_DIRECTORY, "/conf/bad-rules.config" );
	private static final File VALID_RULES_FILE = new File( CURRENT_DIRECTORY, "/conf/poem-rules.config" );
	
	private static final String ACTUAL_ADJECTIVE_RULE = "black|white|dark|light|bright|murky|muddy|clear <NOUN>|<ADJECTIVE>|$END";
	
	private KeyValueRulesManager manager;
	
	@Before
	public void setUp() throws Exception {
		manager = KeyValueRulesManager.getRulesManager();
	}

	@After
	public void tearDown() throws Exception {
		manager.clearRules();
	}

	@Test
	public void testGetRulesManager() {
		//test that pulling rules manager more than once doesn't give you different objects.
		assertTrue(manager == KeyValueRulesManager.getRulesManager() );
	}

	@Test
	public void testAddGetValidRule() throws RulesArgumentException {
		manager.addRule("BLAH", "FOO|BAR|IS|NOT|FUN $END");
		IRuleCommand<String> rule = manager.getRule( "BLAH" );
		assertEquals( "BLAH", rule.getName() );
		assertEquals( "FOO|BAR|IS|NOT|FUN $END", rule.getRule() );
	}


	@Test
	public void testAddGetInvalidRule() throws RulesArgumentException {
		manager.addRule("BLAH", "FOO|BAR|IS|NOT|FUN $END");
		IRuleCommand<String> rule = manager.getRule( "$END" );
		assertTrue( rule == null );
	}

	
	@Test
	public void testProcessValidRules() throws RulesArgumentException, FileNotFoundException, IOException {
		manager.loadRules(VALID_RULES_FILE);
		IRuleCommand<String> command = manager.getRule( "ADJECTIVE" );
		assertNotNull( command );
		assertEquals( "ADJECTIVE", command.getName() );
		assertEquals( ACTUAL_ADJECTIVE_RULE, command.getRule() );		
		String output = command.execute();
		assertTrue( output.contains( "black" ) || 
					output.contains( "white" ) ||
					output.contains( "dark" )  ||
					output.contains( "bright" )||
					output.contains( "murky" ) ||
					output.contains( "light" ) ||
					output.contains( "muddy" ) ||
					output.contains( "clear" ));
	}
	
	
	@Test
	public void testLoadRulesValidRulesFile() throws RulesArgumentException, FileNotFoundException, IOException {
		manager.loadRules(VALID_RULES_FILE);
		IRuleCommand<String> command = manager.getRule( "ADJECTIVE" );
		assertNotNull( command );
		assertEquals( "ADJECTIVE", command.getName() );
		assertEquals( ACTUAL_ADJECTIVE_RULE, command.getRule() );
	}	

	@Test(expected = RulesArgumentException.class)
	public void testLoadRulesInvalidRulesFile() throws RulesArgumentException, FileNotFoundException, IOException {
		manager.loadRules(INVALID_RULES_FILE);
	}

	@Test(expected = FileNotFoundException.class)
	public void testLoadRulesNonExistantFile() throws RulesArgumentException, FileNotFoundException, IOException {
		manager.loadRules(NON_EXISTANT_RULES_FILE);
	}
	
}
