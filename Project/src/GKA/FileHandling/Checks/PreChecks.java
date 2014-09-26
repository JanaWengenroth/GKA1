package GKA.FileHandling.Checks;

import java.io.File;

import GKA.FileHandling.Errors.FileNotExists;

public class PreChecks 
{
	public static File checkExistingFile(File file) throws FileNotExists
	{
		if (!(file.exists() && file.isFile() && file.canRead()))
		{
			throw new FileNotExists();
		}
		return file;
	}
}
