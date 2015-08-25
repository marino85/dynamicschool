/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.dto.FiltroUsuariosDto;
import com.ccit.ejb.modelo.IappCursos;
import com.ccit.ejb.modelo.IappPerfiles;
import com.ccit.ejb.modelo.IappUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

/**
 *
 * @author marino
 */
@Stateless
public class IappUsuarioFacade extends AbstractFacade<IappUsuario> {

    @PersistenceContext(unitName = "DinamicPortal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappUsuarioFacade() {
        super(IappUsuario.class);
    }

    public IappUsuario findUser(String numDoc) {
        return getEntityManager().createNamedQuery("IappUsuario.findByNumeroDoc", IappUsuario.class).setParameter("numeroDoc", numDoc).getSingleResult();
    }

    public IappUsuario findUser(String numDoc, String tipoDoc) {
        return getEntityManager().createNamedQuery("IappUsuario.findByNumeroDocTipoDoc", IappUsuario.class).setParameter("numeroDoc", numDoc).setParameter("tipoDoc", tipoDoc).getSingleResult();
    }

    public List findUserByPerfil(Integer id_perfil) {
        return (List) ((IappPerfiles) getEntityManager().createNamedQuery("IappPerfiles.findByIdPerfil", IappPerfiles.class).setParameter("idPerfil", id_perfil).getSingleResult()).getIappUsuarioCollection();
    }

    public List<IappUsuario> getEstudiantesActivos() {

        Query q = em.createQuery("SELECT o FROM IappUsuario o WHERE o.idPerfil.idPerfil = :idPerfil "
                + "AND o.estadoUsuario.idEstadoUsuario = :idEstadoUsuario").setParameter("idPerfil", Constants.PERFIL_ESTUDIANTE).setParameter("idEstadoUsuario",
                Constants.REGISTRO_ACTIVO);

        return q.getResultList();
    }

    public List<IappUsuario> getEstudiantesNoCursoFiltro(IappCursos editCourse, Integer idjornada, Integer idNivel, String nombres, String apellidos,String matricula) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT  DISTINCT( u.id_usuario ) as \"ID_USUARIO\", u.nombres as \"NOMBRES\", u.apellidos as \"APELLIDOS\" ,u.id_nivel as \"ID_NIVEL\", u.id_jornada as \"ID_JORNADA\" ,u.codigo as \"CODIGO\"");
        sb.append(" FROM general.iapp_matriculas e");
        sb.append(" INNER JOIN  general.iapp_usuario u");
        sb.append(" ON e.id_usuario = u.id_usuario");        
        sb.append(" WHERE  e.id_curso != ?  ");
        sb.append(" AND u.id_usuario NOT IN  (select m.id_usuario from general.iapp_matriculas m where m.id_curso = ?)");
        
        
        if(idjornada!=null && idjornada!=-1){
            sb.append(" AND u.id_jornada = ");
            sb.append(idjornada);
        }
        if(idNivel != null && idNivel!=-1 ){
            sb.append(" AND u.id_nivel = ");
            sb.append(idNivel);
        }
        if(nombres!=null && !nombres.equals("")){
            sb.append(" AND u.nombres LIKE '%");
            sb.append(nombres);
            sb.append("%'");
        }
         if(apellidos!=null && !apellidos.equals("")){
            sb.append(" AND u.apellidos LIKE '%");
            sb.append(apellidos);
            sb.append("%'");
        }
         if(matricula!=null && !matricula.equals("")){
            sb.append(" AND u.codigo = '");
            sb.append(matricula);
            sb.append("'");
        }


        Query q = em.createNativeQuery(sb.toString(), IappUsuario.class);
        q.setParameter(1, Constants.PERFIL_ESTUDIANTE);
        q.setParameter(2, (editCourse.getIdCurso() == null) ? -1 : editCourse.getIdCurso());
        q.setParameter(3, (editCourse.getIdCurso() == null) ? -1 : editCourse.getIdCurso());
        return q.getResultList();
    }

    public List<IappUsuario> getEstudiantesNoCurso(IappCursos editCourse) {
        Query q = em.createNativeQuery("SELECT e.id_usuario as \"ID_USUARIO\", e.nombres as \"NOMBRES\", e.apellidos as \"APELLIDOS\" ,e.id_nivel as \"ID_NIVEL\", e.id_jornada as \"ID_JORNADA\" "
                + " FROM general.iapp_usuario e"
                + " WHERE e.id_Perfil = ?"
                + " EXCEPT"
                + " SELECT e.id_usuario,e.nombres,e.apellidos,e.id_nivel, e.id_jornada"
                + " FROM general.iapp_usuario e"
                + " LEFT JOIN general.iapp_matriculas m ON (e.id_usuario = m.id_usuario )"
                + " WHERE m.id_Curso = ?", IappUsuario.class);
        q.setParameter(1, Constants.PERFIL_ESTUDIANTE);
        q.setParameter(2, (editCourse.getIdCurso() == null) ? -1 : editCourse.getIdCurso());
        return q.getResultList();
    }

    public List<IappUsuario> getEstudiantes(FiltroUsuariosDto filtro) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
//        Metamodel m = em.getMetamodel();
//        EntityType<IappUsuario> usuario_ = m.entity(IappUsuario.class);
        CriteriaQuery<IappUsuario> cr = cb.createQuery(IappUsuario.class);
        Root<IappUsuario> usuario = cr.from(IappUsuario.class);
        List<Predicate> prediates = new ArrayList<Predicate>();




        if (filtro.getNumeroDoc() != null && !filtro.getNumeroDoc().equals("")) {
            prediates.add(cb.equal(usuario.get("numeroDoc"), filtro.getNumeroDoc()));


        }
        if (filtro.getNombres() != null && !filtro.getNombres().equals("")) {
            prediates.add(
                    cb.like(usuario.get("nombres").as(String.class), "%" + filtro.getNombres() + "%"));

        }
        if (filtro.getApellidos() != null && !filtro.getApellidos().equals("")) {
            prediates.add(cb.like(usuario.get("apellidos").as(String.class), "%" + filtro.getApellidos() + "%"));

        }
        if (filtro.getCodigo() != null && !filtro.getCodigo().equals("")) {
            prediates.add(cb.equal(usuario.get("codigo"), filtro.getCodigo()));

        }
        if (filtro.getEmail() != null && !filtro.getEmail().equals("")) {
            prediates.add(cb.equal(usuario.get("email"), filtro.getEmail()));

        }
        if (filtro.getFechaInicio() != null) {
            prediates.add(cb.equal(usuario.get("fechaInicio"), filtro.getFechaInicio()));

        }
        if (filtro.getFechaFin() != null) {
            prediates.add(cb.equal(usuario.get("fechaFin"), filtro.getFechaFin()));

        }

        if (prediates.size() > 0) {
            cr.where(prediates.toArray(new Predicate[prediates.size()]));
        }
        //cr.where(p1, p2, p3, p4, p5, p6, p7);
        TypedQuery<IappUsuario> tq = em.createQuery(cr);

        return tq.getResultList();

    }
}
