package br.com.controledespesa.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	private DozerConverter() {

	}

	public static <O, D> D parseObject(O origin, Class<D> destination) {
		if (origin == null) {
			return null;
		}

		return mapper.map(origin, destination);
	}

	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
		if (CollectionUtils.isEmpty(origin)) {
			return new ArrayList<>();
		}

		List<D> destinationObjects = new ArrayList<>();
		for (Object o : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}

		return destinationObjects;
	}
}
