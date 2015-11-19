package com.appia.techtest.poemgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;


/**
 * 
 * Class for loading text file and poem generation rules.  
 * The class is also responsible for running the main method.
 *
 */
public class PoemGeneratorDriver {

	private static final String CONFIG_FILE_COULD_NOT_BE_LOCATED = Messages.getString("PoemGeneratorDriver.0"); //$NON-NLS-1$

	public static void main( String[] args )
	{		
		final String argument = args[0];		
		try
		{
			if( argument == null )
			{
				throw new RulesArgumentException( MessageFormat.format(CONFIG_FILE_COULD_NOT_BE_LOCATED, new Object[]{argument}) );
			}
			
			if( argument.equals( Messages.getString("PoemGeneratorDriver.1") ) ) //$NON-NLS-1$
			{
				printHelp();
				return;
			}
			
			String rulesInputPath = argument;
			
			File rulesInputFile = new File( rulesInputPath );
			if( !rulesInputFile.exists() )
			{
				throw new RulesArgumentException( MessageFormat.format(CONFIG_FILE_COULD_NOT_BE_LOCATED, new Object[]{rulesInputPath}) );
			}
						
			
			KeyValueRulesManager manager = KeyValueRulesManager.getRulesManager();
			manager.loadRules( rulesInputFile );
			String output = manager.processRules( PoemGeneratorConstants.POEM_CONSTANT_NAME );
			
			System.out.println( "--------------------------------------------" ); //$NON-NLS-1$
			System.out.println( MessageFormat.format(Messages.getString("PoemGeneratorDriver.3"), new Object[]{getComputerName()} ) ); //$NON-NLS-1$
			System.out.println( "--------------------------------------------" ); //$NON-NLS-1$
			System.out.println();
			System.out.println( output );
			return;
		
		}
		catch( RulesArgumentException ex )
		{
			System.err.println( ex.getMessage() );
			if(ex.getCause() != null)
			{
				ex.getCause().printStackTrace();
			}
		}
		catch( FileNotFoundException ex )
		{
			System.err.println( ex.getMessage() );
		}
		catch( IOException ex )
		{
			System.err.println( ex.getMessage() );
		}	
		
		printHelp();
	}
	
	private static void printHelp()
	{
		System.out.println( "-------------------------------------------------------------" ); //$NON-NLS-1$
		System.out.println( Messages.getString("PoemGeneratorDriver.6") ); //$NON-NLS-1$
		System.out.println( Messages.getString("PoemGeneratorDriver.7") ); //$NON-NLS-1$
		System.out.println( "-------------------------------------------------------------" ); //$NON-NLS-1$
	}
	
	private static String getComputerName()
	{
		try
		{
			InetAddress address = InetAddress.getLocalHost();
			return address.getHostName();
		}
		catch( UnknownHostException ex )
		{
			return "this computer"; //$NON-NLS-1$
		}
	}
}
