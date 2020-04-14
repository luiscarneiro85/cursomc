package com.luiscarneiro.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.luiscarneiro.cursomc.domain.Cidade;
import com.luiscarneiro.cursomc.domain.Cliente;
import com.luiscarneiro.cursomc.domain.Endereco;
import com.luiscarneiro.cursomc.domain.enums.TipoCliente;
import com.luiscarneiro.cursomc.dto.ClienteDTO;
import com.luiscarneiro.cursomc.dto.ClienteNewDTO;
import com.luiscarneiro.cursomc.repositories.ClienteRepository;
import com.luiscarneiro.cursomc.repositories.EnderecoRepository;
import com.luiscarneiro.cursomc.services.exceptions.DataIntegrityException;
import com.luiscarneiro.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Cliente que possui pedidos.");
		}		
	}
	
	public List<Cliente> findAll(){
		return repository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, 
				objDTO.getNome(), 
				objDTO.getEmail(), 
				objDTO.getCpfOuCnpj(), 
				TipoCliente.toEnum(objDTO.getTipo()));
		
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		
		Endereco end = new Endereco(null, 
				objDTO.getLogradouro(), 
				objDTO.getNumero(), 
				objDTO.getComplemento(), 
				objDTO.getBairro(), 
				objDTO.getCep(), 
				cli, 
				cid);
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		
		if(objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		
		if(objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}