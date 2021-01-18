package com.tutorial.crud.service;


import com.tutorial.crud.entity.diaPollos;
import com.tutorial.crud.repository.diaPolloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class diaPolloService {

    @Autowired
    diaPolloRepository diaRepo;

    public void Guardar(diaPollos update){ diaRepo.save(update); }

    public diaPollos Listar(int id){return diaRepo.getOne(id);}

    public boolean Existe(int id){return diaRepo.existsById(id);}

}
