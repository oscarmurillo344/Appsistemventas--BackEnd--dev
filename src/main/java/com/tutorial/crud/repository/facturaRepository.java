package com.tutorial.crud.repository;

import com.tutorial.crud.dto.VentasDay;
import com.tutorial.crud.entity.facturacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;


@Repository
public interface facturaRepository extends JpaRepository<facturacion, Integer> {

    @Query(value = "SELECT case" +
            "     when MAX(numero_fact) is null then 0" +
            "     else MAX(numero_fact)" +
            "      end as valor" +
            "     FROM facturacion", nativeQuery = true)
    Integer FacturaMaxima();

    @Query(value = "SELECT f.usuario,pr.nombre,pr.precio,sum(f.cantidad) as cantidad " +
            "FROM facturacion f, rel_fact_product pf, producto pr " +
            " where pf.fk_product=pr.id and pf.fk_fact=f.id and f.usuario= :user " +
            " and extract(day from f.Datenow)=extract(day from current_date) " +
            " group by f.usuario,pr.nombre,pr.precio " +
            " order by pr.nombre;", nativeQuery = true)
    List<VentasDay> TotalDay(@Param("user") String usuario);

    @Query(value = "SELECT f.usuario,pr.nombre,pr.precio,sum(f.cantidad) as cantidad " +
            "FROM facturacion f, rel_fact_product pf, producto pr " +
            " where pf.fk_product=pr.id and pf.fk_fact=f.id and f.usuario= :user and " +
            "f.Datenow between :dateFirst and :dateSecond " +
            " group by f.usuario,pr.nombre,pr.precio " +
            " order by pr.nombre;",nativeQuery = true)
    List<VentasDay> TotalUserFechas(@Param("user") String usuario,
                                @Param("dateFirst") Date dateF,
                                @Param("dateSecond") Date dateS);

    @Query(value = "SELECT pr.nombre,pr.precio,sum(f.cantidad) as cantidad " +
            "FROM facturacion f, rel_fact_product pf, producto pr " +
            " where pf.fk_product=pr.id and pf.fk_fact=f.id and " +
            " f.Datenow between :dateFirst and  :dateSecond " +
            " group by pr.nombre,pr.precio" +
            " order by pr.nombre;",nativeQuery = true)
    List<VentasDay> TotalFechas(@Param("dateFirst") Date dateF,
                                @Param("dateSecond") Date dateS);

    @Query(value = "SELECT f.usuario,pr.nombre,pr.precio,sum(f.cantidad) as cantidad " +
            "FROM facturacion f, rel_fact_product pf, producto pr " +
            " where pf.fk_product=pr.id and pf.fk_fact=f.id and f.usuario= :user and f.dia=:dia and " +
            "f.Datenow between :dateFirst and :dateSecond " +
            " group by f.usuario,pr.nombre,pr.precio " +
            " order by pr.nombre;",nativeQuery = true)
    List<VentasDay> TotalUserFechasdia(@Param("user") String usuario,
                                    @Param("dateFirst") Date dateF,
                                    @Param("dateSecond") Date dateS,
                                   @Param("dia") String dia);
    @Query(value = "SELECT f.usuario,pr.nombre,pr.precio,sum(f.cantidad) as cantidad " +
            "FROM facturacion f, rel_fact_product pf, producto pr " +
            " where pf.fk_product=pr.id and pf.fk_fact=f.id and f.dia=:dia and " +
            "f.Datenow between :dateFirst and :dateSecond " +
            " group by f.usuario,pr.nombre,pr.precio " +
            " order by pr.nombre;",nativeQuery = true)
    List<VentasDay> TotalFechasdia(@Param("dateFirst") Date dateF,
                                       @Param("dateSecond") Date dateS,
                                       @Param("dia") String dia);

    @Query(value = "SELECT f.usuario,pr.nombre,pr.precio,sum(f.cantidad) as cantidad ,datenow as fecha ,dia "+
            "FROM facturacion f, rel_fact_product pf, producto pr " +
            " where pf.fk_product=pr.id and pf.fk_fact=f.id  and " +
            "f.Datenow between :dateFirst and :dateSecond " +
            " group by f.usuario,pr.nombre,pr.precio,datenow,dia " +
            " order by pr.nombre;",nativeQuery = true)
    List<VentasDay> TotalFechasComplete(@Param("dateFirst") Date dateF,
                                        @Param("dateSecond") Date dateS);

    List<facturacion> findByNumeroFact(int id);

    boolean existsByNumeroFact(int id);

    long deleteByNumeroFact(int id);

}
