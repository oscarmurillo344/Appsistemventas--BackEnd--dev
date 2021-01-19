package com.tutorial.crud.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.BetweenFechas;
import com.tutorial.crud.dto.VentasDay;
import com.tutorial.crud.dto.facturaDto;
import com.tutorial.crud.entity.facturacion;
import com.tutorial.crud.entity.inventario;
import com.tutorial.crud.service.FacturaService;
import com.tutorial.crud.service.inventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/factura")
@CrossOrigin(origins = "*")
public class FacturaController {

    @Autowired
    FacturaService facturaservice;
    @Autowired
    inventarioService inventarioservice;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista/{numero}")
    public ResponseEntity<List<facturacion>> list(@PathVariable("numero") int numero){
        if (!facturaservice.existsByNumero(numero))
            return new ResponseEntity(new Mensaje("transacción no existente"), HttpStatus.OK);
        List<facturacion> list = facturaservice.listaNumero(numero);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!facturaservice.existsByNumero(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        facturaservice.eliminarFact(id);
        return new ResponseEntity(new Mensaje("factura eliminada"), HttpStatus.OK);
    }

    @PostMapping("/facturar")
    public ResponseEntity<?> create(@RequestBody facturaDto factDto){
        int count=0,count2=0;

        if(factDto.getCantidad()<0)
            return new ResponseEntity(new Mensaje("cantidad debe ser mayor a 0"), HttpStatus.BAD_REQUEST);

        facturacion fact = new facturacion(factDto.getNumeroFact(), factDto.getUsuarioId()
                , factDto.getDatenow(),facturaservice.convertir(),factDto.getDia()
                ,factDto.getProductoId(),factDto.getCantidad());
        facturaservice.save(fact);

        inventario inventory=inventarioservice.ActulizarProduct(factDto.getProductoId());
        count=inventory.getCantidadExist()- factDto.getCantidad();
        inventory.setCantidadExist(count);
        inventarioservice.save(inventory);

        if(factDto.getExtras() != ""){
            String[] lista=factDto.getExtras().split(",");
            for (int i=0;i < lista.length ;i++){
                inventario invent=inventarioservice.getOne(Integer.parseInt(lista[i])).get();
                count2=invent.getCantidadExist()- factDto.getCantidad();
                invent.setCantidadExist(count2);
                inventarioservice.save(invent);
            }
        }
        return new ResponseEntity(new Mensaje("Venta Exitosa"), HttpStatus.OK);
    }


    @GetMapping("/numero")
    public ResponseEntity<Integer> Numerofactura(){
        Integer listaN=facturaservice.MaximoValor();
        return new ResponseEntity(listaN, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/totalDay/{usu}")
    public ResponseEntity<List<VentasDay>> Totalday(@PathVariable("usu") String usu){
        List<VentasDay> l=facturaservice.TotalDia(usu);
        return new ResponseEntity(l, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/totalfechaUser")
    public ResponseEntity<List<VentasDay>> totalFechaUser(@RequestBody BetweenFechas fec){
        if(fec.getFechaFirst() == null )
            return new ResponseEntity(new Mensaje("No existe fecha"),HttpStatus.BAD_REQUEST);
        if(fec.getUsuario().isEmpty())
            return new ResponseEntity(new Mensaje("No existe usuario"),HttpStatus.BAD_REQUEST);

        List<VentasDay> listar=facturaservice.TotalFechasUser
                (fec.getUsuario(),fec.getFechaFirst(),fec.getFechaSecond());
        return new ResponseEntity(listar,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/totalfechaHour")
    public ResponseEntity<List<VentasDay>> totalFechaHoras(@RequestBody BetweenFechas fec){
        if(fec.getFechaFirst() == null )
            return new ResponseEntity(new Mensaje("No existe fecha"),HttpStatus.BAD_REQUEST);
        if(fec.getUsuario().isEmpty())
            return new ResponseEntity(new Mensaje("No existe usuario"),HttpStatus.BAD_REQUEST);
        List<VentasDay> listar=facturaservice.TotalFechasHour
        (fec.getUsuario(),fec.getTiempoF(),fec.getTiempoS(),fec.getFechaFirst(),fec.getFechaSecond());
        return new ResponseEntity(listar,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/totalfecha")
    public ResponseEntity<List<VentasDay>> totalFecha(@RequestBody BetweenFechas fec)
    {
        if(fec.getFechaFirst() == null )
            return new ResponseEntity(new Mensaje("No existe fecha"),HttpStatus.BAD_REQUEST);

        List<VentasDay> listar=facturaservice.TotalFechas(fec.getFechaFirst(),fec.getFechaSecond());
        return new ResponseEntity(listar,HttpStatus.OK);
    }



}
