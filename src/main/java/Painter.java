import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rondy on 14/04/2017.
 */
public class Painter {

    private String hash;
    private String value;
    private Graphics2D graphics;

    private int size = 250;
    private int margin = size / 10; //margin 10% of size
    private int halfMargin = margin / 2;
    private int base = (size - margin) / 5;
    private int width = 5 * base;
    private int height = 5 * base;

    public Painter(String value, String hash) {
        this.value = value;
        this.hash = hash;
    }

    private void paintBackground() {
        graphics.setPaint(getBackgroundColor());
        graphics.fill(new Rectangle(0, 0, width + margin, height + margin));
    }

    private void paintIdenticon() {

        List<Cell> cells = new ArrayList<Cell>();
        Color foregroundColor = getForegroundColor();

        while (isTooBrighter(foregroundColor)) {
            foregroundColor = foregroundColor.darker();
        }

        int i = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 5; y++) {
                int hashInt = Character.getNumericValue(hash.charAt(i));
                if (hashInt % 2 == 0) {
                    cells.add(new Cell(x, y));
                }
                i++;
            }
        }

        List<Cell> mirroredCells = new ArrayList<Cell>();
        for (Cell cell : cells) {
            mirroredCells.add(new Cell(4 - cell.getPosX(), cell.getPosY()));
        }

        graphics.setPaint(foregroundColor);
        for (Cell cell : cells) {
            graphics.fill(cell.getShape());
        }
        for (Cell cell : mirroredCells) {
            graphics.fill(cell.getShape());
        }
    }

    class Cell {
        private int posX;
        private int posY;
        private int finalPosX;
        private int finalPosY;

        public Cell(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
            this.finalPosX = this.posX * base + halfMargin;
            this.finalPosY = this.posY * base + halfMargin;
        }

        public int getFinalPosX(){
            return finalPosX;
        }
        public int getFinalPosY(){
            return finalPosY;
        }
        public int getPosX() {
            return posX;
        }
        public int getPosY() {
            return posY;
        }
        public Shape getShape(){
            return new Rectangle(this.finalPosX, this.finalPosY, base, base);
        }
    }

    /*
    Backgronund color must be calculate
     */
    private Color getBackgroundColor() {
        return Color.WHITE;
    }

    /*
    Foreground color is calculates with last 9 characters of hash
     */
    private Color getForegroundColor() {
        StringBuilder color = new StringBuilder();
        for (int x = hash.length() - 9; x < hash.length(); x++) {
            color.append(Character.getNumericValue(hash.charAt(x)));
        }
        return new Color(Integer.parseInt(color.substring(0, 9)));

    }

    //temporary. Create a new method with predefined colors (maybe)
    private boolean isTooBrighter(Color color) {
        int brightness = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
        int whiteBrightness = (299 * Color.WHITE.getRed() + 587 * Color.WHITE.getGreen() + 114 * Color.WHITE.getBlue()) / 1000;
        int difference = whiteBrightness - brightness;
        if (difference <= 100) {
            return true;
        }
        return false;
    }

    public void paint() throws IOException {

        BufferedImage bufferedImage = new BufferedImage(width + margin, height + margin, BufferedImage.TYPE_INT_ARGB);
        graphics = bufferedImage.createGraphics();

        paintBackground();
        paintIdenticon();

        ImageIO.write(bufferedImage, "PNG", new File("./image-" + value + "-" + hash + ".png"));
        graphics.finalize();
        bufferedImage.flush();
    }
}
