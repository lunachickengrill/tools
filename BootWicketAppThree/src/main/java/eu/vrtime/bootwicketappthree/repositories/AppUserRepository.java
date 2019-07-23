package eu.vrtime.bootwicketappthree.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import eu.vrtime.bootwicketappthree.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long>, JpaSpecificationExecutor<AppUser> {

	public long count();

	public void delete(AppUser entity);

	public <S extends AppUser> S saveAndFlush(S entity);

	public Optional<AppUser> findByOid(Long oid);

	public Optional<AppUser> findByUsername(String username);

}
