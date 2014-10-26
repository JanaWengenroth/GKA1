package GKA.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Matrix<K,V>{
	List<K> rows;
	List<K> columns;
	List<List<V>> matrix;
	public Matrix(Set<K> rows, Set<K> columns){
		this.rows = new ArrayList<>(rows);
		this.columns = new ArrayList<>(columns);
		matrix = new ArrayList<>();
		for(K row : rows){
			ArrayList<V> tmpList = new ArrayList<>();
			for (K column : columns){
				tmpList.add(null);
			}
			matrix.add(tmpList);
		}
	}
	public Matrix(Matrix<K,V> matrix){
		this(new HashSet<>(matrix.getRows()), new HashSet<>(matrix.getColumns()));
		for(K row : rows){
			for(K column : columns){
				put(row, column, matrix.get(row, column));
			}
		}
	}
	public List<K> getColumns(){
		return Collections.unmodifiableList(columns);
	}
	
	public List<K> getRows(){
		return Collections.unmodifiableList(rows);
	}
	
	public V get(K row, K column) throws IndexOutOfBoundsException{
		if (!rows.contains(row) || !columns.contains(column)){
			throw new IndexOutOfBoundsException();
		}
		return matrix.get(rows.indexOf(row)).get(columns.indexOf(column));
	}
	public void put(K row, K column, V value) throws IndexOutOfBoundsException{
		if (!rows.contains(row) || !columns.contains(column)){
			throw new IndexOutOfBoundsException();
		}
		matrix.get(rows.indexOf(row)).set(columns.indexOf(column), value);
	}
	public String toString(){
		String retVal = "[";
		for (K row : rows){
			for (K column : columns){
				retVal += "(" + String.valueOf(row) + ", " + String.valueOf(column) + ") = " + String.valueOf(get(row,column)) + "; ";
			}
		}
		return retVal + "]";
	}
}
