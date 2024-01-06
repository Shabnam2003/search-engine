package com.example.searchengine.searchsection;

import java.util.*;

public class FindWord {
    String sentence;
    CompleteMap map;
    int counter;
    Set<String> result = new LinkedHashSet<>();

    public FindWord(String sentence, CompleteMap map) {
        this.sentence = sentence;
        this.map = map;
    }

    public String searching() throws NullPointerException {
        this.sentence = this.sentence.toLowerCase();

        String[] search = this.sentence.split(" ");

        StringBuilder orString = new StringBuilder();
        Set<String> andList = new HashSet<>();


        for (int i = 0; i < search.length; i++) {
            if (search[i].contains("-")) {
                //make not
                if (!CompleteMap.ignoredWords.contains(search[i].substring(1))) {
                    try {
                        String res = this.map.createNotWord(search[i].substring(1));
                        andList.add(res);
                    } catch (NullPointerException ignored) {
                    }
                }
                search[i] = null;                      //remove words those are not important
            }
        }

        for (int i = 0; i < search.length; i++) {                       //spilt and sentences
            if (search[i] != null && search[i].contains("+")) {
                if (!CompleteMap.ignoredWords.contains(search[i].substring(1))) {         //remove words those are not important
                    try {
                        String res = this.map.wordMap.get(search[i].substring(1)).toString();
                        orString.append(res);
                    } catch (NullPointerException ignored) {
                    }
                }                                                                                              //make orString
                search[i] = null;
            }
        }

        for (String s : search) {
            //fill andList
            if (CompleteMap.ignoredWords.contains(s))
                s = null;                      //remove words those are not important
            if (s != null) {
                andList.add(this.map.wordMap.get(s).toString());
            }
        }
        Set<String> andRes = new HashSet<>();
        if (andList.size() != 0) {
            andRes=andOperation(andList);
            if (orString.length() != 0) showRes(andRes, orString.toString());
            else this.result = andRes;
            this.counter = this.result.size();
        } else {
            this.result = Set.of(String.valueOf(orString));
            this.counter = orString.length();
        }

        return this.counter + "\n" + this.result;
    }

    Set<String> andOperation(Set<String> andList) {
        String[] values = andList.stream().toList().get(0).split(", ");     //spilt

        Set<String> andResult = new HashSet<>();

        for (String value : values) {
            boolean find = true;

            for (String s : andList) {
                if (!s.contains(value)) {
                    find = false;
                    break;
                }
            }

            if (find) andResult.add(value);    //if all of them has it,add to result
        }
        return andResult;
    }

    void showRes(Set<String> andList, String orList) {
        for (String s : andList) {
            if (orList.contains(s)) this.result.add(s);
        }
    }

}
