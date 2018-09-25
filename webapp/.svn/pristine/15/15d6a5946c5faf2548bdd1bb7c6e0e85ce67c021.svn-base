package com.addressmanagement.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.addressmanagement.domain.model.address.Address;
import com.addressmanagement.domain.model.city.City;
import com.addressmanagement.infrastructure.shared.DomainBaseRepository;

@Transactional(readOnly = true)
public interface AddressRepository extends DomainBaseRepository<Address, Long> {

	@Override
	public Address findOne(Long id);

	@Override
	public List<Address> findAll();

	// @Query("select a from Address a where a.addressId = :id")
	// public Address findAddressById(@Param("id") Long id);

	// public List<Address> findByAddressDetailStreetName(String streetName);

	// public List<Address>
	// findByAddressDetailStreetNameContainingIgnoreCase(String streetName);

	List<Address> findByCity(City city);

	@Modifying
	@Transactional
	@Query("delete from Address a where a.addressId = :id")
	public void deleteAddressById(@Param("id") Long addressId);

	@Override
	@Modifying
	@Transactional
	public void delete(Address entity);

	@Override
	@Modifying
	@Transactional
	public Address save(Address address);

}
