package arrayspractice;

import java.util.Arrays;

public class ArraysPractice {

    public static int[] arrInt = {50, 10, 25, 20};
    String[] arrStr = {"S", "B", "E", "D"};

    public static void main(String[] args) {
        
        for (int i = 0; i < arrInt.length - 1; i++) {
            int maxVal = i;
            for (int j = i + 1; j < arrInt.length; j++) {
                if (arrInt[j] < arrInt[maxVal]) {
                    maxVal = j;
                }
            }
            
            int val = arrInt[i];
            arrInt[i] = arrInt[maxVal];

            arrInt[maxVal]= val;

        }
        System.out.println(Arrays.toString(arrInt));

    }

}
