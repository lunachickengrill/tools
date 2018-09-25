package com.genericinventory.domain.dataobject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.genericinventory.domain.objecttype.AttributeType;
import com.genericinventory.domain.shared.AttributeId;

public final class AttributeValidator implements ObjectValidator<DataObject> {

	AttributeValidator() {

	}

	@Override
	public boolean isValid(DataObject object) {

		Set<AttributeId> mandatory = new HashSet<AttributeId>();
		object.getAttributeSpecs().stream()
				.filter(p -> p.getAttributeType() == AttributeType.PRIMARY
						|| p.getAttributeType() == AttributeType.REQUIRED)
				.filter(Objects::nonNull).forEach(p -> mandatory.add(p.getAttributeId()));

		Set<AttributeId> actual = new HashSet<AttributeId>();
		object.getParams().stream().forEach(p -> actual.add(p.getAttributeId()));

		if ((!(actual.isEmpty()) && (!(mandatory.isEmpty())))) {
			if (!(actual.containsAll(mandatory)))
				return false;
		}
		return true;
	}

}
