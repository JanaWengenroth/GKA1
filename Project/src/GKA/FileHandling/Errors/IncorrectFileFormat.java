package GKA.FileHandling.Errors;

public class IncorrectFileFormat extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6028437295885224459L;

	public IncorrectFileFormat() 
	{
		super("Datei ist nicht richtig formatiert");
	}
}
