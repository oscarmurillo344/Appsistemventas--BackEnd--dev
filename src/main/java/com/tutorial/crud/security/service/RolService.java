package com.tutorial.crud.security.service;

import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){ return rolRepository.findByRolNombre(rolNombre); }
    public void saveRol(Rol rol){ rolRepository.save(rol); }
    public void eliminarRol(int id){rolRepository.deleteById(id);}
    public boolean existeRol(int id){return rolRepository.existsById(id);}
}

