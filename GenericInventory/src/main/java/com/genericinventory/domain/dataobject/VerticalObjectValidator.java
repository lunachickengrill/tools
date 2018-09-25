package com.genericinventory.domain.dataobject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.genericinventory.domain.objecttype.Geometry;

public final class VerticalObjectValidator implements ObjectValidator<DataObject> {

	VerticalObjectValidator() {
	}

	@Override
	public boolean isValid(DataObject object) {
		Set<Long> vertical = new HashSet<Long>();
		object.getStructure().stream().filter(s -> s.getGeometry() == Geometry.VERTICAL).filter(Objects::nonNull)
				.forEach(p -> vertical.add(p.getRelatedTypeId()));

		if (!(object.getParentId() == null)) {
			if (vertical != null && (!(vertical.isEmpty()))) {
				if (!(vertical.contains(object.getParentId().getObjectTypeId())))
					return false;
			}
		}

		return true;
	}

}
