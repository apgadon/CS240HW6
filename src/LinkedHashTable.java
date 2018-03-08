public class LinkedHashTable<K, V>
{
    private TableNode<K, V>[] hashTable;
    private static final int MAX_SIZE = 10000;
    private static final int DEFAULT_SIZE = 10;

    // Current capacity of arrayList
    private int numBuckets;

    // number of items currently in arrayList
    private int numberOfEntries;


    public LinkedHashTable()
    {
        this(DEFAULT_SIZE);
    }

    public LinkedHashTable(int numBuckets)
    {
        this.numBuckets = numBuckets; // better to use a prime number
        numberOfEntries = 0;
        @SuppressWarnings("unchecked")
        TableNode<K, V>[] temp = (TableNode<K, V>[])new TableNode[numBuckets];
        hashTable = temp;
    }

    public int getNumberOfEntries() { return numberOfEntries; }
    public boolean isEmpty() { return getNumberOfEntries() == 0; }

    // implements hash function to find index for a key
    private int hashFunction(K key)
    {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % numBuckets);
    }

    public V getValue(K key)
    {
        // find head of chain for given key
        int bucketIndex = hashFunction(key);
        TableNode<K, V> head = hashTable[bucketIndex];

        // search key in chain
        while (head != null)
        {
            if(head.key.equals(key))
            {
                return head.getValue();
            }
            head = head.getNext();
        }
        // key not found
        return null;
    }

    public void add(K key, V value)
    {
    	boolean added = false;
        int ind = hashFunction(key);    // Apply hash function to find index for the given key
        TableNode<K, V> toInsert = new TableNode(key, value);
        TableNode<K, V> trvrs = hashTable[ind];	//we set equal to actual index 
        									//b/c of null errors if set to next
       	
       	if(hashTable[ind] == null) {	//if bucket is empty
       		hashTable[ind] = toInsert;
       	}
       	else {    // See if key already exists in table. If so, overwrite value.
       		if(hashTable[ind].key.equals(key)) {	//overwriting the first one (since it's index, need separate conditional)
       			hashTable[ind].setValue(value);
       		}
       		else{
       			while(trvrs.next != null) {
			       	if(trvrs.next.key.equals(key)) {
			       		trvrs.next.setValue(value);	//overwrite existing Node with identical key to inserted
			       		added = true;
			        	break;
			       	}
			      	trvrs = trvrs.next;
		        }
		       	if(!added) {					//if traversed list w/o adding
		       		trvrs.next = toInsert;
		        	numberOfEntries++;
		       	}
	        }
       	}
        
        
    }

    // remove a key-value pair from the HashTable
    public V remove(K key)
    {
    	V toReturn = null;
    	int ind = hashFunction(key);		//hash function to find index for given key
    	TableNode<K, V> head = hashTable[ind];	//reference variable to refer to the head of the chain

    	if(head.key.equals(key)) {	//deal with TableNode in index first
    		toReturn = hashTable[ind].value;
    		hashTable[ind] = head.next;
    		numberOfEntries--;		//Decrement the number of entries
    	}
    	else {								//if the key could be further in the chain
    		while(head.next != null) {			//search for the key in its chain
    			if(head.next.key.equals(key)) {		//If the key is found, remove it from the linked list as we always do.
    				toReturn = head.next.value;
    				if(head.next.next == null)
    					head.setNext(null);
    				else
    					head.setValue(head.next.value);
    				numberOfEntries--;		//Decrement the number of entries
    				break;
    			}
    			head = head.next;
    		}
    	}
    	System.out.println("Made it");
        return toReturn;	//returns V if found, null if not found
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < hashTable.length; i++)
        {
            sb.append("Bucket #").append(i).append(":").append(System.getProperty("line.separator"));
            for(TableNode<K, V> iterator = hashTable[i]; iterator != null; iterator = iterator.getNext())
            {
                sb.append(iterator);
            }
            if(hashTable[i] == null)
            {
                sb.append("No Entries");
            }
            sb.append(System.getProperty("line.separator")).append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    private class TableNode<S, T>
    {
        private S key;
        private T value;

        // reference to next node
        TableNode<S, T> next;

        private TableNode(S key, T value)
        {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        private S getKey()
        {
            return key;
        }

        private T getValue()
        {
            return value;
        }

        private void setValue(T value)
        {
            this.value = value;
        }

        private TableNode<S, T> getNext()
        {
            return next;
        }

        private void setNext(TableNode<S, T> next)
        {
            this.next = next;
        }

        @Override
        public String toString()
        {
            return value.toString();
        }
    }
}
