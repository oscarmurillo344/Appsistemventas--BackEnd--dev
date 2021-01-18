package com.tutorial.crud.repository;

import com.tutorial.crud.entity.gastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface    gastosRepository extends JpaRepository<gastos, Integer> {

    @Query(value = "SELECT * FROM gastos " +
            "where tipo= :tipo and CAST(fecha AS date) between CAST( :desde AS date) and CAST( :hasta AS date)",nativeQuery = true)
    List<gastos>BuscarxFechaxTipo(@Param("desde") Calendar since,
                                  @Param("hasta")Calendar until,
                                  @Param("tipo")String type);

    @Query(value = "SELECT * FROM gastos " +
            "where tipo= :tipo and usuario= :user " +
            " and CAST(fecha AS date) between CAST( :desde AS date) and CAST( :hasta AS date)",nativeQuery = true)
    List<gastos>BuscarxFechaxUserxTipo(@Param("desde") Calendar since,
                                       @Param("hasta")Calendar until,
                                       @Param("user")String usuario,
                                       @Param("tipo")String type);

    @Query(value = "SELECT * FROM gastos " +
            "where usuario= :user" +
            " and CAST(fecha AS date) between CAST( :desde AS date) and CAST( :hasta AS date)",nativeQuery = true)
    List<gastos>BuscarxFechaxUser(@Param("desde") Calendar since,
                                  @Param("hasta")Calendar until,
                                  @Param("user")String usuario);

    @Query(value = "SELECT * FROM gastos " +
            "where CAST(fecha AS date) between CAST( :desde AS date) and CAST( :hasta AS date)",nativeQuery = true)
    List<gastos>BuscarxFecha(@Param("desde") Calendar since,
                             @Param("hasta")Calendar until);




}

