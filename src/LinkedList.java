/*  Student information for assignment:
 *
 *  On my honor, Ryan Resma, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: rmr3429
 *  email address: ryan.resma@utexas.edu
 *  Grader name: Joseph Manahan
 *  Number of slip days I am using: 0
 */

import java.util.Iterator;

public class LinkedList<E> implements IList<E> {

	private DoubleListNode<E> header;
	private int size;
	
	// constructor for the LinkedList class, instantiate a dummy header node to
	// point to itself, initialize size to 0
	public LinkedList() {
		header = new DoubleListNode<E>();
		header.setPrev(header);
		header.setNext(header);
		size = 0;
	}

	/*
	 * Helper method to quickly check if the list is empty
	 * pre: none
	 * post: none
	 * O(1)
	 */
	public boolean isEmpty() {
		return size == 0;
	}

    /**
     * add item to the front of the list.
     * <br>pre: item != null
     * <br>post: size() = old size() + 1, get(0) = item
     * @param item the data to add to the front of this list
     * O(1)
     */
    public void addFirst(E item){
    		// check precondition
    		if (item == null) {
    			throw new IllegalArgumentException("item != null");
    		}
    		// check to see if the LinkedList is empty, if so, create a new node to be placed after 
    		// the header with the given data encapsulated
    		if (this.isEmpty()) {
    			this.addLast(item);
    		} else {	
    			// create new node with the encapsulated data to be inserted in front of the header
    			// node that will point to the header node and its previous
    			DoubleListNode<E> newNode = new DoubleListNode<E> (header, item, header.getNext());  			
    			// header's next's prev reference points to the new node
    			header.getNext().setPrev(newNode); 			
    			// header's prev reference points to the new node
    			header.setNext(newNode);	
        		size++;
    		}   		
    }


    /**
     * add item to the end of the list.
     * <br>pre: item != null
     * <br>post: size() = old size() + 1, get(size() -1) = item
     * @param item the data to add to the end of this list
     * O(1)
     */
    public void addLast(E item){
    		// check precondition
    		if (item == null) {
    			throw new IllegalArgumentException("item != null");
    		}	
    		// check to see if the LinkedList is empty, if so, create a new node as the header with
    		// the given data encapsulated
    		if (this.isEmpty()) {
    			DoubleListNode<E> newNode = new DoubleListNode<E>(header, item, header);
    			header.setNext(newNode);
    			header.setPrev(newNode);
    		} else {	
    			// create new node with data to be inserted after tailer, points to tailer & header
    			DoubleListNode<E> newNode = new DoubleListNode<E> (header.getPrev(), item, header);
    			// header's previous's next reference points to the new node
    			header.getPrev().setNext(newNode); 			
    			// header node's prev reference points to the new node
    			header.setPrev(newNode);
    		}  		
    		size++;
    }


    /**
     * remove and return the first element of this list.
     * <br>pre: size() > 0
     * <br>post: size() = old size() - 1
     * @return the old first element of this list
     * O(1)
     */
    public E removeFirst(){
    		// check precondition
    		if (this.isEmpty()) {
    			throw new IllegalArgumentException("size() > 0");
    		} 
    		// store the value encapsulated by the header node in E toReturn
    		E toReturn = header.getData();
    		// check if there is only one element in the list, if so, empty list
    		if (this.size() == 1) { 
    			this.makeEmpty();
    		} else {
    			// two nodes ahead of header now "prev" refers to header
        		header.getNext().getNext().setPrev(header);
        		// header's next refs now points to two nodes ahead
        		header.setNext(header.getNext().getNext());   
        		size--;
    		}
    		return toReturn;
    }

    /**
     * remove and return the last element of this list.
     * <br>pre: size() > 0
     * <br>post: size() = old size() - 1
     * @return the old last element of this list
     * O(1)
     */
    public E removeLast(){	
    		// check precondition
		if (this.isEmpty()) {
			throw new IllegalArgumentException("size() > 0");
		} 
		// store the value encapsulated by the header node's previous node in E toReturn
		E toReturn = header.getPrev().getData();	
		// check if there is only one element in the list, if so, empty list
		if (this.size() == 1) {
			this.makeEmpty();
		} else {
			// two nodes behind header now "next" refers to header
			header.getPrev().getPrev().setNext(header);
			// header's prev ref now points to two nodes before header
			header.setPrev(header.getPrev().getPrev());
			size--;
		}	
		return toReturn;
    }
    
    /*
     * Helper Method to return the node at a given position by parsing through the 
     * LinkedList from the header node
     * O(N)
     */
    private DoubleListNode<E> getNode(int pos) {
    		// check preconditions
    		if (pos < 0 || pos >= size || this.isEmpty()) {
    			throw new IllegalArgumentException ("0 <= pos < size, size > 0");
    		}
    		DoubleListNode<E> result;
    		if (pos <= size/2) {
    			// instantiate the result to point to the first node in the list
        		result = header.getNext();
        		// "increment" the node (point to the next node) until int pos is reached
        		for (int nodeIndex = 0; nodeIndex < pos; nodeIndex++) {
        			result = result.getNext();
        		}
    		} else {
    			// instantiate the result to point to the last node in the list
    			result = header.getPrev();
    			// "decrement" the node (point to the prev node) until int pos is reached
    			for (int nodeIndex = size -1; nodeIndex > pos; nodeIndex--) {
    				result = result.getPrev();
    			}
    		}
    		return result;
    }

    /**
     * Add an item to the end of this list.
     * <br>pre: item != null
     * <br>post: size() = old size() + 1, get(size() - 1) = item
     * @param item the data to be added to the end of this list, item != null
     * O(1)
     */
	@Override
	public void add(E item) {
		this.addLast(item);
	}

    /**
     * Insert an item at a specified position in the list.
     * <br>pre: 0 <= pos <= size(), item != null
     * <br>post: size() = old size() + 1, get(pos) = item, all elements in
     * the list with a position >= pos have a position = old position + 1
     * @param pos the position to insert the data at in the list
     * @param item the data to add to the list, item != null
     * O(N)
    */
	@Override
	public void insert(int pos, E item) {
		// check precondition
		if (pos < 0 || pos > size || item == null) {
			throw new IllegalArgumentException("pos >= 0 && pos <= size, item must not be null");
		}	
		// quick check to see if adding at the front of the list
		if (pos == 0) {
			this.addFirst(item);
		} else if (pos == size) {
			this.addLast(item);
		} else {
			// instantiate the temporary node that references the node at the given position
			DoubleListNode<E> temp = this.getNode(pos);
			// instantiate a new node that points to the temp and it's previous
			DoubleListNode<E> newNode = new DoubleListNode<E> (temp.getPrev(), item, temp);
			// temp's previous's next ref now points to new node
			temp.getPrev().setNext(newNode);	
			// temp's prev ref now points to new node
			temp.setPrev(newNode);
			size++;
		}
	}

    /**
     * Change the data at the specified position in the list.
     * the old data at that position is returned.
     * <br>pre: 0 <= pos < size(), item != null
     * <br>post: get(pos) = item, return the
     * old get(pos)
     * @param pos the position in the list to overwrite  
     * @param item the new item that will overwrite the old item, item != null
     * @return the old data at the specified position
     * O(N)
     */
	@Override
	public E set(int pos, E item) {
		// check precondition
		if (pos < 0 || pos >= size || item == null || this.isEmpty()) {
			throw new IllegalArgumentException("pos >= 0 && pos < size, "
					+ "item must not be null, size > 0");
		}
		// instantiate the temporary node that references the node at the given position
		DoubleListNode<E> temp = this.getNode(pos);
		// instantiate a variable E to store element that is being changed
		E toReturn = temp.getData();
		// set the data of the node at the int pos to E item
		temp.setData(item);	
		return toReturn;
	}

    /**
     * Get an element from the list.
     * <br>pre: 0 <= pos < size()
     * <br>post: return the item at pos
     * @param pos specifies which element to get
     * @return the element at the specified position in the list
     * O(N)
     */
	@Override
	public E get(int pos) {
		// check precondition
		if (pos < 0 || pos >= size || this.isEmpty()) {
			throw new IllegalArgumentException("pos >= 0 && pos < size, size > 0");
		}
		// instantiate the temporary node that references the node at the given position
		DoubleListNode<E> temp = this.getNode(pos);
		return temp.getData();
	}

    /**
     * Remove an element in the list based on position.
     * <br>pre: 0 <= pos < size()
     * <br>post: size() = old size() - 1, all elements of
     * list with a positon > pos have a position = old position - 1
     * @param pos the position of the element to remove from the list
     * @return the data at position pos
     * O(N)
     */
	@Override
	public E remove(int pos) {	
		// check precondition
		if (pos < 0 || pos >= size || this.isEmpty()) {
			throw new IllegalArgumentException("pos >= 0 && pos < size, size > 0");
		}		
		// variable to store element to be removed 
		E toReturn;
		// instantiate the temporary node that references the node at the given position
		DoubleListNode<E> temp = this.getNode(pos);
		if (size == 1) {
			toReturn = temp.getData();
			this.makeEmpty();
		} else {
			// temp's previous's next ref now points to the node after temp
			temp.getPrev().setNext(temp.getNext());
			// temp's next's prev ref now points to the node before temp
			temp.getNext().setPrev(temp.getPrev());
			// initialize variable to store the element to be removed 
			toReturn = temp.getData();
			size--;
		}	
		return toReturn;
	}

	
    /**
     * Remove the first occurrence of obj in this list.
     * Return <tt>true</tt> if this list changed as a result of this call, 
     * <tt>false</tt> otherwise.
     * <br>pre: obj != null
     * <br>post: if obj is in this list the first occurrence has been removed and 
     * 			size() = old size() - 1. 
     * If obj is not present the list is not altered in any way.
     * @param obj The item to remove from this list. obj != null
     * @return Return <tt>true</tt> if this list changed as a result of this call, 
     * 			<tt>false</tt> otherwise.
     * O(N)
     */
	@Override
	public boolean remove(E obj) {
		// check precondition
		if (obj == null) {
			throw new IllegalArgumentException("E obj must not be null");
		}
		// parse through the linked list of nodes, checking if the node with data E obj
		// is found
		boolean found = false;
		int nodeIndex = 0;
		DoubleListNode<E> temp = header.getNext();
		while (nodeIndex < size && found == false) {
			// check if the data of temp matches the data passed into the method (E obj)
			if (temp.getData().equals(obj)) {
				found = true;
				// temp's previous's next ref now points to the node after temp
				temp.getPrev().setNext(temp.getNext());
				// temp's next's prev ref now points to the node before temp
				temp.getNext().setPrev(temp.getPrev());
				size--;
			}
			// move onto the next node and increment the current index)
			temp = temp.getNext();
			nodeIndex++;
		}
		return found;
	}

    /**
     * Return a sublist of elements in this list from 
     * <tt>start</tt> inclusive to <tt>stop</tt> exclusive.
     * This list is not changed as a result of this call.
     * <br>pre: <tt>0 <= start <= size(), start <= stop <= size()</tt>
     * <br>post: return a list whose size is stop - start and contains the 
     * 			elements at positions start through stop - 1 in this list.
     * @param start index of the first element of the sublist.
     * @param stop stop - 1 is the index of the last element of the sublist.
     * @return a list with <tt>stop - start</tt> elements, The elements are from 
     * 			positions <tt>start</tt> inclusive to
     * <tt>stop</tt> exclusive in this list. If start == stop an empty list is returned.
     * O(N)
     */
	@Override
	public IList<E> getSubList(int start, int stop) {
		// check precondition
		if (start < 0 || start > stop || stop > size) {
			throw new IllegalArgumentException("0 <= start <= stop <= size");
		}
		// instantiate a LinkedList<E> to be returned at the end of the method
		LinkedList<E> result = new LinkedList<E>();
		// parse through the current index from index start to stop non-inclusive,
		// adding each element at the current index to the result
		for (int pos = start; pos < stop; pos++) {
			result.add(this.get(pos));
		}
		return result;
	}

    /**
     * Return the size of this list. In other words the number of elements in this list.
     * <br>pre: none
     * <br>post: return the number of items in this list
     * @return the number of items in this list
     * O(1)
     */
	@Override
	public int size() {
		return size;
	}

    /**
     * Find the position of an element in the list.
     * <br>pre: item != null
     * <br>post: return the index of the first element equal to item
     * or -1 if item is not present
     * @param item the element to search for in the list. item != null
     * @return return the index of the first element equal to item or a -1 if item is not present
     * O(N)
     */

	@Override
	public int indexOf(E item) {	
		// check preconditions
		if (item == null) {
			throw new IllegalArgumentException("item != null");
		}	
		// parse through the linked list of nodes, checking if the node with data E obj
		// exists
		int nodeIndex = 0;
		DoubleListNode<E> temp = header.getNext();
		while (nodeIndex < size) {
			// check if the data of temp matches the data passed into the method (E item)
			if (temp.getData().equals(item)) {
				return nodeIndex;
			}
			temp = temp.getNext();
			nodeIndex++;
		}
		// if E item does not exist, return -1
		return -1;
	}

    /**
     * find the position of an element in the list starting at a specified position.
     * <br>pre: 0 <= pos < size(), item != null
     * <br>post: return the index of the first element equal to item starting at pos
     * or -1 if item is not present from position pos onward
     * @param item the element to search for in the list. Item != null
     * @param pos the position in the list to start searching from
     * @return starting from the specified position return the index of the first element equal to 
     * 			item or a -1 if item is not present between pos and the end of the list
     * O(N)
     */
	@Override
	public int indexOf(E item, int pos) {	
		// check preconditions
		if (item == null || pos < 0 || pos >= size) {
			throw new IllegalArgumentException("item != null and 0 <= pos < size");
		}	
		// parse through the linked list of nodes, checking if the node with data E obj
		// exists
		int nodeIndex = pos;
		DoubleListNode<E> temp = this.getNode(pos);
		while (nodeIndex < size) {
			// check if the data of temp matches the data passed into the method (E item)
			if (temp.getData().equals(item)) {
				return nodeIndex;
			}
			temp = temp.getNext();
			nodeIndex++;
		}	
		// if E item does not exist after pos, return -1
		return -1;
	}

    /**
     * return the list to an empty state.
     * <br>pre: none
     * <br>post: size() = 0
     * O(1)
     */
	@Override
	public void makeEmpty() {
		header.setNext(header);
		header.setPrev(header);
		size = 0;
	}

	/**
	 * return an Iterator for this list. <br>
	 * pre: none <br>
	 * post: return an Iterator object for this List
	 * O(1)
	 */
	@Override
	public Iterator<E> iterator() {
		return new LLIterator();
	}
	
	/***
	 * private LLIterator class for the LinkedList<E> class
	 * @author ryanresma
	 *
	 */
	private class LLIterator implements Iterator<E> {
		
		private DoubleListNode<E> nextNode;
		private boolean removeOk;
		private int posToRemove;
		
		// constructor for the LLIterator class, initialize the private instance
		// variables accordingly
		// O(1)
		private LLIterator() {
			nextNode = header.getNext();
			removeOk = false;
			posToRemove = -1;
		}
		
		// hasNext() method for LLIterator class; because the list is circular, hasNext()
		// will return true for as long as the list has an element
		// O(1)
		public boolean hasNext() {
			return posToRemove < size - 1;
		}
		
		// next() method for LLIterator class, increments the posToRemove, and changes the
		// current node to the following node
		// O(1)
		public E next() {		
			// check precondition
			if (!hasNext()) {
				throw new IllegalStateException("nextNode is at position size");
			}	
			// instantiate a variable E to be returned at end of method
			E result = nextNode.getData();
			// make nextNode the node that follows it
			nextNode = nextNode.getNext();
			// increment position of iterator
			posToRemove++;
			removeOk = true;	
			return result;
		}
		
		// remove() method for LLIterator class
		// O(1)
		public void remove() {	
			// check precondition
			if (removeOk == false) {
				throw new IllegalStateException("must call next() before calling remove");
			}
			// remove the element before nextNode from the LinkedList
			nextNode.getPrev().getPrev().setNext(nextNode);
			nextNode.setPrev(nextNode.getPrev().getPrev());
			// decrement position of iterator
			posToRemove--;
			// must call next() again to make removeOk true
			removeOk = false;
			LinkedList.this.size--;
		}
	}

    /**
     * Remove all elements in this list from <tt>start</tt> inclusive to <tt>stop</tt> exclusive.
     * <br>pre: <tt>0 <= start < size(), start <= stop <= size()</tt>
     * <br>post: <tt>size() = old size() - (stop - start)</tt>
     * @param start position at beginning of range of elements to be removed
     * @param stop stop - 1 is the position at the end of the range of elements to be removed
     * O(N)
     */
	@Override
	public void removeRange(int start, int stop) {
		// check precondition
		if (start < 0 || start > stop || stop > size) {
			throw new IllegalArgumentException("0 <= start <= stop <= size");
		}	
		// quick check to see if you are removing the whole list
		if (stop - start == size) {
			this.makeEmpty();
		} else if (start != stop) {
			// parse through the list starting at index start and running to index stop,
			// removing the elements at the current index
			DoubleListNode<E> temp1 = this.getNode(start);
			DoubleListNode<E> temp2 = temp1;
			for (int index = start; index < stop; index++) {
				temp2 = temp2.getNext();
			}
			temp1.getPrev().setNext(temp2);
			temp2.setPrev(temp1);
			// adjust size accordingly
			size = size - (stop - start);
		}
	}  
	
    /**
     * Return a String version of this list enclosed in
     * square brackets, []. Elements are in
     * are in order based on position in the 
     * list with the first element
     * first. Adjacent elements are separated by comma's
     * @return a String representation of this IList
     * O(N)
     */
	@Override
	public String toString() {
		// instantiate new StringBuilder object to be returned as a string at end of method
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if (size != 0) {
			// instantiate a temporary node to be assigned to each node as we parse the list
			DoubleListNode<E> temp = header.getNext();
			sb.append(temp.getData());
			temp = temp.getNext();
			// parse through the list, starting at header node and running to end of list
			for (int index = 1; index < size; index++) {
				sb.append(", ");
				// add the element to the StringBuilder
				sb.append(temp.getData());
				// move on to the next node
				temp = temp.getNext();
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * Method to compare two LinkedList objects. Two LinkedLists are equal if they have
	 * the same number of elements in the same order. Two empty lists are equal regardless
	 * of the kinds of elements they store.
	 * pre: other != null, object.getClass() == this.getClass()
	 * post: none
	 * @return boolean value of whether the lists are equal to each other
	 * O(N)
	 */
	@Override
	public boolean equals(Object other) {	
		// check preconditions
		if (other == null || other.getClass() != this.getClass()) {
			throw new IllegalArgumentException("other != null, "
					+ "compared objects' classes must match");
		}
		// quick check to see if the lists are the same size, if not, then they cannot be equal
		if (this.size() != ((IList<E>) other).size()) {
			return false;
		} else {
			// instantiate iterators for both objects in comparison
			Iterator LHS = this.iterator();
			Iterator RHS = ((IList<E>) other).iterator();
			// parse through both lists, checking if the data of all corresponding elemnts are
			// equal to each other
			int index = 0;
			while(index < size) {
				if (!LHS.next().equals(RHS.next())) {
					return false;
				}
				index++;
			}
			// if loop is escaped, then lists have been checked for differences through 1 rotation
			return true;
		}
	}
}