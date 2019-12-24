package ru.unn.agile.huffman.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import ru.unn.agile.huffman.model.Huffman;

public class HuffmanViewModel {
    private StringProperty input = new SimpleStringProperty();
    private StringProperty outputEncode = new SimpleStringProperty();
    private StringProperty outputDecode = new SimpleStringProperty();
    private boolean enabledButtonStart = true;

    public HuffmanViewModel() {
        input.set("Reference string");
        outputEncode.set("Encode");
        outputDecode.set("Decode");
    }

    public StringProperty getInput() {
        return input;
    }

    public void setInput(final String str) {
        input.set(str);
    }

    public StringProperty getOutputEncode() {
        return outputEncode;
    }

    public StringProperty getOutputDecode() {
        return outputDecode;
    }

    public void startEncodeAndDecode() {
        String inputString = input.get();
        outputEncode.set(Huffman.encodeString(inputString));
        outputDecode.set(inputString);
    }

    public boolean getButtonStartEnabled() {
        return enabledButtonStart;
    }
}
