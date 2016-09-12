import java.util.*;
/**
 * Does calculations on the data.
 * HashMap will not be changed!
 */
public class Calc
{
    /**
     * Sum of data.
     */
    public static double sum(HashMap h)
    {
        if(h.size() == 0)
        {
            return -1;
        }

        double total = 0;
        for(int i = 0 ; i < h.size() ; i++)
        {
            total += Integer.parseInt( (String) h.get(i) );
        }

        return total;
    }

    /**
     * Average of data.
     */
    public static double ave(HashMap h)
    {
        if(h.size() == 0)
        {
            return -1;
        }
        double total = 0;

        for(int i = 0; i < h.size() ; i++)
        {
            int nextNum = Integer.parseInt((String)h.get(i));
            total += nextNum;
        }

        return (total / h.size());
    }

    /**
     * Middle of data.
     */

    public static Integer median(HashMap h)
    {
        if(h.size() == 0)
        {
            return -1;
        }
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i = 0; i < h.size() ; i++)
        {
            int nextNum = Integer.parseInt( (String) h.get(i) );
            arr.add(nextNum);
        }
        Collections.sort(arr);

        if(arr.size() % 2 != 0)
        {
            int middle = arr.size() / 2;
            return arr.get(middle);
        }
        else
        {
            int oneValue = (int) (arr.size()/2);
            int anotherValue = (int) ((arr.size()/2) +1);
            int ave = (int) (arr.get(oneValue)) + (int) (arr.get(anotherValue));
            return ave/2;
        }
    }

    /**
     * Range of data.
     */
    public static int range(HashMap h)
    {
        if(h.size() == 0)
        {
            return -1;
        }
        int smallest = Integer.parseInt( (String) h.get(0) );
        int largest = Integer.parseInt( (String) h.get(0) );

        for(int i = 1 ; i < h.size() ; i++)
        {
            int num = Integer.parseInt( (String) h.get(i) );
            if(num < smallest)
            {
                smallest = num;
            }
            if(num > largest)
            {
                largest = num;
            }
        }

        return largest - smallest;
    }

    /**
     * Standard Deviation of data.
     */
    public static double sd(HashMap h)
    {
        if(h.size() == 0)
        {
            return -1;
        }
        double mean = ave(h);
        double[] arr = new double[h.size()];

        for(int i = 0; i < h.size() ; i++)
        {
            int num = Integer.parseInt( (String) h.get(i) );
            arr[i] = Math.pow(num - mean,2);
        }

        int sum = 0;
        for(int i = 0; i < arr.length ; i++)
        {
            sum += arr[i];
        }

        sum /= arr.length;

        return (Math.sqrt(sum));
    }
}
