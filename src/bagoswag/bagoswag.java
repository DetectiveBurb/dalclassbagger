package bagoswag;

import javax.swing.SwingConstants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;

public class bagoswag implements SwingConstants {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Path path= Paths.get("C:\\Users\\Student\\Pictures\\lkjubkjb\\");
		Bag bag = new Bag(path, false);
		bag.pack();	
	}

}
