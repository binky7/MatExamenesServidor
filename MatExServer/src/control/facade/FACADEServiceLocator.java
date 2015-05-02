/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.facade;

/**
 *
 * @author Jesus Donaldo
 */
public class FACADEServiceLocator {

    private static CursoFACADE cursoFACADE;
    private static TemaFACADE temaFACADE;
    private static BaseFACADE baseFACADE;
    private static UsuarioFACADE usuarioFACADE;
    private static ReactivoFACADE reactivoFACADE;
    private static ExamenFACADE examenFACADE;
    private static ExamenAsignadoFACADE examenAsignadoFACADE;
    private static GrupoFACADE grupoFACADE;

    public static CursoFACADE getCursoFACADE() {
        if (cursoFACADE == null) {
            cursoFACADE = new CursoFACADE();
        }

        return cursoFACADE;
    }

    public static TemaFACADE getTemaFACADE() {
        if (temaFACADE == null) {
            temaFACADE = new TemaFACADE();
        }

        return temaFACADE;
    }

    public static UsuarioFACADE getUsuarioFACADE() {
        if (usuarioFACADE == null) {
            usuarioFACADE = new UsuarioFACADE();
        }

        return usuarioFACADE;
    }

    public static ReactivoFACADE getReactivoFACADE() {
        if (reactivoFACADE == null) {
            reactivoFACADE = new ReactivoFACADE();
        }

        return reactivoFACADE;
    }

    public static ExamenFACADE getExamenFACADE() {
        if (examenFACADE == null) {
            examenFACADE = new ExamenFACADE();
        }

        return examenFACADE;
    }

    public static BaseFACADE getBaseFACADE() {
        if (baseFACADE == null) {
            baseFACADE = new BaseFACADE();
        }

        return baseFACADE;
    }

    public static GrupoFACADE getGrupoFACADE() {
        if (grupoFACADE == null) {
            grupoFACADE = new GrupoFACADE();
        }
        return grupoFACADE;
    }
    
    public static ExamenAsignadoFACADE getExamenAsignadoFACADE() {
        if (examenAsignadoFACADE == null) {
            examenAsignadoFACADE = new ExamenAsignadoFACADE();
        }
        return examenAsignadoFACADE;
    }

}