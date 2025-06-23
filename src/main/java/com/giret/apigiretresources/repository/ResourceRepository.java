package com.giret.apigiretresources.repository;

import com.giret.apigiretresources.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResourceRepository {

    @Autowired
    DataSource dataSource;


    public List<Resource> findAllResource() {
        List<Resource> recursos = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             CallableStatement cs = conn.prepareCall("{call SP_GET_RECURSOS(?)}")) {

            cs.registerOutParameter(1, Types.REF_CURSOR);
            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(1)) {
                while (rs.next()) {
                    Resource recurso = new Resource();

                    recurso.setIdRecurso(rs.getLong("ID_RECURSO"));
                    recurso.setNombre(rs.getString("NOMBRE"));
                    recurso.setDescripcion(rs.getString("DESCRIPCION"));
                    recurso.setNumeroSerie(rs.getString("NUMEROSERIE"));
                    recurso.setFechaVencimientoGarantia(rs.getString("FECHAVENCIMIENTOGARANTIA"));
                    recurso.setFechaCompra(rs.getString("FECHACOMPRA"));
                    recurso.setIdEstado(rs.getLong("ID_ESTADO"));
                    recurso.setIdCategoria(rs.getInt("ID_CATEGORIA"));
                    recursos.add(recurso);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // puedes manejarlo con log
        }
        return recursos;
    }

    public Resource findResourceById(Long id) {
        Resource recurso = null;
        try (Connection conn = dataSource.getConnection();
             CallableStatement cs = conn.prepareCall("{ call SP_GET_RECURSO_BY_ID(?, ?) }")) {

            cs.setLong(1, id);
            cs.registerOutParameter(2, Types.REF_CURSOR);
            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                if (rs.next()) {
                    recurso = new Resource();
                    recurso.setIdRecurso(rs.getLong("ID_RECURSO"));
                    recurso.setNombre(rs.getString("NOMBRE"));
                    recurso.setDescripcion(rs.getString("DESCRIPCION"));
                    recurso.setNumeroSerie(rs.getString("NUMEROSERIE"));
                    recurso.setFechaVencimientoGarantia(rs.getString("FECHAVENCIMIENTOGARANTIA"));
                    recurso.setFechaCompra(rs.getString("FECHACOMPRA"));
                    recurso.setIdEstado(rs.getLong("ID_ESTADO"));
                    recurso.setIdCategoria(rs.getInt("ID_CATEGORIA"));
                    return recurso;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Resource();
    }

    public boolean saveResource(Resource recurso) {
        String procedure = "{ call SP_CREATE_RECURSO(?, ?, ?, ?, ?, ?, ?) }";

        try (Connection conn = dataSource.getConnection();
             CallableStatement cs = conn.prepareCall(procedure)) {

            cs.setString(1, recurso.getNombre());
            cs.setString(2, recurso.getDescripcion());
            cs.setString(3, recurso.getNumeroSerie());


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaGarantia = LocalDate.parse(recurso.getFechaVencimientoGarantia(), formatter);
            LocalDate fechaCompra = LocalDate.parse(recurso.getFechaCompra(), formatter);

            cs.setDate(4, java.sql.Date.valueOf(fechaGarantia));
            cs.setDate(5, java.sql.Date.valueOf(fechaCompra));

            cs.setLong(6, recurso.getIdEstado());
            cs.setInt(7, recurso.getIdCategoria());

            cs.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateResource(Long id, Resource recurso) {
        String procedure = "{ call SP_UPDATE_RECURSO(?, ?, ?, ?, ?, ?, ?, ?) }";

        try (Connection conn = dataSource.getConnection();
             CallableStatement cs = conn.prepareCall(procedure)) {

            // Parámetros simples
            cs.setLong(1, id);
            cs.setString(2, recurso.getNombre());
            cs.setString(3, recurso.getDescripcion());
            cs.setString(4, recurso.getNumeroSerie());

            // Enviar fechas como String directamente en formato yyyy-MM-dd
            cs.setString(5, recurso.getFechaVencimientoGarantia());
            cs.setString(6, recurso.getFechaCompra());

            cs.setLong(7, recurso.getIdEstado());
            cs.setInt(8, recurso.getIdCategoria());

            cs.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteResource(Long id) {
        try (Connection conn = dataSource.getConnection();
             CallableStatement cs = conn.prepareCall("{ call SP_DELETE_RECURSO(?) }")) {

            cs.setLong(1, id);
            cs.execute();
           return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
