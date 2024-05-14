package com.example.demoDatabase.common.service;

import com.example.demoDatabase.common.exception.WBHBussinessExeption;
import com.example.demoDatabase.common.model.BaseEntity;
import com.example.demoDatabase.common.util.WBHMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenericService<T extends BaseEntity, D, I> {
    JpaRepository<T, I> getRepository();

    WBHMapper getMapper();

    default List<D> findAll(Class<D> clazzDTO) {
        return getRepository().findAll().stream().map((model) ->
                getMapper().map(model, clazzDTO)
        ).toList();
    }

    default D findById(I id, Class<D> clazzDTO) {
        T model = getRepository().findById(id)
                .orElseThrow(() -> new WBHBussinessExeption("Entity with id not found"));

        return getMapper().map(model, clazzDTO);

    }

    default D save(D dto, Class<T> clazzModel, Class<D> clazzDTO) {
        T model = getRepository().save(getMapper().map(dto, clazzModel));
        return getMapper().map(model, clazzDTO);
    }

    default void delete(I id) {
        T entity = getRepository().findById(id)
                .orElseThrow(() -> new WBHBussinessExeption("Entity to delete not found"));
        getRepository().delete(entity);
    }

    default T update(T model) {
        return getRepository().save(model);
    }
}
