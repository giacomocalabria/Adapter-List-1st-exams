package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;

//import java.util.NoSuchElementException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

/**
 * Test class of {@link myAdapter.ListAdapter} methods
 * <p>
 * <p>
 * Summary: this class tests the functionality of all ListAdapter methods
 * <br><br>
 * Design test: in this class every method tests a different method of ListAdapter.
 * In every method the execution variables changes before calling the tested method in order to test all the possible conditions
 * <br><br>
 * Description: The ListAdapter class implements HList and HCollection, therefore the HCollection methods were tested using an HCollection as object
 * The ListAdapterTest class deals with the testing for all the methods related
 * to the main list and the list iterator, always of the main list.
 * For simplicity, the values entered are all integers belonging to the
 * Integer class (which however is part of the Object class and therefore
 * respects the type of parameters entered) because it can be easily verified
 * if the tested methods assume anomalous behavior.
 * <br><br>
 * Preconditions:
 * <br>A new empty object of type ListAdapter must always be instantiated before each test
 * <br>All execution variables are never in an uninitialized state (i.e. list = null) unless explicitly desired
 * <br>Methods that take as parameters classes that implement HCollection are passed suitable objects for this interface, therefore they do not throw the exception ClassCastException
 * <br>All the elements contained in the list are known a priori in order to allow the verification after the invocation of the tested methods
 * <br><br>
 * Postconditions: the methods implemented must always modify the list so that the elements contained are exactly those expected starting from their manual insertion
 * <br><br>
 * Execution record: each tested method is correct if all the tests that verify the correct functioning give positive results. Correct execution of the entire test method can then be considered the execution record
 * <br><br>
 * Execution variables:
 * <br>HList list - main empty list on which all methods common to HCollection and HList are tested
 * <br>HList listWithData - non-empty list on which the iterator is tested
 * <br>HCollection coll - object of type CollectionAdapter used to test methods that accept parameters like HCollection and to test methods of HCollection
 * <br><br>
 *
 * @author Alessandro Marcassa
 * @see myAdapter.HList
 * @see myAdapter.HIterator
 * @see myAdapter.HListIterator
 * @see myAdapter.HCollection
 */
public class ListTest {
    HCollection coll;
    private HList list, listWithData;

    /**
     * Method for initializing execution variables before tests
     * <p>
     * A new and empty Collection is created before each test method,
     * in this way the collection on which the various tested methods
     * are invoked always has a valid state
     * <br><br>Preconditions: The constructor successfully instantiates a new object
     * of the ListAdapter class
     */
    @Before
    public void setup() {
        coll = new ListAdapter();
        list = new ListAdapter();
        listWithData = new ListAdapter();

        for (int i = 0; i < 5; i++)
            listWithData.add(i + 1);
    }

    /**
     * Test the constructor with parameter of the ListAdapter class
     * <p>
     *
     * <br><br>Summary: test to verify the correct creation of an object of type
     * ListAdapter that contains the same values as
     * the collection passed as a parameter
     * <br><br>Design test: adding elements to a test collection, the constructor is invoked passing the collection
     * containing the elements as a parameter and the respective
     * arrays obtained with toArray () are compared
     * <br><br>Preconditions:
     * <br>the passed Collection object is non-null
     * <br>The toArray () method must be working correctly
     * <br><br>Postconditions: the created object must be the same as the object passed as a parameter
     * <br><br>Expected results: the same elements of the test collection
     * must be present in the main test collection
     */
    @Test
    public void testConstructorWithParameter() {
        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);

        HCollection newCollection = new ListAdapter(coll);

        assertArrayEquals(coll.toArray(), newCollection.toArray());
    }

    /**
     * Test of {@link myAdapter.ListAdapter#size()}
     * <p>
     * <br><br>Summary: Method test that returns the current size of the Collection
     * <br><br>Design test: test of the size of the collection before and after adding an element and its removal,
     * increasing and decreasing the value of the size of the collection
     * <br><br>Description: The size method is invoked both before adding a new element in the collection and after verifying
     * the increment of one unit of the size of the collection.
     * Similarly with the removal of that element.
     * <br><br>Preconditions: the add () and remove () methods must be properly implemented and working
     * <br><br>Postconditions: the returned value must correspond to the number of elements contained in the collection
     * <br><br>Expected results: 0 if collection is empty, otherwise the number of elements present in the collection.
     * The size of the collection must vary consistently as the insertion / removal elements vary
     */
    @Test
    public void testSize() {
        assertEquals(0, coll.size());
        coll.add(1);
        assertEquals(1, coll.size());
        coll.remove(1);
        assertEquals(0, coll.size());
    }

    /**
     * Test of {@link myAdapter.ListAdapter#isEmpty()}
     * <p>
     * <br><br>Summary: The method that indicates whether the collection is empty is tested
     * <br><br>Design test: An element is added and the method is tested, the element is removed and the method is invoked again
     * <br><br>Description: after adding a data item it is verified that the container is not indicated as empty, while after removal it is verified that the container is correctly considered empty
     * <br><br>Preconditions: the add () and remove () methods must be properly implemented and working
     * <br><br>Postconditions: the method must return true if the container is empty and therefore does not contain any elements
     * <br><br>Expected results: false after insert, true after removal
     */
    @Test
    public void testIsEmpty() {
        coll.add(1);
        assertFalse(coll.isEmpty());
        coll.remove(1);
        assertTrue(coll.isEmpty());
    }

    /**
     * Test of {@link myAdapter.ListAdapter#contains(Object)}
     * <p>
     * <br><br>Summary: The test verifies that the method returns true if the container contains the specified element
     * <br><br>Design test: addition of two elements, verification of the existence of an element present, verification of the existence of an element not present. Check with different types belonging to Object
     * <br><br>Description: after adding two elements, I verify that the method returns true using one of the two elements as a parameter, false using an element that does not exist in the collection as a parameter
     * <br><br>Preconditions: the add (Object) method is correctly implemented
     * <br><br>Postconditions: the method must return true if the element passed is actually contained, false otherwise
     * <br><br>Expected results: true with manually entered data, false with data not present
     */
    @Test
    public void testContains() {
        coll.add(1);
        coll.add(3);
        coll.add(4.5);
        coll.add(null);
        assertTrue(coll.contains(1));
        assertTrue(coll.contains(4.50));
        assertTrue(coll.contains(null));
        assertFalse(coll.contains(2));
    }

    /**
     * Test iterator and relatives methods
     * <p>
     * <br><br>Summary:
     * {@link myAdapter.ListAdapter#iterator()},
     * {@link myAdapter.ListAdapter.IteratorAdapter#hasNext()},
     * {@link myAdapter.ListAdapter.IteratorAdapter#next()} and
     * {@link myAdapter.ListAdapter.IteratorAdapter#remove()} methods are tested
     * <br><br>Design test: an iterator is created, elements are added to the collection, an array is created with multiple calls of next () and elements with multiple calls of remove are eliminated. Exceptions for next () on empty collection or remove () are tested before calling next ()
     * <br><br>Description: After creating the iterator, elements are added to the collection with the add (Object) method. Subsequently an array of Object is created, through a while loop that uses the hasNext () method, containing all the elements of the collection and is compared with the array obtained through the toArray () method. Finally, all the elements contained in the collection are removed by invoking cyclically (with the same while precedenet) the method next () and remove () to then verify that the collection has size 0
     * <br><br>Preconditions: the size () and toArray () and add () methods must be correctly implemented and working
     * <br><br>Postconditions: the iterator must be able to sequentially return and delete each file contained in the Collection. It must also communicate the presence or absence of other elements not removed
     * <br><br>Expected results: the Object array is filled correctly with all the elements of the collection obtained with the iterator and the final size of the collection = 0
     */
    @Test
    public void testIteratorMethods() {
        HIterator collIterator = coll.iterator();

        try {
            collIterator.remove();
            throw new Exception();
        } catch (Exception e) {
            assertEquals(IllegalStateException.class, e.getClass());
        }

        try {
            collIterator.next();
            throw new Exception();
        } catch (Exception e) {
            assertEquals(NoSuchElementException.class, e.getClass());
        }

        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);

        Object[] testArray = new Object[coll.size()];
        int i = 0;
        while (collIterator.hasNext()) {
            testArray[i] = collIterator.next();
            i++;
        }
        assertArrayEquals(coll.toArray(), testArray);

        collIterator = coll.iterator();
        i = 0;
        while (collIterator.hasNext()) {
            collIterator.next();
            collIterator.remove();
            i++;
        }
        assertEquals(0, coll.size());
    }

    /**
     * Test iterator and relatives methods without entering any element
     * <p>
     * <br><br>Summary: the previous test is performed with empty collection
     * <br><br>Description: All previous methods are invoked the way they were invoked in {@link #testIteratorMethods()} without adding elements to the collecton
     * <br><br>Preconditions:
     * <br>The size () and toArray () methods must be correctly implemented and working
     * <br>Cannot be called remove () before calling next ()
     * <br>Cannot be called next () with empty Collection otherwise throw exception
     * <br><br>Postconditions: no exceptions thrown
     * <br><br>Expected results: no exception is thrown and no segmentation fault appears
     */
    @Test
    public void testIteratorMethodsWithoutData() {
        Object[] testArray = new Object[coll.size()];

        HIterator collIterator = coll.iterator();

        int i = 0;
        while (collIterator.hasNext()) {
            testArray[i] = collIterator.next();
            i++;
        }
        assertArrayEquals(coll.toArray(), testArray);

        collIterator = coll.iterator();
        i = 0;
        while (collIterator.hasNext()) {
            testArray[i] = collIterator.next();
            collIterator.remove();
            i++;
        }
        assertEquals(0, coll.size());
    }

    /**
     * Test of {@link myAdapter.ListAdapter#toArray()}
     * <p>
     * <br><br>Summary: the test verifies that the toArray () method correctly returns an array containing all the objects present in the collection in the order in which they were inserted
     * <br><br>Design test: after some data has been entered, a manually created (test) array is compared with the one returned by the method
     * <br><br>Description: after inserting some data in the colleciton, it is verified that the object returned by the toArray () method corresponds with an array of Object containing the attested elements having the same order in which they were inserted in the collection
     * <br><br>Preconditions: the collection can be either empty or partially filled
     * <br><br>Postconditions: the method must return an array of Objects containing all the elements contained in the collection, in the order in which they were originally inserted
     * <br><br>Expected results: the array obtained by the method and the one created manually must match
     */
    @Test
    public void testToArray() {
        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);

        assertArrayEquals(new Object[]{1, 2, 3, 4}, coll.toArray());

        coll = new ListAdapter();
        assertArrayEquals(new Object[0], coll.toArray());
    }

    /**
     * Test of {@link myAdapter.ListAdapter#toArray(Object[])}
     * <p>
     * <br><br>Summary: comparable test with the toArray () method with the difference that, if it is large enough, the array passed as a parameter is used
     * <br><br>Design Test:
     * <br>Nothing is inserted into the collection and a partially occupied array is passed as a parameter
     * <br>Some elements are inserted into the collection and a partially occupied array with a larger size is passed as a parameter
     * <br>Some elements are inserted into the collection and a partially occupied array with a smaller size is passed as a parameter. Also test of the correct throw of the exception with null parameter
     * <br><br>Description: for all three situations indicated by the design test, it is verified with the use of a manually created array that the tested method returns an array of Object, filled with null the greater the difference between the size of the array passed as parameter and the size of the collection, exactly the elements of the collection, or a new array always with the elements of the collection
     * <br><br>Preconditions: The add (Object) method must be correctly implemented
     * <br><br>Postconditions: the returned array must contain all the elements present in the collection on which the method is invoked and in the order with which they appear in the collection
     * <br><br>Expected results: the method must return the array passed as a parameter with the objects of the collection, if large enough, otherwise return another one with a size equal to the number of objects in the collection. If collection empty, the array supplied as a parameter is not modified
     */
    @Test
    public void testToArrayWithParameter() {
        Object[] array1 = null;

        try {
            coll.toArray(array1);
            throw new Exception();
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }

        array1 = new Object[]{1, 2, 3};
        assertArrayEquals(new Object[]{null, null, null}, coll.toArray(array1));

        coll.add(5);
        coll.add(6);
        assertArrayEquals(new Object[]{5, 6, null}, coll.toArray(array1));

        coll.add(7);
        coll.add(8);
        assertArrayEquals(new Object[]{5, 6, 7, 8}, coll.toArray(array1));
    }

    /**
     * Test of {@link myAdapter.ListAdapter#add(Object)}
     * <p>
     * <br><br>Summary: the correct functioning of the add (Object obj) method is tested, which adds an element ("in the queue" if considered the array returned by toArray ()) to the collection
     * <br><br>Design test: elements are added and the array returned by toArray () is analyzed
     * <br><br>Description: after adding two values it is verified with the inspection of the array returned by toArray () that the elements have been inserted and are in the right position
     * <br><br>Preconditions:
     * <br>The collection can be either empty or full
     * <br>The toArray () method must be properly implemented and working
     * <br><br>Postconditions: the collection must contain the value passed as a parameter
     * <br><br>Expected results: Elements must be arranged in the array in the same way the tested method is invoked
     */
    @Test
    public void testAdda() {
        coll.add(3);
        assertEquals(3, coll.toArray()[0]);

        coll.add(null);
        assertNull(coll.toArray()[1]);
    }

    /**
     * Test of {@link myAdapter.ListAdapter#remove(Object)}
     * <p>
     * <br><br>Summary: Verifies that the tested method removes the specified element or returns false if it is not present
     * <br><br>Design test:
     * <br>An element from the empty collection is deleted
     * <br>An element not present from a non-empty collection is deleted
     * <br>An element present from a non-empty collection is deleted
     * <br><br>Description: both with empty collection and with non-existent element, the tested method is invoked verifying that it returns the expected result. After adding an element, if passed as a parameter of the tested method, the first matching element will no longer be present
     * <br><br>Preconditions: the add (Object obj) method and the toArray () method must be correctly implemented
     * <br><br>Postconditions: after invoking the method, the collection must not contain the data entered as a parameter, returning true, if the collection is not modified it returns false
     * <br><br>Expected results: after the removal of the specified element, the size of the collection must be decreased by one unit, the first element corresponding to the entered parameter must have been removed and true must be returned, with data not present, however, false
     */
    @Test
    public void testRemove() {
        assertFalse(coll.remove(new Object()));

        coll.add(2);
        coll.add(3);
        coll.add(2);
        coll.add(null);
        assertFalse(coll.remove(1));
        assertTrue(coll.remove(2));
        assertTrue(coll.remove(null));
        assertEquals(2, coll.size());
        assertArrayEquals(new Object[]{3, 2}, coll.toArray());

    }

    /**
     * Test of
     * {@link myAdapter.ListAdapter#containsAll(HCollection)}
     * <p>
     * <br><br>Summary: the test verifies that the main collection contains all the elements of the collection passed as a parameter
     * <br><br>Design test: elements are added to the collection on which the method will be invoked. Subsequently a collection is created that will be passed as a parameter to the tested method containing only some of the elements present in the main collection and the tested method is invoked. A value not present in the main collection is then inserted into the collection passed as a parameter and the tested method is invoked again, verifying the correctness of the returned value in both cases. The throwing of the exception in the event of a null parameter is also tested
     * <br><br>Description: after the creation and addition of some values in the collection that will be passed as a parameter, I add the same values in the main collection and verify that a true is returned (returned even in the case of an empty parameter collection). After adding a new element in the collection passed as a parameter, I check that the returned value is false
     * <br><br>Preconditions: The add (Object obj) method must be correctly implemented
     * <br><br>Postconditions: the method must return true if the collection contains all the elements present in the collection passed as a parameter
     * <br><br>Expected results: Only if the test collection contains only a subset of the elements contained in the main collection then true is returned
     */
    @Test
    public void testContainsAll() {
        try {
            coll.containsAll(null);
            throw new Exception();
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }

        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);
        coll.add(null);

        HCollection testColl = new ListAdapter(coll);

        assertTrue(coll.containsAll(testColl));

        testColl.add(1);
        testColl.add(2);
        testColl.add(3);
        testColl.add(null);
        assertTrue(coll.containsAll(testColl));

        testColl.add(5);
        assertFalse(coll.containsAll(testColl));
    }

    /**
     * Test of {@link myAdapter.ListAdapter#addAll(HCollection)}
     * <p>
     * <br><br>Summary: the test verifies that the addAll (HCollection) method inserts all the elements contained in the collection passed as a parameter in the collection in which the method is invoked
     * <br><br>Design test: initially the tested method is invoked with a null collection to verify the correct throwing of the exception. Then a non-null collection is created in which elements are inserted. This collection is used as a parameter of the tested method invoked on a collection that already contains data to verify that the elements of the collection (parameter) are added but not removed from the main collection. Checked if an exception was thrown
     * <br><br>Description: to verify that the tested method works correctly, elements are added both to the list used as a parameter and to the list in which the method will be invoked (in which the elements will be inserted). These operations are carried out to verify that after the invocation of the tested method the only changes that occurred in the main collection were only the addition of the values ​​contained in the parameter collection and that no elements already present in the main collection were deleted (verified with the of the final size of the main collection which must assume a size equal to the previous one + size of the parameter collection)
     * <br><br>Preconditions: the add (Object obj) method and the containsAll (HCollection coll) method must be correctly implemented
     * <br><br>Postconditions: all the elements contained in the collection passed as a parameter must also belong to the main collection, not considering the elements already belonging to the main collection
     * <br><br>Expected results: after the invocation of the method, the collection also contains the elements of the collection passed as a parameter
     */
    @Test
    public void testAddAll() {
        HCollection testColl = null;

        try {
            coll.addAll(testColl);
            throw new Exception();
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }

        testColl = new ListAdapter();
        testColl.add(1);
        testColl.add(2);
        testColl.add(3);
        coll.add(null);

        int collDim = coll.size();
        coll.addAll(testColl);
        assertTrue(coll.containsAll(testColl));
        assertTrue(coll.contains(null));
        assertEquals(collDim + testColl.size(), coll.size());
    }

    /**
     * Test of {@link myAdapter.ListAdapter#removeAll(HCollection)}
     * <p>
     * <br><br>Summary: the method that removes all the elements contained in the collection passed as parameter from the main collection is tested
     * <br><br>Design test:
     * <br>Check that if an empty collection is passed, the main collection is not modified (neither if empty nor if not empty)
     * <br>Check that if a collection with elements not contained in the main one is passed, this does not change
     * <br>Verify that if a collection with elements in common is passed, they are deleted from the main collection
     * <br><br>Description: after verification of correct operation using empty collections, elements are added to the main collection and an element not present in this one, in the parameter collection. After checking that the returned value is false, another element is added, this time present in the main collection, and it is verified that the invocation of the tested method returns true and that in the main collection there are no elements in common with the one passed as a parameter . It also tests the correct throwing of an exception in case of null parameter
     * <br><br>Preconditions:
     * <br>The collection passed as a parameter must not be null
     * <br>The add (Object obj) method and the toArray () method must be correctly implemented
     * <br><br>Postconditions: in the main collection there must be no elements contained in the collection passed as a parameter
     * <br><br>Expected results: false if an empty collection is passed, true if at least one element is removed
     */
    @Test
    public void testRemoveAll() {
        HCollection testColl = new ListAdapter();

        try {
            coll.removeAll(null);
            throw new Exception();
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }

        assertFalse(coll.removeAll(testColl));

        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(2);

        testColl.add(5);
        assertFalse(coll.removeAll(testColl));
        assertArrayEquals(new Object[]{1, 2, 3, 2}, coll.toArray());

        testColl.add(2);
        coll.removeAll(testColl);
        assertArrayEquals(new Object[]{1, 3}, coll.toArray());
    }

    /**
     * Test of {@link myAdapter.ListAdapter#retainAll(HCollection)}
     * <p>
     * <br><br>Summary: the method that removes from the main collection all elements not in common with the collection passed as a parameter is tested
     * <br><br>Design test:
     * <br>Main and parameter collection both empty, expected value false (no change)
     * <br>Empty parameter collection, non-empty main collection, expected value true (change, empty main collection)
     * <br>Collection of parameter with values different from those of the main collection, same result pt.2
     * <br>A common value, expected value true, main collection dimension 1 with that value
     * <br>A common value and parent collection that single repeating value, expected value true, and parent collection empty
     * <br><br>Description: the main collection is always filled with the same values, while a second collection, the one passed as a parameter, contains an always different combination of values to study if the method works correctly in different cases
     * <br><br>Preconditions:
     * <br>The collection passed as a parameter cannot be null, but it can be empty or with repeated values
     * <br>The add (Object obj) method, the toArray () and size () method must be correctly implemented
     * <br><br>Postconditions: the main collection must not contain data that is not also contained in the collection passed as a parameter and must return true if the first has been modified
     * <br><br>Expected results: the collection must have zero size if there is no common data, otherwise these must be present
     */
    @Test
    public void testRetainAll() {
        HCollection testColl = new ListAdapter();

        assertFalse(coll.retainAll(testColl));

        coll.add(1);
        coll.add(2);
        coll.add(3);
        assertTrue(coll.retainAll(testColl));
        assertEquals(0, coll.size());

        coll.add(1);
        coll.add(2);
        coll.add(3);
        testColl.add(5);
        assertTrue(coll.retainAll(testColl));
        assertEquals(0, coll.size());

        coll.add(1);
        coll.add(2);
        coll.add(3);
        testColl.add(2);
        assertTrue(coll.retainAll(testColl));
        assertArrayEquals(new Object[]{2}, coll.toArray());
        coll.clear();

        coll.add(1);
        coll.add(1);
        coll.add(1);
        assertTrue(coll.retainAll(testColl));
        assertEquals(0, coll.size());
    }


    /**
     * Test of {@link myAdapter.ListAdapter#addAll(int, HCollection)}
     * <p>
     * <br><br>Design test: non-empty collection is created and is used as a parameter for a non-empty list Throwing of exceptions in case of wrong index or null collection is also tested
     * <br><br>Description: after creating a non-empty collection and inserting the data into the list using the add (Object obj) method defined in the ListAdapter.add (Object) class, the collection is inserted in the three points: initial, intermediate and final and the results are compared with a manually created array with the expected values
     * <br><br>Preconditions: the add (Object) method must be correctly implemented, as well as the toArray () method, both of HCollection
     * <br><br>Postconditions: the size of the list must be larger after the method is run
     * <br><br>Expected results: the values of the collection must appear in the list at the specified point
     */
    @Test
    public void testAddAllIndex() {
        HCollection coll;

        try {
            list.addAll(0, null);
            throw new Exception();
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }

        coll = new ListAdapter();
        try {
            list.addAll(list.size() + 1, coll);
            throw new Exception();
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }


        coll.add(8);
        coll.add(9);

        list.add(1);
        list.add(2);
        list.add(3);

        list.addAll(0, coll);
        assertArrayEquals(new Object[]{8, 9, 1, 2, 3}, list.toArray());


        list.addAll(3, coll);
        assertArrayEquals(new Object[]{8, 9, 1, 8, 9, 2, 3}, list.toArray());

        list.addAll(list.size(), coll);
        assertArrayEquals(new Object[]{8, 9, 1, 8, 9, 2, 3, 8, 9}, list.toArray());
    }

    /**
     * Test of {@link myAdapter.ListAdapter#equals(Object)}
     * <p>
     * <br><br>Summary: the equals method must return true s if the length of the two compared lists is equal and the same elements are contained in the same order
     * <br><br>Design test: a new list is created and both in this and in the main list the same elements are inserted and compared. The insertion of incompatible objects is also tested
     * <br><br>Description: after the creation of a second list, the same elements are inserted in the two lists and are compared. Then an element is added in one of the two and they are compared again. After being made equal again, one value is changed to one of the two and a further comparison is made. A collection is inserted both with the same elements in the same order as they appear in the main list, and in a different order. It also tests the insertion of a non-empty Object array to test the compatibility of the data entered
     * <br><br>Preconditions: the add (Object) methods of HList and HCollection must be correctly implemented
     * <br><br>Postconditions: the method must return true if the two containers have the same elements arranged in the same order and must return false if the two objects are incompatible for a comparison (according to logic, incompatible corresponds to not equal)
     * <br><br>Expected results: true only in the first case described and in the case of data compatible and respecting the <br><br>Description in HList, exception with incompatible data, false with the other cases described
     */
    @Test
    public void testEquals() {
        HList list2 = new ListAdapter();
        list.add(1);
        list.add(2);
        list.add(3);

        list2.add(1);
        list2.add(2);
        list2.add(3);

        assertEquals(list, list2);

        list2.add(4);
        assertNotEquals(list, list2);

        list2.clear();
        list2.add(1);
        list2.add(4);
        list2.add(3);
        assertNotEquals(list, list2);


        coll.clear();
        coll.add(1);
        coll.add(3);
        coll.add(2);
        assertNotEquals(list, coll);

        assertNotEquals(list, new Object[]{1, 2, 3});
    }

    /**
     * Test of {@link myAdapter.ListAdapter#hashCode()}
     * <p>
     * <br><br>Summary: Text of the hash calculation method defined in HList
     * <br><br>Design test: the same data is entered in the main list and in one created ad hoc. The hash must be the same. The order of two elements in one of the two lists is reversed and the tested method is rerun
     * <br><br>Description: after having entered the data in the main list, a second one is created which contains the same data as the first and in the same order. The calculated hash must be the same. The reversal of the order of two elements and and subsequent hash calculation must be different
     * <br><br>Preconditions: the hash calculation must be the one described in HList
     * <br><br>Postconditions: only if the compared objects have the same elements arranged in the same order, the hash must be identical
     * <br><br>Expected results: the cases in which the hash of the two objects match is exactly when the content and order of the elements of the two objects are the same
     */
    @Test
    public void testHashCode() {
        list.add(1);
        list.add(2);
        list.add(3);

        HList list2 = new ListAdapter();
        list2.add(1);
        list2.add(2);
        list2.add(3);

        assertEquals(list2.hashCode(), list.hashCode());

        list2.clear();
        list2.add(1);
        list2.add(3);
        list2.add(2);
        assertNotEquals(list2.hashCode(), list.hashCode());

        list2.clear();
        assertNotEquals(list2.hashCode(), list.hashCode());

        HCollection coll = new ListAdapter();
        coll.add(1);
        coll.add(2);
        coll.add(3);
        assertEquals(coll.hashCode(), list.hashCode());
    }

    /**
     * Test of {@link myAdapter.ListAdapter#get(int)}
     * <p>
     * <br><br>Summary: test to verify the correct return parameter of the get function
     * <br><br>Design test: after inserting some elements, check that each inserted element is reachable through the tested method
     * <br><br>Description: after the manual insertion of values, a for loop is called to verify that indeed each inserted element is correctly returned by the get method
     * <br><br>Preconditions: the index inserted must not be less than 0 or greater than the number of elements inserted
     * <br><br>Postconditions: the element returned must be the one contained in the i-th position of the list
     * <br><br>Expected results: the for loop ends correctly (i.e. it reaches the end of the conditions for which it loops)
     */
    @Test
    public void testGet() {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        try {
            list.get(list.size());
            throw new NullPointerException();
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }

        for (int i = 0; i < 5; i++)
            assertEquals(i, list.get(i));
    }

    /**
     * Test of {@link myAdapter.ListAdapter#set(int, Object)}
     * <p>
     * <br><br>Summary: The method must correctly set all elements of the list
     * <br><br>Design test: the list is filled with n values (for simplicity 0), after the invocation for n times of set the list must contain increasing numbers from 1 to n + 1. The check is performed with the equals method. The correct throw of the exception with an index not consistent with the size of the list is also checked
     * <br><br>Description: after inserting an arbitrary number of values (0), a for loop is invoked which must modify the contents of the list so that it corresponds to the contents of a second list containing the expected data entered manually
     * <br><br>Preconditions: The implementation of the add (Object), size () and equals (Object) method of ListAdapter is working correctly
     * <br><br>Postconditions: the main list is different than it was before the invocation of the set method
     * <br><br>Expected results: the master list on which the set is performed and the verification sublist must be equal or the equals method must return true
     */
    @Test
    public void testSet() {

        HList list2 = new ListAdapter();
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);

        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);

        try {
            list.set(list.size(), "error");
            throw new Exception();
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }

        for (int i = 0; i < list.size(); i++)
            list.set(i, i + 1);

        assertEquals(list, list2);
    }

    /**
     * Test of {@link myAdapter.ListAdapter#add(int, Object)}
     * <p>
     * <br><br>Summary: Testing the method for adding an item to a specific location
     * <br><br>Design test: given a for loop that inserts increasing numbers in the half of the list containing the elements, the resulting list is compared to an array built ad hoc containing the expected elements. Also verified the correct throwing of the exceptions
     * <br><br>Description: The method verifies that the call to the add (Integer, Object) function inserts the specified element at the specified location. This is verified at each insertion by comparing the array returned by the toArray () method with an array built ad hoc containing the expected values. Wrong index entries are also performed to verify that exceptions are thrown correctly. It is also verified that by inserting an element at the beginning of an empty list it will become the first element of the list
     * <br><br>Preconditions:
     * <br>The index must be between 0 and the maximum size of the list
     * <br>ListAdapter's toArray () method must be properly implemented and working
     * <br>Object's getClass () method must be working correctly
     * <br><br>Postconditions: elements must be added in the indicated position provided that this is valid
     * <br><br>Expected results: the returned arrays correspond to the elements inserted in the position in which they were inserted
     */
    @Test
    public void testAdd() {

        listWithData.add(0, 0);
        assertArrayEquals(new Object[]{0, 1, 2, 3, 4, 5}, listWithData.toArray());

        listWithData.add(3, 2.5);
        assertArrayEquals(new Object[]{0, 1, 2, 2.5, 3, 4, 5}, listWithData.toArray());

        listWithData.add(listWithData.size(), 5.5);
        assertArrayEquals(new Object[]{0, 1, 2, 2.5, 3, 4, 5, 5.5}, listWithData.toArray());

        try {
            listWithData.add(-1, "Exception");
            throw new Exception();
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }

        listWithData.clear();
        listWithData.add(0, "wiped");
        assertArrayEquals(new Object[]{"wiped"}, listWithData.toArray());

    }

    /**
     * Test of {@link myAdapter.ListAdapter#remove(int)}
     * <p>
     * <br><br>Summary: the remove method is tested which must correctly delete the element contained in the specified position, resulting in a list with a size smaller than 1 compared to before the invocation of the method
     * <br><br>Design test: after adding values to the list, removing some of them must result in a list of less than and equal size to a manually created checklist
     * <br><br>Description: the list is filled with ints, for simplicity; a for loop is then called to check if the element contained in the i-th position is to be deleted (in this case if it is even) and if so it is removed. The resulting list must correspond to a list created ad hoc containing the expected values. The re-entry of an element into an empty list is also tested
     * <br><br>Preconditions: ListAdapter's add (Object), get (int) and equals (Object) methods must be properly implemented and working
     * <br><br>Postconditions: the removed element must match the element contained in the position indicated by the index passed as a parameter
     * <br><br>Expected results: at the end of the test the list containing the additional values is the same as the list with the expected values
     */
    @Test
    public void testRemoveIndex() {
        try {
            list.remove(0);
            throw new Exception();
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        try {
            list.remove(list.size());
            throw new Exception();
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }

        HList list2 = new ListAdapter();
        list2.add(1);
        list2.add(3);
        list2.add(5);

        for (int i = 0; i < list.size(); i++)
            if ((Integer) list.get(i) % 2 == 0)
                list.remove(i);

        assertEquals(list, list2);

    }

    /**
     * Test of {@link myAdapter.ListAdapter#indexOf(Object)}
     * <p>
     * <br><br>Summary: Test for the method that looks for the position of the first element corresponding to the element passed as a parameter
     * <br><br>Design test: after inserting some elements I perform three searches for single element, duplicate element, non-existent element. Search also with empty list
     * <br><br>Description: the first test is performed on an empty list, thus not returning the index containing the desired element. The following invocations are made after the insertion of values, some even repeated, specifically: invocation to find a non-repeated element, invocation to find a repeated element and invocation to find an element not contained in the list. For simplicity, the values entered are Integer
     * <br><br>Preconditions: the list object must not be null (but can be empty)
     * <br><br>Postconditions: the index of the first matching element found is returned, otherwise -1
     * <br><br>Expected results: -1 for empty list and element not found; index of the position of the single element, searched; index of the position of the first element of the list found
     */
    @Test
    public void testIndexOf() {
        assertEquals(-1, list.indexOf(9));

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(5);

        assertEquals(1, list.indexOf(2));
        assertEquals(0, list.indexOf(1));
        assertEquals(-1, list.indexOf(6));
    }

    /**
     * Test of {@link myAdapter.ListAdapter#lastIndexOf(Object)}
     * <p>
     * <br><br>Summary: Test for the method that looks for the position of the last element corresponding to the element passed as a parameter
     * <br><br>Design test: after the insertion of some elements, three searches are carried out for single element, duplicate element, non-existent element. Search also with empty list
     * <br><br>Description: the first test is performed on an empty list, thus not returning the index containing the desired element. The following invocations are made after the insertion of values, some even repeated, specifically: invocation to find a non-repeated element, invocation to find a repeated element and invocation to find an element not contained in the list. For simplicity, the values ​​entered are Integer
     * <br><br>Preconditions: the list object must not be null (but can be empty)
     * <br><br>Postconditions: the index of the last matching element found is returned, otherwise -1
     * <br><br>Expected results: -1 for empty list and element not found; index of the position of the single element, searched; index of the position of the first element of the list found
     */
    @Test
    public void testLastIndexOf() {
        assertEquals(-1, list.lastIndexOf(9));

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(5);

        assertEquals(1, list.lastIndexOf(2));
        assertEquals(3, list.lastIndexOf(1));
        assertEquals(-1, list.lastIndexOf(6));
    }

    /**
     * Test of {@link myAdapter.ListAdapter#listIterator(int)}
     * <p>
     * <br><br>Summary: Tests that the listIterator (int) method correctly returns an iterator initialized at the point indicated by the parameter
     * <br><br>Design test: the iterator is invoked on a non-empty list and it is verified that the object returned by the first invocation of next corresponds to the one corresponding to the index provided The correct throw of the exception after the invocation of next () is also tested because it is equivalent that the method has correctly returned an iterator at the end of the list. Furthermore, the throw of exception is also checked in the case of an invalid index with respect to the positions that the iterator can take
     * <br><br>Description: to verify that the method works correctly, an iterator is created on a list containing values, and the next () method is invoked to verify that the returned value is the one expected with respect to the position passed as a parameter to the tested method. Next () is also performed on an iterator returned by the method to which the exact size of the list has been passed as a parameter to verify that the method is able to return an iterator on all n + 1 positions. Finally it is verified that if the method is executed passing an invalid index (greater than the size of the list) an exception is thrown
     * <br><br>Preconditions: the next () method of the list iterator and the size () method of the list must be correctly implemented
     * <br><br>Postconditions: the index must indicate the first element returned by the next () call
     * <br><br>Expected results: the element returned is the one corresponding to the first element returned by the next () call
     */
    @Test
    public void testListIteratorIndex() {
        HListIterator listIterator = listWithData.listIterator(3);
        assertEquals(4, listIterator.next());

        listIterator = listWithData.listIterator(listWithData.size());
        try {
            listIterator.next();
            throw new Exception();
        } catch (Exception e) {
            assertEquals(NoSuchElementException.class, e.getClass());
        }

        try {
            listIterator = listWithData.listIterator(listWithData.size() + 1);
            throw new Exception();
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
    }

    /**
     * Test of {@link myAdapter.ListAdapter#subList(int, int)}
     * <p>
     * <br><br>Summary: the method tests the correct creation of a sublist starting from the original list
     * <br><br>Design test: the method with two valid indices is invoked and is checked by comparing the array returned by the toArray () method and an array containing the expected values, that the sublist actually contains the range of values indicated by the two indices. The empty sublist is also checked, in case of co-incidence of the indexes in the invocation of the tested method, and an exception throw in case of invalid indexes
     * <br><br>Description: to verify that the sublist contained in the indexes passed as parameters is returned correctly, first of all it is specified that if the indexes are equal then the sublist will be empty, then it is verified that with a non-empty sublist the elements contained in it are exactly those expected (verification carried out by comparing arrays with expected values and the one returned by the toArray () method invoked on the sublist). It is also verified that if the main list is empty the only valid indexes are 0, if indexes are put outside the maximum size of the list the exception is raised.
     * <br><br>Preconditions: the size (), toArray () and getClass () method must be correctly implemented
     * <br><br>Postconditions: the main list is not changed immediately after the method invocation. With indexes from zero to the value returned by size () the sublist has a size equal to the list
     * <br><br>Expected results: the sublist contains all the elements contained in the range [from, to)
     */
    @Test
    public void testSubList() {
        HList subList = listWithData.subList(2, 2);
        assertEquals(0, subList.size());

        subList = listWithData.subList(2, 4);
        assertEquals(2, subList.size());
        assertArrayEquals(new Object[]{3, 4}, subList.toArray());

        subList = listWithData.subList(0, listWithData.size());
        assertEquals(listWithData.size(), subList.size());

        listWithData.clear();
        subList = listWithData.subList(0, 0);
        try {
            subList = listWithData.subList(2, 3);
            throw new Exception();
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }

    }

    /**
     * Test of
     * {@link myAdapter.ListAdapter.ListIteratorAdapter})
     * <p>
     * <br><br>Summary: This method tests the correct operation of all methods of the list iterator
     * <br><br>Design test: the entire scan is carried out first in one direction and then in the other of the entire list, with the iterator, and the data are checked manually, then a data is entered and the correct insertion is verified and then removed by checking it correct removal. Finally, a datum present in the list is modified and the change is verified by comparing the list to an array created manually with the expected elements
     * <br><br>Description: first of all the methods that do not throw exceptions are tested using an iterator on an empty list, in this case the add (Object obj) method and the previous () method which must return the value just inserted. Subsequently, an array is created with a size equal to the length of the list, and is filled by scanning the entire list with the iterator in both directions, this array is compared with an array built ad hoc containing the expected values. At the end of these two operations it is verified with prevIndex () and nextIndex () that the iterator has actually reached the start / end Finally, the add, remove and set methods are tested, which insert, remove and modify data always in the same position of the list after calling next () and previous () each.
     * <br><br>Preconditions:
     * <br>The toArray () and size () methods must be correctly implemented
     * <br>The add (Object obj) and remove () methods must not be invoked if the next () or previous () methods have not been invoked first and they must not be invoked twice in a row
     * <br>The iterator is created on a non-empty list
     * <br><br>Postconditions: the list iterator must allow you to navigate back and forth along the entire list sequentially and to be able to modify, remove and add data
     * <br><br>Expected results: the list contains exactly the elements contained by the manually created Object arrays with the expected values
     */
    @Test
    public void testListIteratorMethods() {
        HListIterator listIterator = list.listIterator();
        assertFalse(listIterator.hasNext());
        assertFalse(listIterator.hasPrevious());

        listIterator.add(0.5);
        assertEquals(0.5, listIterator.previous());


        listIterator = listWithData.listIterator();
        Object[] arrayList = new Object[listWithData.size()];

        int i = 0;
        while (listIterator.hasNext()) {
            arrayList[i] = listIterator.next();
            i++;
        }
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5}, arrayList);
        assertEquals(4, listIterator.previousIndex());

        i = 0;
        while (listIterator.hasPrevious()) {
            arrayList[i] = listIterator.previous();
            i++;
        }
        assertArrayEquals(new Object[]{5, 4, 3, 2, 1}, arrayList);
        assertEquals(1, listIterator.nextIndex());

        listIterator.next();
        listIterator.add(0.5);
        assertArrayEquals(new Object[]{1, 0.5, 2, 3, 4, 5}, listWithData.toArray());

        listIterator.previous();
        listIterator.remove();
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5}, listWithData.toArray());

        listIterator.next();
        listIterator.previous();
        listIterator.set(0.5);
        assertArrayEquals(new Object[]{1, 0.5, 3, 4, 5}, listWithData.toArray());
    }
}
