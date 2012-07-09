package com.rentix.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import rentix.dao.HibernateSessionFactory;

import com.rentix.beans.Proyecto;
import com.rentix.beans.Users;

/**
 * A data access object (DAO) providing persistence and search support for
 * Proyecto entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rentix.beans.Proyecto
 * @author MyEclipse Persistence Tools
 */

public class ProyectoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ProyectoDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String PATH = "path";

	protected void initDao() {
		// do nothing
	}

	public void save(Proyecto transientInstance) {
		log.debug("saving Proyecto instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Proyecto persistentInstance) {
		log.debug("deleting Proyecto instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Proyecto findById(java.lang.Integer id) {
		log.debug("getting Proyecto instance with id: " + id);
		try {
			Proyecto instance = (Proyecto) getHibernateTemplate().get(
					"com.rentix.beans.Proyecto", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Proyecto> findByExample(Proyecto instance) {
		log.debug("finding Proyecto instance by example");
		try {
			List<Proyecto> results = (List<Proyecto>) getHibernateTemplate()
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
		log.debug("finding Proyecto instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Proyecto as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Proyecto> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Proyecto> findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	public List findAll() {
		log.debug("finding all Proyecto instances");
		try {
			String queryString = "from Proyecto";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllbyUser(Users user) {
		log.debug("finding all Proyecto instances by User");
		try {

			/*
			Query query = this.getSession().getNamedQuery("findAllbyUser").set
			return query.list();
			 */
			return findAll();
			
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	

	public Proyecto merge(Proyecto detachedInstance) {
		log.debug("merging Proyecto instance");
		try {
			Proyecto result = (Proyecto) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Proyecto instance) {
		log.debug("attaching dirty Proyecto instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Proyecto instance) {
		log.debug("attaching clean Proyecto instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProyectoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ProyectoDAO) ctx.getBean("ProyectoDAO");
	}
}