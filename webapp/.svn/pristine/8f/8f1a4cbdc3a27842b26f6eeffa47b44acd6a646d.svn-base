package com.addressmanagement.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.addressmanagement.domain.model.region.Region;
import com.addressmanagement.infrastructure.shared.DomainBaseRepository;

@Repository
@Transactional(readOnly = true)
public interface RegionRepository extends DomainBaseRepository<Region, Long> {

	@Override
	public Region findOne(Long id);

	@Override
	public List<Region> findAll();

	// @Query("select r from Region r where r.regionId = :regionId")
	// public Region findRegionById(@Param("regionId") ObjectId id);
	//
	// @Query("select r from Region r where r.name like :region")
	// public List<Region> findByRegionNameLike(@Param("region") String name);
	//
	// public List<Region> findRegionByNameContainingIgnoreCase(String name);
	//
	// public List<Country> findCountryByNameContainingIgnoreCase(String name);
	//
	public Region findByRegionCodeIgnoreCase(String regionCode);

	@Override
	@Modifying
	@Transactional
	public <S extends Region> List<S> save(Iterable<S> entities);

	@Modifying
	@Transactional
	@Query("delete from Region r where r.regionId = :id")
	public void deleteRegionById(@Param("id") Long id);

	@Override
	@Modifying
	@Transactional
	public void delete(Region entity);

	@Override
	@Modifying
	@Transactional
	public Region save(Region region);

}
