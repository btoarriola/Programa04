/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.programa04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author btoarriola
 */
public class DAOEmpleado implements IDAOGeneral<Empleado, Long>{

    @Override
    public Empleado create(Empleado p) {
        ConexionDB cx=ConexionDB.getInstance();
        TransaccionDB tdb = new TransaccionDB<Empleado>(p){
                @Override
                public boolean execute(Connection con){
                        try{
                            String sql = "insert into empleados (clave, nombre, direccion, telefono) values(?,?,?,?);";
                            PreparedStatement pst= con.prepareStatement(sql);
                            pst.setLong(1, p.getClave());
                            pst.setString(2, p.getNombre());
                            pst.setString(3, p.getDireccion());
                            pst.setString(4, p.getTelefono());
                            pst.execute();
                            return true;
                        }catch(SQLException ex){
                            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                            return false;
                        }
                }
            
        };
        boolean res=cx.execute(tdb);
        if(res)
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO,"guardado");
        else
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "error");
        return null;
    }

    @Override
    public boolean delete(Long id) {
        ConexionDB cx = ConexionDB.getInstance();
        TransaccionDB tdb = new TransaccionDB<Void>(null) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "DELETE FROM empleados WHERE clave=?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setLong(1, id);
                    pst.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        boolean res = cx.execute(tdb);
        if (res) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "eliminado");
        } else {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "error");
        }
        return res;
    }


    @Override
    public Empleado update(Empleado p, Long id) {
        ConexionDB cx=ConexionDB.getInstance();
        TransaccionDB tdb = new TransaccionDB<Empleado>(p){
                @Override
                public boolean execute(Connection con) {
                        try{
                            String sql = "update empleados set clave=?, nombre=?, direccion=?, telefono=? where clave=? ;";
                            PreparedStatement pst= con.prepareStatement(sql);
                            pst.setLong(1, p.getClave());
                            pst.setString(2, p.getNombre());
                            pst.setString(3, p.getDireccion());
                            pst.setString(4, p.getTelefono());
                            pst.setLong(5, id);
                            pst.execute();
                            return true;
                        }catch(SQLException ex){
                            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                            return false;
                        }
                }
            
        };
        boolean res=cx.execute(tdb);
        if(res)
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO,"guardado");
        else
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "error");
        return null;
    }

    @Override
    public List<Empleado> findAll() {
        List<Empleado> empleados = new ArrayList<>();
        ConexionDB cx = ConexionDB.getInstance();
        TransaccionDB tdb = new TransaccionDB<List<Empleado>>(null) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "SELECT * FROM empleados";
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Empleado empleado = new Empleado();
                        empleado.setClave(rs.getLong("clave"));
                        empleado.setNombre(rs.getString("nombre"));
                        empleado.setDireccion(rs.getString("direccion"));
                        empleado.setTelefono(rs.getString("telefono"));
                        empleados.add(empleado);
                    }
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        boolean res = cx.execute(tdb);
        if (!res) {
            empleados.clear();
        }
        return empleados;
    }


    @Override
    public Empleado FindById(Long id) {
        ConexionDB cx=ConexionDB.getInstance();
        TransaccionDB<Empleado> tdb = new TransaccionDB<Empleado>(null){
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "select * from empleados where clave=?";
                    PreparedStatement pst= con.prepareStatement(sql);
                    pst.setLong(1, id);
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        Empleado emp = new Empleado();
                        emp.setClave(rs.getLong("clave"));
                        emp.setNombre(rs.getString("nombre"));
                        emp.setDireccion(rs.getString("direccion"));
                        emp.setTelefono(rs.getString("telefono"));
                        p = emp;
                        return true;
                    } else {
                        return false;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        boolean res = cx.execute(tdb);
        if (res) {
            return tdb.p;
        } else {
            return null;
        }
    }


    
}
