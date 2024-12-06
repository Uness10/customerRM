package com.crm.customerRM.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.crm.customerRM.entities.Store;
public interface StoreRepository extends JpaRepository<Store, Long> {

}
