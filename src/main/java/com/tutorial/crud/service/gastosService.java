package com.tutorial.crud.service;

import com.tutorial.crud.entity.gastos;
import com.tutorial.crud.repository.gastosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class gastosService {

    @Autowired
    gastosRepository gastosrepo;

    public List<gastos> Listar(){return gastosrepo.findAll();}

    public void Guardar(gastos gasto){gastosrepo.save(gasto);}

    public void eliminar(int id){gastosrepo.deleteById(id);}

    public List<gastos> ListarTipo(Calendar desde, Calendar hasta,String tipo){
        return  gastosrepo.BuscarxFechaxTipo(desde, hasta,tipo);}

    public List<gastos> ListarxFechaxUserxTipo(Calendar desde,Calendar hasta,String user,String tipo){
        return  gastosrepo.BuscarxFechaxUserxTipo(desde,hasta,user,tipo);}

    public List<gastos> ListarxFechaxUser(Calendar desde,Calendar hasta,String user){
        return  gastosrepo.BuscarxFechaxUser(desde,hasta,user);}

    public List<gastos> ListarxFecha(Calendar desde, Calendar hasta){
        return  gastosrepo.BuscarxFecha(desde,hasta);}

    public boolean existsById(int id){return gastosrepo.existsById(id);}
}
