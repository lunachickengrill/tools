package com.addressmanagement.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.addressmanagement.domain.model.city.City;
import com.addressmanagement.domain.model.country.Country;
import com.addressmanagement.infrastructure.shared.DomainBaseRepository;

@Repository
@Transactional(readOnly = true)
public interface CityRepository extends DomainBaseRepository<City, Long> {

	@Override
	public City findOne(Long id);

	@Override
	public List<City> findAll();

	// @Query("select c from City c where c.cityId = :cityId")
	// public City findCityById(@Param("cityId") ObjectId id);

	public City findByZip(String zip);
	//
	// public List<City> findByNameIgnoreCase(String name);
	//
	// public List<City> findByNameContainingIgnoreCase(String name);

	// public List<Address> findByNameIgnoreCase(String name);

	public List<City> findByCountry(Country country);

	@Modifying
	@Transactional
	@Query("delete from City c where c.cityId = :cityId")
	public void deleteCityById(@Param("cityId") Long cityId);

	@Override
	@Modifying
	@Transactional
	public void delete(City entity);

	@Override
	@Modifying
	@Transactional
	public City save(City city);

}
