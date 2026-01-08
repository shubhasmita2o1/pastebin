package com.shubhasmita.pastebin.repository;

import com.shubhasmita.pastebin.entity.Paste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasteRepository extends JpaRepository<Paste, Long> {
	
	Optional<Paste> findByShortCode(String shortCode);

}
