package com.addressmanagement.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.addressmanagement.domain.model.country.Country;
import com.addressmanagement.domain.model.region.Region;
import com.addressmanagement.infrastructure.shared.DomainBaseRepository;

@Repository
@Transactional(readOnly = true)
public interface CountryRepository extends DomainBaseRepository<Country, Long> {

	@Override
	public Country findOne(Long id);

	@Override
	public List<Country> findAll();

	// @Query("select c from Country c where c.countryId = :id")
	// public Country findCountryById(@Param("id") Long id);
	//
	// @Query("select c from Country c where c.name like :name")
	// public List<Country> findByCountryNameLike(@Param("name") String name);
	//
	// @Query("select c from Country c where lower(c.name) = lower(:name)")
	// public Country findByNameIgnoreCaseQuery(@Param("name") String name);
	//
	// public Country findByNameIgnoreCase(String name);
	//
	// public List<City> findCitiesByName(Country name);

	public List<Country> findByRegion(Region region);

	@Override
	@Modifying
	@Transactional
	public <S extends Country> List<S> save(Iterable<S> entities);

	@Modifying
	@Transactional
	@Query("delete from Country c where c.countryId = :id")
	public void deleteCountryById(@Param("id") Long countryId);

	@Override
	@Modifying
	@Transactional
	public void delete(Country entity);

	@Override
	@Modifying
	@Transactional
	public Country save(Country country);

}
