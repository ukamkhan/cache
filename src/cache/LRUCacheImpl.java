/**
 * 
 */
package cache;

import java.util.HashMap;

/**
 * Implements a Least Recently Used cache using the CacheItem class. The cache is backed by a simple HashMap
 * 
 * @author uzma
 */
public class LRUCacheImpl implements LRUCache{
	
	/** A HashMap backing the cache, stores data retrievable by keys in O(1) time */
	private	HashMap<Object, CacheItem> map; 

	/** Maximum capacity of LRU Cache */
	private int maxSize;
	
	/** Number of items currently contained in the cache */
	private int currentSize = 0;
	
	/** Head of the list, should always point to least recently used item */
	private CacheItem head;
	
	/** Tail of the list, always points to most recently used item */
	private CacheItem tail;

	/** Gets the maximum size of the cache */
	public int getMaxSize(){
		return maxSize;
	}
	
	/**
	 * No arg constructor, careats a cache of size 100
	 */
	public LRUCacheImpl(){
		map = new HashMap<Object, CacheItem>();
		this.maxSize = 100;
	}
	
	/**
	 * Initialize with size
	 * 
	 * @param capacity the size of cache
	 */
	public LRUCacheImpl(int capacity){
		map = new HashMap<Object, CacheItem>();
		this.maxSize = capacity;
	}
	
	/**
	 * Gets the item specified by key 
	 * 
	 * @param key
	 * @return
	 */
	public Object get(Object key){
		
		// get the object from the map, no need to remove from map
		CacheItem toGet = map.get(key);
		
		if(toGet != null) {
			// unlink from linked list at current position
			toGet.next.prev = toGet.prev;
			toGet.prev.next = toGet.next;
			
			// re-insert at the end of linked list
			tail.next = toGet;
			toGet.prev = tail;
			tail = toGet;
			
			return toGet;
		}
		System.out.println("Object not found in Cache! ");
		return null;
	}
	
	/**
	 * Sets the maximum capacity of LRU Cache
	 * 
	 * @param size max number of items
	 */
	public void setMaxSize(int size){
		this.maxSize = size;
	}
	
	/**
	 * Add the item in the cache
	 * 
	 * @param key the key for the value
	 * @param value to be added
	 */
	public void put(Object key, Object value){
		
		// first insert, need to set head and tail
		if(currentSize == 0){
			CacheItem item = new CacheItem(key, value, null, null);
			head = item;
			tail = item;
			// add to map
			map.put(key, item);
			currentSize++;
		}
		// typical insert, tail should be advanced
		else if(currentSize > 0 && currentSize < maxSize) {
			CacheItem item = new CacheItem(key, value);
			tail.next = item;
			item.prev = tail;
			tail = item;
			map.put(key, item);
			currentSize++;
		}
		// currentSize reached maxSize, it will never exceed maxSize
		else {
			System.out.println("Max Size reached, removing least recently used item!");
			// remove head from map
			CacheItem remove = map.remove(head.getKey());
			// remove from linked list and reset head
			remove.next.prev = null;
			head = remove.next;
			
			// now insert normally 
			CacheItem item = new CacheItem(key, value);
			tail.next = item;
			item.prev = tail;
			tail = item;
			map.put(key, item);
		}
	}
	
	/**
	 * Returns a string representation of the items in the cache, including keys and values, in order of recent use.
	 * 
	 * @return string representation
	 */
	public String toString(){
		CacheItem current = tail;
		StringBuffer string = new StringBuffer();
		
		string.append("[");
		
		while(current != null){
			string.append("<");
			string.append(current.getKey());
			string.append(",");
			string.append(current.getData());
			string.append(">");
			current = current.prev;
		}
		string.append("]");
		return string.toString();
	}
}
