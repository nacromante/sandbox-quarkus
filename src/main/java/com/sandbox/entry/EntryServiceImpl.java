package com.sandbox.entry;

import com.google.common.collect.ImmutableList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service("entryService")
public class EntryServiceImpl implements EntryService {
    @Inject
    private EntryRepository repository;

    private ModelMapper mapper = new ModelMapper();

    @Override
    public void save(@Valid EntrySaveDto entrySaveDto) {
        Entry entry = mapper.map(entrySaveDto, Entry.class);
        repository.save(entry);
    }


    @Override
    public void update(EntrySaveDto entrySaveDto, Integer entryId) {
        if (entryId == null)
            throw new NotFoundException();

        Entry entry = mapper.map(entrySaveDto, Entry.class);
        Iterable<Entry> entryIterable = repository.findAllById(Arrays.asList(entryId));
        boolean isNext = entryIterable.iterator().hasNext();
        if (isNext) {
            changeEntryForSave(entryId, entry, entryIterable);
            repository.save(entry);
        } else {
            throw new NotFoundException();
        }
    }

    private void changeEntryForSave(Integer entryId, Entry entry, Iterable<Entry> entryIterable) {
        LocalDateTime creationDate = entryIterable.iterator().next().getCreationDate();
        entry.setCreationDate(creationDate);
        entry.setId(entryId);
    }

    @Override
    @Transactional
    public void remove(Integer entryId) {
        Optional<Entry> optionalEntry = repository.findById(entryId);
        optionalEntry.ifPresent(et -> repository.delete(et));
    }

    @Override
    public List<EntryListDto> findAll() {
        Iterable<Entry> entriesIt = repository.findAll();
        ImmutableList<Entry> entryList = ImmutableList.copyOf(entriesIt);
        List<EntryListDto> resultEntry = new ModelMapper().map(entryList, new TypeToken<List<EntryListDto>>() {
        }.getType());
        return resultEntry;
    }


}
