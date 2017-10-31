package it.umberto.palo.datamongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="customer")
public class Customer {

	@Id
	@Indexed
	private String id;
	
	@Indexed
	private String name;
	private int age;
	
	public Customer(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	public int getAge(){
		return this.age;
	}
	
	public String toString(){
		String info = String.format("{'id': %s 'name': %s, 'age': %d}", id, name, age);
		return info;
	}
}
