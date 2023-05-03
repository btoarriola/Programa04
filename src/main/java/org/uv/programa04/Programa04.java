/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.uv.programa04;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author btoarriola
 */
public class Programa04 {

    public static void main(String[] args) {
        DAOEmpleado daoempleado = new DAOEmpleado();
        Empleado emp= new Empleado();
        emp.setClave(12);
        emp.setNombre("ximeAAAAA");
        emp.setDireccion("av2");
        emp.setTelefono("4560000000");
        long clave=12;
        
        //daoempleado.create(emp);
        //daoempleado.update(emp, clave);
        //daoempleado.delete(clave);
        
        //Select 
//        List<Empleado> list = daoempleado.findAll();
//        
//        for (Empleado elemento : list) {
//            String msg=String.valueOf(elemento.getClave()+" "+elemento.getNombre()+" "+elemento.getDireccion()+" "+elemento.getTelefono());
//            Logger.getLogger(Programa04.class.getName()).log(Level.INFO, msg);
//        }
//        
        Empleado FindById = daoempleado.FindById(clave);
        String msg=String.valueOf(FindById.getClave()+" "+FindById.getNombre()+" "+FindById.getDireccion()+" "+FindById.getTelefono());
        
        Logger.getLogger(Programa04.class.getName()).log(Level.INFO, msg);
            
    }
}
