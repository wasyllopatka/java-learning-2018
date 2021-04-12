public class MovingItem extends Sprite {

    private String icon;

    public MovingItem(int x, int y, int direction, String icon) {
        super(x, y, direction);
        this.icon = icon;
        initCar();
    }

    private void initCar() {
        loadImage(icon);
        getImageDimensions();
    }
}
