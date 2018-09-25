package com.addressmanagement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addressmanagement.domain.model.address.Address;
import com.addressmanagement.domain.model.city.City;
import com.addressmanagement.domain.model.country.Country;
import com.addressmanagement.domain.model.region.Region;
import com.addressmanagement.infrastructure.persistence.AddressRepository;
import com.addressmanagement.infrastructure.persistence.CityRepository;
import com.addressmanagement.infrastructure.persistence.CountryRepository;
import com.addressmanagement.infrastructure.persistence.RegionRepository;
import com.addressmanagement.service.dto.AddressDTO;
import com.addressmanagement.service.dto.CityDTO;
import com.addressmanagement.service.dto.CountryDTO;
import com.addressmanagement.service.dto.RegionDTO;
import com.addressmanagement.service.internal.DTOAssembler;

@Service
public class AdministrationServiceImpl implements AdministrationService {

	private final static Logger logger = LoggerFactory.getLogger(AdministrationServiceImpl.class);

	private RegionRepository regionRepository;

	private CountryRepository countryRepository;

	private CityRepository cityRepository;

	private AddressRepository addressRepository;

	@Autowired
	private DTOAssembler assembler;

	@Autowired
	public AdministrationServiceImpl(RegionRepository regionRepository, CountryRepository countryRepository,
			CityRepository cityRepository, AddressRepository addressRepository) {
		this.regionRepository = regionRepository;
		this.countryRepository = countryRepository;
		this.cityRepository = cityRepository;
		this.addressRepository = addressRepository;
	}

	public void setAssembler(DTOAssembler assembler) {
		this.assembler = assembler;
	}

	@Override
	public List<RegionDTO> listRegions() {
		logger.debug("AdministrationService listRegions");
		List<Region> dbRegions = regionRepository.findAll();
		return assembler.toRegionDTOList(dbRegions);
	}

	@Override
	public List<CountryDTO> listCountries() {
		logger.debug("AdministrationService listCountries");
		List<Country> dbCountries = countryRepository.findAll();
		return assembler.toCountryDTOList(dbCountries);
	}

	@Override
	public List<CityDTO> listCities() {
		logger.debug("AdministrationService listCities");
		List<City> dbCities = cityRepository.findAll();
		return assembler.toCityDTOList(dbCities);
	}

	@Override
	public List<AddressDTO> listAddresses() {
		logger.debug("AdministrationService listAddresses");
		List<Address> dbAddresses = addressRepository.findAll();
		return assembler.toAddressDTOList(dbAddresses);
	}

	@Override
	public RegionDTO openRegion(Long id) {
		logger.debug("AdministrationService openRegion {}", id.toString());
		Region dbRegion = regionRepository.findOne(id);

		if (dbRegion != null) {
			RegionDTO dto = assembler.toRegionDTO(dbRegion);
			List<Country> dbCountries = countryRepository.findByRegion(dbRegion);
			if (dbCountries != null && (!(dbCountries.isEmpty()))) {
				List<CountryDTO> dtoList = assembler.toCountryDTOList(dbCountries);
				dto.setCountries(dtoList);
				return dto;
			}
		}
		return assembler.toRegionDTO(dbRegion);
	}

	@Override
	public CountryDTO openCountry(Long id) {
		logger.debug("AdministrationService openCountry {}", id.toString());
		Country dbCountry = countryRepository.findOne(id);

		if (dbCountry != null) {
			CountryDTO dto = assembler.toCountryDTO(dbCountry);
			List<City> dbCities = cityRepository.findByCountry(dbCountry);
			if (dbCities != null && (!(dbCities.isEmpty()))) {
				List<CityDTO> dtoList = assembler.toCityDTOList(dbCities);
				dto.setCities(dtoList);
				return dto;
			}
		}
		return assembler.toCountryDTO(dbCountry);
	}

	@Override
	public CityDTO openCity(Long id) {
		logger.debug("AdministrationService openCity {}", id.toString());
		City dbCity = cityRepository.findOne(id);

		if (dbCity != null) {
			CityDTO dto = assembler.toCityDTO(dbCity);
			List<Address> dbAddresses = addressRepository.findByCity(dbCity);
			if (dbAddresses != null && (!(dbAddresses.isEmpty()))) {
				List<AddressDTO> dtoList = assembler.toAddressDTOList(dbAddresses);
				dto.setAddresses(dtoList);
				return dto;
			}
		}
		return assembler.toCityDTO(dbCity);
	}

	@Override
	public AddressDTO openAddress(Long id) {
		logger.debug("AdministrationService openAddress {}", id.toString());
		Address dbAddress = addressRepository.findOne(id);

		return assembler.toAddressDTO(dbAddress);
	}

	@Override
	public RegionDTO createOrUpdateRegion(RegionDTO dto) {
		logger.debug("AdministrationService createOrUpdateRegion");
		Region assembled = assembler.fromRegionDTO(dto);
		Region dbRegion = regionRepository.save(assembled);
		return assembler.toRegionDTO(dbRegion);
	}

	@Override
	public CountryDTO createOrUpdateCountry(CountryDTO dto) {
		logger.debug("AdministrationService createOrUpdateCountry");
		Country assembled = assembler.fromCountryDTO(dto);
		Country dbCountry = countryRepository.save(assembled);
		return assembler.toCountryDTO(dbCountry);
	}

	@Override
	public CityDTO createOrUpdateCity(CityDTO dto) {
		logger.debug("AdministrationService createOrUpdateCity");
		City assembled = assembler.fromCityDTO(dto);
		City dbCity = cityRepository.save(assembled);
		return assembler.toCityDTO(dbCity);
	}

	@Override
	public AddressDTO createOrUpdateAddress(AddressDTO dto) {
		logger.debug("AdministrationService createOrUpdateAddress");
		Address assembled = assembler.fromAddressDTO(dto);
		Address dbAddress = addressRepository.save(assembled);
		return assembler.toAddressDTO(dbAddress);
	}

}
