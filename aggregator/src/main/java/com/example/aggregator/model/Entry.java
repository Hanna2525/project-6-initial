package com.example.aggregator.model;

import java.util.Objects;

public class Entry implements Comparable<Entry> {

    private String word;
    private String definition;

    public Entry() {
        this.word = "Doh!";
        this.definition = "A deer, a female deer! [No definition found!]";
    }

    public Entry(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(word, entry.word);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(word);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Entry {");
        sb.append("word='").append(word).append('\'');
        sb.append(", definition='").append(definition).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Entry that) {

        if(this.word.compareTo(that.word) > 0) {
            return 1;
        } else if (this.word.compareTo(that.word) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}

