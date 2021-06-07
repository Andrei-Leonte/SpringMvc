package com.dreamcar.andreea.repositories;

import com.dreamcar.andreea.entites.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DealRepository  extends JpaRepository<Deal, Long> {
}
