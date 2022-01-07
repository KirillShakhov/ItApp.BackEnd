package ru.itapp.entity;

public class View {
    public interface User { }
    public interface Category { }
    public interface Recipe { }
    public interface RecipeAllInfo extends Recipe, Ingredients, RecipeStep { }
    public interface Product { }
    public interface RecipeCategory { }
    public interface RecipeStep { }
    public interface Ingredients extends Product { }
    public interface Profile { }
    public interface Menu { }
    public interface MenuAllInfo extends Menu, Recipe { }
}