package com.aqacources.tests.enums;

/**
 * Created by Marina on 03.03.2019.
 */
public enum CategoriesBreadcrumb {

    //  These are values
    WOMEN("Women"),
    DRESSES("Dresses"),
    TOPS("Tops"),
    TSHIRTS("T-shirts");

    private String name;

    /**
     * Constructor
     *
     * @param name
     */
    CategoriesBreadcrumb(String name) {
        this.name = name;
    }

    /**
     * Getter of Category
     *
     * @return string category
     */
    public String getName() {
        return name;
    }
}
