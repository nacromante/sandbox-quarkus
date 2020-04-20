package com.sandbox.entry;

import com.sandbox.exception.BeanValidationException;
import com.sandbox.wrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("entries")
public class EntryResource {

    @Autowired
    @Qualifier("entryService")
    private EntryService entryService;

    @PostMapping
    public ResponseEntity<Object> saveEntry(EntrySaveDto entrySaveDto) {
        try{
            entryService.save(entrySaveDto);
        } catch (ConstraintViolationException e) {
            throw  new BeanValidationException(e.getConstraintViolations());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public void saveEntry(EntrySaveDto entrySaveDto, @PathVariable(value = "id") Integer entryId) {
        entryService.update(entrySaveDto, entryId);

    }

    @GetMapping
    public ResponseWrapper<List<EntryListDto>> getEntries() {
        return new ResponseWrapper<List<EntryListDto>>(entryService.findAll());
    }


    @DeleteMapping("{id}")
    public void deleteEntry(@PathVariable(value = "id") Integer entryId) {
        entryService.remove(entryId);
    }
}