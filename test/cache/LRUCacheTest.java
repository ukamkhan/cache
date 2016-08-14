package cache;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LRUCacheTest {
	
	/** Cache to test */
	LRUCache cache = new LRUCacheImpl(7);

	@Before
	public void setUp() throws Exception {
		
		cache.put("1", "ONE");
		cache.put("2", "TWO");
		cache.put("3", "THREE");
		cache.put("4", "FOUR");
		cache.put("5", "FIVE");
		cache.put("6", "SIX");
	}

	/**
	 * Test original expected order in cache
	 */
	@Test
	public void testOriginalOrder() {
		String origOrder = "[<6,SIX><5,FIVE><4,FOUR><3,THREE><2,TWO><1,ONE>]";
		Assert.assertEquals(origOrder, cache.toString());
	}
	
	/**
	 * Retrieve one item and check if the list re-orders
	 */
	@Test
	public void testReOrder() {
		String reOrdered = "[<3,THREE><6,SIX><5,FIVE><4,FOUR><2,TWO><1,ONE>]";
		cache.get("3");
		
		Assert.assertEquals(reOrdered, cache.toString());
	}
	
	/**
	 * Test filling to max capacity
	 */
	@Test
	public void fillMaxTest(){
		// reorder
		cache.get("3");
		// Fill to max capacity
		cache.put("7", "SEVEN");
		String maxed = "[<7,SEVEN><3,THREE><6,SIX><5,FIVE><4,FOUR><2,TWO><1,ONE>]";
		Assert.assertEquals(maxed, cache.toString());
	}
	
	/**
	 * Check puts beyond max capacity to see if least recently used are removed correctly
	 */
	@Test
	public void testBeyondMax(){
		String maxed = "[<8,EIGHT><7,SEVEN><3,THREE><6,SIX><5,FIVE><4,FOUR><2,TWO>]";
		
		//reorder
		cache.get("3");
		// Fill to max capacity
		cache.put("7", "SEVEN");
		cache.put("8", "EIGHT");
		Assert.assertEquals(maxed, cache.toString());
		
	}
	
	/**
	 * Test getting an item not present in the cache
	 */
	@Test
	public void testItemNotPresent(){
		Assert.assertNull("Item not present", cache.get("14"));
	}

}
