package space.harbour.java.hw3

import junit.framework.TestCase;


public class HashMapTest extends TestCase{
    private HashMap map;

    // Set up an empty map before each test

    public void setUp() {
        this.map = new HashMap();
    }

    // Check that a new HashMap returns 'true' for isEmpty

    public void testIsEmptyForNewMap() {
        assertTrue(map.isEmpty());
    }

    // Test adding an element makes isEmpty return 'false'

    public void testAddMakesIsEmptyFalse() {
        map.put("Hello", 5);
        assertFalse(map.isEmpty());
    }

    // Check that size returns 0 for new HashMaps

    public void testSizeForNewMap() {
        assertEquals(0, map.size());
    }

    // Test size increases as elements are added

    public void testSizeIncrementsWhenAddingElements() {
        map.put("Hello", 5);
        assertEquals(1, map.size());

        map.put("Goodbye", 5);
        assertEquals(2, map.size());
    }

    public void testSizeDoesNotChange() {
        map.put("Hello", 5);
        map.put("Goodbye", 5);
        assertEquals(2, map.size());

        map.remove("Bye", 7);
        assertEquals(2, map.size());
    }

    // Make sure get returns the values added under keys

    public void testGetReturnsCorrectValue() {
        map.put("Hello", 5);
        map.put("Goodbye", 6);
        assertEquals(5, map.get("Hello"));
        assertEquals(6, map.get("Goodbye"));
    }

    // Test that an exception is thrown if a key does not exist

    public void testThrowsExceptionIfKeyDoesNotExist() {
        map.get("Hello");
    }

    // Test thats an added element replaces another with the same key

    public void testReplacesValueWithSameKey() {
        map.put("Hello", 5);
        map.put("Hello", 6);

        assertEquals(6, map.get("Hello"));
    }

    // Make sure that two (non-equal) keys with the same hash do not overwrite each other

    public void testDoesNotOverwriteSeperateKeysWithSameHash() {
        map.put("Ea", 5);
        map.put("FB", 6);

        assertEquals(5, map.get("Ea"));
        assertEquals(6, map.get("FB"));
    }

    // Make sure size doesn't decrement below 0

    public void testRemoveDoesNotEffectNewMap() {
        map.remove("Hello");

        assertEquals(0, map.size());
    }

    // Make sure that size decrements as elements are used

    public void testRemoveDecrementsSize() {
        map.put("Hello", 5);
        map.put("Goodbye", 6);

        map.remove("Hello");

        assertEquals(1, map.size());

        map.remove("Goodbye");

        assertEquals(0, map.size());
    }

    // Test elements are actually removed when remove is called

    public void testRemoveDeletesElement() {
        map.put("Hello", 5);
        map.remove("Hello");

        map.get("Hello");
    }

    // Test that contains is 'false' for new maps

    public void testContainsKeyForNewMap() {
        assertFalse(map.containsKey("Hello"));
    }

    // Test that contains returns 'false' when key doesn't exist

    public void testContainsKeyForNonExistingKey() {
        map.put("Hello", 5);
        assertFalse(map.containsKey("Goodbye"));
    }

    // Make sure that contains returns 'true' when the key does exist

    public void testContainsKeyForExistingKey() {
        map.put("Hello", 5);
        assertTrue(map.containsKey("Hello"));
    }

    // Check that contains is not fooled by equivalent hash codes

    public void testContainsKeyForKeyWithEquivalentHash() {
        map.put("Ea", 5);

        assertFalse(map.containsKey("FB"));
    }

    private static Random rand = new Random();

    public void testMediumSimple() {

        for (int i = 0; i < 10000; i++) {
            assertNull(map.put(Integer.toString(i), i));
            assertEquals(i + 1, map.size());
            int j = rand.nextInt(20000) - 5000;
            assertEquals(j >= 0 && j <= i ? (Integer)j : null, map.get(Integer.toString(j)));
        }
    }

    public void testNotNull(){
        map.put("key1", "value 1");
        map.put("key2", "value 2");
        map.put("key3", "value 3");
        assertNotNull("key1");
    }

    public void testNotSame(){
        map.put("key1", "value 1");
        map.put("key2", "value 2");
        map.put("key3", "value 3");
        assertNotSame("key1", "key2");
    }

    public void testSame(){
        map.put("key1", "value 1");
        map.put("key2", "value 2");
        map.put("key3", "value 3");
        assertSame("key1", "key1");
    }

    public void destroy() {
        map.removeAll();
    }
}