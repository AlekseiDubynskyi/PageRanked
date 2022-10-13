package com.solvd.pageranked.dao;

import com.solvd.pageranked.models.Links;

import java.util.List;

public interface ILinks {
    Links getByLinkHref(String linkHref);
    List<Links> getAllLinks();

    void addLink(Links links);

    void updateLink(Links links);

    void deleteLink(int id);
}
