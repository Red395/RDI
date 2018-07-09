package com.example.wiskowski.rounddisland;
import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.IOException;

public class FileReaderMechanics {
    private String[] files = {}; // used to hold all the files
    private String dir;     // used to hold the directory
    private Context mContext;

    /** Checks the default directory ("app\src\assets\Directory")
     *  Creates a list of the files in that directory
     */
    public FileReaderMechanics(Context mContext) {
        AssetManager am = mContext.getAssets();

        try { files = am.list("Directory"); }
        catch (IOException e) {}

        final String[] fileList = files;
    }

    /** Checks the specified directory
     *  @param dir : the directory for the files
     */
    public FileReaderMechanics(Context mContext, String dir) {
        this.dir = dir;

        AssetManager am = mContext.getAssets();

        try { files = am.list(dir); }
        catch (IOException e) {}

        final String[] fileList = files;
    }

    /** Returns a list of files
     *  @return : the list of files as type File
     */
    public String[] getFiles() {
        return files;
    }
}
