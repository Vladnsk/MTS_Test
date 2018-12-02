package utils;

public enum MainCategory {
    NOTEBOOKS_AND_TABLETS("Ноутбуки и планшеты"),
    SMARTPHONES_AND_SMARTWATCH("Смартфоны и смарт-часы");

    private String category;

    MainCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
