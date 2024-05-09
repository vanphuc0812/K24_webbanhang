package com.example.demoDatabase.common.service;

import com.example.demoDatabase.common.model.BaseEntity;
import com.example.demoDatabase.common.util.WBHMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenericService<T extends BaseEntity, D, I> {
    JpaRepository<T, I> getRepository();

    WBHMapper getMapper();

    default List<D> findAll(Class<D> clazzDTO) {
        return getRepository().findAll().stream().map((model) ->
                getMapper().map(model, clazzDTO)
        ).toList();
    }

    default Optional<D> findById(I id, Class<D> clazzDTO) {
        D dto = null;
        Optional<T> model = getRepository().findById(id);
        if (model.isPresent()) {
            dto = getMapper().map(model.get(), clazzDTO);
        }
        return Optional.of(dto);
    }

    default D save(D dto, Class<T> clazzModel, Class<D> clazzDTO) {
        T model = getRepository().save(getMapper().map(dto, clazzModel));
        return getMapper().map(model, clazzDTO);
    }

    default void delete(I id) {
        Optional<T> model = getRepository().findById(id);
        if (model.isPresent()) {
            getRepository().delete(model.get());
        }
    }

    default T update(T model) {
        return getRepository().save(model);
    }
}
