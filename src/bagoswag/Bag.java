package bagoswag;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Bag {
	private boolean zipped;
	private String bagversion = "0.97";
	private SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd");
	private Path path;
	private Path payload[];
	
	
	//constructors
	public Bag() {
		
	}
	
	public Bag(Path p, boolean z) {
		zipped=z;
		path=p;		
	}
	
	//getters, setters and other utils
	public boolean isZipped() {
		return zipped;
	}

	public void setZipped(boolean zipped) {
		this.zipped = zipped;
	}

	public String getBagversion() {
		return bagversion;
	}

	public void setBagversion(String bagversion) {
		this.bagversion = bagversion;
	}

	public SimpleDateFormat getDate() {
		return date;
	}

	public void setDate(SimpleDateFormat date) {
		this.date = date;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}
	
	//call to create bag
	public void pack() {
		String home=System.getProperty("user.home");
	
		try {
			payload = Files.walk(path).filter(Files::isRegularFile).toArray(Path[]::new);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//set destination dir (users desktop with the name as selected folder for now)
		Path destination=Paths.get(home+"\\Desktop\\"+path.getFileName()+"-bagged\\"+path.getFileName());
		
		destination.toFile().mkdirs();
		
		copyFolder(path, destination);
		
		
	}
	
	
	 public void copyFolder( Path src, Path dest ){
	     try{
	    	 Files.walk( src )
	         .forEach( s ->{
	        	 try
	             {   Path d = dest.resolve( src.relativize(s) );
	                 if( Files.isDirectory( s ) )
	                 {   if( !Files.exists( d ) )
	                         Files.createDirectory( d );
	                     return;
	                 }
	                 Files.copy( s, d );// use flag to override existing
	             }
	        	 catch( Exception e )
	                 { e.printStackTrace(); }
	         });
	     }
	     catch( Exception ex )
	         {   ex.printStackTrace(); }
	 }
}