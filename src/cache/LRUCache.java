/**
 * 
 */
package cache;

/**
 * Given Interface
 * @author uzma
 */
public interface LRUCache {
	
	Object get(Object key);
	
	void put(Object key, Object value);
	
	int getMaxSize();
	
	String toString();
}
