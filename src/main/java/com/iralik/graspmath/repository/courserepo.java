package com.iralik.graspmath.repository;

import com.iralik.graspmath.model.t_course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface courserepo extends JpaRepository<t_course, Long> {
}
