package util.list;

import java.util.Iterator;

import util.Node;

public class List<Type> implements Iterable<Type>
{
	private Node<Type> head;
	private Node<Type> tail;
	private int length;
	
	public boolean append(Node<Type> node)
	{
		if (head == null)
		{
			head = node;
			tail = node;
			length ++;
			return true;
		}
		else
		{
			Node<Type> temp = tail;
			node.setPrev(temp);
			temp.setNext(node);
			node.setNext(null);
			tail = node;
			length ++;
			return true;
			
		}		
	}
	
	
	public void remove(Node<Type> node)
	{
		tail = tail;
		if (tail.equals(node))
		{
			if (head.equals(node))
			{
				tail = head = null;
				node.setNext(null);
				node.setPrev(null);
				length --;
				return;
			}
			else
			{
				Node<Type> temp = node.getPrev();
				tail = temp;
				temp.setNext(null);
				node.setPrev(null);
				length --;
				return;
			}
		}
		else if (head.equals(node))
		{
			head = node.getNext();
			node.setPrev(null);
			node.setNext(null);
			node.setPrev(null);
			length --;
			return;
		}
		else
		{
			Node<Type> prev = node.getNext();
			Node<Type> next = node.getNext();
			prev.setNext(next);
			next.setPrev(prev);
			node.setNext(null);
			node.setPrev(null);
			length --;
			return;
		}
	}
	
	
	public int length()
	{
		return length;
	}
	
	@Override
	public Iterator<Type> iterator() 
	{
		if (head != null)
		{
			return new ListIterator<Type>(head);
		}
		return null;
	}
	
	
	
	
	
}
