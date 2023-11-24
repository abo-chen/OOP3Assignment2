package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import datastructures.MyDLL;
import utilities.Iterator;

public class MyDLLTests {
	
	private MyDLL<String> dll;
	
	@Before
	void setUp() {
		dll = new MyDLL<>();
	}
	
	@Test
	void testSize() {
		assertEquals(0, dll.size());
	}
	
	@Test
	void testAdd() {
		assertTrue(dll.isEmpty());
		dll.add("item1");
		assertFalse(dll.isEmpty());
	}
	
	@Test
	void testGet() {
		dll.add("item1");
		dll.add("item2");
		assertEquals("item1", dll.get(0));
		assertEquals("item2", dll.get(1));
	}
	
	@Test
	void testRemove() {
		dll.add("item1");
		dll.add("item2");
		assertEquals("item1", dll.remove(0));
		assertEquals(1, dll.size());
	}
	
	@Test
	void testSet() {
		dll.add("item");
		dll.set(0, "new item");
		assertEquals("new item", dll.get(0));
	}
	
	@Test
	void testIterator() {
		dll.add("item1");
		dll.add("item2");
		
		Iterator<String> iterator = dll.iterator();
		assertTrue(iterator.hasNext());
		assertEquals("item1", iterator.hasNext());
		assertTrue(iterator.hasNext());
		assertEquals("item2", iterator.hasNext());
		assertFalse(iterator.hasNext());
	}
}
