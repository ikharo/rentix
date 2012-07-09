package com.rentix.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rentix.beans.Scene;

/**
 * A data access object (DAO) providing persistence and search support for Scene
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.rentix.beans.Scene
 * @author MyEclipse Persistence Tools
 */

public class SceneDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(SceneDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String NUM_VERTICES = "numVertices";
	public static final String NUM_NORMALS = "numNormals";
	public static final String NUM_TEXCOORDS = "numTexcoords";
	public static final String NUM_FACETNORMS = "numFacetnorms";
	public static final String NUM_TRIANGLES = "numTriangles";
	public static final String NUM_MATERIALS = "numMaterials";
	public static final String NUM_GROUPS = "numGroups";
	public static final String RENDER_EST_TIME = "renderEstTime";
	public static final String CAMERA_POS = "cameraPos";

	protected void initDao() {
		// do nothing
	}

	public void save(Scene transientInstance) {
		log.debug("saving Scene instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Scene persistentInstance) {
		log.debug("deleting Scene instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Scene findById(java.lang.Integer id) {
		log.debug("getting Scene instance with id: " + id);
		try {
			Scene instance = (Scene) getHibernateTemplate().get(
					"com.rentix.beans.Scene", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Scene> findByExample(Scene instance) {
		log.debug("finding Scene instance by example");
		try {
			List<Scene> results = (List<Scene>) getHibernateTemplate()
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
		log.debug("finding Scene instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Scene as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Scene> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Scene> findByNumVertices(Object numVertices) {
		return findByProperty(NUM_VERTICES, numVertices);
	}

	public List<Scene> findByNumNormals(Object numNormals) {
		return findByProperty(NUM_NORMALS, numNormals);
	}

	public List<Scene> findByNumTexcoords(Object numTexcoords) {
		return findByProperty(NUM_TEXCOORDS, numTexcoords);
	}

	public List<Scene> findByNumFacetnorms(Object numFacetnorms) {
		return findByProperty(NUM_FACETNORMS, numFacetnorms);
	}

	public List<Scene> findByNumTriangles(Object numTriangles) {
		return findByProperty(NUM_TRIANGLES, numTriangles);
	}

	public List<Scene> findByNumMaterials(Object numMaterials) {
		return findByProperty(NUM_MATERIALS, numMaterials);
	}

	public List<Scene> findByNumGroups(Object numGroups) {
		return findByProperty(NUM_GROUPS, numGroups);
	}

	public List<Scene> findByRenderEstTime(Object renderEstTime) {
		return findByProperty(RENDER_EST_TIME, renderEstTime);
	}

	public List<Scene> findByCameraPos(Object cameraPos) {
		return findByProperty(CAMERA_POS, cameraPos);
	}

	public List findAll() {
		log.debug("finding all Scene instances");
		try {
			String queryString = "from Scene";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Scene merge(Scene detachedInstance) {
		log.debug("merging Scene instance");
		try {
			Scene result = (Scene) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Scene instance) {
		log.debug("attaching dirty Scene instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Scene instance) {
		log.debug("attaching clean Scene instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SceneDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SceneDAO) ctx.getBean("SceneDAO");
	}
}