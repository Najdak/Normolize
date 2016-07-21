public class Tests {
    public static void main(String[] args) {
        double asdas = System.currentTimeMillis();
        Tests t = new Tests();
        int d = t.num(3, 5);
        System.out.println(d);
        System.out.println(asdas = System.currentTimeMillis() - asdas);
    }

    private int num(int i1, int i2){
        return i1-i2;
    }
}
