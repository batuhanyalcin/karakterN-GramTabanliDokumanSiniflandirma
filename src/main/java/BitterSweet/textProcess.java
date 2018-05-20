package BitterSweet;

import org.apache.commons.io.FileUtils;
import zemberek.core.*;
import zemberek.morphology.*;
import zemberek.morphology.analysis.*;
import zemberek.normalization.*;


import java.io.IOException;
import java.util.List;
import org.antlr.v4.runtime.Token;
import javax.swing.*;

import zemberek.core.logging.Log;
import zemberek.morphology.TurkishMorphology;
import zemberek.normalization.TurkishSpellChecker;
import zemberek.tokenization.TurkishTokenizer;
import zemberek.tokenization.antlr.TurkishLexer;



import java.io.*;


public class textProcess {


    // Function for reading txt file and lowering case
    public static String readFile(String fileName) {


        // This will reference one line at a time
        String line = null;
        String totalLine = "";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);


            while ((line = bufferedReader.readLine()) != null) {
                totalLine += line.toLowerCase();
            }

            // Always close files.
            bufferedReader.close();
            return totalLine;
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();

        }
        return null;

    }


    // Deleting punctuation mark
    public static String deleteMark(String fileText) {

        fileText = fileText.replaceAll("[^a-zA-Z ığüİşçö]", " ");
        return fileText;

    }

    //Replacing the Blank with Underscore
    public static String replaceBlankToUnderscore(String fileText) {

        fileText = fileText.replace(" ", "_");
        return fileText;

    }

    //Lang spell Checker
    public static String spellChecker(String fileText) throws IOException {

        TurkishTokenizer tokenizer = TurkishTokenizer.ALL;
        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
        TurkishSpellChecker spellChecker = new TurkishSpellChecker(morphology);
        StringBuilder output = new StringBuilder();

        for (Token token : tokenizer.tokenize(fileText)) {
            String text = token.getText();
            if (analyzeToken(token) && !spellChecker.check(text)) {
                List<String> strings = spellChecker.suggestForWord(token.getText());
                if (!strings.isEmpty()) {
                    String suggestion = strings.get(0);
                    //Log.info("Correction: " + text + " -> " + suggestion);
                    output.append(suggestion);
                } else {
                    output.append(text);
                }
            } else {
                output.append(text);
            }
        }
        //Log.info(output);
        return output.toString().toLowerCase();

    }

    static boolean analyzeToken(Token token) {
        return token.getType() != TurkishLexer.NewLine
                && token.getType() != TurkishLexer.SpaceTab
                && token.getType() != TurkishLexer.UnknownWord
                && token.getType() != TurkishLexer.RomanNumeral
                && token.getType() != TurkishLexer.Unknown;
    }

    //Find root and derivational affix
    public static String analyzeWord(String fileText) throws IOException {

        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();

        String[] words = fileText.split(" ");
        String totalLine = "";
        for (String word : words) {
            WordAnalysis results = morphology.analyze(word);
            for (SingleAnalysis result : results) {
                //Log.info("Oflazer style       : " + AnalysisFormatters.OFLAZER_STYLE.format(result).split("\\+")[0]);
                totalLine += (AnalysisFormatters.OFLAZER_STYLE.format(result).split("\\+")[0] + " ");

                break;
            }}

        return totalLine.substring(0,totalLine.length()-1);
    }


    //Choose a file and return the PATH
    public static String chooseFile(){

        JFileChooser chooser = new JFileChooser();

        File file = null;
        String filePath = "";
        int returnValue = chooser.showOpenDialog( null ) ;
        if( returnValue == JFileChooser.APPROVE_OPTION ) {
            file = chooser.getSelectedFile() ;
        }
        if(file != null)
        {
            filePath = file.getPath();
        }

        return filePath;

    }



    public static String primitiveChooseFolder(){

        String path = chooseFile().substring(0,chooseFile().lastIndexOf("/"));

        return path;
    }

    public static String chooseFilesFromFolder(String folderPath) throws IOException {

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        String Total = "";

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String content = FileUtils.readFileToString(file);
                System.out.println(content);
            }
        }
        return Total;
    }





}
