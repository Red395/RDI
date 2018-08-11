package com.example.wiskowski.rounddisland;
import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class FileReaderMechanics {
    private String[] files = {}; // used to hold all the files
    private String dir;     // used to hold the directory
    private Context mContext;
    private AssetManager am;

    /** Checks the default directory ("app\src\assets\Directory")
     *  Creates a list of the files in that directory
     */
    public FileReaderMechanics(Context mContext) {
        this.mContext = mContext;
        am = this.mContext.getAssets();
        dir = "Directory";

        try { files = am.list("Directory"); }
        catch (IOException e) {}

        final String[] fileList = files;
    }

    /** Checks the specified directory
     *  @param dir : the directory for the files
     */
    public FileReaderMechanics(Context mContext, String dir) {
        this.dir = dir;

        this.mContext = mContext;
        am = this.mContext.getAssets();

        try { files = am.list(dir); }
        catch (IOException e) {}

        final String[] fileList = files;
    }

    /** Returns a list of files
     *  @return : the list of files as type String[]
     */
    public String[] getFiles() {
        return files;
    }

    /*  Returns a String containing the value of
     *  a file at the index provided
     *  @param fileName : the name of the file to find
     *  @return : the contents of a file as a String
     */
    public ArrayList<String> getTextFileContents(String fileN) throws IOException{
        ArrayList<String> lines = new ArrayList<String>();

        // makes sure the specified file is in the "files" list
        String fileName = null;
        for (int line = 0; line < files.length; line++) {
            if (files[line].equals(fileN)){
                fileName = fileN;
                break;
            }
        }

        InputStream fileContents = am.open(dir +"/" + fileName);
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(fileContents));
        String line;

        while((line = fileReader.readLine()) != null) {
            lines.add(line); // appends the current line to lines ArrayList
        }

        return lines;
    }
}
