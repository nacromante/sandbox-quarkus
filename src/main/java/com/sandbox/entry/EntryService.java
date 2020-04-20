package com.sandbox.entry;

import javax.validation.Valid;
import java.util.List;


public interface EntryService {

    public void save(@Valid EntrySaveDto entrySaveDto);

    public void update(EntrySaveDto entrySaveDto, Integer entryId);

    public void remove(Integer entryId);

    public List<EntryListDto> findAll();
}
