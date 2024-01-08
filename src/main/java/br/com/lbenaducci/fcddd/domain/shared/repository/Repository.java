package br.com.lbenaducci.fcddd.domain.shared.repository;

import java.util.List;

public interface Repository<T, I> {
	void create(T entity);

	void update(T entity);

	void delete(T entity);

	T findById(I id);

	List<T> findAll(int page, int size);
}
