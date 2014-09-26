package GKA.FileHandling;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.Test;

import GKA.FileHandling.Errors.FileNotExists;

public class FileHandlerTest {

	@Test(expected = FileNotExists.class)
	public void testLoadFileNotExistsError() throws FileNotExists, IOException 
	{
		FileHandler.load(new File("z:\\existiertnicht.txt"));
	}

	@Test 
	public void testLoad()
	{
		try 
		{
			FileHandler.load(new File("Z:\\win7\\GKA1\\aufgabe1Bsp\\graph1.gka"));
		} 
		catch (FileNotExists | IOException e) 
		{
			
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	
}
