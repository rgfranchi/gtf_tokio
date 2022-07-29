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

import gtf.tokio.teste.services.EmpresaService;
import gtf.tokio.teste.web.model.EmpresaDto;

@RequestMapping("/api/v1/empresa")
@RestController
public class EmpresaController {
    
    @Autowired
    private EmpresaService empresaService;

    @GetMapping(value = "find_all")
    public ResponseEntity<List<EmpresaDto>> getAll() {
        return new ResponseEntity<>(empresaService.listAll(), HttpStatus.OK);
    }


    @GetMapping(value = "paginate")
    public ResponseEntity<List<EmpresaDto>> getPaginate(
        @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        return new ResponseEntity<>(empresaService.listPage(PageRequest.of(pageNumber,pageSize)), HttpStatus.OK);
    }


}
