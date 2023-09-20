public enum Type implements java.lang.reflect.Type {
    FOOD, WATER, CLOTHE;

  public String typeName;

    @Override
    public String getTypeName() {
        return typeName;
    }
}
