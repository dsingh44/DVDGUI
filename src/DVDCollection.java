import java.io.*;

public class DVDCollection {
	
	/* The current number of DVDs in the array */
	private int numdvds;
	
	/* The array to contain the DVDs*/
	private DVD[] dvdArray;
	
	/*Boolean flag to indicate whether the  DVD collection was modified since it was saved*/
	private boolean modified;
	
	/*The name of data file that contain DVD data*/
	private String sourceName;
	
	/* Construct an empty directory as an array
	 * Initial capacity of 10. We will double the size of array when capacity reach 10
	 */
	public DVDCollection() {
		numdvds= 0;
		dvdArray= new DVD[10];
	}
	public DVD[] DVDinfo() {
		return dvdArray;
	}
	
	/* Convert the DVD collection to a string representation
	 * return string representation of DVD collection
	 */
	
	public String toString() {
		String stringdvds ="";
		stringdvds += "\n" + "Total DVDs: " + numdvds + "\n \n";
		
		for (int i = 0; i< numdvds;i++) {
			stringdvds += dvdArray[i].getTitle() +  "  " + dvdArray[i].getRating() + " "+ dvdArray[i].getRunningTime()+'\n';
		}
		return stringdvds;
	}
	
	/* Add or modifies a DVD in the collection based on provided information
	 * @param Title , rating , running time of DVD
	 */
	public void addOrModifyDVD (String title, String rating, String runningTime) {
		
		if(!(rating.equals ("NC-17")|| rating.equals("PG-13") || rating.equals("PG") || rating.equals("R") || rating.equals("G"))) {
			return;
		}
		int runT = Validruntime(runningTime);
		if (runT <= 0) {
			return;
		}
		modified = true;
		int j= DVDlookup(title);
		
		//DVD already in collection
		if (j >= 0)
		{
			dvdArray[j].setTitle(title);
			dvdArray[j].setRating(rating);
			dvdArray[j].setRunningTime(runT);
		}
		//add DVD
		else
		{
			if (numdvds < dvdArray.length)
			{
				dvdArray[numdvds] = new DVD (title, rating, runT);
				numdvds++;
			}
			else 
			{
				DVD[] newArray = new DVD [dvdArray.length * 2];
				System.arraycopy(dvdArray, 0, newArray, 0, numdvds);
				dvdArray = newArray;
				System.out.print(toString());	
			}
		}
	}
	
	/* Removes a DVD from the collection based on provided title
	 * @param title The title of DVD to be removed.
	 */
	public void  removeDVD(String title) {
		int index = DVDlookup(title);
		
		if (index >=0)
		{
			modified = true;
			DVD[] newArray = new DVD[dvdArray.length-1];
			System.arraycopy(dvdArray,0,newArray,0, index);
			System.arraycopy(dvdArray,index+1,newArray,index,dvdArray.length-index-1);
			dvdArray = newArray;
			numdvds--;
		}
	}
	/*
	 * Retrieves DVDs from the collection that match the specified rating.
	 * @param rating The rating to filter the DVDs 
	 * @return A string containing title of DVDs that match the rating.
	 */
	public  String getDVDsByRating(String rating) {
		String findbyrating = "";
		for (int i =0; i < numdvds; i++) {
			if (rating.equals(dvdArray[i].getRating())) {
				findbyrating += (dvdArray[i].getTitle()+'\n');
			}
		}
		return findbyrating;
	}
	
	/* Calculates the total running time of all DVDs in the collection
	 * @return The total running time in minutes.
	 */
	public int getTotalRunningTime() {
		int calcruntime =0;
		
		for(int i =0; i< numdvds; i++) {
			calcruntime += dvdArray[i].getRunningTime();
		}
		return calcruntime;
	}
	
	/* Loads DVD data from a file and add or modifies DVD in the collection based on the data.
	 * @param filename The name of the file to load the data from.
	 */
	public void loadData (String filename) {
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			String Line;
			
			while ((Line = br.readLine()) != null) 
			{
				String[] content = Line.split(",");
				if (content.length !=3) {
					System.out.println("Eroor: Invalid DVD entry " + Line + "\n");
					return;
				}
				addOrModifyDVD(content[0], content[1], content[2]);
			}
			modified = false;
			sourceName= filename;
			br.close();
		}
		catch(IOException e) {
			
		}	
	}
	/* Save the DVD collection to a file
	 * if the collection has not been modified, it does nothing.
	 * write each DVD title, rating , running time to the file.
	 */
	
	public void save() {
		
		try {
			if(!modified) {
				return;
			}
			FileWriter File = new FileWriter(sourceName);
			
			if (numdvds > 0) {
				for (int j =0; j < numdvds; j++) {
					File.write(dvdArray[j].getTitle()+ "," +dvdArray[j].getRating() + "," + dvdArray[j].getRunningTime());
					File.write(System.lineSeparator());
				}
			}
			File.close();
			modified = false;
		}
		catch (IOException e) {
			
		}
	}
	
	//additional private helper methods
	private int Validruntime(String runningTime) {
		int runT=0;
		try {
			runT = Integer.parseInt(runningTime);
		}
		catch(NumberFormatException e)
		{
			
		}
		
		return runT;	
	}
	
	private int DVDlookup(String title) 
	{
		if (numdvds > 0) 
		{
			for (int index =0; index < numdvds; index++) 
			{
				if (dvdArray[index].getTitle().equals(title)) 
				{
					return index;
				}
			}
		}
		return -1 ;
	}
}