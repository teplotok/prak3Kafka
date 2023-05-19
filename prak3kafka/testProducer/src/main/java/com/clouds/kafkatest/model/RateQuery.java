package com.clouds.kafkatest.model;

public class RateQuery {
    private Integer rate;
    private long count_notes;

    public RateQuery() {
    }

    public RateQuery(Integer rate, long count_notes) {
        this.rate = rate;
        this.count_notes = count_notes;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public long getCount_notes() {
        return count_notes;
    }

    public void setCount_notes(long count_notes) {
        this.count_notes = count_notes;
    }
}
