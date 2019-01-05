package main.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by pengweijie on 2018/11/27
 */
public class Page {
    private Integer currentPage;
    private Integer prevPage;
    private Integer nextPage;
    private List<Score> showScores =new ArrayList<Score>();
    private List<Integer> showPageNums =new ArrayList<Integer>();
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    public Integer getPrevPage() {
        return prevPage;
    }
    public void setPrevPage(Integer prevPage) {
        this.prevPage = prevPage;
    }
    public Integer getNextPage() {
        return nextPage;
    }
    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }
    public List<Score> getShowScores() {
        return showScores;
    }
    public void setShowScores(List<Score> showScores) {
        this.showScores = showScores;
    }
    public List<Integer> getShowPageNums() {
        return showPageNums;
    }
    public void setShowPageNums(List<Integer> showPageNums) {
        this.showPageNums = showPageNums;
    }
}
