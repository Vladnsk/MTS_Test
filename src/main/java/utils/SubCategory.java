package utils;

public enum SubCategory {
    NOTEBOOKS("Ноутбуки"),
    TABLETS("Планшеты"),
    EBOOK("Электронные книги"),
    SMARTPHONES("Смартфоны");

    private String category;

    SubCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
