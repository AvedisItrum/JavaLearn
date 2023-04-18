import java.math.BigDecimal;
import java.util.*;
import java.util.stream.*;


public class CodeWars {

    public static int Multiples(int number) {
        if (number < 0) return 0;
        HashSet<Integer> values = new HashSet<>();
        int third = 3;
        int fifth = 5;

        while (third < number) {
            values.add(third);
            third += 3;
        }
        while (fifth < number) {
            values.add(fifth);
            fifth += 5;
        }
        return values.stream().reduce(Integer::sum).get();
    }

    public static int VowelsInString(String str) {
        return (int) str.chars()
                .filter(x -> "aeiou".indexOf(x) != -1)
                .count();
    }

    public static String Disemvowel(String str) {
        StringBuilder sB = new StringBuilder();

        str.chars()
                .filter(x -> "aeiouAEIOU".indexOf(x) == -1)
                .forEach(x -> sB.append((char) x));
        return sB.toString();
    }

    public static String WhoLikesIt(String... names) {
        return switch (names.length) {
            case 0 -> "no one likes this";
            case 1 -> String.format("%s likes this", names[0]);
            case 2 -> String.format("%s and %s like this", names[0], names[1]);
            case 3 -> String.format("%s, %s and %s like this", names[0], names[1], names[2]);
            default -> String.format("%s, %s and %d others like this", names[0], names[1], names.length - 2);
        };
    }

    public static String CreatePhoneNumber(int[] n) {
        return String.format("(%d%d%d) %d%d%d-%d%d%d%d", n[0], n[1], n[2], n[3], n[4], n[5], n[6], n[7], n[8], n[9]);

      /*  StringBuilder str = new StringBuilder();
        Arrays.stream(n).forEach(str::append);
        str.insert(6, '-');
        str.insert(3, ") ");
        str.insert(0, '(');
        return str.toString();*/
    }

    //5
    public static int SquareDigits(int n) {
        StringBuilder sb = new StringBuilder();
        int buf;
        do {
            buf = n % 10;
            n /= 10;
            sb.insert(0, buf * buf);
        } while (n != 0);
        return Integer.parseInt(sb.toString());
    }

    public static int findOdd(int[] a) {
        HashSet<Integer> integers = new HashSet<>();
        for (int i : a)
            if (!integers.add(i))
                integers.remove(i);
        return integers.stream().findFirst().get();
    }

    public static String highAndLow(String numbers) {
        numbers = numbers.replaceAll(" ", "\n");
        List<Integer> ints = new ArrayList<>();

        numbers.lines().forEach(x -> ints.add(Integer.valueOf(x)));
        IntSummaryStatistics stats = ints.stream().mapToInt(Integer::intValue).summaryStatistics();

        return stats.getMax() + " " + stats.getMin();
    }

    public static int digital_root(int n) {
        if (n < 10)
            return n;

        List<Integer> ints = new ArrayList<>();

        do {
            ints.add(0, n % 10);
            n /= 10;
        } while (n != 0);

        return digital_root(ints.stream().reduce(Integer::sum).get());
    }

    public static boolean isSquare(int n) {
        return Math.sqrt(n) % 1 == 0;
    }

    //10
    public static String spinWords(String sentence) {
        return Arrays.stream(sentence.split(" "))
                .map(x -> x.length() > 4 ? new StringBuilder().append(x).reverse().toString() : x)
                .collect(Collectors.joining(" "));
    /*    sentence = sentence.replaceAll(" ", "\n");
        StringBuilder buffer = new StringBuilder();

        List<String> stringsDone = sentence.lines()
                .map(x -> {
                    if (x.length() > 4) {
                        x = buffer.append(x).reverse().toString();
                        buffer.setLength(0);
                    }
                    return x;
                })
                .collect(Collectors.toList());

        for (String s : stringsDone)
            buffer.append(s + " ");

        buffer.deleteCharAt(buffer.length() - 1);

        return buffer.toString();*/
    }

    public static int[] arrayDiff(int[] a, int[] b) {
        List<Integer> ints = new ArrayList<>();
        for (int i : b) ints.add(i);

        return Arrays.stream(a).filter(x -> !ints.contains(x)).toArray();

    }

    public static String getMiddle(String w) {
        int l = w.length();
        return (l % 2 == 0) ? (w.charAt((int) (l / 2 - 0.5)) + "" + w.charAt((l + 1) / 2)) : (w.charAt(l / 2) + "");

    }

    public static int sort(int num) {
        List<Integer> ints = new ArrayList<>();
        while (num != 0) {
            ints.add(0, num % 10);
            num /= 10;
        }
        ints.sort(Integer::compareTo);
        for (int i = 0; i < ints.size(); i++)
            num += ints.get(i) * Math.pow(10, i);
        return num;
    }

    public static List<Object> filterList(List<Object> list) {
        return list.stream()
                .filter(x -> x instanceof Integer)
                .toList();
    }

    //15
    public static boolean isIsogram(String str) {
        HashSet<Integer> chars = new HashSet<>();

        int[] ints = str.toLowerCase().chars().toArray();

        for (int i : ints)
            if (!chars.add(i))
                return false;

        return true;

        //  return str.length() == str.toLowerCase().chars().distinct().count();
    }

    public static int countBits(int n) {
        return Integer.bitCount(n);
    }

    public static boolean getXO(String str) {
        int i = 0;
        for (var c : str.toLowerCase().chars().toArray())
            if (c == 'x') i++;
            else if (c == 'o') i--;
        return i == 0;
    }

    public String toJadenCase(String phrase) {
        if (phrase == null || phrase.length() == 0)
            return null;
        return phrase.replaceAll(" ", "\n")
                .lines()
                .map(x -> x.replaceFirst(String.valueOf(x.charAt(0)), String.valueOf(Character.toUpperCase(x.charAt(0)))))
                .collect(Collectors.joining(" "));
    }

    public static int findShort(String s) {
        return s.replaceAll(" ", "\n")
                .lines()
                .min(Comparator.comparingInt(String::length))
                .get()
                .length();
    }

    //20
    public static String accum(String s) {
        StringBuilder string = new StringBuilder();
        int i = 0;
        for (int c : s.chars().toArray()) {
            String cha = (char) c + "";
            string.append(cha.toUpperCase() + cha.toLowerCase().repeat(i));
            if (i < s.length() - 1)
                string.append('-');
            i++;
        }
        return string.toString();
    }

    public static int duplicateCount(String text) {
        List<Character> chars = new ArrayList<>();
        int i = 0;
        for (int c : text.toLowerCase().chars().toArray()) {
            chars.add((char) c);
            if (chars.stream().filter(x -> x == (char) c).count() == 2)
                i++;
        }
        return i;
    }

    public static String makeComplement(String dna) {
        StringBuilder sB = new StringBuilder();
        Character[] f = {'A', 'T', 'C', 'G'};

        List<Character> orig = Arrays.asList(f);
        char[] s = {'T', 'A', 'G', 'C'};
        for (int ch : dna.chars().toArray())
            sB.append(s[orig.indexOf(Character.valueOf((char) ch))]);

        return sB.toString();
    }

    public static int findDifferent(int[] integers) {
        int odd = 0, even = 0, count = 0, i = 0;
        while ((odd == 0 || even == 0 || count % 2 == 0) && i < integers.length) {
            if (integers[i] % 2 == 0) {
                even = integers[i];
                count++;
            } else {
                odd = integers[i];
                count--;
            }
            i++;
        }
        return count < 0 ? even : odd;
    }

    static String encode(String word) {
        Map<Integer, Integer> chars = new HashMap<>();
        StringBuilder sB = new StringBuilder();
        word = word.toLowerCase();
        word.chars().forEach(x -> {
            if (chars.containsKey(x))
                chars.replace(x, chars.get(x) + 1);
            else
                chars.put(x, 1);
        });
        word.chars().forEach(x -> {
            sB.append(chars.get(x) == 1 ? '(' : ')');
        });
        return sB.toString();
    }

    //25
    public static int GetSum(int a, int b) {
        return Stream.iterate(Math.min(a, b), n -> n + 1)
                .limit(Math.abs(Math.max(a, b) - Math.min(a, b)) + 1)
                .reduce(Integer::sum).get();

    }

    public static boolean isValid(char[] walk) {
        if (walk.length != 10)
            return false;
        int horizontal = 0, vertical = 0;
        for (char c : walk)
            switch (c) {
                case 'n' -> vertical++;
                case 's' -> vertical--;
                case 'w' -> horizontal++;
                case 'e' -> horizontal--;
            }
        return horizontal == 0 && vertical == 0;
    }

    public static int persistence(long n) {
        int k = 0;
        return pers(n, k);
    }

    static int pers(long n, int k) {
        if (n < 10)
            return k;
        k++;
        List<Long> longs = new ArrayList<>();
        do {
            longs.add(0, n % 10);
            n /= 10;
        } while (n != 0);

        return pers(longs.stream().reduce((x, y) -> x * y).get(), k);
    }

    public static boolean solution(String str, String ending) {
        return str.endsWith(ending);
    }

    public static String maskify(String str) {
        //Регулярки
        //   return str.replaceAll(".(?=.{4})", "#"); Как это понимат?

        return "#".repeat(Math.max(0, str.length() - 4)) +
                str.substring(Math.max(0, str.length() - 4));
    }

    //30
    static String toCamelCase(String s) {
        if (s.equals(""))
            return s;
        char[] chars = Arrays.stream(s.split("[-_]"))
                .map(x -> {
                    var charArr = x.toCharArray();
                    charArr[0] = Character.toUpperCase(charArr[0]);
                    return new String(charArr);
                })
                .reduce((x, y) -> x + y).get().toCharArray();
        chars[0] = s.charAt(0);
        return new String(chars);
    }

    public static boolean isTriangle(int a, int b, int c) {
        if (a > b && a > c)
            return a < (b + c);
        if (b > a && b > c)
            return b < (a + c);
        return c < (a + b);

        //   return a < (b + c) && b < (a + c) && c < (a + b);
    }

    public static String binaryAddition(int a, int b) {
        return Integer.toBinaryString(a + b);
    }

    public static String order(String words) {
        return Arrays.stream(words.split(" "))
                .sorted(Comparator.comparingInt(x -> Integer.parseInt(x.replaceAll("\\D", ""))))
                .collect(Collectors.joining(" "));
    }

    public static int nbYear(int p0, double percent, int aug, int p) {
        int i = 0;
        while (p0 < p) {
            i++;
            p0 = (int) (p0 * ((100 + percent) / 100)) + aug;
        }
        return i;
    }

    //35
    public static String longest(String s1, String s2) {
        return (s1 + s2).chars()
                .distinct()
                .sorted()
                .mapToObj(x -> String.valueOf((char) x))
                .collect(Collectors.joining());
    }

    //13.04
    public static boolean validatePin(String pin) {
        int l = pin.length();
        return (l == 4 || l == 6) & pin.replaceAll("\\D", "").length() == l;
        //  return pin.matches("[0-9]{4}|[0-9]{6}")
    }

    public static boolean check(String sentence) {
      /*  for (char c = 'a'; c <= 'z'; c++)
            if (sentence.toLowerCase().indexOf(c) == -1)
                return false;
        return true;*/
        List<Character> alphabet = Stream.iterate(65, i -> i < 91, i -> i += 1)
                .mapToInt(x -> x)
                .mapToObj(x -> (char) x)
                .collect(Collectors.toList());
        sentence.chars()
                .forEach(x -> alphabet.remove(Character.valueOf(Character.toUpperCase((char) x))));
        return alphabet.size() == 0;
    }

    public static int rowSumOddNumbers(int n) {
        //return n*n*n; -_-

        int eI = 0, sum = 0;
        for (int i = n - 1; i > 0; i--)
            eI += i;
        eI = eI * 2 - 1;
        for (int i = 1; i <= n; i++)
            sum += eI + 2 * i;
        return sum;
    }

    public static long findNextSquare(long sq) {
        double pow = Math.pow(Math.sqrt(sq) + 1, 2);
        return (pow % 1 != 0) ? -1 : (long) pow;
    }

    public static boolean isNarcissistic(int number) {
        int pow = Integer.toString(number).length();
        int bufNum = number;
        int sum = 0;
        while (bufNum != 0) {
            sum += Math.pow(bufNum % 10, pow);
            bufNum /= 10;
        }
        return sum == number;
    }

    public double[] tribonacci(double[] s, int n) {
        if (s.length < 3)
            return new double[0];
        double[] rS = new double[n];
        System.arraycopy(s, 0, rS, 0, Math.min(n, 3));
        for (int i = 3; i < n; i++)
            rS[i] = rS[i - 1] + rS[i - 2] + rS[i - 3];

        return rS;
    }

    public static String printerError(String s) {
        return s.replaceAll("[a-m]","").length()+"/"+s.length();
    }

    // 43/74

    public static void main(String[] str) {

    }
}
