package com.rafaelAbreu.LoginJWT.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rafaelAbreu.LoginJWT.entities.Player;

@Component
@FeignClient(name = "rafateron-jogoquiz", path = "/rafateron-jogoquiz")
public interface PlayerFeign {
	
	@GetMapping(value = "/quiz/search")
	ResponseEntity<Player> findByUsuario(@RequestParam String usuario);	
	
}