package com.rentix.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rentix.beans.Obj;

/**
 * A data access object (DAO) providing persistence and search support for Obj
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.rentix.beans.Obj
 * @author MyEclipse Persistence Tools
 */

public class ObjDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(ObjDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String NUM_TRIANGLES = "numTriangles";

	protected void initDao() {
		// do nothing
	}

	public void save(Obj transientInstance) {
		log.debug("saving Obj instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Obj persistentInstance) {
		log.debug("deleting Obj instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Obj findById(java.lang.Integer id) {
		log.debug("getting Obj instance with id: " + id);
		try {
			Obj instance = (Obj) getHibernateTemplate().get(
					"com.rentix.beans.Obj", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Obj> findByExample(Obj instance) {
		log.debug("finding Obj instance by example");
		try {
			List<Obj> results = (List<Obj>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Obj instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Obj as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Obj> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Obj> findByNumTriangles(Object numTriangles) {
		return findByProperty(NUM_TRIANGLES, numTriangles);
	}

	public List findAll() {
		log.debug("finding all Obj instances");
		try {
			String queryString = "from Obj";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Obj merge(Obj detachedInstance) {
		log.debug("merging Obj instance");
		try {
			Obj result = (Obj) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Obj instance) {
		log.debug("attaching dirty Obj instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Obj instance) {
		log.debug("attaching clean Obj instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ObjDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ObjDAO) ctx.getBean("ObjDAO");
	}
}