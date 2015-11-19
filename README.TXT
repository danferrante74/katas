Hi...

This is a RANDOM POEM GENERATOR that can take an input file in the form of:

POEM: <LINE> <LINE> <LINE> <LINE> <LINE>
LINE: <NOUN>|<PREPOSITION>|<PRONOUN> $LINEBREAK
ADJECTIVE: black|white|dark|light|bright|murky|muddy|clear <NOUN>|<ADJECTIVE>|$END
NOUN: heart|sun|moon|thunder|fire|time|wind|sea|river|flavor|wave|willow|rain|tree|flower|field|meadow|pasture|harvest|water|father|mother|brother|sister <VERB>|<PREPOSITION>|$END
PRONOUN: my|your|his|her <NOUN>|<ADJECTIVE>
VERB: runs|walks|stands|climbs|crawls|flows|flies|transcends|ascends|descends|sinks <PREPOSITION>|<PRONOUN>|$END
PREPOSITION: above|across|against|along|among|around|before|behind|beneath|beside|between|beyond|during|inside|onto|outside|under|underneath|upon|with|without|through <NOUN>|<PRONOUN>|<ADJECTIVE>

and turn it into something like:

--------------------------------------------
MY RANDOM POEM BY: WIN-HLFVB6R67C5
--------------------------------------------

 beside dark fire ascends beside light river
 her murky
 along clear sister without time
 brother walks between your black dark
 around her river upon black meadow crawls underneath murky black


-----------------------------------BUILDING-------------------------------------
 To build this application, you can use the ant build script with the command:
 
 c:\opt\projects\appia>ant build.all
 
 This will build the necessary executable jar file under the .\lib directory.
 
-----------------------------------RUNNING--------------------------------------
 To run this application, you can use either the ant build script with the command:
 
 c:\opt\projects\appia>ant run.jar
 
OR

 c:\opt\projects\appia>java -jar ./lib/poemgen.jar ./conf/poem-rules.config

--------------------------------------------------------------------------------
  
The sources are under the src directory.
Unit tests are under the test directory.

The project was built using eclipse and tests were run using the junit plugin for eclipse.  We can add junit test to
ant in order to automate tests further.   