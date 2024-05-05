package com.iralik.graspmath.repository;

import com.iralik.graspmath.dto.registrationdto;
import com.iralik.graspmath.model.t_registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface registrationrepo extends JpaRepository<t_registration, Long> {



}
