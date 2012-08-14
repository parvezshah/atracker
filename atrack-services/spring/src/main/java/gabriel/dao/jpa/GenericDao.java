package gabriel.dao.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class GenericDao<T> {

	@PersistenceContext
	protected EntityManager em;

	protected Class<T> type;

	public GenericDao() {

		// type = (Class<T>) this.getClass();
		/*
		 * ParameterizedType genericSuperclass = (ParameterizedType) getClass()
		 * .getGenericSuperclass(); Type t =
		 * genericSuperclass.getActualTypeArguments()[0]; if (t instanceof
		 * Class) { this.type = (Class<T>) t; } else if (t instanceof
		 * ParameterizedType) { this.type = (Class<T>) ((ParameterizedType)
		 * t).getRawType(); }
		 */

	}

	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	public long countAll(final Map<String, Object> params) {

		final StringBuffer queryString = new StringBuffer(
				"SELECT count(o) from ");

		queryString.append(type.getSimpleName()).append(" o ");
		// queryString.append(this.getQueryClauses(params, null));

		final Query query = this.em.createQuery(queryString.toString());

		return (Long) query.getSingleResult();

	}

	@SuppressWarnings("unchecked")
	public T find(String query, final Object... queryArgs) {
		final Query namedQuery = this.em.createNamedQuery(query);

		for (int i = 0; i < queryArgs.length; i++) {
			namedQuery.setParameter(i + 1, queryArgs[i]);
		}
		try {
			return (T) namedQuery.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List findAll(String query, Integer startPosition,
			Integer maxRowCount, final Object... queryArgs) {
		Query namedQuery = null;
		try {
			namedQuery = this.em.createNamedQuery(query);
		} catch (Exception e) {
			// TODO THIS IS A HACK REMOVE THIS
			namedQuery = this.em.createQuery(query);
		}

		if (maxRowCount != null)
			namedQuery.setMaxResults(maxRowCount);
		
		if (startPosition != null)
			namedQuery.setFirstResult(startPosition);
		
		for (int i = 0; i < queryArgs.length; i++) {
			namedQuery.setParameter(i + 1, queryArgs[i]);
		}
		return namedQuery.getResultList();
	}

	public T create(final T t) {
		this.em.persist(t);
		return t;
	}

	// TODO implement batch
	public void createBatch(T[] ts) {
		for (T t : ts) {
			this.em.persist(t);
		}

	}

	public void delete(final Object id) {
		this.em.remove(this.em.getReference(type, id));
	}

	public T find(final Object id) {
		return (T) this.em.find(type, id);
	}

	public T update(final T t) {
		return this.em.merge(t);
	}

}