public class Tips {
    boolean floatCompare(float f1, float f2) {
        final float EPS = 1E-6F;
        return Math.abs(f1 - f2) < EPS; // true

    }
    //Time
    // ArrayList.Add < LinkedList.Add
    // ArrayList.Append(0,x) >>>>> LinkedList.Append(0,x)
}
