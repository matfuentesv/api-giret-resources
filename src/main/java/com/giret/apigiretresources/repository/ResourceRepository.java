package com.giret.apigiretresources.repository;

import com.giret.apigiretresources.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
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

}
