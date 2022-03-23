/**
 * Filename:   TestHashTable.java
 * Project:    p3
 * Authors:    Collin L, Tyler Dolan
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   
 * Version:    1.0
 * 
 * Credits:    
 * 
 * Bugs:       
 */

import java.util.NoSuchElementException; // expect to need
import static org.junit.Assert.*; 
import org.junit.Before;  // setUp method
import org.junit.After;   // tearDown method
import org.junit.Test;   

public class TestHashTable{
	
	// Allows us to create a new hash table before each test
	static HashTableADT<Integer, Object> ht;
	
	@Before
	public void setUp() throws Exception {
		ht = new HashTable<Integer, Object>();  
	}

	@After
	public void tearDown() throws Exception {
		ht = null;
	}
		
	/** IMPLEMENTED AS EXAMPLE FOR YOU
	 * Tests that a HashTable is empty upon initialization
	 */
	@Test
	public void test000_IsEmpty() {
		assertEquals("size with 0 entries:", 0, ht.size());
	}
	
	/** IMPLEMENTED AS EXAMPLE FOR YOU
	 * Tests that a HashTable is not empty after adding one (K, V) pair
	 */
	@Test
	public void test001_IsNotEmpty() {
		ht.put(1,"0001");
		int expected = 1;
		int actual = ht.size();
		assertEquals("size with one entry:",expected,actual);
	}

	/** IMPLEMENTED AS EXAMPLE FOR YOU
	 * Other tests assume <int,Object> pairs,
	 * this test checks that <Long,Object> pair also works.
	 */
	@Test
	public void test010_Long_Object() {
		Long key = 9876543210L;
		Object expected = "" + key;		
		HashTableADT<Long,Object> table = 
				new HashTable<Long,Object>();
		table.put(key, expected);
		Object actual = table.get(key);
		assertTrue("put-get of (Long,Object) pair",
				expected.equals(actual));
	}
	
	/*
	 * Tests that the value for a key is updated 
	 * when tried to insert again.
	 */
	@Test
	public void test011_Update() {
		Integer oldValue = 100;
		Integer expected = 1000;
		ht.put(10, oldValue);
		ht.put(10, expected);
		Integer actual = (Integer)ht.get(10);
		assertEquals("value at key 10:",expected,actual);
	}
	
	/*
	 * Tests that inserting many and removing one entry
	 * from the hash table works
	 */
	@Test(timeout=1000 * 10)
	public void test100_InsertManyRemoveOne() {
		int size = 1000;
		for(int i = 0; i < size; i++) {
			ht.put(i, i);
			try{
				//System.out.println(ht.get(i));
			} catch (NoSuchElementException e) {
				//System.out.println(i);
			}
		}
		
		int expectedSize, actualSize;
		assertEquals("Number of keys in hashtable:",expectedSize = 1000 ,
				actualSize = ht.size());
		for(int i = 0; i < 129; i++) {
			
		}
		
		Integer expected = 500;
		Integer actual = (Integer)ht.get(expected);
		
		assertEquals("value at key 500:",expected,actual);
		ht.remove(500);
		try {
			ht.get(500);
			fail("Key 500 should not be found after removing, which would be "
					+ "indicated by a NoSuchElementException after attempting"
					+ "to get key 500.");
		} catch (NoSuchElementException e) {}
		
		assertEquals("Number of keys in hashtable:",expectedSize = 1000 - 1,
				actualSize = ht.size());
	}
	
	/*
	 * Tests ability to insert many entries and 
	 * and remove many entries from the hash table
	 */
	@Test(timeout=1000 * 10)
	public void test110_InsertRemoveMany() {
		int size = 1000;
		for(int i = 0; i < size; i++) {
			ht.put(i, i);
		}
		for(int i = 0; i < size; i += 2) {
			ht.remove(i);
		}
		int expectedSize, actualSize;
		assertEquals("Number of keys in hashtable:",expectedSize = 1000 - 500,
				actualSize = ht.size());
	}
	
	/*
	 * Tests ability to store keys when given
	 * in no particular order
	 */
	//@Test
	public void test120_InsertManyNoOrder() {
		Integer[] keys = new Integer[] 
				{645,3940,19405,2,4025,23,40563,239,194,105940,2045,10};
		for(int i = 0; i < keys.length; i++) {
			ht.put(keys[i], keys[i]);
		}
		Integer expected, actual;
		for(int i = 0; i < keys.length; i++) {
			expected = (Integer)ht.get(keys[i]);
			assertEquals("Values at key " + keys[i] + ":",expected,actual = keys[i]);
		}
		for(int i = 0; i < keys.length; i++) {
			ht.remove(keys[i]);
		}
	}
}
