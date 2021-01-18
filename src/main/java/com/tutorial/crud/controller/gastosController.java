package com.tutorial.crud.controller;


import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.gastosX;
import com.tutorial.crud.entity.gastos;
import com.tutorial.crud.service.gastosService;
import com.tutorial.crud.dto.gastoDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/gastos")
@CrossOrigin(origins = "*")
public class gastosController {

        @Autowired
        gastosService gastosSer;

         @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/lista")
        public ResponseEntity<List<gastos>> listar(){
            List<gastos> list = gastosSer.Listar();
            return new ResponseEntity(list, HttpStatus.OK);
        }

        @PostMapping("/ingresar")
        public ResponseEntity<?> crear(@RequestBody gastoDto gastos){
            if(StringUtils.isBlank(gastos.getDescripcion()))
                return new ResponseEntity<>(new Mensaje("La descripcion es obligatoria"),HttpStatus.BAD_REQUEST);
            if (gastos.getValor()<0)
                return new ResponseEntity<>(new Mensaje("valor debe ser mayor a 0"),HttpStatus.BAD_REQUEST);

            gastos g =new gastos(gastos.getTipo(),gastos.getUsuario(),Calendar.getInstance(),
                        gastos.getValor(),gastos.getDescripcion());
            gastosSer.Guardar(g);
            return new ResponseEntity<>(new Mensaje("Ingreso Exitoso"),HttpStatus.OK);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable("id")int id){
            if(!gastosSer.existsById(id))
                return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
            gastosSer.eliminar(id);
            return new ResponseEntity(new Mensaje("gasto eliminado"), HttpStatus.OK);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/listaTipo/")
        public ResponseEntity<List<gastos>> listarTipo(@RequestBody gastosX gasto){
            List<gastos> list = gastosSer.ListarTipo(gasto.getInicial(),gasto.getFin(),gasto.getTipo());
            return new ResponseEntity(list, HttpStatus.OK);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/listaTipoUserFecha/")
        public ResponseEntity<List<gastos>> listarTipoUserFecha(@RequestBody gastosX gasto){
            List<gastos> list = gastosSer.ListarxFechaxUserxTipo
                    (gasto.getInicial(),gasto.getFin(),gasto.getUsuario(),gasto.getTipo());
            return new ResponseEntity(list, HttpStatus.OK);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/listaUserFecha/")
        public ResponseEntity<List<gastos>> listarUserFecha(@RequestBody gastosX gasto){
            List<gastos> list = gastosSer.ListarxFechaxUser
                    (gasto.getInicial(),gasto.getFin(),gasto.getUsuario());
            return new ResponseEntity(list, HttpStatus.OK);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/listaFecha/")
        public ResponseEntity<List<gastos>> listarFecha(@RequestBody gastosX gasto){
            List<gastos> list = gastosSer.ListarxFecha
                    (gasto.getInicial(),gasto.getFin());
            return new ResponseEntity(list, HttpStatus.OK);
        }


}
