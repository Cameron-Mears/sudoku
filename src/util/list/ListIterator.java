package util.list;

import java.util.Iterator;

import util.Node;

public class ListIterator<Type> implements Iterator<Type>
{

	private Node<Type> node;
	
	public ListIterator(Node<Type> node) 
	{
		this.node = node;
	}
	
	@Override
	public boolean hasNext() 
	{
		return node != null;
	}

	@Override
	public Type next() 
	{
		Node<Type> temp = node;
		node = node.getNext();
		return temp.getData();
	}

}
