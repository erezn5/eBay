package com.ebay.automation.tests.components;

public class Rating {

    String Source;
    String Value;

    public void setSource(String source) {
        Source = source;
    }

    public void setValue(String value) {
        Value = value;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "Source='" + Source + '\'' +
                ", Value='" + Value + '\'' +
                '}';
    }

    public String getSource() {
        return Source;
    }

    public String getValue() {
        return Value;
    }
}
