package com.addressmanagement.service.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.addressmanagement.domain.model.address.Address;
import com.addressmanagement.domain.model.address.AddressDetail;
import com.addressmanagement.domain.model.city.City;
import com.addressmanagement.domain.model.country.Country;
import com.addressmanagement.domain.model.region.Region;
import com.addressmanagement.infrastructure.persistence.AddressRepository;
import com.addressmanagement.infrastructure.persistence.CityRepository;
import com.addressmanagement.infrastructure.persistence.CountryRepository;
import com.addressmanagement.infrastructure.persistence.RegionRepository;
import com.addressmanagement.service.dto.AddressDTO;
import com.addressmanagement.service.dto.AddressDetailDTO;
import com.addressmanagement.service.dto.CityDTO;
import com.addressmanagement.service.dto.CountryDTO;
import com.addressmanagement.service.dto.RegionDTO;

@Component
public class DTOAssembler {

	private RegionRepository regionRepository;

	private CountryRepository countryRepository;

	private CityRepository cityRepository;

	private AddressRepository addressRepository;

	@Autowired
	public DTOAssembler(RegionRepository regionRepository, CountryRepository countryRepository,
			CityRepository cityRepository, AddressRepository addressRepository) {
		super();
		this.regionRepository = regionRepository;
		this.countryRepository = countryRepository;
		this.cityRepository = cityRepository;
		this.addressRepository = addressRepository;
	}

	private boolean hasId(String aId) {
		if (aId != null && (!(aId.isEmpty())))
			return true;
		return false;
	}

	private boolean isNewValue(String aObject, String bObject) {
		if (aObject != null && (!aObject.equalsIgnoreCase(bObject)))
			return true;
		return false;
	}

	public List<RegionDTO> toRegionDTOList(List<Region> regions) {
		final List<RegionDTO> dtoList = new ArrayList<RegionDTO>(regions.size());
		for (Region region : regions) {
			dtoList.add(toRegionDTO(region));
		}
		return dtoList;
	}

	public List<CountryDTO> toCountryDTOList(List<Country> countries) {
		final List<CountryDTO> dtoList = new ArrayList<CountryDTO>(countries.size());
		for (Country country : countries) {
			dtoList.add(toCountryDTO(country));
		}
		return dtoList;
	}

	public List<CityDTO> toCityDTOList(List<City> cities) {
		final List<CityDTO> dtoList = new ArrayList<CityDTO>(cities.size());
		for (City city : cities) {
			dtoList.add(toCityDTO(city));
		}
		return dtoList;
	}

	public List<AddressDTO> toAddressDTOList(List<Address> addresses) {
		final List<AddressDTO> dtoList = new ArrayList<AddressDTO>(addresses.size());
		for (Address address : addresses) {

			dtoList.add(toAddressDTO(address));
		}
		return dtoList;
	}

	public RegionDTO toRegionDTO(Region region) {
		final RegionDTO dto = new RegionDTO(region.idString(), region.getRegionCode(), region.getName(),
				region.getDescription());
		return dto;
	}

	public CountryDTO toCountryDTO(Country country) {
		final CountryDTO dto = new CountryDTO(country.idString(), country.getName(), country.getCountryCode(),
				country.getDescription(), toRegionDTO(country.getRegion()));
		return dto;
	}

	public CityDTO toCityDTO(City city) {
		final CityDTO dto = new CityDTO(city.idString(), city.getZip(), city.getName(), city.getDescription(),
				toCountryDTO(city.getCountry()));
		return dto;
	}

	public AddressDTO toAddressDTO(Address address) {
		final AddressDetailDTO details = toAddressDetailDTO(address.getAddressDetail());
		final AddressDTO dto = new AddressDTO(address.idString(), address.getDescription(), details,
				toCityDTO(address.getCity()));
		return dto;
	}

	public AddressDetailDTO toAddressDetailDTO(AddressDetail details) {
		final AddressDetailDTO dto = new AddressDetailDTO(details.getStreetName(), details.getStreetNumber(),
				details.getEntrance());
		return dto;
	}

	public Region fromRegionDTO(RegionDTO dto) {

		if (hasId(dto.getId())) {
			final Long id = Long.valueOf(dto.getId());
			Region dbRegion = regionRepository.findOne(id);
			if (dbRegion != null) {

				if (isNewValue(dto.getDescription(), dbRegion.getDescription())) {
					dbRegion.setDescription(dto.getDescription());
				}
				if (isNewValue(dto.getName(), dbRegion.getName())) {
					dbRegion.setName(dto.getName());
				}
				if (isNewValue(dto.getRegionCode(), dbRegion.getRegionCode())) {
					dbRegion.setRegionCode(dto.getRegionCode());
				}
			}
			return dbRegion;
		}
		return new Region(dto.getRegionCode(), dto.getName());
	}

	public Country fromCountryDTO(CountryDTO dto) {
		if (hasId(dto.getId())) {
			final Long id = Long.valueOf(dto.getId());
			Country dbCountry = countryRepository.findOne(id);
			if (dbCountry != null) {
				if (isNewValue(dto.getCountryCode(), dbCountry.getCountryCode())) {
					dbCountry.setCountryCode(dto.getCountryCode());
				}
				if (isNewValue(dto.getDescription(), dbCountry.getDescription())) {
					dbCountry.setDescription(dto.getDescription());
				}
				if (isNewValue(dto.getName(), dbCountry.getName())) {
					dbCountry.setName(dto.getName());
				}
				if (isNewValue(dto.getRegion().getId(), dbCountry.idString())) {
					dbCountry.setRegion(fromRegionDTO(dto.getRegion()));
				}
			}
			return dbCountry;
		}
		return new Country(fromRegionDTO(dto.getRegion()), dto.getName(), dto.getCountryCode());
	}

	public City fromCityDTO(CityDTO dto) {
		if (hasId(dto.getId())) {
			final Long id = Long.valueOf(dto.getId());
			City dbCity = cityRepository.findOne(id);
			if (dbCity != null) {
				if (isNewValue(dto.getDescription(), dbCity.getDescription())) {
					dbCity.setDescription(dto.getDescription());
				}
				if (isNewValue(dto.getName(), dbCity.getName())) {
					dbCity.setName(dto.getName());
				}
				if (isNewValue(dto.getZip(), dbCity.getZip())) {
					dbCity.setZip(dto.getZip());
				}
				// if (isNewValue(dto.getCountry().getId(), dbCity.idString()))
				// {
				if (fromCountryDTO(dto.getCountry()).sameIdentityAs(dbCity.getCountry())) {
					dbCity.setCountry(fromCountryDTO(dto.getCountry()));
				}
			}
			return dbCity;
		}
		return new City(fromCountryDTO(dto.getCountry()), dto.getZip(), dto.getName());
	}

	public Address fromAddressDTO(AddressDTO dto) {
		if (hasId(dto.getId())) {
			final Long id = Long.valueOf(dto.getId());
			Address dbAddress = addressRepository.findOne(id);
			if (dbAddress != null) {
				if (isNewValue(dto.getDescription(), dbAddress.getDescription())) {
					dbAddress.setDescription(dto.getDescription());
				}
				if (!(fromAddressDetailDTO(dto.getAddressDetail()).sameValueAs(dbAddress.getAddressDetail()))) {
					dbAddress.setAddressDetail(fromAddressDetailDTO(dto.getAddressDetail()));
				}
				if (fromCityDTO(dto.getCity()).sameIdentityAs(dbAddress.getCity())) {
					dbAddress.setCity(fromCityDTO(dto.getCity()));
				}
			}
			return dbAddress;
		}
		return new Address(fromCityDTO(dto.getCity()), fromAddressDetailDTO(dto.getAddressDetail()));
	}

	private AddressDetail fromAddressDetailDTO(AddressDetailDTO dto) {
		final AddressDetail detail = new AddressDetail(dto.getStreetName(), dto.getStreetName(), dto.getEntrance());
		return detail;
	}

	public List<Region> fromRegionDTOList(List<RegionDTO> dtoList) {
		final List<Region> regions = new ArrayList<Region>(dtoList.size());
		for (RegionDTO dto : dtoList) {
			regions.add(fromRegionDTO(dto));
		}
		return regions;
	}

	public List<Country> fromCountryDTOList(List<CountryDTO> dtoList) {
		final List<Country> countries = new ArrayList<Country>(dtoList.size());
		for (CountryDTO dto : dtoList) {
			countries.add(fromCountryDTO(dto));
		}
		return countries;
	}

	public List<City> fromCityDTOList(List<CityDTO> dtoList) {
		final List<City> cities = new ArrayList<City>(dtoList.size());
		for (CityDTO dto : dtoList) {
			cities.add(fromCityDTO(dto));
		}
		return cities;
	}

	public List<Address> fromAddressDTOList(List<AddressDTO> dtoList) {
		final List<Address> addresses = new ArrayList<Address>(dtoList.size());
		for (AddressDTO dto : dtoList) {
			addresses.add(fromAddressDTO(dto));
		}
		return addresses;
	}

}
