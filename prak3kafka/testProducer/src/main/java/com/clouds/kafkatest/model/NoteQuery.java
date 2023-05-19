package com.clouds.kafkatest.model;

import java.math.BigDecimal;

public class NoteQuery {
    private String name;
    private String text;
    private BigDecimal avg_rate;

    public NoteQuery() {
    }

    public NoteQuery(String name, String text, BigDecimal avg_rate) {
        this.name = name;
        this.text = text;
        this.avg_rate = avg_rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigDecimal getAvg_rate() {
        return avg_rate;
    }

    public void setAvg_rate(BigDecimal avg_rate) {
        this.avg_rate = avg_rate;
    }
}
