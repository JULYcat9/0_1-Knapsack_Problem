package main.java.blog;

import java.util.List;

public class sortList implements Comparable<sortList>{

//    每一个数据项集
    private List<item> data;

//    每一个数据项集的第三个物品的价值重量比
    private Float rate;

    public List<item> getData() {
        return data;
    }

    public void setData(List<item> data) {
        this.data = data;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "["
                + data +
                ", " + rate +
                ']';
    }

    @Override
    public int compareTo(sortList o) {
        if(this.rate<o.rate){
            return 1;
        }else if(this.rate==o.rate){
            return 0;
        }else {
            return -1;
        }
    }
}
