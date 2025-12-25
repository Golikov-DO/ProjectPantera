package com.javarush.khmelov.lesson16.p04_builder.modern;

public class ModernPizza {
    private String dough;
    private String sauce;
    private String topping;

    ModernPizza(String dough, String sauce, String topping) {
        this.dough = dough;
        this.sauce = sauce;
        this.topping = topping;
    }

    public static ModernPizzaBuilder builder() {
        return new ModernPizzaBuilder();
    }

    public static class ModernPizzaBuilder {
        private String dough;
        private String sauce;
        private String topping;

        ModernPizzaBuilder() {
        }

        public ModernPizzaBuilder dough(String dough) {
            this.dough = dough;
            return this;
        }

        public ModernPizzaBuilder sauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public ModernPizzaBuilder topping(String topping) {
            this.topping = topping;
            return this;
        }

        public ModernPizza build() {
            return new ModernPizza(this.dough, this.sauce, this.topping);
        }

        public String toString() {
            return "ModernPizza.ModernPizzaBuilder(dough=" + this.dough + ", sauce=" + this.sauce + ", topping=" + this.topping + ")";
        }
    }
}
