package main.vo;

/**
 * Create by pengweijie on 2018/12/1
 */

public class ScorePeople {
    private String scoreSection;//分数段
    private int num;//该分数段的人数
    private float passRate;//及格率

    public ScorePeople(String scoreSection, int num, float passRate) {
        this.scoreSection = scoreSection;
        this.num = num;
        this.passRate = passRate;
    }

    public ScorePeople(String scoreSection, int num) {
        this.scoreSection = scoreSection;
        this.num = num;
    }

    public float getPassRate() {
        return passRate;
    }

    public void setPassRate(float passRate) {
        this.passRate = passRate;
    }

    public String getScoreSection() {
        return scoreSection;
    }

    public void setScoreSection(String scoreSection) {
        this.scoreSection = scoreSection;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
