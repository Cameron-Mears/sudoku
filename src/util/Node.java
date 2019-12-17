package util;

public class Node<Type> 
{
	private Type data;
	private Node<Type> next;
	private Node<Type> prev;
	
	public Node(Type data)
	{
		this.data = data;
	}
	
	public Type getData()
	{
		return data;
	}
	
	public Node<Type> getNext()
	{
		return next;
	}

	public Node<Type> getPrev() {
		return prev;
	}

	public void setPrev(Node<Type> prev) {
		
		this.prev = prev;
	}

	public void setData(Type data) 
	{
		this.data = data;
	}

	public void setNext(Node<Type> next) 
	{
		this.next = next;
	}

}
