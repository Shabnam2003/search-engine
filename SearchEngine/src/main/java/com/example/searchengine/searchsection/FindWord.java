package com.example.searchengine.searchsection;

import java.util.*;

public class FindWord {
    String sentence;
    CompleteMap map;
    Set<String> result = new LinkedHashSet<>();

    public FindWord(String sentence, CompleteMap map) {
        this.sentence = sentence;
        this.map = map;
    }

    public String searching() throws NullPointerException {

        LinkedList<String> andList = new LinkedList<>();

        if (!this.sentence.contains("-") && !this.sentence.contains("+")) {
            this.result.add(this.map.wordMap.get(this.sentence).toString());       //exact word
        }
        else if (!this.sentence.contains("-") && this.sentence.contains("+")) {
            String[] str = this.sentence.split("\\+");          //spilt and "or"

            for (String string : str) {
                this.result.add(this.map.wordMap.get(string).toString());
            }
        }
        else {
            String[] plusSpilt = this.sentence.split("\\+");        //spilt by +

            for (String string : plusSpilt) {
                if (string.contains("-")) {
                    String[] minusSpilt = string.split("-");          //spilt by -

                    if (!Objects.equals(minusSpilt[0], "")) {      //if not started with -
                        andList.add(this.map.wordMap.get(minusSpilt[0]).toString());
                    }
                    for (int i = 1; i < minusSpilt.length; i++) {
                        andList.add(this.map.createNotWord(minusSpilt[i]));
                    }
                } else {
                    andList.add(this.map.wordMap.get(string).toString());
                }
            }

            andOperation(andList);
        }

        return this.result.toString();
    }

    void andOperation(LinkedList<String> andList) {
        String[] values = andList.get(0).split(", ");     //spilt

        for (String value : values) {
            boolean find = true;

            for (String s : andList) {
                if (!s.contains(value)) {
                    find = false;
                    break;
                }
            }

            if (find) this.result.add(value);    //if all of them has it,add to result
        }
    }
}
