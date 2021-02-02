package io.github.curso.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.curso.model.Todo;
import io.github.curso.repository.TodoRepository;

@RestController  //classe como component rest receba reqquisições e envie
@RequestMapping("/api/todos") //faz o mapeamento
@CrossOrigin("http://localhost:4200")
//configurando outra url, pois a do vscode é esta, para que não dê problema de permissão
public class TodoController {
	
	@Autowired
	private TodoRepository repository;
	
	@PostMapping
	public Todo save(@RequestBody Todo todo) {
		return repository.save(todo);
	}
	
	//url/api/todo/1
	@GetMapping("{id}")
	public Todo getById(@PathVariable Long id) {
	 //vou obter todo pelo id
		return repository 
             .findById(id)
			 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
    @GetMapping
	public List<Todo> getAll(){
		 return repository.findAll();
	}
    
    //http: //localhost:8080/api/todos/1
    @DeleteMapping("{id}")
    public void delete( @PathVariable Long id) {
    	repository.deleteById(id);
    }
    
    //http://localhost:8080/api/todos/done
    @PatchMapping("{id}/done")
    public Todo markAsDone(@PathVariable Long id) {
    	return repository.findById(id).map(todo -> {
    		todo.setDone(true);
    		todo.setDoneDate(LocalDateTime.now());
    		repository.save(todo);
    		return todo;
    	}).orElse(null);
    }
}
