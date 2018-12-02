package utils;

public enum PriceSort {
    MAX_TO_LOW("по убыванию цены"),
    LOW_TO_MAX("по возрастанию цены");

    private String criteria;

    PriceSort(String criteria) {
        this.criteria = criteria;
    }

    public String getCriteria() {
        return criteria;
    }
}
