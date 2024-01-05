package com.example.searchengine.searchsection;

import java.io.*;
import java.util.*;

public class CompleteMap {
    public Map<String, StringBuilder> wordMap = new HashMap<>();
    static List<String> ignoredWords = new ArrayList<>();   //words those are in any text
    final File folder = new File("D:\\data structures\\search-engine-Shabnam2003\\EnglishData");
    File[] files;
    List<String> fileNames = new ArrayList<>();

    public CompleteMap() throws IOException {
        ignoredWords.add("am");
        ignoredWords.add("is");
        ignoredWords.add("are");
        ignoredWords.add("an");
        ignoredWords.add("a");
        ignoredWords.add("the");
        ignoredWords.add("in");
        ignoredWords.add("on");
        ignoredWords.add("there");
        ignoredWords.add("this");
        ignoredWords.add("that");
        ignoredWords.add("those");

        readFiles();
    }

    private String removeSigns(File file) throws IOException {


        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);    //read file

        StringBuilder content = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
                                                          // Remove punctuation marks using regular expressions
            String modifiedLine = line.replaceAll("\\p{Punct}", "");
            content.append(modifiedLine).append("\n");
        }

        bufferedReader.close();
        fileReader.close();

        return content.toString().toLowerCase();                      //make it lower case
    }

    void readFiles() throws IOException {
        this.files = this.folder.listFiles();
        assert this.files != null;
        for (File file : this.files) {
            createMap(file);
            fileNames.add(file.getName());
        }
    }

    private void createMap(File file) throws IOException {
        String[] text = removeSigns(file).split(" ");             //spilt text

        for (String s : text) {
            if (!ignoredWords.contains(s)) {

                StringBuilder val = this.wordMap.get(s);

                if (val == null) {
                    val = new StringBuilder(file.getName());
                } else if (!val.toString().contains(file.getName())) {
                    val.append(", ").append(file.getName());               //update value
                }

                this.wordMap.put(s, val);
            }
        }
    }

    public String createNotWord(String key) {
        StringBuilder notVal=new StringBuilder();
        String val = this.wordMap.get(key).toString();      //find files those are contains the key

        for (String name : this.fileNames) {
            if (!val.contains(name)){                         //add others to the string
                notVal.append(name).append(", ");
            }
        }

        return notVal.toString();
    }

}