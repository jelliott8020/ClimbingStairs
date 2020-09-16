import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/*
Climbing Stairs
You're a rebel scout on the ice planet, Hoth. While killing time waiting for the Empire to find your base, you ride your trusty Tauntaun around the plant. You wander upon a very tall ice staircase leading up a mountainside that takes n steps to reach. Having plenty of time on your hands, you decide you want to figure out how many ways are there to reach the top. The catch is that for each step, your Tauntauan can only climb 1 or 2 steps at a given time.
Your job is to write a function 'climbingStairs' that will return the total number of ways you can reach the top.
'climbingStairs' should take the following parameter:
•	totalSteps: an integer, total n steps to the top.
Constraints:
•	0 <= totalSteps < infinity
•	For each given step, you can only take 1 or 2 steps at a time
Sample Input:
•	Given: 2
•	Output: 2
o	There are two ways to the top (1 + 1) and (2).
Sample Input:
•	Given: 3
•	Output: 3
o	There are three ways to the top (1 + 1 + 1), (1 + 2), and (2 + 1).
*/



/**
 * This is essentially finding the fibonnaci sequence shifted 1 position ahead
 * 
 * 1 = 1  -> 1
 * 2 = 2  -> 11 2
 * 3 = 3  -> 111 21 12
 * 4 = 5  -> 1111 22 211 121 112
 * 5 = 8  -> 1111 221 212 122 2111 1211 1121 1112
 * 6 = 13 -> 11111 222 2211 2121 2112 1221 1212 1122 21111 12111 11211 11121 11112
 * 
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        Climber climber = new Climber();

        // Int can handle 45 steps before overflowing
        for (int i = 1; i <= 45; ++i) {
            System.out.println(i + " " + climber.climbingStairsWithInt(i));
        }

        // Long can handle 91 steps before overflowing
        for (int i = 1; i <= 91; ++i) {
            System.out.println(i + " " + climber.climbingStairsWithLong(i));
        }

        // BigInt can do the max length of a string, 2^31 -1
        for (int i = 1; i <= 5000; ++i) {
            System.out.println(i + " " + climber.climbingStairsWithBigInt(i));
        }

        // Store the first results in a map to make subsequent lookups fast
        for (int i = 1; i <= 5000; ++i) {
            System.out.println(i + " " + climber.climbingStairsWithBigInt(i));
        }

    }
}

class Climber {
    Map<Integer, BigInteger> map;

    public Climber() {
        this.map = new HashMap<>();
    }

    public int climbingStairsWithInt(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        // DP solution using variables instead of array
        int a = 1;
        int b = 1;
        int sum = 0;

        // O(n) time complexity, O(1) space
        for (int i = 2; i <= n; ++i) {
            sum = a + b;
            a = b;
            b = sum;
        }

        return sum;
    }

    public long climbingStairsWithLong(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        long a = 1;
        long b = 1;
        long sum = 0;

        for (int i = 2; i <= n; ++i) {
            sum = a + b;
            a = b;
            b = sum;
        }

        return sum;
    }

    public String climbingStairsWithBigInt(int n) {
        if (n == 0) return "0";
        if (n == 1) return "1";

        if (map.containsKey(n)) {
            return map.get(n).toString();
        }
        
        BigInteger a = new BigInteger("1");
        BigInteger b = new BigInteger("1");
        BigInteger sum = new BigInteger("0");

        for (int i = 2; i <= n; ++i) {
            sum = a.add(b);
            map.put(i, sum);
            a = b;
            b = sum;
        }

        return sum.toString();
    }
}




