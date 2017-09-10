package com.example.sunny.readxml;

import android.widget.ArrayAdapter;

import com.example.sunny.parsexml.CustomFile;
import com.example.sunny.parsexml.XMLFile;

import java.io.File;

/**
 * Created by Sunny on 6/18/2017.
 */

public interface FileDataPass {
    final String tempFilename="TEMP_XML.XML";

    CustomFile getTempFile();
    ArrayAdapter getViewList();
}
