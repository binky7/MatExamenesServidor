/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.facade;

import java.util.List;
import modelo.dao.DAOServiceLocator;
import modelo.dto.ClaveExamenDTO;
import modelo.dto.ClaveExamenPK;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
import modelo.dto.ExamenDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.TablaEstadisticas;
import modelo.dto.UsuarioDTO;

/**
 *
 * @author Jesus Donaldo
 */
public class ExamenAsignadoFACADE extends BaseFACADE<ExamenAsignadoDTO, ExamenAsignadoPK> {

    public boolean asignarExamenes(List<ExamenAsignadoDTO> examenes) {
        return DAOServiceLocator.getExamenAsignadoDAO().insertar(examenes);
    }

    public ExamenDTO obtenerExamen(int idExamen) {
        return DAOServiceLocator.getExamenAsignadoDAO().obtenerExamen(idExamen);
    }
    
    public ClaveExamenDTO obtenerClaveExamen(ClaveExamenPK idClave) {
        return DAOServiceLocator.getExamenAsignadoDAO()
                .obtenerClaveExamen(idClave);
    }
    
    public List<ExamenAsignadoDTO> obtenerExamenesAsignados(UsuarioDTO alumno) {
        return DAOServiceLocator.getExamenAsignadoDAO().obtenerAsignados(alumno);
    }

    public ExamenAsignadoDTO obtenerExamenAsignado(ExamenAsignadoPK id) {
        return DAOServiceLocator.getExamenAsignadoDAO().obtenerAsignado(id);
    }
    
    public List<ExamenAsignadoDTO> obtenerExamenesContestados(UsuarioDTO alumno,
            CursoDTO curso) {

        return DAOServiceLocator.getExamenAsignadoDAO()
                .obtenerContestados(alumno, curso);
    }

    public ExamenAsignadoDTO obtenerExamenContestado(ExamenAsignadoPK id) {
        return DAOServiceLocator.getExamenAsignadoDAO().obtenerContestado(id);
    }

    public TablaEstadisticas generarEstadisticasPorGrupos(
            List<ExamenDTO> examenes, List<GrupoDTO> grupos) {
        //El objeto que contendra los datos de las estadisticas para graficar
        TablaEstadisticas tabla = null;

        if (grupos != null && !grupos.isEmpty()) {
            String[] columnas = new String[grupos.size()];
            int i = 0;

            //Crear nombres de columnas con los grupos
            for (GrupoDTO grupo : grupos) {
                columnas[i] = grupo.getGrado() + grupo.getNombre()
                        + grupo.getTurno();

                i++;
            }

            //Crea una matriz de examenes filas por grupos columnas
            Object[][] datos = new Object[examenes.size()][];

            i = 0;
            //Llena la matriz con los promedios de los grupos por cada examen
            for (ExamenDTO examen : examenes) {
                Object[] promedios = DAOServiceLocator.getExamenAsignadoDAO()
                        .obtenerPromediosPorGrupo(examen, grupos);

                datos[i] = promedios;
                i++;
            }

            tabla = new TablaEstadisticas(columnas, datos);
        }

        return tabla;
    }

    public TablaEstadisticas generarEstadisticasPorGrados(
            List<ExamenDTO> examenes) {
        //El objeto que contendra los datos de las estadisticas para graficar
        TablaEstadisticas tabla;

        //Crear columnas para los 3 grados
        String[] columnas = new String[3];

        for (int i = 0; i < 3; i++) {
            columnas[i] = (i + 1) + "°";
        }

        //Crea una matriz de examenes filas por grados columnas
        Object[][] datos = new Object[examenes.size()][];

        int i = 0;
        //Llena la matriz con los promedios de los grados por cada examen
        for (ExamenDTO examen : examenes) {
            Object[] promedios = DAOServiceLocator.getExamenAsignadoDAO()
                    .obtenerPromediosPorGrado(examen);

            datos[i] = promedios;
            i++;
        }

        tabla = new TablaEstadisticas(columnas, datos);

        return tabla;
    }

    public TablaEstadisticas generarEstadisticasPorTurnos(
            List<ExamenDTO> examenes) {
        //El objeto que contendra los datos de las estadisticas para graficar
        TablaEstadisticas tabla;
        //Crear columnas para los 2 turnos
        String[] columnas = new String[2];

        //Crear nombres de columnas con los turnos
        for(int i = 0; i < 2; i++) {
            if(i == 0) {
                columnas[i] = "Matutino";
            }
            else {
                columnas[i] = "Vespertino";
            }
        }

        //Crea una matriz de examenes filas por grupos columnas
        Object[][] datos = new Object[examenes.size()][];

        int i = 0;
        //Llena la matriz con los promedios de los grupos por cada examen
        for (ExamenDTO examen : examenes) {
            Object[] promedios = DAOServiceLocator.getExamenAsignadoDAO()
                    .obtenerPromediosPorTurno(examen);

            datos[i] = promedios;
            i++;
        }

        tabla = new TablaEstadisticas(columnas, datos);

        return tabla;
    }
}