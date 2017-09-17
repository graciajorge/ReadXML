package com.example.sunny.readxml;

import android.widget.ArrayAdapter;

import com.example.sunny.parsexml.CustomFile;
import com.example.sunny.parsexml.XMLFile;

import java.io.File;

/**
 * Pass custom file variable from intent to dialog
 * in order to save file
 *
 * Created by Sunny on 6/18/2017.
 */

public interface FileDataPass {
    //constant filename
    final String tempFilename="TEMP_XML.XML";

    /**
     * return temp file from the intent to save file dialog
     *
     * @return CustomFile
     */
    CustomFile getTempFile();

    /**
     * return list of element names in xml file to
     * use in dialog.
     *
     * @return ArrayAdapter list of element names in xml
     */
    ArrayAdapter getViewList();
}
