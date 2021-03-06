package com.tutorial.crud.service;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.VentasDay;
import com.tutorial.crud.entity.facturacion;
import com.tutorial.crud.repository.facturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
@Transactional
public class FacturaService {

    @Autowired
    facturaRepository facturarepository;


    public void save (facturacion fact){  facturarepository.save(fact);}


    public boolean existsByNumero(int id){
        return facturarepository.existsByNumeroFact(id);
    }

    public List<facturacion> list(){
        return facturarepository.findAll();
    }

    public Integer MaximoValor(){ return facturarepository.FacturaMaxima(); }

    public List<VentasDay> TotalDia(String usuario){ return facturarepository.TotalDay(usuario);}

    public List<VentasDay> TotalFechasUser(String usua, Date dateF, Date dateS)
    { return facturarepository.TotalUserFechas(usua,dateF,dateS);}

    public List<VentasDay> TotalFechas(Date dateF, Date dateS)
    { return facturarepository.TotalFechas(dateF,dateS);}

    public List<VentasDay> TotalUserFechasdia(String usua,Date dateF, Date dateS,String dia)
    { return facturarepository.TotalUserFechasdia(usua,dateF,dateS,dia);}

    public List<VentasDay> TotalFechasDia(Date dateF, Date dateS,String dia)
    { return facturarepository.TotalFechasdia(dateF,dateS,dia);}

    public List<VentasDay> TotalFechasComplete(Date dateF, Date dateS)
    { return facturarepository.TotalFechasComplete(dateF,dateS);}

    public List<facturacion> listaNumero(int id){return facturarepository.findByNumeroFact(id);}

    public List<facturacion> eliminarFact(int numero){return facturarepository.deleteByNumeroFact(numero);}

}
