package main.java.blog;

public class item {

    private int profit;

    private int weight;

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "["+profit+","+weight+"]";
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
