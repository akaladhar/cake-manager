package com.waracle.cakemgr.repo;

import com.waracle.cakemgr.entity.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeRepo extends JpaRepository<Cake, Long> {

}

