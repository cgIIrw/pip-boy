public class FindNumberWithSum {
    public static int[] findNumberWithSum(int[] data, int sum) {
        int left = 0;
        int right = data.length - 1;
        int[] re = new int[2];

        while ((data[left] + data[right]) != sum && left < right) {
            if ((data[left] + data[right]) < sum) {
                left = left + 1;
            } else if ((data[left] + data[right]) > sum) {
                right = right - 1;
            }
        }
        if ((data[left] + data[right]) == sum) {
            re[0] = data[left];
            re[1] = data[right];
            return re;

        }
        return null;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 4, 7, 11, 15};
        int sum = 18;
        System.out.println(findNumberWithSum(array, sum)[0]);
        System.out.println(findNumberWithSum(array, sum)[1]);
    }
}
