package com.example.wiskowski.rounddisland;
import java.io.File;

public class FileReaderMechanics {
    private File[] files; // used to hold all the files
    private String dir;     // used to hold the directory

    /** Checks the default directory ("app\src\assets\Directory")
     *  Creates a list of the files in that directory
     */
    public FileReaderMechanics() {
        this.dir = dir;

        File folder = new File("assets/Directory");
        files = folder.listFiles();
    }

    /** Checks the specified directory
     *  @param dir : the directory for the files
     */
    public FileReaderMechanics(String dir) {
        this.dir = dir;

        File folder = new File(dir);
        files = folder.listFiles();
    }

    /** Returns a list of files
     *  @return : the list of files as type File
     */
    public File[] getFiles() {
        return files;
    }
}
