package com.dxl.techreading.model;

import java.util.List;

/**
 * @author dxl
 * @date 2018/11/12 13:49
 */
public class TestGsons {
    private boolean error;
    private List<CategoryResult> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<CategoryResult> getResults() {
        return results;
    }

    public void setResults(List<CategoryResult> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "TestGsons{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
