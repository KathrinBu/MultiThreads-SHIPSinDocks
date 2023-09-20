public enum Size {
    S(5), M(10), L(15);
 public int value;

    public int getValue() {
        return value;
    }

    Size(int value) {
        this.value = value;
    }
}
