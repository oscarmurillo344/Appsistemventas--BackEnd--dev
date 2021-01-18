package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.entity.inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface inventarioRepository extends JpaRepository<inventario, Integer> {

    inventario findByProductoId(Producto pid);
}
