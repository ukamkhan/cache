/**
 * 
 */
package cache;


/**
 * A Cache Item is a Node of a doubly linked list. This is done to be able to traverse the list in order of use
 * and also to be able to modify the cache easily. 
 * 
 * @author uzma
 */
public class CacheItem {
	
	/** the data held by the item */
	private Object data;
	
	/** key the Object is used to retrieve by from the cache */
	private Object key;
	
	/** pointer to previous cache entry in the doubly linked list */
	CacheItem prev; 
	
	/** pointer to next cache item in the doubly linked list */
	CacheItem next;

	/** 
	 * Initialize CacheItem with key and data value 
	 * 
	 * @param key for the value to be held
	 * @param data value to be held
	 **/
	public CacheItem(Object key, Object data){
		this.key = key;
		this.data = data;
	}
	
	/**
	 * Initialize CacheItem with data and next previous pointers
	 * @param key for the value to be held
	 * @param data value to be held
	 * @param next the next item in the cache
	 * @param prev the previous item in the cache
	 */
	public CacheItem(Object key, Object data, CacheItem next, CacheItem prev){
		this.key = key;
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
	
	/**
	 * Gets the data 
	 * @return data contained in the Item
	 */
	public Object getData(){
		return data;
	}
	
	/**
	 * Gets the data 
	 * @return data contained in the Item
	 */
	public Object getKey(){
		return key;
	}
}
