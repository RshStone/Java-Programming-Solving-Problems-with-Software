
/**
 * Write a description of Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test{
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1 || index >= input.length() - 3) {
                break;
            }
            System.out.println(index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
        }
        //code
        System.out.println("index after updating " + index);
    }
    public void test() {
        //findAbc("abcd");
        findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
                 01234567890123456789012345678901234567890123
    //no code yet
    }
}

