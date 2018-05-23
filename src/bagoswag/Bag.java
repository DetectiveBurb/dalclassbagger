package bagoswag;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class Bag {
	private boolean zipped;
	private String bagversion = "0.97";
	private String date;
	private Path path;
	private Path payload[];
	private String home;
	private double size;
	
	
	//constructors
	public Bag() {
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		Date day= new Date();
		this.date=format.format(day);
		this.home=System.getProperty("user.home");
		this.size=countBagSize();
	}
	

	public Bag(Path p, boolean z) {
		zipped=z;
		path=p;		
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		Date day= new Date();
		this.date=format.format(day);
		this.home=System.getProperty("user.home");
		this.size=countBagSize();
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
		makeMetaText(destination);
}
	
	//calculates the size of the bag
	private double countBagSize() {
		
		return 0;
	}
	
	//create text about the bag (not the manifest)
	 private void makeMetaText(Path destination) {
		 destination=Paths.get(home+"\\Desktop\\"+path.getFileName()+"-bagged\\meta.txt");
		 try {destination.toFile().createNewFile();}
		 catch (IOException e1) {e1.printStackTrace();}
		 String text="Created on "+ this.date +"\r\n";
		 for (Path file:payload)
		 {
			 text+=file.toString()+"\r\n";
		 }
		 
		byte data[]=text.getBytes();
		try {Files.write(destination, data);}
		catch (IOException e) {e.printStackTrace();}
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