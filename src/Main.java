import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;
public class Main {
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<String> lines = new ArrayList<>();

    private static File curFile;

    private static String curFileName;
    private static boolean isDirty = false;

    public static void main(String[] args) {

        String menuPrompt = "O - Open  S - Save  A - Add  D - Delete  V - View  Q - Quit";
        String cmd = "";
        boolean done = false;
        try {
            do {
                showList();
                cmd = SafeInput.getRegExString(in, menuPrompt, "[AaDdVvQqOoSs]");
                cmd = cmd.toUpperCase();
                switch (cmd) {
                    case "A":
                        // case "a":
                        add();
                        isDirty = true;
                        break;

                    case "D":
                        // case "d":
                        delete();
                        isDirty = true;
                        break;

                    case "V":
                        // case "v":
                        showList();
                        break;

                    case "O":
                        // case "o":
                        if (isDirty) {
                            System.out.println("You are about to lose the current memo data!");
                            boolean saveYN = SafeInput.getYNConfirm(in, "Do you want to save the current memo.");

                            if (saveYN) {
                                if (curFile == null)
                                    curFileName = SafeInput.getNonZeroLengthString(in, "Enter the name for the file: ");
                                else
                                    curFileName = curFile.getName();

                                saveFile(curFileName);
                            }
                        }
                        openFile();
                        isDirty = false;
                        break;

                    case "S":
                        // case "s":
                        if (!lines.isEmpty()) {
                            if (curFile == null)
                                curFileName = SafeInput.getNonZeroLengthString(in, "Enter the name for the file");
                            else
                                curFileName = curFile.getName();

                            saveFile(curFileName);
                            isDirty = false;
                        } else
                            System.out.println("Nothing to save!");
                        break;

                    case "Q":
                        // case "q":
                        quit();
                        break;
                }
            } while (!done);
        } // End of the Try block

        catch (FileNotFoundException e)
        {
            System.out.println("The file cannot be found!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    } // end of main method

    private static void showList() {
        System.out.println("------------------------------------");
        if (lines.size() == 0) {
            System.out.println("\nThe list is currently empty.\n");
        } else {
            for (String l : lines)
                System.out.println("\t" + l);
        }
        System.out.println("------------------------------------");
    }

    /**
     * Quit function
     */
    private static void quit() throws IOException {
        boolean quitYN = false;
        boolean saveYN = false;
        if (isDirty) {
            System.out.println("You are about to lose the data for your memo.");
            System.out.println("Save the file before you Quit!");

            saveYN = SafeInput.getYNConfirm(in, "Save the file? ");
            if (saveYN) {
                saveFile(curFileName);
                System.out.println("File Saved, exiting...");
            }
            System.exit(0);

        }

        quitYN = SafeInput.getYNConfirm(in, "Are you sure you want to Quit:");

        if (quitYN) {
            System.exit(0);
        }
    }

    /**
     * Adds a line
     */
    private static void add() {
        String lineItem = "";
        lineItem = SafeInput.getNonZeroLengthString(in, "Enter the new line for the memo");
        lines.add(lineItem);
    }

    public static void delete() {
        System.out.println("------------------------------------");
        if (lines.size() == 0) {
            System.out.println("\nNothing to delete.\n");
            System.out.println("------------------------------------");
            return;
        } else {
            int ln = 0;

            for (String l : lines) {
                ln++;
                System.out.printf("\t%3d %-80s\n", ln, l);
            }
        }
        System.out.println("------------------------------------");
        int low = 1;
        int high = lines.size();
        int target = SafeInput.getRangedInt(in, "Enter the number of the line you want to delete", low, high);

        target--; // Adjust the display value to be a valid 0 based index for the array list

        // Delete the item
        lines.remove(target);

        return;
    }

    private static void openFile() throws IOException, FileNotFoundException
    {
        // Code to open
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path wd = workingDirectory.toPath();
        wd = Paths.get(workingDirectory + "\\src");
        workingDirectory = wd.toFile();

        chooser.setCurrentDirectory(workingDirectory);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            curFile = selectedFile;
            Path file = selectedFile.toPath();

            InputStream in =
                    new BufferedInputStream(Files.newInputStream(file, CREATE));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in));


            // Clear any existing list items when you open a file
            lines.clear();

            int line = 0;
            while (reader.ready()) {
                rec = reader.readLine();
                line++;
                lines.add(rec);

                System.out.printf("\nLine %4d %-60s", line, rec);
            }
            reader.close();
            System.out.println("\n\nData file read!");
        }

        else {
            System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file");
        }

    }

    private static void saveFile(String fileName) throws IOException
    {
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName);

        OutputStream out =
                new BufferedOutputStream(Files.newOutputStream(file, CREATE));
        BufferedWriter writer =
                new BufferedWriter(new OutputStreamWriter(out));

        if (curFile == null)
            curFile = file.toFile();

        for(String rec : lines)
        {
            writer.write(rec, 0, rec.length());

            writer.newLine();
            System.out.println("Writing file data: " + rec);
        }
        writer.close();
        System.out.println("Data file written: " + curFile.getName());
    }

}