public class Tips {
    boolean floatCompare(float f1, float f2) {
        final float EPS = 1E-6F;
        return Math.abs(f1 - f2) < EPS; // true
    }

}
