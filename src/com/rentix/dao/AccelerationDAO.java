package com.rentix.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rentix.beans.Acceleration;

/**
 * A data access object (DAO) providing persistence and search support for
 * Acceleration entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.rentix.beans.Acceleration
 * @author MyEclipse Persistence Tools
 */

public class AccelerationDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AccelerationDAO.class);
	// property constants
	public static final String ACCEL = "accel";

	protected void initDao() {
		// do nothing
	}

	public void save(Acceleration transientInstance) {
		log.debug("saving Acceleration instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Acceleration persistentInstance) {
		log.debug("deleting Acceleration instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Acceleration findById(java.lang.Integer id) {
		log.debug("getting Acceleration instance with id: " + id);
		try {
			Acceleration instance = (Acceleration) getHibernateTemplate().get(
					"com.rentix.beans.Acceleration", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Acceleration> findByExample(Acceleration instance) {
		log.debug("finding Acceleration instance by example");
		try {
			List<Acceleration> results = (List<Acceleration>) getHibernateTemplate()
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
		log.debug("finding Acceleration instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Acceleration as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Acceleration> findByAccel(Object accel) {
		return findByProperty(ACCEL, accel);
	}

	public List findAll() {
		log.debug("finding all Acceleration instances");
		try {
			String queryString = "from Acceleration";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Acceleration merge(Acceleration detachedInstance) {
		log.debug("merging Acceleration instance");
		try {
			Acceleration result = (Acceleration) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Acceleration instance) {
		log.debug("attaching dirty Acceleration instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Acceleration instance) {
		log.debug("attaching clean Acceleration instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AccelerationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AccelerationDAO) ctx.getBean("AccelerationDAO");
	}
}