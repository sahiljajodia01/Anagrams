/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {
    ArrayList<String> wordList;
    HashSet<String> wordSet;
    ArrayList<String> words;
    HashMap<String, ArrayList<String>> lettersToWord;
    HashMap<String, ArrayList<String>> sizeToWord;

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        wordSet = new HashSet<>();
        lettersToWord = new HashMap<>();
        wordList = new ArrayList<>();
        while((line = in.readLine()) != null) {



            String sortedWord = sortLetters(line.trim());
            if(lettersToWord.containsKey(sortedWord)) {
                lettersToWord.get(sortedWord).add(line.trim());
            }
            else{
                words = new ArrayList<>();
                words.add(line.trim());
                lettersToWord.put(sortLetters(line.trim()), words);
            }
            wordList.add(line.trim());
            wordSet.add(line.trim());

        }
        Log.i("list", wordList.toString());
    }

    public String sortLetters(String word) {
        char tempArray[] = word.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public boolean isGoodWord(String word, String base) {
        if(wordSet.contains(word) && !word.contains(base)) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();

//        String sortedTargetWord = sortLetters(targetWord);
//
//        for(int i=0; i< wordList.size(); i++) {
//            if(targetWord.length() == wordList.get(i).length()) {
//                String sortedWordListWord = sortLetters(wordList.get(i));
//
//                if(sortedTargetWord.equalsIgnoreCase(sortedWordListWord)){
//                    result.add(wordList.get(i));
//                }
//            }
//        }

        return result = lettersToWord.get(sortLetters(targetWord));
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();

        for(char letter = 'a'; letter <= 'z'; letter++) {
            if(lettersToWord.containsKey(sortLetters(word + letter))){
                ArrayList<String> anagramList = lettersToWord.get(sortLetters(word + letter));

                for(int i = 0; i<anagramList.size(); i++) {
                    if(isGoodWord(anagramList.get(i), word)) {
                        Log.i("anangramWord", anagramList.get(i));
                        result.add(anagramList.get(i));
                    }
                }
            }
        }
//        Log.i("list", result.toString());
        return result;
    }

    public String pickGoodStarterWord() {
//        Random random = new Random();
//        int listLength = wordList.size();
//        int randomIndex = random.nextInt(listLength);
//
//        if(getAnagramsWithOneMoreLetter(wordList.get(randomIndex)).size() > 5) {
//            return wordList.get(randomIndex);
//        }
//        else {
//            return pickGoodStarterWord();
//        }
        return "sabre";
    }
}
