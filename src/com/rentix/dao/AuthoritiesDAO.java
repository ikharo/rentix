package com.rentix.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rentix.beans.Authorities;

/**
 * A data access object (DAO) providing persistence and search support for
 * Authorities entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.rentix.beans.Authorities
 * @author MyEclipse Persistence Tools
 */

public class AuthoritiesDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AuthoritiesDAO.class);
	// property constants
	public static final String AUTHORITYNAME = "authorityname";

	protected void initDao() {
		// do nothing
	}

	public void save(Authorities transientInstance) {
		log.debug("saving Authorities instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Authorities persistentInstance) {
		log.debug("deleting Authorities instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Authorities findById(java.lang.String id) {
		log.debug("getting Authorities instance with id: " + id);
		try {
			Authorities instance = (Authorities) getHibernateTemplate().get(
					"com.rentix.beans.Authorities", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Authorities> findByExample(Authorities instance) {
		log.debug("finding Authorities instance by example");
		try {
			List<Authorities> results = (List<Authorities>) getHibernateTemplate()
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
		log.debug("finding Authorities instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Authorities as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Authorities> findByAuthorityname(Object authorityname) {
		return findByProperty(AUTHORITYNAME, authorityname);
	}

	public List findAll() {
		log.debug("finding all Authorities instances");
		try {
			String queryString = "from Authorities";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Authorities merge(Authorities detachedInstance) {
		log.debug("merging Authorities instance");
		try {
			Authorities result = (Authorities) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Authorities instance) {
		log.debug("attaching dirty Authorities instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Authorities instance) {
		log.debug("attaching clean Authorities instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AuthoritiesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AuthoritiesDAO) ctx.getBean("AuthoritiesDAO");
	}
}