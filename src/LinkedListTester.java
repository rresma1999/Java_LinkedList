/*  Student information for assignment:
 *
 *  On my honor, <NAME>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Ryan Resma
 *  email address: ryan.resma@utexas.edu
 *  UTEID: rmr3429
 *  Section 5 digit ID: 
 *  Grader name: Joseph Manahan
 *  Number of slip days used on this assignment: 0
 */

// Experiment results. CS314 students, place your experiment
// results here:

/*
 * Operations that are faster using ArrayList: Getting random, Getting all using get method
 * Operations that are faster using LinkedList: Adding at front, Removing from front, 
 * Operations that are about the same speed for both: Adding at end, Getting all using iterator
 * 
 * Adding at end:
 * 	ArrayList - O(N), as N number of elements is doubled, time to add an element at the end is also doubled
 * 	LinkedList - O(N), as N number of elements is doubled, time to add an element at the end is also doubled
 * 
 * Adding at front:
 * 	ArrayList - O(N^2), as N number of elements is doubled, time to add an element at the front is quadrupled
 * 	LinkedList - O(N), as N number of elements is doubled, time to add an element at the front is also doubled
 * 
 * Removing from front:
 * 	ArrayList - O(N^2), as N number of elements is doubled, time to remove an element from front is quadrupled
 * 	LinkedList - O(N), as N number of elements is doubled, time to remove an element from front is also doubled
 * 
 * Getting random:
 * 	ArrayList - O(N), as N number of elements is doubled, time to get a random element is also doubled
 * 	LinkedList - O(N^2), as N number of elements is doubled, time to get a random element is quadrupled
 * 
 * Getting all using iterator:
 * 	ArrayList - O(N), as N number of elements is doubled, time to get all using iterator is also doubled
 * 	LinkedList - O(N), as N number of elements is doubled, time to get all using iterator is also doubled
 * 
 * Getting all using get method:
 * 	ArrayList - O(N), as N number of elements is doubled, time to get all using get method is also doubled
 * 	LinkedList - O(N^2), as N number of element is doubled, time to get all using get method is quadrupled
 * 
 */


import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class LinkedListTester {

    public static void main(String[] args) {

        myTests();
        comparison();
    }
    
    // should have approximately 60 tests total at end, three per public method
    private static void myTests() {
    		LinkedList<String> list = new LinkedList<>();
    		
    		// test 1, isEmpty()
    		printTest("isEmpty", "testing constructor, list size should be 0", 
    				list.isEmpty() == true);
    		
    		// test 2, 3, 4, add(E item)/addLast(E item), get(int pos)
    		list.add("abc");
		printTest("add, addLast, getNode", "ability to mutate the list "
				+ "and add new element to end", list.get(0).equals("abc"));
    		
		// test 5, 6, 7, add(E item)/addLast(E item), get(int pos)
		list.add("$$$");
		printTest("add, addLast, getNode", "ability to mutate the list and add new element to end",
				list.get(1).equals("$$$"));
		
		// test 8, 9, 10, add(E item)/addLast(E item), get(int pos)
		list.add("DEFHIJKL");
		printTest("add, addLast, getNode", "ability to mutate the list and add new element to end",
				list.get(2).equals("DEFHIJKL"));
		
		// test 11, addFirst(E item)
		list.addFirst("Manahan is the GOAT");
		printTest("addFirst", "ability to mutate the list and add new element to front",
				list.get(0).equals("Manahan is the GOAT"));
		
		// test 12, isEmpty()
		printTest("isEmpty", "testing mutation of list, list size should be > 0", 
				list.isEmpty() == false);
		
		// test 13, 14, makeEmpty(), isEmpty
		list.makeEmpty();
		printTest("MakeEmpty, isEmpty", "testing the resetting of list, "
				+ "size shoud be 0", list.isEmpty() == true);
		
		// test 15, addFirst(E item)
		list.addFirst("Scott");
		printTest("addFirst", "ability to addFirst when the list is empty", 
				list.get(0).equals("Scott"));
		
		// test 16, insert(int pos, E item)
		list.insert(0, "Mike");
		printTest("insert", "ability to insert at position 0", list.get(0).equals("Mike"));
		
		// test 17, insert(int pos, E item)
		list.insert(2, "rocks");
		printTest("insert", "ability to insert when position = size", 
				list.get(2).equals("rocks"));
		
		// test 18, indexOf(E item)
		printTest("indexOf", "ability to locate the index of a passed in element", 
				list.indexOf("rocks") == 2);
		
		// test 19, 20, insert(int pos, E item), indexOf(E item)
		list.insert(1, "Freaking");
		printTest("insert, indexOf", "ability to insert an element "
				+ "and find the index of such element", list.indexOf("Freaking") == 1);
		
		// test 21, indexOf(E item)
		printTest("indexOf", "ability to handle a situation where an item does not exist in list",
				list.indexOf("Joseph") == -1);
		
		// test 22, toString()
		printTest("toString", "ability to convert list into a String format",
				list.toString().equals("[Mike, Freaking, Scott, rocks]"));
		
		// test 23, 24, remove(int pos), toString()
		list.remove(1);
		printTest("remove, toString", "ability to remove element at a specific position "
				+ "and see mutated list", list.toString().equals("[Mike, Scott, rocks]"));
		
		// test 25 remove(int pos)
		list.remove(0);
		printTest("remove", "ability to remove element at position 0 "
				+ "and see if elements shift down properly", list.indexOf("Scott") == 0);
		
		// test 26, remove(int pos)
		list.remove(1);
		printTest("remove", "ability to remove element at position = size() - 1", 
				list.indexOf("rocks") == -1);
		
		list.add("Sterling");
		list.add("The");
		list.add("Myth");
		list.add("The");
		list.add("Legend");
		
		// test 27, 28, removeFirst(), removeLast()
		list.removeLast();
		list.removeFirst();
		printTest("removeFirst, removeLast", "ability to remove first and last elements of list",
				list.toString().equals("[Sterling, The, Myth, The]"));
		
		// test 29, 30, removeFirst(), removeLast()
		list.removeLast();
		list.removeFirst();
		printTest("removeFirst, removeLast", "ability to remove first and last elements of list",
				list.toString().equals("[The, Myth]"));
		
		list.add("Ology");
		list.add("asdf");
		
		// test 31, 32, removeFirst(), removeLast()
		list.removeLast();
		list.removeFirst();
		printTest("removeFirst, removeLast", "ability to remove first and last elements of list",
				list.toString().equals("[Myth, Ology]"));
		
		// test 33, makeEmpty()
		list.makeEmpty();
		printTest("makeEmpty", "ability to empty a list", list.toString().equals("[]"));
		
		// test 34, makeEmpty()
		list.makeEmpty();
		printTest("makeEmpty", "ability to call makeEmpty() on an empty list", list.size() == 0);
		
		// test 35, remove(E obj)
		printTest("remove", "ability to call remove(E obj) on empty list and return false", 
				list.remove("") == false);
		
		list.add("Joseph");
		list.add("Manahan");
		list.add("The");
		list.add("Hor$e");
		list.add("Master");
		
		// test 36, remove(E obj)
		list.remove("The");
		printTest("remove", "ability to remove(E obj) from a list and mutate it", 
				list.indexOf("The") == -1);
		
		// test 37, remove(E obj)
		list.remove("Master");
		printTest("remove", "ability to remove(E obj) form a list and mutate it",
				list.toString().equals("[Joseph, Manahan, Hor$e]"));
		
		list.add("Master");
		
		// test 38, set(int pos, E item)
		list.set(2, "CS");
		printTest("set", "ability to set an element with new data and mutate list", 
				list.get(2).equals("CS"));
		
		// test 39, set(int pos, E item)
		list.set(0, "Mr.");
		printTest("set", "ability to set an element with new data and mutate list", 
				list.indexOf("Mr.") == 0);
		
		// test 40, set(int pos, E item)
		list.set(list.size() - 1, "Mentor");
		printTest("set", "ability to set an element with new data and mutate list", 
				list.toString().equals("[Mr., Manahan, CS, Mentor]"));
		
		// test 41, 42 getSublist(int start, int stop), equals()
		LinkedList<String> sublist = (LinkedList<String>)list.getSubList(0, list.size());
		printTest("getSubList, equals", "ability to get a sublist that is a "
				+ "copy of the original list", sublist.equals(list));
		
		// test 43, 44, 45, getSublist(int start, int stop), equals(), toString()
		sublist = (LinkedList<String>)list.getSubList(1, 3);
		printTest("getSubList, equals", "ability to get a sublist that is a "
				+ "portion of the original list", sublist.equals(list) == false);
		
		// test 46, 47, getSublist(int start, int stop), equals()
		sublist = (LinkedList<String>)list.getSubList(1, 1);
		LinkedList<String> emptyList = new LinkedList<String>();
		printTest("getSubList", "ability to get a sublist that is empty", 
				sublist.equals(emptyList));
		
		// test 48, size()
		printTest("size", "ability to return the size of an empty list", emptyList.size() == 0);
		
		// test 49, size()
		printTest("size", "ability to return the size of a list", list.size() == 4);
		
		// test 50, addFirst()
		emptyList.addFirst("Hello");
		printTest("addFirst", "ability to addFirst() in an empty list", 
				emptyList.get(0).equals("Hello"));
		
		// test 51, size()
		printTest("size", "ability to return the size of a mutated list", emptyList.size() == 1);
		
		LinkedList<String> list2 = new LinkedList<String>();
		list2.add("Ryan");
		list2.add("Resma");
		list2.add("is");
		list2.add("a");
		list2.add("CS");
		list2.add("Major");
		
		// test 52, indexOf(E item, int pos)
		printTest("indexOf", "ability to find an element when parsing from "
				+ "specified pos to end of list", list2.indexOf("CS", 3) == 4);
		
		// test 53, indexOf(E item, int pos)
		printTest("indexOf", "ability to find an element when parsing from "
				+ "specified pos to end of list", list2.indexOf("CS", 4) == 4);	
		
		// test 54, indexOf(E item, int pos)
		printTest("indexOf", "ability to find an element when parsing from "
				+ "specified pos to end of list", list2.indexOf("CS", 5) == -1);
		
		// test 55, removeRange(int start, int stop)
		list2.removeRange(3, 3);
		printTest("removeRange", "ability to call removeRange() with start and stop "
				+ "equalling each other, list should be unchanged", list2.size() == 6);
		
		// test 56, removeRange(int start, int stop)
		list2.removeRange(2, 4);
		printTest("removeRange", "ability to remove a range of elements", 
				list2.toString().equals("[Ryan, Resma, CS, Major]"));
		
		// test 56, removeRange(int start, int stop)
		list2.removeRange(0, 4);
		printTest("removeRange", "ability to remove all the elements in list with removeRange()", 
				list2.isEmpty());
		
		// test 57 - 60, iterator() methods
		LinkedList<String> list3 = new LinkedList<String>();
		list3.add("abc");
		list3.add("def");
		list3.add("hij");
		list3.add("klm");
		list3.add("nop");
		list3.add("qrs");
		list3.add("tuv");
		list3.add("wxyz");
		
		Iterator listItr = list3.iterator();
		printTest("iterator hasNext", "ability to check if there are more "
				+ "elements to parse over using iterator", listItr.hasNext() == true);
		printTest("iterator next", "ability to parse over an element using an iterator", 
				listItr.next().equals("abc"));
		listItr.remove();
		printTest("iterator remove", "ability to remove an element from list using an iterator", 
				list3.indexOf("abc") == -1);
		
		while (listItr.hasNext()) {
			listItr.next();
			listItr.remove();
		}
		
		printTest("iterator class", "ability to parse a linked list using an iterator "
				+ "and removing all elements", list3.isEmpty());

	}
    
    // helper method to help print test
    private static void printTest(String testName, String testingWhat, boolean correct) {
    		StringBuilder sb = new StringBuilder();
    		sb.append("test(s): ");
    		sb.append(testName);
    		sb.append(" - testing the ");
    		sb.append(testingWhat);
    		if (correct) {
    			sb.append(", \ntest has passed, pop the champagne!!");
    		} else {
    			sb.append(", \ntest has failed... :-(");
    		}
    		System.out.println(sb.toString());
    }

    private static Object[] toArray(LinkedList<String> list) {
        Object[] result = new Object[list.size()];
        Iterator<String> it = list.iterator();
        int index = 0;
        while( it.hasNext() ){
            result[index] = it.next();
            index++;
        }
        return result;
    }

    //pre: none
    private static boolean arraysSame(Object[] one, Object[] two)  {
        boolean same;
        if( one == null || two == null ) {
            same = (one == two);
        }
        else {
            //neither one or two are null
            assert one != null && two != null;
            same = one.length == two.length;
            if( same ) {
                int index = 0;
                while( index < one.length && same ) {
                    same = ( one[index].equals(two[index]) );
                    index++;
                }
            }
        }
        return same;
    }
   
    
    public static final int NUM_DOUBLINGS_OF_N = 5;
    private static final int NUM_REPEATS_OF_TEST = 100;

    // A method to be run to compare the LinkedList you are completing and the Java ArrayList class
    public static void comparison(){
        Stopwatch s = new Stopwatch();
        
        int initialN = 30000;
        addEndArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        addEndLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 2000;
        addFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 10000;
        addFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 2000;
        removeFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 5000;
        removeFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 10000;
        getRandomArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 1000;
        getRandomLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 50000;
        getAllArrayListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);
        getAllLinkedListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 100000;
        getAllArrayListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 1000;
        getAllLinkedListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);

    }

    // These pairs of method illustarte a failure to use polymorphism
    // If the students had implemented the Java list interface there
    // could be a single method. Also we could use function objects to
    // reduce the awful repitition of code.
    public static void addEndArrayList(Stopwatch s, int initialN, int numTests){

        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++){
                ArrayList<Integer> javaList = new ArrayList<Integer>();
                s.start();
                for(int j = 0; j < n; j++)
                    javaList.add(j);
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at end: ArrayList", totalTimes, initialN);
    }

    private static void showResults(String title, double[] times, int initialN) {
        System.out.println();
        System.out.println("Num Repeats: " + NUM_REPEATS_OF_TEST);
        System.out.println(title);
        for(double time : times) {
            System.out.print("N = " + initialN + ", total time: ");
            System.out.printf("%7.4f\n", time);
            
            initialN *= 2;
        }
        System.out.println();
    }

    public static void addEndLinkedList(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++){
                LinkedList<Integer> studentList = new LinkedList<Integer>();
                s.start();
                for(int j = 0; j < n; j++)
                    studentList.add(j);
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at end: LinkedList", totalTimes, initialN);
    }

    public static void addFrontArrayList(Stopwatch s, int initialN, int numTests){

        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++){
                ArrayList<Integer> javaList = new ArrayList<Integer>();
                s.start();
                for(int j = 0; j < n; j++)
                    javaList.add(0, j);
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at front: ArrayList", totalTimes, initialN);
    }

    public static void addFrontLinkedList(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++){
                LinkedList<Integer> studentList = new LinkedList<Integer>();
                s.start();
                for(int j = 0; j < n; j++)
                    studentList.insert(0, j);
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at front: LinkedList", totalTimes, initialN);
    }

    public static void removeFrontArrayList(Stopwatch s, int initialN, int numTests){     
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++){
                ArrayList<String> javaList = new ArrayList<String>();
                for(int j = 0; j < n; j++)
                    javaList.add(j + "");
                s.start();
                while(!javaList.isEmpty())
                    javaList.remove(0);
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Removing from front: ArrayList", totalTimes, initialN);
    }

    public static void removeFrontLinkedList(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;    
            for(int i = 0; i < numTests; i++){
                LinkedList<String> studentList = new LinkedList<String>();
                for(int j = 0; j < n; j++)
                    studentList.add(j + "");
                s.start();
                while( studentList.size() != 0 )
                    studentList.removeFirst();
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("removing from front: LinkedList", totalTimes, initialN);
    }

    public static void getRandomArrayList(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            int total = 0;
            Random r = new Random();
            for(int i = 0; i < numTests; i++){
                ArrayList<Integer> javaList = new ArrayList<Integer>();
                for(int j = 0; j < n; j++)
                    javaList.add(j);
                s.start();
                for(int j = 0; j < n; j++){
                    total += javaList.get( r.nextInt(javaList.size()) );
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting random: ArrayList", totalTimes, initialN);
    }

    public static void getRandomLinkedList(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            int total = 0;
            Random r = new Random();
            for(int i = 0; i < numTests; i++){
                LinkedList<Integer> studentList = new LinkedList<Integer>();
                for(int j = 0; j < n; j++)
                    studentList.add(j);
                s.start();
                for(int j = 0; j < n; j++){
                    total += studentList.get( r.nextInt(studentList.size()) );
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting random: LinkedList", totalTimes, initialN);
    }

    public static void getAllArrayListUsingIterator(Stopwatch s, int initialN, int numTests){

        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            int total = 0;
            for(int i = 0; i < numTests; i++){
                ArrayList<Integer> javaList = new ArrayList<Integer>();
                for(int j = 0; j < n; j++)
                    javaList.add(j);
                Iterator<Integer> it = javaList.iterator();
                s.start();
                while( it.hasNext() ){
                    total += it.next();
                }        
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using iterator: ArrayList", totalTimes, initialN);
    }

    public static void getAllLinkedListUsingIterator(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            int total = 0;
            for(int i = 0; i < numTests; i++){
                LinkedList<Integer> studentList = new LinkedList<Integer>();
                for(int j = 0; j < n; j++)
                    studentList.add(j);
                Iterator<Integer> it = studentList.iterator();
                s.start();
                while(it.hasNext()){
                    total += it.next();
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using iterator: LinkedList", totalTimes, initialN);
    }

    public static void getAllArrayListUsingGetMethod(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++){
                ArrayList<Integer> javaList = new ArrayList<Integer>();
                for(int j = 0; j < n; j++)
                    javaList.add(j);

                s.start();
                int x = 0;
                for(int j = 0; j < javaList.size(); j++){
                    x += javaList.get(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using get method: ArrayList", totalTimes, initialN);
    }

    public static void getAllLinkedListUsingGetMethod(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++){
                LinkedList<Integer> studentList = new LinkedList<Integer>();
                for(int j = 0; j < n; j++)
                    studentList.add(j);

                s.start();
                int x = 0;
                for(int j = 0; j < studentList.size(); j++){
                    x += studentList.get(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using get method: LinkedList", totalTimes, initialN);
    }

    // for future changes
    private static interface ArrayListOp {
        public <E> void op(ArrayList<E> list, E data);
    }
    
    private ArrayListOp addAL = new ArrayListOp() {
        public <E> void op(ArrayList<E> list, E data) {
            list.add(data);
        }
    };
    
    private ArrayListOp addFrontAL = new ArrayListOp() {
        public <E> void op(ArrayList<E> list, E data) {
            list.add(0, data);
        }
    };
    
    private ArrayListOp removeFrontAL = new ArrayListOp() {
        public <E> void op(ArrayList<E> list, E data) {
            list.remove(0);
        }
    };

    private static interface LinkedListOp<E> {
        public void op(LinkedList<E> list);
    }
}