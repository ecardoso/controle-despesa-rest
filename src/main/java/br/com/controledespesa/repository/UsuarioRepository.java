package br.com.controledespesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.controledespesa.data.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value = "SELECT * FROM USUARIO U WHERE U.EMAIL = :email AND SENHA = :senha AND TIPO_LOGIN = :tipoLoginEnum", nativeQuery = true)
	Usuario getUsuarioByEmailAndSenha(@Param("email") String email, @Param("senha") String senha, @Param("tipoLoginEnum") Integer tipoLoginEnum);

	@Query(value = "SELECT * FROM USUARIO U WHERE U.EMAIL = :email AND TIPO_LOGIN = :tipoLoginEnum", nativeQuery = true)
	Usuario getUsuarioByEmail(@Param("email") String email, @Param("tipoLoginEnum") Integer tipoLoginEnum);

}
