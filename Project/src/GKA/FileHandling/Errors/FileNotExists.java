package GKA.FileHandling.Errors;

public class FileNotExists extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8220426979329386797L;

	public FileNotExists()
	{
		super("Datei wurde nicht gefunden oder ist nicht zugreifbar");
	}
}
