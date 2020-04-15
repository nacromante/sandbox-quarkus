package com.sandbox.entry;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class EntryService {
    @Inject
    private EntryRepository repository;

//    @Inject
//    private ModelMapper mapper;

    public void save(EntryDto entryDto){
        ModelMapper mapper = new ModelMapper();
        Entry entry = mapper.map(entryDto, Entry.class);
        repository.save(entry);
    }


    public void remove(Integer entryId){
        Optional<Entry> optionalEntry = repository.findById(entryId);
        optionalEntry.ifPresent(et -> repository.delete(et));
    }

    public Iterable<Entry> findAll(){
        return repository.findAll();
    }


}
