package kz.arabro.planogram.nomenclature.domain.entity;

public class Size {

    private final int height;
    private final int weight;
    private final int length;

    public Size(int height, int weight, int length) {
        this.height = height;
        this.weight = weight;
        this.length = length;
    }

    public static Size of(int height, int weight, int length) {
        if (height < 0) {
            throw ProductError.errNegativeSizeHeightValue();
        }
        if (weight < 0) {
            throw ProductError.errNegativeSizeWeightValue();
        }
        if (length < 0) {
            throw ProductError.errNegativeSizeLengthValue();
        }

        return new Size(height, weight, length);
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getLength() {
        return length;
    }
}
