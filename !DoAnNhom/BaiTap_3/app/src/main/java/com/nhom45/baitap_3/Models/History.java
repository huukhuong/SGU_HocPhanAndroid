package com.nhom45.baitap_3.Models;

public class History {

    private int id;
    private String codeFrom;
    private String valueInput;
    private String codeTo;
    private String valueOutput;
    private String createdAt;

    public History() {
    }

    public History(int id, String codeFrom, String valueInput, String codeTo, String valueOutput, String createdAt) {
        this.id = id;
        this.codeFrom = codeFrom;
        this.valueInput = valueInput;
        this.codeTo = codeTo;
        this.valueOutput = valueOutput;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeFrom() {
        return codeFrom;
    }

    public void setCodeFrom(String codeFrom) {
        this.codeFrom = codeFrom;
    }

    public String getValueInput() {
        return valueInput;
    }

    public void setValueInput(String valueInput) {
        this.valueInput = valueInput;
    }

    public String getCodeTo() {
        return codeTo;
    }

    public void setCodeTo(String codeTo) {
        this.codeTo = codeTo;
    }

    public String getValueOutput() {
        return valueOutput;
    }

    public void setValueOutput(String valueOutput) {
        this.valueOutput = valueOutput;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", codeFrom='" + codeFrom + '\'' +
                ", valueInput='" + valueInput + '\'' +
                ", codeTo='" + codeTo + '\'' +
                ", valueOutput='" + valueOutput + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
