import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/** Class to read a file
 * The files orders.txt and cinemas.txt should both either
 * End with "End" or end with null 
 * @author Alfred Ababio
 * @version 18
 */
public class ReadFile {

    /** The name of the file that is being read */
    private String name;

    
    /**
     * Constructor for a ReadFile
     * @param fileName The name of the file to be read
     */
    public ReadFile(String fileName) {
        this.name = fileName;
    }
    
    /**
     * Main method to make it easy for this to read
     * 
     * @param args
     *
    public static void main(String[] args) {
        // This will probably call of the methods in this class in some order
        ReadFile rf = new ReadFile("TestMovieInput.txt");
        ArrayList<String> stuff = new ArrayList<String>();
        try {
            stuff = rf.openFile();
        } catch (IOException e) {
            System.out.println(e.toString() + "<--- Read the error message.");
        }
        /** At this point we have every line of the text file as an
         * element in our ArrayList 
         *
        System.out.println(stuff);
    } */
    
    /**
     * This method reads a file line by line and will return an 
     * ArrayList<String> with each element being a line of the file
     * @return ArrayList<String> of each line of the file
     * @throws IOException If the file can't be read for some reason
     */
    public ArrayList<String> openFile() throws IOException {
        // Replace this with name when finished with the class
        FileReader fr = new FileReader(this.name);
        BufferedReader br = new BufferedReader(fr);
        
        String currLine;
        ArrayList<String> fileStrings = new ArrayList<String>();
        // While currLine is NOT equal to "End"
        // AND
        // While currLine is NOT equal to null
        currLine = br.readLine();
        while ((currLine != null) &&
                (!currLine.equals("End"))) {
            fileStrings.add(currLine);
            currLine = br.readLine();
        }
        br.close();
        /**
         * Gets rid of the triple dots so I don't have to deal with them
         * later 
         */
        this.filter(fileStrings, "...");
        return fileStrings;
    }
    
    /**
     * This actually belongs in an ArrayList child class
     * but I can put it here and it will work.
     * @param alist ArrayList<String> filters the arrayList of r
     * @param r String to remove all of from alist
     */
    public void filter(ArrayList<String> alist, String r) {
        while (alist.contains(r)) {
            alist.remove(r);
        }
    }
    

}
