package gtf.tokio.teste.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gtf.tokio.teste.services.ColaboradorService;
import gtf.tokio.teste.web.model.ColaboradorDto;

@RequestMapping("/api/v1/colaborador")
@RestController
public class ColaboradorController {
    
    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping(value = "find_all")
    public ResponseEntity<List<ColaboradorDto>> getAll() {
        return new ResponseEntity<>(colaboradorService.listAll(), HttpStatus.OK);
    }

    @GetMapping(value = "paginate")
    public ResponseEntity<List<ColaboradorDto>> getPaginate(
        @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        return new ResponseEntity<>(colaboradorService.listPage(PageRequest.of(pageNumber,pageSize)), HttpStatus.OK);
    }
}
