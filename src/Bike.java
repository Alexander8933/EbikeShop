class Bike {
    private String title;
    private int price;
    private int number;

    protected Bike(String title, int price, int number) {
        this.title = title;
        this.price = price;
        this.number = number;
    }


    protected String getTextBikePresentation() {
        return "Title : " + title + " Price : " + price + " Number : " + number;
    }

    protected String getValueBikeInSaveTxt() {
        return title + "#" + price + "#" + number + "#";
    }

    protected void setTitle(String title) {
        if (!(title.isEmpty())) {
            this.title = title;
        }
    }

    protected void setPrice(int price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    protected void setNumber(int number) {
        if (number >= 0) {
            this.number = number;
        }
    }

    protected String getTitle() {
        return title;
    }

    protected int getNumber() {
        return number;
    }

    protected int getPrice() {
        return price;
    }


}
