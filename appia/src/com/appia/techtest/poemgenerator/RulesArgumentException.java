package com.appia.techtest.poemgenerator;

@SuppressWarnings("serial")
public class RulesArgumentException extends Exception {

	public RulesArgumentException()
	{
		super();
	}
	
	public RulesArgumentException( String message )
	{
		super( message );
	}
	
	public RulesArgumentException( String message, Throwable ex )
	{
		super( message, ex );
	}
}
