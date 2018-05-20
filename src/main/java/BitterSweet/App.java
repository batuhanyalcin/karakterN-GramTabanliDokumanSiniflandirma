package BitterSweet;

import BitterSweet.textProcess.*;
import BitterSweet.nGram.*;

import static BitterSweet.textProcess.*;
import static BitterSweet.nGram.*;

import java.io.*;
import java.util.*;

import zemberek.core.*;
import zemberek.morphology.*;

import javax.swing.*;
import zemberek.normalization.*;




public class App
{
    public static void main( String[] args ) throws IOException {

        // The name of the file to open.
        //String fileName = "deneme.txt";


        //String fileName = chooseFile();

        //System.out.println(primitiveChooseFolder());
        //File folder = new File(primitiveChooseFolder());
        //File[] listOfFiles = folder.listFiles();
        //System.out.println(listOfFiles);



        //String fileName = chooseFilesFromFolder(primitiveChooseFolder());

        String fileText = null;

        String fileTextWordRoot = null;

        //fileText = readFile(fileName);

        fileText = chooseFilesFromFolder(primitiveChooseFolder());

        //System.out.println(fileText);
        //System.out.println(deleteMark(fileText));
        //System.out.println(replaceBlankToUnderscore(fileText));
        //System.out.println(spellChecker(fileText));

        System.out.println(analyzeWord(spellChecker(fileText)));

        fileTextWordRoot = replaceBlankToUnderscore(analyzeWord(spellChecker(fileText)));
        System.out.println(fileTextWordRoot);
        System.out.println(calculateNGram(fileTextWordRoot,3));



    }//End Of main






}//End Of Class
