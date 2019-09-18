package br.com.controledespesa.repository;

import org.springframework.stereotype.Repository;

import br.com.controledespesa.entity.Usuario;

//public interface UsuarioDaoImpl extends CrudRepository<Usuario, Long> {
@Repository
public class UsuarioDaoImpl extends GenericDao<Usuario, Long> implements UsuarioDao {

}
