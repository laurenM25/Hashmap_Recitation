import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static int RomneyVotes = 0;
    static int ObamaVotes = 0;

    public static void main(String[] args) {
        File selectedfile = new File("data/ElectoralVotes.csv");
        // Confirm if the file exists -- if not, prompt error
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(selectedfile);
        } catch (FileNotFoundException e1) {
            System.out.println("\n[ERROR]: Cannot find the electoral votes file.");
        }

        HashMap<String, Integer> votesMap = new HashMap<>(); // INITIALIZE HASHMAP

        // Scan each entry until no more exist
        while (fileInput.hasNextLine()) {
            // Read the next line in the file
            String buffline = fileInput.nextLine();
            // Split the line into an array
            String[] data_line = buffline.split(",");
            // Store the information in your HashMap
            votesMap.put(data_line[0], Integer.valueOf(data_line[1]));
        }

        //////////// Use above logic to read each state's actual vote total ////////////
        selectedfile = new File("data/ElectionData2012.csv");
        fileInput = null;
        try {
            fileInput = new Scanner(selectedfile);
        } catch (FileNotFoundException e1) {
            System.out.println("\n[ERROR]: Cannot find the electoral votes file.");
        }
        // For each state, find out who won
        // Look up in HashMap number of electoral votes for the state
        // Award electoral votes for that state to the winning candidate
        while (fileInput.hasNextLine()) {
            String buffline2 = fileInput.nextLine();
            String[] data_line_2 = buffline2.split(",");

            // get num votes for state
            int numVotes = votesMap.get(data_line_2[0]);

            // see who won
            if (Integer.valueOf(data_line_2[1]) > Integer.valueOf(data_line_2[2])) { // obama won
                ObamaVotes += numVotes;
            } else {
                RomneyVotes += numVotes;
            }
        }

        System.out.println("Romney Votes: " + RomneyVotes);
        System.out.println("Obama Votes: " + ObamaVotes);
    }
}
