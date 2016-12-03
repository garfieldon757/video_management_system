package com.adp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adp.dao.AlgorithmDAO;
import com.adp.model.Algorithm;
import com.adp.model.AlgorithmCategory;

@Repository("ad")
@Transactional
public class AlgorithmDAOImpl implements AlgorithmDAO{

	@PersistenceContext(name="un")
	private EntityManager em ;

	@Override
	public AlgorithmCategory getAlgorithmCategoryByAlgorithmCategoryID(int AlgorithmCategoryID) {
		String jpql = "select ac from AlgorithmCategory ac where ac.algorithmCategoryID =: AlgorithmCategoryID";
		AlgorithmCategory algorithmCategory= (AlgorithmCategory) em.createQuery(jpql).getSingleResult();
		return algorithmCategory;
	}

	@Override
	public List<Algorithm> getAlgorithmsByCategory(AlgorithmCategory algorithmCategory) {
		String jpql = "select a from Algorithm a where a.algorithmCategory =: algorithmCategory";
		List<Algorithm> algorithms = em.createQuery(jpql).getResultList();
		return algorithms;
	}

	@Override
	public List<Algorithm> getAllAlgorithm() {
		String jpql = "select a from Algorithm a";
		List<Algorithm> algorithms = em.createQuery(jpql).getResultList();
		return algorithms;
	}
	
}
