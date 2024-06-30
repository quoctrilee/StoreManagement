package DAO;

import java.util.ArrayList;

public interface DAO<T> {
	public int insert(T t);

	public int update(T t);

	public int delete(int t);

	public ArrayList<T> selectAll();

	public T selectById(int t);

}
