package com.s25054_idea.s25054.repository;

import com.s25054_idea.s25054.model.UreticiTuccar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UreticiTuccarRepository extends JpaRepository<UreticiTuccar, Integer> {
    List<UreticiTuccar> findByTuccarId(int tuccarId);
}
