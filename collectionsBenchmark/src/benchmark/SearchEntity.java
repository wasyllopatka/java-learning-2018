package benchmark;

public class SearchEntity {
    private int first;
    private int middle;
    private int last;

    public SearchEntity(int first, int middle, int last) {
        this.first = first;
        this.middle = middle;
        this.last = last;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getMiddle() {
        return middle;
    }

    public void setMiddle(int middle) {
        this.middle = middle;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "SearchEntity{" +
                "first=" + first +
                ", middle=" + middle +
                ", last=" + last +
                '}';
    }
}
