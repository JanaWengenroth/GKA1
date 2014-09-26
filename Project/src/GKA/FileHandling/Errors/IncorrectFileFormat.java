package GKA.FileHandling.Errors;

public class IncorrectFileFormat extends Exception
{
	public IncorrectFileFormat() 
	{
		super("Datei ist nicht richtig formatiert");
	}
}
